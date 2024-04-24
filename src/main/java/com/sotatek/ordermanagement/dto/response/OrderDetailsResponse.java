package com.sotatek.ordermanagement.dto.response;


import com.sotatek.ordermanagement.entity.Order;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderDetailsResponse {
    List<LineOrderDetailsResponse> lineOrders;
    Double totalMoney;
    Date issueDate;

    public static OrderDetailsResponse from(Order order) {
        return OrderDetailsResponse.builder()
                .lineOrders(
                        order.getLineOrders().stream().map(LineOrderDetailsResponse::from).toList())
                .totalMoney(order.getTotalMoney())
                .issueDate(order.getIssueDate())
                .build();
    }
}
