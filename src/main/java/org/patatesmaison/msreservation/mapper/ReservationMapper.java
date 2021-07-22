package org.patatesmaison.msreservation.mapper;

import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.client.ConcentrateurApiClient;
import org.patatesmaison.msreservation.dao.ReservationDAO;
import org.patatesmaison.msreservation.dto.BarDTO;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.exception.APIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReservationMapper implements EntityDTOMapper<Reservation, ReservationDTO> {

    @Value("${messages.reservation.dto.invalid}")
    protected String messageReservationInvalid;

    @Value("${messages.reservation.not-found}")
    protected String messageReservationNotFound;

    private final ConcentrateurApiClient concentrateurApiClient;

    public ReservationMapper(ConcentrateurApiClient concentrateurApiClient) {
        this.concentrateurApiClient = concentrateurApiClient;
    }

    @Override
    public ReservationDTO fromEntity(Reservation entity) {
        return fromEntity(new ReservationDTO(), entity);
    }

    @Override
    public ReservationDTO fromEntity(ReservationDTO dto, Reservation entity) {

        dto.setId(entity.getId());
        dto.setBarId(entity.getBarId());

        BarDTO barDTO = concentrateurApiClient.getBarById(entity.getBarId());
        dto.setBar(barDTO);

        dto.setName(entity.getName());
        dto.setDateTime(entity.getDateTime());
        dto.setNbPerson(entity.getNbPerson());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }

    @Override
    public Reservation fromDto(ReservationDTO dto) throws APIException {
        return fromDto(new Reservation(), dto);
    }

    @Override
    public Reservation fromDto(Reservation entity, ReservationDTO dto) throws APIException {
        if(entity.getId() == null) entity.setId(dto.getId());

        if((dto.getBar() == null || dto.getBar().getId() == null) && entity.getBarId() == null) {
            throw new IllegalArgumentException(messageReservationInvalid);
        }
        if(dto.getBar() != null && dto.getBar().getId() != null) entity.setBarId(dto.getBar().getId());

        if(dto.getName() != null) entity.setName(dto.getName());
        if(dto.getDateTime() != null) entity.setDateTime(dto.getDateTime());
        if(dto.getNbPerson() != null) entity.setNbPerson(dto.getNbPerson());

        return entity;
    }
}
