package org.patatesmaison.msreservation.mapper;

import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReservationMapper implements EntityDTOMapper<Reservation, ReservationDTO> {

    @Override
    public ReservationDTO fromEntity(Reservation entity) {
        return fromEntity(new ReservationDTO(), entity);
    }

    @Override
    public ReservationDTO fromEntity(ReservationDTO dto, Reservation entity) {

        dto.setId(entity.getId());
        dto.setBarId(entity.getBarId());
        dto.setName(entity.getName());
        dto.setDateTime(entity.getDateTime());
        dto.setNbPerson(entity.getNbPerson());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }

    @Override
    public Reservation fromDto(ReservationDTO dto) {
        return fromDto(new Reservation(), dto);
    }

    @Override
    public Reservation fromDto(Reservation entity, ReservationDTO dto) {
        entity.setId(dto.getId());
        entity.setBarId(dto.getBarId());
        entity.setName(dto.getName());
        entity.setDateTime(dto.getDateTime());
        entity.setNbPerson(dto.getNbPerson());
        entity.setCreatedAt(dto.getCreatedAt());

        return entity;
    }
}
