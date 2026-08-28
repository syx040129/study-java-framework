package com.atguigu.liteims.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("sale_order")
@Schema(name = "SaleOrder", description = "销售订单信息")
public class SaleOrder {

    @Schema(name = "id", title = "主键ID", description = "自动生成的订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(name = "orderNo", title = "订单编号", description = "用户可见的唯一订单号", requiredMode = Schema.RequiredMode.REQUIRED, example = "SO202405280001")
    private String orderNo; //订单号

    @Schema(name = "customerId", title = "客户ID", description = "关联的客户主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5001")
    private Long customerId; //客户ID

    @Schema(name = "userId", title = "系统用户ID", description = "操作员工的系统用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Long userId; //系统用户ID

    @Schema(name = "totalAmount", title = "总金额", description = "订单商品总金额（单位：元）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2999.00")
    private BigDecimal totalAmount; //订单总金额

    @Schema(name = "status", title = "订单状态", description = "0-待处理, 1-已完成, 2-已取消", allowableValues = {"0", "1", "2"}, example = "1")
    private Integer status; // 0-待处理, 1-已完成, 2-已取消

    @Schema(name = "remark", title = "备注", description = "订单相关备注说明", example = "紧急订单，请优先处理")
    private String remark; //备注

    @Schema(name = "createTime", title = "创建时间", description = "订单记录创建时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime createTime; //创建时间

    @Schema(name = "updateTime", title = "更新时间", description = "订单最后更新时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime updateTime; //修改时间

    @Schema(name = "isDeleted", title = "逻辑删除", description = "软删除标记：0-正常，1-已删除", hidden = true)
    @TableLogic
    private Integer isDeleted; //逻辑删除：0-正常，1-已删除
}
