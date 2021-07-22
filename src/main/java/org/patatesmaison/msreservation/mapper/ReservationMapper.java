package org.patatesmaison.msreservation.mapper;

import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.client.ConcentrateurApiClient;
import org.patatesmaison.msreservation.dao.UserDAO;
import org.patatesmaison.msreservation.dto.BarDTO;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.dto.UserDTO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReservationMapper implements EntityDTOMapper<Reservation, ReservationDTO> {

    @Value("${messages.reservation.dto.invalid}")
    protected String messageReservationInvalid;

    @Value("${messages.reservation.not-found}")
    protected String messageReservationNotFound;

    private final ConcentrateurApiClient concentrateurApiClient;

    private final UserDAO userDAO;

    public ReservationMapper(ConcentrateurApiClient concentrateurApiClient, UserDAO userDAO) {
        this.concentrateurApiClient = concentrateurApiClient;
        this.userDAO = userDAO;
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

        User user = entity.getUser();
        UserDTO userDTO = new UserDTO(user.getId(), user.getLogin(), user.getEmail(), user.getLastname(), user.getFirstname());
        dto.setUser(userDTO);

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
    public Reservation fromDto(Reservation entity, ReservationDTO dto) throws IllegalArgumentException {

        if(entity.getId() == null) entity.setId(dto.getId());

        if(dto.getBar() != null && dto.getBar().getId() != null) entity.setBarId(dto.getBar().getId());

        entity.setUser(userDAO.getById(dto.getUser().getId()));

        if(dto.getDateTime() != null) entity.setDateTime(dto.getDateTime());
        if(dto.getNbPerson() != null) entity.setNbPerson(dto.getNbPerson());

        return entity;
    }
}
