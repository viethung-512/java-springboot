package com.sotatek.ordermanagement.dto.request;


import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UpdateInventoryRequest {
    @Min(0)
    long stockQuantity;
}
