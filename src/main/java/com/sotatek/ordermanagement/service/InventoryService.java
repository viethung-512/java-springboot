package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.UpdateInventoryRequest;
import com.sotatek.ordermanagement.dto.response.InventoryDetailsResponse;
import com.sotatek.ordermanagement.entity.Inventory;
import com.sotatek.ordermanagement.exception.NotFoundException;
import com.sotatek.ordermanagement.exception.ProductQuantityIsNotEnoughException;
import com.sotatek.ordermanagement.repository.InventoryRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryDetailsResponse updateInventory(
            long productId, UpdateInventoryRequest request) {
        final Inventory inventory = getInventoryByProductIdOrFailed(productId);
        inventory.setStockQuantity(request.getStockQuantity());
        inventory.setUpdatedDate(new Date());
        final Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryDetailsResponse.from(savedInventory);
    }

    public InventoryDetailsResponse reduceStockQuantity(long productId, long incomingReduce) {
        final Inventory inventory = getInventoryByProductIdOrFailed(productId);
        if (incomingReduce > inventory.getStockQuantity()) {
            throw new ProductQuantityIsNotEnoughException(inventory.getProduct().getName());
        }
        inventory.setStockQuantity(inventory.getStockQuantity() - incomingReduce);
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
