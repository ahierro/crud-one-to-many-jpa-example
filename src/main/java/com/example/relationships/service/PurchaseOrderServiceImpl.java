package com.example.relationships.service;

import com.example.relationships.dto.PurchaseOrderCreationDTO;
import com.example.relationships.dto.PurchaseOrderDTO;
import com.example.relationships.exceptions.BadRequest;
import com.example.relationships.exceptions.NotFound;
import com.example.relationships.model.PurchaseOrder;
import com.example.relationships.repository.PurchaseOrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ConversionService conversionService;

    @Override
    public PurchaseOrderDTO create(PurchaseOrderCreationDTO dto) {
        return savePurchaseOrder(conversionService.convert(dto, PurchaseOrder.class));
    }

    @Override
    public PurchaseOrderDTO update(Long id, PurchaseOrderDTO dto) {
        if(dto.getId()!=null && !dto.getId().equals(id)) throw new BadRequest();
        dto.setId(id);
        if(purchaseOrderRepository.exists(id)){
            return savePurchaseOrder(conversionService.convert(dto, PurchaseOrder.class));
        }else{
            throw new NotFound();
        }
    }

    @Override
    public void delete(Long idPurchaseOrder) {
        int number = purchaseOrderRepository.softDelete(idPurchaseOrder);
        if(number == 0){
            throw new NotFound();
        }
    }

    private PurchaseOrderDTO savePurchaseOrder(PurchaseOrder purchaseOrder) {
        if (purchaseOrder == null) throw new IllegalArgumentException();
        if (purchaseOrder.getLines() != null) {
            purchaseOrder.getLines().forEach(line -> line.setPurchaseOrder(purchaseOrder));
        }
        PurchaseOrder createdPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return conversionService.convert(createdPurchaseOrder, PurchaseOrderDTO.class);
    }

}
