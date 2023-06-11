package com.example.relationships.service;

import com.example.relationships.dto.PageResponseDTO;
import com.example.relationships.dto.PurchaseOrderDTO;
import com.example.relationships.exceptions.NotFound;
import com.example.relationships.model.PurchaseOrder;
import com.example.relationships.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseOrderServiceImplTest {

    @Mock
    PurchaseOrderRepository repository;
    @Mock
    ConversionService conversionService;

    @InjectMocks
    PurchaseOrderServiceImpl service;

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getById() {
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .id(1L)
                .deleted(false)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        PurchaseOrderDTO purchaseOrderDTO = PurchaseOrderDTO.builder()
                .id(1L)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        when(repository.findByIdAndDeleted(anyLong(), anyBoolean()))
                .thenReturn(Optional.ofNullable(purchaseOrder));
        when(conversionService.convert(any(PurchaseOrder.class), eq(PurchaseOrderDTO.class)))
                .thenReturn(purchaseOrderDTO);
        var responseDTO = service.getById(1L);

        assertNotNull(responseDTO);
    }
    @Test
    void getById_ThrowNotFound() {
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .id(1L)
                .deleted(false)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        PurchaseOrderDTO purchaseOrderDTO = PurchaseOrderDTO.builder()
                .id(1L)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        when(repository.findByIdAndDeleted(anyLong(), anyBoolean()))
                .thenReturn(Optional.ofNullable(purchaseOrder));
        when(conversionService.convert(any(PurchaseOrder.class), eq(PurchaseOrderDTO.class)))
                .thenReturn(purchaseOrderDTO);

        assertThrows(NotFound.class,()-> service.getById(1L));
    }

    @Test
    void getPurchaseOrders() {

        var newPurchaseOrder = PurchaseOrder.builder()
                .id(1L)
                .deleted(false)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        var newPurchaseOrderDTO = PurchaseOrderDTO.builder()
                .id(1L)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        var pageRequest = PageRequest.of(0, 10);
        var page = new PageImpl<>(List.of(newPurchaseOrder), pageRequest, 1);
        when(repository.findAllByJpqlQuery(anyBoolean(), any(Pageable.class))).thenReturn(page);
        when(conversionService.convert(any(PurchaseOrder.class), eq(PurchaseOrderDTO.class))).thenReturn(newPurchaseOrderDTO);

        var responseDTO = service.getPurchaseOrders(pageRequest);

        assertNotNull(responseDTO);
    }
}