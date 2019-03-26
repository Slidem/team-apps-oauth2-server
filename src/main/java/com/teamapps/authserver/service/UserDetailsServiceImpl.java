package com.teamapps.authserver.service;

import com.teamapps.authserver.entity.User;
import com.teamapps.authserver.exception.UserNotFoundException;
import com.teamapps.authserver.objects.UserDetailsImpl;
import com.teamapps.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Objects.isNull;


/**
 * @author Mihai Alexandru
 * @date 24.11.2018
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.getByLogin(login);
        if (isNull(user)) {
            throw new UserNotFoundException("No user found for login: " + login);
        }
        return new UserDetailsImpl.Builder()
                .withUserName(user.getLogin())
                .withPassword(user.getPassword())
                .withAccountNonExpired(true)
                .withAccountNonLocked(true)
                .withCredentialsNonExpired(true)
                .withEnabled(true)
                .withAuthorities(getAuthoritiesFromRoles(user.getRoles()))
                .build();
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromRoles(String roles) {
        return stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
