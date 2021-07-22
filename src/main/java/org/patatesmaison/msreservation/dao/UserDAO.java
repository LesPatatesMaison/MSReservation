package org.patatesmaison.msreservation.dao;

import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO  extends JpaRepository<User, Long> {
}
