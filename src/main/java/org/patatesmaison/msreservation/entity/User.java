package org.patatesmaison.msreservation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.patatesmaison.msreservation.entity.auditing.DateAudit;
import org.patatesmaison.msreservation.util.Logging;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User extends DateAudit {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = false, nullable = false)
    private String login;

    @Column(unique = false, nullable = false)
    private String password;

    @Column(unique = false, nullable = false)
    private String email;

    @Column(unique = false, nullable = true)
    private String lastname;

    @Column(unique = false, nullable = true)
    private String firstname;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL
                , orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Reservation> reservationSet = new HashSet<>();

    public User(String login, String password, String email, String lastname, String firstname) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return Logging.toStringNotNull(this);
    }
}
