package com.example.relationships.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderCreationDTO {

    private String customerName;
    private List<PurchaseOrderLineCreationDTO> lines;
    private BigDecimal total;
}
