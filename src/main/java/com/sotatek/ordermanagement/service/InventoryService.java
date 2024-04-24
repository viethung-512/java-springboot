package com.sotatek.ordermanagement.service;

import com.sotatek.ordermanagement.dto.request.UpdateInventoryRequest;
import com.sotatek.ordermanagement.dto.response.InventoryDetailsResponse;
import com.sotatek.ordermanagement.dto.response.ProductDetailsResponse;
import com.sotatek.ordermanagement.entity.Inventory;

import java.util.List;

public interface InventoryService {
    public void initInventory(long productId);
    public InventoryDetailsResponse updateInventory(long productId, UpdateInventoryRequest request);
    public Inventory getInventoryByProductIdOrFailed(long productId);
    public List<ProductDetailsResponse> getListProductQtyLessOrEqualThan3();
}
