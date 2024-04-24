package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.UpdateInventoryRequest;
import com.sotatek.ordermanagement.dto.response.InventoryDetailsResponse;
import com.sotatek.ordermanagement.service.impl.InventoryServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryServiceImpl inventoryServiceImpl;

    @PatchMapping("{productId}")
    @Secured({"ADMIN", "OPERATOR"})
    public InventoryDetailsResponse updateInventory(
            @PathVariable("productId") long productId,
            @Valid @RequestBody UpdateInventoryRequest request) {
        return inventoryServiceImpl.updateInventory(productId, request);
    }
}
