package org.patatesmaison.msreservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.patatesmaison.msreservation.entity.auditing.DateAudit;
import org.patatesmaison.msreservation.util.Logging;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Reservation extends DateAudit {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = false, nullable = false)
    private Long barId;

    @Column(unique = false, nullable = false)
    private String name;

    @Column(unique = false, nullable = false, columnDefinition = "timestamp with time zone")
    private ZonedDateTime dateTime;

    @Column(unique = false, nullable = false, columnDefinition = "integer default 1")
    private Integer nbPerson;

    public Reservation(Long barId, String name, ZonedDateTime dateTime, Integer nbPerson) {
        this.barId = barId;
        this.name = name;
        this.dateTime = dateTime;
        this.nbPerson = nbPerson;
    }

    public ZonedDateTime getDateTime() {
        return ZonedDateTime.from(dateTime);
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = ZonedDateTime.from(dateTime);
    }

    @Override
    public String toString() {
        return Logging.toStringNotNull(this);
    }
}

