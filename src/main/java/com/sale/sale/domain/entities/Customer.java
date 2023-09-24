package com.sale.sale.domain.entities;

import com.sale.sale.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity()
@Table(name="customer")
@Data()
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
public class Customer {
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
}
