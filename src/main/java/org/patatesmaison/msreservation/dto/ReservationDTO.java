package org.patatesmaison.msreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO implements Serializable {

    private Long id;

    private Long barId;

    private String name;

    private ZonedDateTime dateTime;

    private Integer nbPerson;

    private LocalDateTime createdAt;


    public ZonedDateTime getDateTime() {
        return dateTime == null ? null : ZonedDateTime.from(dateTime);
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime == null ? null : ZonedDateTime.from(dateTime);
    }
}
