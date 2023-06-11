package com.example.relationships.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "PURCHASE_ORDER")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @BatchSize(size = 10)
    private List<PurchaseOrderLine> lines;

    private BigDecimal total;

    @Column(nullable = false)
    private Boolean deleted = false;

}
