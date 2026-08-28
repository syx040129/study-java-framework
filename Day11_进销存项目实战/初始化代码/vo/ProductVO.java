package com.atguigu.liteims.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(name = "ProductVO", description = "商品信息视图对象") // 类级别描述
public class ProductVO {

    @Schema(name = "id", title = "主键ID", description = "商品唯一标识符", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    private Long id; //商品主键

    @Schema(name = "categoryId", title = "分类ID", description = "所属商品分类的主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5001")
    private Long categoryId; //分类ID

    @Schema(name = "categoryName", title = "分类名称", description = "所属分类的名称（冗余字段用于展示）", example = "智能手机")
    private String categoryName; //分类名称

    @Schema(name = "name", title = "商品名称", description = "商品的展示名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "iPhone 15 Pro Max")
    private String name; //商品名称

    @Schema(name = "price", title = "价格", description = "当前销售单价（单位：元）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8999.00")
    private BigDecimal price; //价格

    @Schema(name = "stock", title = "库存", description = "当前可用库存数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100", minimum = "0")
    private Integer stock; //库存

    @Schema(name = "status", title = "状态", description = "商品上下架状态：0=下架，1=上架", allowableValues = {"0", "1"}, example = "1")
    private Integer status; //状态

    @Schema(name = "createTime", title = "创建时间", description = "商品记录创建时间", readOnly = true, example = "2024-05-28 15:30:00")
    private LocalDateTime createTime; //创建时间
}
