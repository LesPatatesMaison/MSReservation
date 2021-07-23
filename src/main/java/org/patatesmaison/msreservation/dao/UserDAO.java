package org.patatesmaison.msreservation.dao;

import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserDAO  extends JpaRepository<User, Long> {

    Optional<User> getByLogin(String login);
}
