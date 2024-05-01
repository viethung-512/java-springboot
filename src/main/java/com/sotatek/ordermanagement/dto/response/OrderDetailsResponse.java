package com.sotatek.ordermanagement.dto.response;


import com.sotatek.ordermanagement.entity.LineOrder;
import com.sotatek.ordermanagement.entity.Order;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderDetailsResponse {
    Long id;
    Double totalMoney;
    Date issueDate;
    List<LineOrder> lineOrders;

    public static OrderDetailsResponse from(final Order order) {
        return OrderDetailsResponse.builder()
                .id(order.getId())
                .totalMoney(order.getTotalMoney())
                .issueDate(order.getIssueDate())
                .build();
    }

    public static OrderDetailsResponse from(final Order order, final List<LineOrder> lineOrders) {
        return OrderDetailsResponse.builder()
                .id(order.getId())
                .totalMoney(order.getTotalMoney())
                .issueDate(order.getIssueDate())
                .lineOrders(lineOrders)
                .build();
    }
}
