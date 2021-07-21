package org.patatesmaison.msreservation.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.patatesmaison.msreservation.dao.ReservationDAO;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@Slf4j
public class ReservationService extends ReservationMapper {

    @Value("${messages.reservation.dto.invalid}")
    private String messageReservationInvalid;

    @Value("${messages.reservation.not-found}")
    private String messageReservationNotFound;

    private final ReservationDAO reservationDAO;

    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    //    @PostConstruct
//    public void logEnvSpecificValue() {
//        log.warn("---------------------------------------------");
//        log.warn("------------------------------ messageReservationInvalid : {}", messageReservationInvalid);
//        log.warn("------------------------------ messageReservationNotFound : {}", messageReservationNotFound);
//        log.warn("---------------------------------------------");
//    }

    public ReservationDTO get(Long id) throws APIException {

        if(id < 1L) throw new APIException(messageReservationInvalid, HttpStatus.BAD_REQUEST);

        Optional<Reservation> reservationOptional = this.reservationDAO.findById(id);

        if(reservationOptional.isEmpty()) throw new APIException(messageReservationNotFound, HttpStatus.NOT_FOUND);

        return fromEntity(reservationOptional.get());
    }

    public ReservationDTO create(ReservationDTO reservationDTO) throws APIException {

        if (!isReservationDTOValid(reservationDTO)) throw new APIException(messageReservationInvalid, HttpStatus.BAD_REQUEST);

        Reservation reservation = fromDto(reservationDTO);
        this.reservationDAO.save(reservation);

        // todo: get it from h2 instead
        return fromEntity(reservation);
    }



    private boolean isReservationDTOValid(ReservationDTO reservationDTO) {
        // todo: chack dateTime valid ?
        return reservationDTO != null
                && reservationDTO.getBarId() != null
                && !Strings.isBlank(reservationDTO.getName())
                && reservationDTO.getDateTime() != null;
    }
}
