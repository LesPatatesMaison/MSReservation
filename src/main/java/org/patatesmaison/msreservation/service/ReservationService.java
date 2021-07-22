package org.patatesmaison.msreservation.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.patatesmaison.msreservation.client.ConcentrateurApiClient;
import org.patatesmaison.msreservation.dao.ReservationDAO;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.mapper.ReservationMapper;
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

    private final ConcentrateurApiClient concentrateurApiClient;


    public ReservationService(ReservationDAO reservationDAO, ReservationMapper reservationMapper, ConcentrateurApiClient concentrateurApiClient) {
        this.reservationDAO = reservationDAO;
        this.reservationMapper = reservationMapper;
        this.concentrateurApiClient = concentrateurApiClient;
    }


    public ReservationDTO get(Long id) throws APIException {

        if(id < 1L) throw new APIException(messageReservationInvalid, HttpStatus.BAD_REQUEST);

        Optional<Reservation> reservationOptional = this.reservationDAO.findById(id);

        if(reservationOptional.isEmpty()) throw new APIException(messageReservationNotFound, HttpStatus.NOT_FOUND);

        return reservationMapper.fromEntity(reservationOptional.get());
    }

    public ReservationDTO create(ReservationDTO reservationDTO) throws APIException {

        if (!isReservationDTOValid(reservationDTO)) throw new APIException(messageReservationInvalid, HttpStatus.BAD_REQUEST);

        try {
            concentrateurApiClient.getBarById(reservationDTO.getBar().getId());
        } catch (RestClientException e) {
            throw new APIException(messageBarNotFound, HttpStatus.NOT_FOUND);
        }

        Reservation reservation = reservationMapper.fromDto(reservationDTO);
        this.reservationDAO.save(reservation);

        // todo: get it from h2 instead
        return reservationMapper.fromEntity(reservation);
    }



    private boolean isReservationDTOValid(ReservationDTO reservationDTO) {
        // todo: chack dateTime valid ?
        return reservationDTO != null
                && reservationDTO.getBar() != null
                && reservationDTO.getBar().getId() != null
                && !Strings.isBlank(reservationDTO.getName())
                && reservationDTO.getDateTime() != null;
    }
}
