package com.sotatek.ordermanagement.service.impl;


import com.sotatek.ordermanagement.dto.request.UpdateInventoryRequest;
import com.sotatek.ordermanagement.dto.response.InventoryDetailsResponse;
import com.sotatek.ordermanagement.entity.Inventory;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.repository.InventoryRepository;
import com.sotatek.ordermanagement.service.InventoryService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public void initInventory(long productId) {
        final Inventory inventory =
                Inventory.builder()
                        .productId(productId)
                        .stockQuantity(0L)
                        .updatedDate(new Date())
                        .build();
        inventoryRepository.save(inventory);
    }

    public InventoryDetailsResponse updateInventory(
            long productId, UpdateInventoryRequest request) {
        final Inventory inventory = getInventoryByProductIdOrFailed(productId);
        inventory.setStockQuantity(request.getStockQuantity());
        inventory.setUpdatedDate(new Date());
        final Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryDetailsResponse.from(savedInventory);
    }

    public Inventory getInventoryByProductIdOrFailed(long productId) {
        final Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory != null) {
            return inventory;
        }
        throw new NotFoundException("Product not found");
    }
}
