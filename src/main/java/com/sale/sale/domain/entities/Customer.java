package com.sale.sale.domain.entities;

import com.sale.sale.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity()
@Table(name="customer")
@Data()
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
public class Customer implements UserDetails {
    @Id()
    @Column()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column()
    private String name;
    @Column(unique = true)
    private String email;
    @Column()
    private String password;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
    @Column()
    private Role role;

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
