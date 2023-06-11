package com.example.relationships.converters;

import com.example.relationships.dto.PurchaseOrderDTO;
import com.example.relationships.dto.PurchaseOrderLineDTO;
import com.example.relationships.model.PurchaseOrder;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderToDTO implements Converter<PurchaseOrder, PurchaseOrderDTO> {

    private final ConversionService conversionService;

    @Lazy
    public PurchaseOrderToDTO(ConversionService conversionService) {
        this.conversionService = conversionService;
    }


    @Override
    public PurchaseOrderDTO convert(PurchaseOrder source) {
        return PurchaseOrderDTO.builder()
                .lines(source.getLines().stream()
                        .map(line -> conversionService.convert(line, PurchaseOrderLineDTO.class))
                        .toList())
                .id(source.getId())
                .customerName(source.getCustomerName())
                .total(source.getTotal())
                .build();
    }
}
