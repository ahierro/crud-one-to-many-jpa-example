package com.example.relationships.converters;

import com.example.relationships.dto.PurchaseOrderLineDTO;
import com.example.relationships.model.PurchaseOrderLine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderLineToEntity implements Converter<PurchaseOrderLineDTO, PurchaseOrderLine> {

    @Override
    public PurchaseOrderLine convert(PurchaseOrderLineDTO source) {

        return PurchaseOrderLine.builder()
                .lineDescription(source.getLineDescription())
                .id(source.getId())
                .price(source.getPrice())
                .build();
    }
}
