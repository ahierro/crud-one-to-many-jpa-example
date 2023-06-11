package com.example.relationships.dto;

import java.util.List;

public class PurchaseOrderPageDTO extends PageResponseDTO<PurchaseOrderDTO> {
    public PurchaseOrderPageDTO(List<PurchaseOrderDTO> content) {
        super(content);
    }
}
