package com.sale.sale.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity()
@Table(name = "sale")
@Data()
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
public class Sale {
    @Column()
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @PrimaryKeyJoinColumn()
    @OneToOne()
    private Cart cart;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}
