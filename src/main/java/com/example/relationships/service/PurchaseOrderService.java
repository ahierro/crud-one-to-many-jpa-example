package com.example.relationships.service;

import com.example.relationships.dto.PurchaseOrderCreationDTO;
import com.example.relationships.dto.PurchaseOrderDTO;

public interface PurchaseOrderService {
    PurchaseOrderDTO create(PurchaseOrderCreationDTO dto);

    PurchaseOrderDTO update(Long id, PurchaseOrderDTO dto);

    void delete(Long idPurchaseOrder);
}
