package org.patatesmaison.msreservation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.dao.ReservationDAO;
import org.patatesmaison.msreservation.dao.UserDAO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.patatesmaison.msreservation.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * http://localhost:8040/h2-console
 * jdbc:h2:mem:testdb -> jdbc:h2:mem:db
 */

@Configuration
@Slf4j
public class H2DBConfig {

    @Bean
    CommandLineRunner initDatabase(ReservationDAO reservationDAO, UserDAO userDAO){
        return args -> {

            List<User> userList = new ArrayList<>(Arrays.asList(
                new User("loginToto", "passwordToto", "email@Toto.com", "lastNameToto", "firstNameToto"),
                new User("loginTiti", "passwordTiti", "email@Titi.com", "lastNameTiti", "firstNameTiti"),
                new User("loginTuTu", "passwordTuTu", "email@TuTu.com", "lastNameTuTu", "firstNameTuTu"),
                new User("loginRiri", "passwordRiri", "email@Riri.com", "lastNameRiri", "firstNameRiri"),
                new User("loginFifi", "passwordFifi", "email@Fifi.com", "lastNameFifi", "firstNameFifi"),
                new User("loginLoulou", "passwordLoulou", "email@Loulou.com", "lastNameLoulou", "firstNameLoulou")
            ));

            userList.forEach(userDAO::save);

            List<Reservation> reservationList = new ArrayList<>(Arrays.asList(
                    new Reservation( 1L, userList.get(0), ZonedDateTime.now().plusDays(10), 5),
                    new Reservation( 1L, userList.get(0), ZonedDateTime.now().plusDays(10), 1),
                    new Reservation( 2L, userList.get(0), ZonedDateTime.now().plusDays(12), 19),
                    new Reservation( 2L, userList.get(1), ZonedDateTime.now().plusDays(12), 2),
                    new Reservation( 2L, userList.get(2), ZonedDateTime.now().plusDays(42), 7),
                    new Reservation( 3L, userList.get(2), ZonedDateTime.now().plusDays(63), 6),
                    new Reservation( 4L, userList.get(2), ZonedDateTime.now().plusDays(10), 5),
                    new Reservation( 5L, userList.get(2), ZonedDateTime.now().plusDays(10), 1),
                    new Reservation( 6L, userList.get(2), ZonedDateTime.now().plusDays(12), 19),
                    new Reservation( 7L, userList.get(3), ZonedDateTime.now().plusDays(12), 2),
                    new Reservation( 8L, userList.get(3), ZonedDateTime.now().plusDays(42), 7),
                    new Reservation( 9L, userList.get(3), ZonedDateTime.now().plusDays(63), 6),
                    new Reservation( 10L, userList.get(4), ZonedDateTime.now().plusDays(10), 5),
                    new Reservation( 11L, userList.get(5), ZonedDateTime.now().plusDays(10), 1),
                    new Reservation( 14L, userList.get(5), ZonedDateTime.now().plusDays(12), 19),
                    new Reservation( 19L, userList.get(5), ZonedDateTime.now().plusDays(21), 2),
                    new Reservation( 22L, userList.get(5), ZonedDateTime.now().plusDays(42), 7),
                    new Reservation( 31L, userList.get(5), ZonedDateTime.now().plusDays(63), 6)
            ));

            reservationList.forEach(reservationDAO::save);
        };
    }
}
