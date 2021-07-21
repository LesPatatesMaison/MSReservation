package org.patatesmaison.msreservation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.dao.ReservationDAO;
import org.patatesmaison.msreservation.entity.Reservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;


/**
 * http://localhost:8040/h2-console
 * jdbc:h2:mem:testdb -> jdbc:h2:mem:db
 */

@Configuration
@Slf4j
public class H2DBConfig {

    @Bean
    CommandLineRunner initDatabase(ReservationDAO reservationDAO){
        return args -> {

            Reservation reservation1 = new Reservation( 1L, 1L, "toto", ZonedDateTime.now().plusDays(10), 5);
            Reservation reservation2 = new Reservation( 2L, 1L, "titi", ZonedDateTime.now().plusDays(10), 1);
            Reservation reservation3 = new Reservation( 3L, 2L, "titi", ZonedDateTime.now().plusDays(12), 19);
            Reservation reservation4 = new Reservation( 4L, 2L, "tutu", ZonedDateTime.now().plusDays(12), 2);
            Reservation reservation5 = new Reservation( 5L, 2L, "tutu", ZonedDateTime.now().plusDays(42), 7);
            Reservation reservation6 = new Reservation( 6L, 3L, "riri", ZonedDateTime.now().plusDays(63), 6);

            reservation1.setCreatedAt(ZonedDateTime.now());
            reservation1.setUpdatedAt(ZonedDateTime.now());
            reservation2.setCreatedAt(ZonedDateTime.now());
            reservation2.setUpdatedAt(ZonedDateTime.now());
            reservation3.setCreatedAt(ZonedDateTime.now());
            reservation3.setUpdatedAt(ZonedDateTime.now());
            reservation4.setCreatedAt(ZonedDateTime.now());
            reservation4.setUpdatedAt(ZonedDateTime.now());
            reservation5.setCreatedAt(ZonedDateTime.now());
            reservation5.setUpdatedAt(ZonedDateTime.now());
            reservation6.setCreatedAt(ZonedDateTime.now());
            reservation6.setUpdatedAt(ZonedDateTime.now());

            reservationDAO.save(reservation1);
            reservationDAO.save(reservation2);
            reservationDAO.save(reservation3);
            reservationDAO.save(reservation4);
            reservationDAO.save(reservation5);
            reservationDAO.save(reservation6);
        };
    }
}
