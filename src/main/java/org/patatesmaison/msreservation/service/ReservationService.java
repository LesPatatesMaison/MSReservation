package org.patatesmaison.msreservation.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class ReservationService extends ReservationMapper {

    @Value("${messages.reservation.dto.invalid}")
    private String messageReservationInvalid;

    @Value("${messages.reservation.not-found}")
    private String messageReservationNotFound;

//    @PostConstruct
//    public void logEnvSpecificValue() {
//        log.warn("---------------------------------------------");
//        log.warn("------------------------------ messageReservationInvalid : {}", messageReservationInvalid);
//        log.warn("------------------------------ messageReservationNotFound : {}", messageReservationNotFound);
//        log.warn("---------------------------------------------");
//    }

    public ReservationDTO get(Long id) {
        return new ReservationDTO();
    }

    public ReservationDTO create(ReservationDTO reservationDTO) throws APIException {

        if (!isReservationDTOValid(reservationDTO)) throw new APIException(messageReservationInvalid, HttpStatus.BAD_REQUEST);


        return new ReservationDTO();
    }



    private boolean isReservationDTOValid(ReservationDTO reservationDTO) {
        return reservationDTO != null
                && reservationDTO.getBarId() != null
                && !Strings.isBlank(reservationDTO.getName())
                && reservationDTO.getDateTime() != null;
    }
}
