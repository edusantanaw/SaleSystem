package com.sale.sale.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity()
@Table(name = "product")
@Data()
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
public class Product {
    @Column()
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column()
    private String name;
    @Column()
    private Float value;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean discount;
    @Column(columnDefinition = "FLOAT DEFAULT 0.00")
    private Float discountValue;
    @Column()
    private String description;
    @Column()
    private String image;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}
