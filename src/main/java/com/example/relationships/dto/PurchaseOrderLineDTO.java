package com.example.relationships.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderLineDTO {
    private Long id;
    private BigDecimal price;
    private String lineDescription;
}
