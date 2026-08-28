package com.atguigu.liteims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("sale_order_item")
@Schema(name = "SaleOrderItem", description = "销售订单明细信息")
public class SaleOrderItem {

    @Schema(name = "id", title = "主键ID", description = "自动生成的明细记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(name = "orderId", title = "订单ID", description = "所属订单的主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    private Long orderId; //订单ID

    @Schema(name = "productId", title = "产品ID", description = "购买商品的主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3001")
    private Long productId; //产品ID

    @Schema(name = "quantity", title = "数量", description = "购买商品的数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Integer quantity; //数量

    @Schema(name = "price", title = "单价", description = "下单时的商品单价（单位：元）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1999.50")
    private BigDecimal price; //单价

    @Schema(name = "amount", title = "小计金额", description = "该商品的小计金额（单价 × 数量）", requiredMode = Schema.RequiredMode.REQUIRED, example = "3999.00")
    private BigDecimal amount; //小计金额

    @Schema(name = "createTime", title = "创建时间", description = "明细记录创建时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime createTime; //创建时间

    @Schema(name = "updateTime", title = "更新时间", description = "明细记录最后更新时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime updateTime; //修改时间

    @Schema(name = "isDeleted", title = "逻辑删除", description = "软删除标记：0-正常，1-已删除", hidden = true)
    @TableLogic
    private Integer isDeleted; //逻辑删除：0-正常，1-已删除
}
