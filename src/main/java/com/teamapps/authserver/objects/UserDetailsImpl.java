package com.teamapps.authserver.objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final String userName;

    private final String password;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    private final Collection<? extends GrantedAuthority> authorities;

    private UserDetailsImpl(Builder builder) {
        userName = builder.userName;
        password = builder.password;
        accountNonExpired = builder.accountNonExpired;
        accountNonLocked = builder.accountNonLocked;
        credentialsNonExpired = builder.credentialsNonExpired;
        enabled = builder.enabled;
        authorities = builder.authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public static final class Builder {

        private String userName;

        private String password;

        private boolean accountNonExpired;

        private boolean accountNonLocked;

        private boolean credentialsNonExpired;

        private boolean enabled;

        private Collection<? extends GrantedAuthority> authorities;

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public Builder withAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public Builder withCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public Builder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder withAuthorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserDetails build() {
            return new UserDetailsImpl(this);
        }

    }
}
