package com.atguigu.liteims.dto;

import lombok.Data;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

// DTO : 接口开发：  用于调用接口时接收数据。

@Data
@Schema(name = "OrderDTO", description = "订单数据传输对象") // 类级别描述
public class OrderDTO {

    @Schema(name = "customerId", title = "客户ID", description = "下单客户的主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5001")
    private Long customerId; //客户ID

    @Schema(name = "userId", title = "系统用户ID", description = "操作员工的系统用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Long userId; //系统用户ID

    @Schema(name = "items", title = "订单明细", description = "订单包含的商品项列表", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<OrderItemDTO> items;

    @Data
    @Schema(name = "OrderItemDTO", description = "订单明细项数据传输对象") // 内部类级别描述
    public static class OrderItemDTO {

        @Schema(name = "productId", title = "产品ID", description = "购买商品的主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3001")
        private Long productId;

        @Schema(name = "quantity", title = "数量", description = "购买商品的数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2", maximum = "9999", minimum = "1")
        private Integer quantity;
    }
}

