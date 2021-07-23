package org.patatesmaison.msreservation.dto;

import org.patatesmaison.msreservation.util.Logging;

import java.io.Serializable;

public class AuthenticationDTO implements Serializable {
    public String login;
    public String password;

    @Override
    public String toString() {
        return Logging.toStringNotNull(this);
    }
}
