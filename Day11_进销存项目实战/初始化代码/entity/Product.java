package com.atguigu.liteims.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("product")
@Schema(name = "Product", description = "商品信息实体类") // 类级别描述
public class Product {

    @Schema(name = "id", title = "主键ID", description = "自动生成的商品唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(name = "categoryId", title = "分类ID", description = "商品所属分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    private Long categoryId; //分类ID

    @Schema(name = "name", title = "商品名称", description = "商品展示名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "华为Mate60 Pro")
    private String name; //产品名称

    @Schema(name = "price", title = "价格", description = "商品销售价格（单位：元）", requiredMode = Schema.RequiredMode.REQUIRED, example = "5999.00")
    private BigDecimal price; //产品价格

    @Schema(name = "stock", title = "库存数量", description = "当前可用库存", example = "100")
    private Integer stock; //库存数量

    @Schema(name = "status", title = "状态", description = "商品上下架状态：1-上架，0-下架", allowableValues = {"0", "1"}, example = "1")
    private Integer status; // 1-上架, 0-下架

    @Schema(name = "createTime", title = "创建时间", description = "记录创建时间（不参与序列化）", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @JsonIgnore
    private LocalDateTime createTime; //创建时间

    @Schema(name = "updateTime", title = "更新时间", description = "记录最后更新时间（不参与序列化）", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @JsonIgnore
    private LocalDateTime updateTime; //修改时间

    @Schema(name = "isDeleted", title = "逻辑删除", description = "软删除标记：0-正常，1-已删除", hidden = true)
    @TableLogic
    private Integer isDeleted; //逻辑删除：0-正常，1-已删除
}

