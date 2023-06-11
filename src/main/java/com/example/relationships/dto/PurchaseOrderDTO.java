package com.example.relationships.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    private Long id;
    private String customerName;
    private List<PurchaseOrderLineDTO> lines;
    private BigDecimal total;
    private LocalDate creationDate;
}
