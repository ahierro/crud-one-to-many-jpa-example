package com.example.relationships.converters;

import com.example.relationships.dto.PurchaseOrderLineCreationDTO;
import com.example.relationships.dto.PurchaseOrderLineDTO;
import com.example.relationships.model.PurchaseOrderLine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderLineCreationToEntity implements Converter<PurchaseOrderLineCreationDTO, PurchaseOrderLine> {

    @Override
    public PurchaseOrderLine convert(PurchaseOrderLineCreationDTO source) {

        return PurchaseOrderLine.builder()
                .lineDescription(source.getLineDescription())
                .price(source.getPrice())
                .build();
    }
}
