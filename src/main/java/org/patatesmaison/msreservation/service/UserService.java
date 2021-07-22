package org.patatesmaison.msreservation.service;

import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.dao.ReservationDAO;
import org.patatesmaison.msreservation.dao.UserDAO;
import org.patatesmaison.msreservation.dto.ReservationDTO;
import org.patatesmaison.msreservation.dto.UserDTO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.entity.User;
import org.patatesmaison.msreservation.exception.APIException;
import org.patatesmaison.msreservation.mapper.ReservationMapper;
import org.patatesmaison.msreservation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Value("${messages.user.dto.invalid}")
    protected String messageUserInvalid;

    @Value("${messages.user.not-found}")
    protected String messageUserNotFound;

    private final UserDAO userDAO;

    private final ReservationDAO reservationDAO;

    private final UserMapper userMapper;

    private final ReservationMapper reservationMapper;

    public UserService(UserDAO userDAO, ReservationDAO reservationDAO, UserMapper userMapper, ReservationMapper reservationMapper) {
        this.userDAO = userDAO;
        this.reservationDAO = reservationDAO;
        this.userMapper = userMapper;
        this.reservationMapper = reservationMapper;
    }

    public UserDTO getUser(Long userId) throws APIException {
        Optional<User> userOptional = userDAO.findById(userId);

        if(userOptional.isEmpty()) throw new APIException(messageUserNotFound, HttpStatus.NOT_FOUND);

       return userMapper.fromEntity(userOptional.get());
    }

    public List<UserDTO> getUserList() {
        return userDAO.findAll().stream().map(userMapper::fromEntity).collect(Collectors.toList());
    }

    public Set<ReservationDTO> getUserReservation(Long userId) throws APIException {
        Optional<User> userOptional = userDAO.findById(userId);
        if(userOptional.isEmpty()) throw new APIException(messageUserNotFound, HttpStatus.NOT_FOUND);

        Set<Reservation> reservationSet = reservationDAO.findAllByUserId(userId);

        return reservationSet.stream().map(reservationMapper::fromEntity).collect(Collectors.toSet());
    }
}
