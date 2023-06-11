package com.example.relationships.controllers;

import com.example.relationships.dto.PurchaseOrderCreationDTO;
import com.example.relationships.dto.PurchaseOrderDTO;
import com.example.relationships.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Tag(name = "Purchase Order")
@RequestMapping("/purchase-order")
@AllArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @Operation(
            summary = "Create",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resource Created", content = @Content)})
    @PostMapping()
    public ResponseEntity<PurchaseOrderDTO> create(@RequestBody PurchaseOrderCreationDTO dto,
                                                   HttpServletRequest request)
            throws URISyntaxException {
        PurchaseOrderDTO createdPurchaseOrder = purchaseOrderService.create(dto);
        return ResponseEntity.created(new URI(request.getRequestURI() + "/" + createdPurchaseOrder.getId()))
                .body(createdPurchaseOrder);
    }

    @Operation(
            summary = "Update",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource Updated", content = @Content)})
    @PutMapping("/{idPurchaseOrder}")
    public ResponseEntity<PurchaseOrderDTO> update(@RequestBody PurchaseOrderDTO dto,
                                                   @PathVariable Long idPurchaseOrder) {
        PurchaseOrderDTO createdPurchaseOrder = purchaseOrderService.update(idPurchaseOrder, dto);
        return ResponseEntity.ok(createdPurchaseOrder);
    }

    @Operation(
            summary = "Delete",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resource Updated", content = @Content)})
    @DeleteMapping("/{idPurchaseOrder}")
    public void deleteLogical(@PathVariable Long idPurchaseOrder) {
        purchaseOrderService.delete(idPurchaseOrder);
    }
}
