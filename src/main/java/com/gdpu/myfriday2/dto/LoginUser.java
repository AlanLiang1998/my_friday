package com.gdpu.myfriday2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gdpu.myfriday2.model.Permission;
import com.gdpu.myfriday2.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/31 21:11
 * Version 1.0
 **/
@Data
public class LoginUser extends User implements UserDetails {

    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.parallelStream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getStatus() != Status.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.getStatus() == Status.ENABLED;
    }
}
