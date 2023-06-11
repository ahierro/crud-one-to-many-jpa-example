package com.example.relationships.converters;

import com.example.relationships.dto.PurchaseOrderDTO;
import com.example.relationships.model.PurchaseOrder;
import com.example.relationships.model.PurchaseOrderLine;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class PurchaseOrderToEntity implements Converter<PurchaseOrderDTO, PurchaseOrder> {

    private final ConversionService conversionService;

    @Lazy
    public PurchaseOrderToEntity(ConversionService conversionService){
        this.conversionService = conversionService;
    }

    @Override
    public PurchaseOrder convert(PurchaseOrderDTO source) {

        return PurchaseOrder.builder()
                .lines((source.getLines()!=null?source.getLines(): Collections.emptyList()).stream()
                        .map(line -> conversionService.convert(line, PurchaseOrderLine.class))
                        .toList())
                .id(source.getId())
                .customerName(source.getCustomerName())
                .total(source.getTotal())
                .deleted(false)
                .build();
    }
}
