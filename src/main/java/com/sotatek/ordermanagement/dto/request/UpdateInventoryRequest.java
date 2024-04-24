package com.sotatek.ordermanagement.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpdateInventoryRequest {
    long stockQuantity;
}
