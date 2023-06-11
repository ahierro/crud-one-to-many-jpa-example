package com.example.relationships.converters;

import com.example.relationships.dto.PurchaseOrderLineDTO;
import com.example.relationships.model.PurchaseOrderLine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderLineToDTO implements Converter<PurchaseOrderLine, PurchaseOrderLineDTO> {
    @Override
    public PurchaseOrderLineDTO convert(PurchaseOrderLine source) {
        return PurchaseOrderLineDTO.builder()
                .id(source.getId())
                .lineDescription(source.getLineDescription())
                .price(source.getPrice())
                .build();
    }
}
