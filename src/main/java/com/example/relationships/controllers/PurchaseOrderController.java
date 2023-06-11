package com.example.relationships.controllers;

import com.example.relationships.dto.PageResponseDTO;
import com.example.relationships.dto.PurchaseOrderCreationDTO;
import com.example.relationships.dto.PurchaseOrderDTO;
import com.example.relationships.dto.PurchaseOrderPageDTO;
import com.example.relationships.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
                    @ApiResponse(responseCode = "200", description = "Successful Operation\"", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @PutMapping("/{idPurchaseOrder}")
    public ResponseEntity<PurchaseOrderDTO> update(@RequestBody PurchaseOrderDTO dto,
                                                   @PathVariable Long idPurchaseOrder) {
        PurchaseOrderDTO createdPurchaseOrder = purchaseOrderService.update(idPurchaseOrder, dto);
        return ResponseEntity.ok(createdPurchaseOrder);
    }

    @Operation(
            summary = "Delete",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Operation\"", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @DeleteMapping("/{idPurchaseOrder}")
    public void deleteLogical(@PathVariable Long idPurchaseOrder) {
        purchaseOrderService.delete(idPurchaseOrder);
    }

    @Operation(
            summary = "Get by Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)})
    @GetMapping("/{idPurchaseOrder}")
    public PurchaseOrderDTO getById(@PathVariable Long idPurchaseOrder){
        return purchaseOrderService.getById(idPurchaseOrder);
    }

    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Get page of users",
            parameters = { @Parameter(in = ParameterIn.QUERY, name = "page", description = "Page"),
                    @Parameter(in = ParameterIn.QUERY, name = "size", description = "Size") },
            responses = {
                    @ApiResponse(responseCode = "200",description = "Successful Operation",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PurchaseOrderPageDTO.class)))})
    @GetMapping ("/page")
    public PageResponseDTO<PurchaseOrderDTO> getUsers(@PageableDefault(size = 10,page = 0) @ParameterObject Pageable pageable) {
        return purchaseOrderService.getPurchaseOrders(pageable);
    }
}
