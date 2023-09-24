package com.sale.sale.infra.security;

import com.sale.sale.domain.entities.Customer;
import com.sale.sale.domain.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomerDetails extends Customer implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      if(this.getRole() == Role.ADMIN){
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
      } else {
          return List.of(new SimpleGrantedAuthority("ROLE_USER"));
      }
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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
        return true;
    }
}
