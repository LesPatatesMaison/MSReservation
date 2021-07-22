package org.patatesmaison.msreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.util.Logging;

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

    private BarDTO bar;

    private UserDTO user;

    private ZonedDateTime dateTime;

    private Integer nbPerson;

    private LocalDateTime createdAt;


    public ZonedDateTime getDateTime() {
        return dateTime == null ? null : ZonedDateTime.from(dateTime);
    }

    public void setDateTime(ZonedDateTime dateTime){
        this.dateTime = dateTime == null ? null : ZonedDateTime.from(dateTime);
    }

    public void setBarId(Long barId) {
        if(bar == null) bar = new BarDTO();
        this.bar.setId(barId);
    }

    @Override
    public String toString() {
        return Logging.toStringNotNull(this);
    }
}
