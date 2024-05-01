package com.sotatek.ordermanagement.service;


import com.sotatek.ordermanagement.dto.request.UpdateInventoryRequest;
import com.sotatek.ordermanagement.dto.response.InventoryDetailsResponse;
import com.sotatek.ordermanagement.entity.Inventory;

public interface InventoryService {
    public void initInventory(long productId);

    public InventoryDetailsResponse updateInventory(long productId, UpdateInventoryRequest request);

    public Inventory getInventoryByProductIdOrFailed(long productId);
}
