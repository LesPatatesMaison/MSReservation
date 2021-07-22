package org.patatesmaison.msreservation.service;

import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.client.ConcentrateurApiClient;
import org.patatesmaison.msreservation.dao.ReservationDAO;
import org.patatesmaison.msreservation.dao.UserDAO;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.dto.UserDTO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.entity.User;
import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.mapper.ReservationMapper;
import org.patatesmaison.msreservation.util.Logging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

@Service
@Slf4j
public class ReservationService {

    @Value("${messages.reservation.dto.invalid}")
    protected String messageReservationInvalid;

    @Value("${messages.reservation.not-found}")
    protected String messageReservationNotFound;

    @Value("${messages.reservation.bar-not-found}")
    protected String messageBarNotFound;

    private final ReservationDAO reservationDAO;

    private final ReservationMapper reservationMapper;

    private final UserDAO userDAO;

    private final ConcentrateurApiClient concentrateurApiClient;


    public ReservationService(ReservationDAO reservationDAO, ReservationMapper reservationMapper, UserDAO userDAO, ConcentrateurApiClient concentrateurApiClient) {
        this.reservationDAO = reservationDAO;
        this.reservationMapper = reservationMapper;
        this.userDAO = userDAO;
        this.concentrateurApiClient = concentrateurApiClient;
    }


    public ReservationDTO get(Long id) throws APIException {

        if(id < 1L) throw new APIException(messageReservationInvalid, HttpStatus.BAD_REQUEST);

        Optional<Reservation> reservationOptional = this.reservationDAO.findById(id);

        if(reservationOptional.isEmpty()) throw new APIException(messageReservationNotFound, HttpStatus.NOT_FOUND);

        return reservationMapper.fromEntity(reservationOptional.get());
    }

    public ReservationDTO create(ReservationDTO reservationDTO) throws APIException {
        Logging.logEnter();

        if (!isReservationDTOValid(reservationDTO)) throw new APIException(messageReservationInvalid, HttpStatus.BAD_REQUEST);

        Reservation reservation = reservationMapper.fromDto(reservationDTO);
        this.reservationDAO.save(reservation);

        // todo: get it from h2 instead
        return reservationMapper.fromEntity(reservation);
    }

    public ReservationDTO update(ReservationDTO reservationDTO) throws APIException {
        Logging.logEnter();

        if (!isReservationDTOValid(reservationDTO) || reservationDTO.getId() == null) throw new APIException(messageReservationInvalid, HttpStatus.BAD_REQUEST);

        Optional<Reservation> reservationOptional = reservationDAO.findById(reservationDTO.getId());
        if(reservationOptional.isEmpty()) throw new APIException(messageReservationNotFound, HttpStatus.NOT_FOUND);

        Reservation reservation = reservationMapper.fromDto(reservationOptional.get(), reservationDTO);
        this.reservationDAO.save(reservation);

        return reservationMapper.fromEntity(reservation);
    }

    public void delete(Long reservationId) throws APIException {
        Optional<Reservation> reservationOptional = reservationDAO.findById(reservationId);

        if(reservationOptional.isEmpty()) throw new APIException(messageReservationNotFound, HttpStatus.NOT_FOUND);

        this.reservationDAO.delete(reservationOptional.get());
    }



    private boolean isReservationDTOValid(ReservationDTO reservationDTO) {

        boolean isValid = reservationDTO != null
                                && reservationDTO.getBar() != null
                                && reservationDTO.getBar().getId() != null
                                && reservationDTO.getUser() != null
                                && reservationDTO.getUser().getId() != null
                                && reservationDTO.getDateTime() != null;

        if(!isValid) return false;

        if(userDAO.findById(reservationDTO.getUser().getId()).isEmpty()) return false;

        try {
            concentrateurApiClient.getBarById(reservationDTO.getBar().getId());
        } catch (RestClientException e) {
            return false;
        }

        return true;
    }
}
