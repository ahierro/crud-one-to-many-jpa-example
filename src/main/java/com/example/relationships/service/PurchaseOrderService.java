package com.example.relationships.service;

import com.example.relationships.dto.PageResponseDTO;
import com.example.relationships.dto.PurchaseOrderCreationDTO;
import com.example.relationships.dto.PurchaseOrderDTO;
import org.springframework.data.domain.Pageable;

public interface PurchaseOrderService {
    PurchaseOrderDTO create(PurchaseOrderCreationDTO dto);

    PurchaseOrderDTO update(Long id, PurchaseOrderDTO dto);

    void delete(Long idPurchaseOrder);

    PurchaseOrderDTO getById(Long idPurchaseOrder);

    PageResponseDTO<PurchaseOrderDTO> getPurchaseOrders(Pageable pageable);
}
