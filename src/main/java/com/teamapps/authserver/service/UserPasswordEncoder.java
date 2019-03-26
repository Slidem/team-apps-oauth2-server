package com.teamapps.authserver.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Mihai Alexandru
 * @date 24.11.2018
 */
@Service
public class UserPasswordEncoder {

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
