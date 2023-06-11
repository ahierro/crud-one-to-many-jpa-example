package com.example.relationships.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PurchaseOrderLine> lines;

    private BigDecimal total;

    private LocalDate creationDate;

    @Column(columnDefinition = "bit not null default 0")
    private Boolean deleted;

}
