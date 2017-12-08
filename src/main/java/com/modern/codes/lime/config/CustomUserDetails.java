package com.modern.codes.lime.config;

import com.modern.codes.lime.model.Role;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = -3962786160780714576L;
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean enabled;

    public CustomUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = translate(user.getRoles());
        this.enabled = user.getEnabled();
    }

    private Collection<? extends GrantedAuthority> translate(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(x -> authorities.add(new SimpleGrantedAuthority(x.getName())));
        return authorities;
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

}