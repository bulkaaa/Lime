package com.modern.codes.lime.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The type Custom user details.
 */
public class CustomUserDetails implements UserDetails {

    /**
     * Instantiates a new Custom user details.
     *
     * @param user the user
     */
    public CustomUserDetails(final User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = translate(user.getRoles());
        this.enabled = user.getEnabled();
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
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private Collection<? extends GrantedAuthority> translate(final List<Role> roles) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(x -> authorities.add(new SimpleGrantedAuthority(x.getName())));
        return authorities;
    }

    private final Collection<? extends GrantedAuthority> authorities;
    private final String password;
    private final String username;
    private final boolean enabled;
    private static final long serialVersionUID = -3962786160780714576L;

}