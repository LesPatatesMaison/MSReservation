package org.patatesmaison.msreservation.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.client.ConcentrateurApiClient;
import org.patatesmaison.msreservation.dto.BarDTO;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.dto.UserDTO;
import org.patatesmaison.msreservation.dto.UserDTO;
import org.patatesmaison.msreservation.entity.User;
import org.patatesmaison.msreservation.entity.User;
import org.patatesmaison.msreservation.exception.APIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class UserMapper implements EntityDTOMapper<User, UserDTO> {

    private final ReservationMapper reservationMapper;

    @Override
    public UserDTO fromEntity(User entity) {
        return fromEntity(new UserDTO(), entity);
    }

    @Override
    public UserDTO fromEntity(UserDTO dto, User entity) {

        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setEmail(entity.getEmail());
        dto.setLastname(entity.getLastname());
        dto.setFirstname(entity.getFirstname());

        return dto;
    }

    @Override
    public User fromDto(UserDTO dto) {
        return fromDto(new User(), dto);
    }

    @Override
    public User fromDto(User entity, UserDTO dto) {
        if(entity.getId() == null) entity.setId(dto.getId());

        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setLastname(dto.getLastname());
        entity.setFirstname(dto.getFirstname());

        return entity;
    }
}
