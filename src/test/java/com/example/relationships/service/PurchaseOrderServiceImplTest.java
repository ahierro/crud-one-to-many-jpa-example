package com.example.relationships.service;

import com.example.relationships.dto.PurchaseOrderCreationDTO;
import com.example.relationships.dto.PurchaseOrderDTO;
import com.example.relationships.dto.PurchaseOrderLineCreationDTO;
import com.example.relationships.dto.PurchaseOrderLineDTO;
import com.example.relationships.exceptions.NotFound;
import com.example.relationships.model.PurchaseOrder;
import com.example.relationships.model.PurchaseOrderLine;
import com.example.relationships.repository.PurchaseOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .id(1L)
                .deleted(false)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L))
                .lines(List.of(PurchaseOrderLine.builder()
                                .id(1L)
                                .price(BigDecimal.valueOf(100))
                                .lineDescription("line 1")
                        .build()))
                .build();

        PurchaseOrderCreationDTO purchaseOrderCreationDTO = PurchaseOrderCreationDTO.builder()
                .customerName("ads")
                .lines(List.of(PurchaseOrderLineCreationDTO.builder()
                        .price(BigDecimal.valueOf(100))
                        .lineDescription("line 1")
                        .build()))
                .total(BigDecimal.valueOf(123L)).build();

        PurchaseOrderDTO purchaseOrderDTO = PurchaseOrderDTO.builder()
                .id(1L)
                .lines(List.of(PurchaseOrderLineDTO.builder()
                        .price(BigDecimal.valueOf(100))
                        .lineDescription("line 1")
                        .build()))
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        when(conversionService.convert(any(PurchaseOrderCreationDTO.class), eq(PurchaseOrder.class)))
                .thenReturn(purchaseOrder);

        when(repository.save(any(PurchaseOrder.class))).thenReturn(purchaseOrder);


        when(conversionService.convert(any(PurchaseOrder.class), eq(PurchaseOrderDTO.class)))
                .thenReturn(purchaseOrderDTO);

        var result = service.create(purchaseOrderCreationDTO);

        assertNotNull(result);
    }

    @Test
    void update() {
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .id(1L)
                .deleted(false)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        PurchaseOrderDTO purchaseOrderDTO = PurchaseOrderDTO.builder()
                .id(1L)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        when(conversionService.convert(any(PurchaseOrderDTO.class), eq(PurchaseOrder.class)))
                .thenReturn(purchaseOrder);

        when(repository.exists(anyLong())).thenReturn(true);

        when(repository.save(any(PurchaseOrder.class))).thenReturn(purchaseOrder);

        when(conversionService.convert(any(PurchaseOrder.class), eq(PurchaseOrderDTO.class)))
                .thenReturn(purchaseOrderDTO);

        var result = service.update(1L,purchaseOrderDTO);

        assertNotNull(result);
    }

    @Test
    void update_NotFound() {

        PurchaseOrderDTO purchaseOrderDTO = PurchaseOrderDTO.builder()
                .id(1L)
                .customerName("ads")
                .total(BigDecimal.valueOf(123L)).build();

        when(repository.exists(anyLong())).thenReturn(false);

        assertThrows(NotFound.class, ()->service.update(1L,purchaseOrderDTO));
        verify(repository,times(0)).save(any(PurchaseOrder.class));
    }

    @Test
    void delete() {
        when(repository.softDelete(anyLong())).thenReturn(1);

        service.delete(1L);

        verify(repository).softDelete(anyLong());
    }

    @Test
    void delete_ThrowNotFound() {
        when(repository.softDelete(anyLong())).thenReturn(0);

        assertThrows(NotFound.class, () -> service.delete(1L));
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

        when(repository.findByIdAndDeleted(anyLong(), anyBoolean()))
                .thenReturn(Optional.empty());

        assertThrows(NotFound.class, () -> service.getById(1L));
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