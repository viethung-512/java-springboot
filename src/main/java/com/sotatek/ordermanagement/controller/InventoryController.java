package com.sotatek.ordermanagement.controller;


import com.sotatek.ordermanagement.dto.request.UpdateInventoryRequest;
import com.sotatek.ordermanagement.dto.response.InventoryDetailsResponse;
import com.sotatek.ordermanagement.service.InventoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    private final InventoryService inventoryService;

    @PatchMapping("{productId}")
    @Secured({"ADMIN", "OPERATOR"})
    public InventoryDetailsResponse updateInventory(
            @PathVariable("productId") long productId,
            @RequestBody UpdateInventoryRequest request) {
        return inventoryService.updateInventory(productId, request);
    }
}
