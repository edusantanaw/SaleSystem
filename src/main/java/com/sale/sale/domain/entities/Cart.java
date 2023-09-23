package com.sale.sale.domain.entities;

import com.sale.sale.domain.enums.PaymentMethods;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity()
@Table(name = "cart")
@Data()
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
public class Cart {
    @Id()
    @Column()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String productsIds;
    @Column()
    private Float value;
    @Column()
    private Integer installmentsCount;
    @JoinColumn()
    @ManyToOne()
    private Customer customer;
    @Column()
    private PaymentMethods paymentMethod;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
    @PrimaryKeyJoinColumn()
    @OneToOne(optional = true)
    private Sale saleId;
}
