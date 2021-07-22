package org.patatesmaison.msreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.patatesmaison.msreservation.util.Logging;

import java.io.Serializable;


@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    private String login;

    private String email;

    private String lastname;

    private String firstname;

    @Override
    public String toString() {
        return Logging.toStringNotNull(this);
    }
}
