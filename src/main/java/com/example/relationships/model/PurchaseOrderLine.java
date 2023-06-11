package com.example.relationships.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PURCHASE_ORDER_LINE", indexes = {@Index(name = "IDX_PURCHASE_ORDER_ID",columnList = "purchase_order_id")})
@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false, foreignKey = @ForeignKey(name="FK_PURCHASE_ORDER_ID"))
    private PurchaseOrder purchaseOrder;

    private BigDecimal price;

    private String lineDescription;

}
