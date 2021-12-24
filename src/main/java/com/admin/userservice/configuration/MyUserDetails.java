package com.admin.userservice.configuration;

import com.admin.userservice.entity.Role;
import com.admin.userservice.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class MyUserDetails implements UserDetails {
    @Autowired
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role>roles=user.getRoles();
        List<SimpleGrantedAuthority>authorities=new ArrayList<>();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        System.out.println(authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println(user.getPassword());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        System.out.println(user.getEmail());
        return user.getEmail();
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
        return false;
    }
}
