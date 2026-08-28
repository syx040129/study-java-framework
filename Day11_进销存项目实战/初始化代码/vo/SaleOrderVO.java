package com.atguigu.liteims.vo;

import com.atguigu.liteims.entity.SaleOrderItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(name = "SaleOrderVO", description = "销售订单视图对象") // 类级别描述
public class SaleOrderVO {

    @Schema(name = "id", title = "主键ID", description = "系统自动生成的唯一订单标识符", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    private Long id;

    @Schema(name = "orderNo", title = "订单编号", description = "业务单据编号（如：SO202405280001）", requiredMode = Schema.RequiredMode.REQUIRED, example = "SO202405280001")
    private String orderNo; //订单编号

    @Schema(name = "customerId", title = "客户ID", description = "关联的客户主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5001")
    private Long customerId; //客户ID

    @Schema(name = "customerName", title = "客户名称", description = "客户的显示名称", example = "某某科技有限公司")
    private String customerName; //客户名称

    @Schema(name = "userId", title = "用户ID", description = "下单操作员的用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Long userId; //系统用户ID

    @Schema(name = "userName", title = "用户名", description = "下单操作员的姓名", example = "张三")
    private String userName; //系统用户名称

    @Schema(name = "totalAmount", title = "总金额", description = "订单商品总金额（单位：元）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29999.00")
    private BigDecimal totalAmount; //订单总金额

    @Schema(name = "status", title = "状态", description = "订单当前状态：0=待处理，1=已确认，2=已完成，3=已取消", allowableValues = {"0", "1", "2", "3"}, example = "1")
    private Integer status; //订单状态

    @Schema(name = "createTime", title = "创建时间", description = "订单记录的创建时间", readOnly = true, example = "2024-05-28 15:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime createTime; //下单时间

    @Schema(name = "items", title = "订单项列表", description = "包含的商品明细条目", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<SaleOrderItem> items; //订单项集合
}

