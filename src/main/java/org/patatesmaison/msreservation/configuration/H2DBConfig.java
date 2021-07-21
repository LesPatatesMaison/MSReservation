package org.patatesmaison.msreservation.configuration;

import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.dao.ReservationDAO;
import org.patatesmaison.msreservation.entity.Reservation;
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
    CommandLineRunner initDatabase(ReservationDAO reservationDAO){
        return args -> {

            List<Reservation> reservationList = new ArrayList<>(Arrays.asList(
                new Reservation( 1L, "toto", ZonedDateTime.now().plusDays(10), 5),
                new Reservation( 1L, "titi", ZonedDateTime.now().plusDays(10), 1),
                new Reservation( 2L, "titi", ZonedDateTime.now().plusDays(12), 19),
                new Reservation( 2L, "tutu", ZonedDateTime.now().plusDays(12), 2),
                new Reservation( 2L, "tutu", ZonedDateTime.now().plusDays(42), 7),
                new Reservation( 3L, "riri", ZonedDateTime.now().plusDays(63), 6)
            ));

            reservationList.forEach(reservationDAO::save);
        };
    }
}
