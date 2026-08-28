package com.atguigu.liteims.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ColumnWidth(20) // Excel全局列宽
@Schema(name = "OrderExportVO", description = "订单导出视图对象（用于Excel生成）") // 类级别说明
public class OrderExportVO {

    @ExcelProperty("订单号")
    @Schema(name = "orderNo", title = "订单编号", description = "系统生成的唯一订单标识符", example = "SO202405280001")
    private String orderNo;

    @ExcelProperty("客户名称")
    @Schema(name = "customerName", title = "客户姓名", description = "下单客户的公司名称或个人姓名", example = "某某科技有限公司")
    private String customerName;

    @ExcelProperty("订单金额")
    @Schema(name = "totalAmount", title = "总金额", description = "订单商品总金额（单位：元）", example = "29999.00")
    private BigDecimal totalAmount;

    @ExcelProperty("订单状态")
    @Schema(name = "statusStr", title = "订单状态", description = "当前订单的状态文本描述（如：已完成、待处理等）", example = "已完成")
    private String statusStr;

    @ExcelProperty("创建时间")
    @ColumnWidth(25) // 单独设置该列宽度
    @Schema(name = "createTime", title = "创建时间", description = "订单记录的创建时间", example = "2024-05-28 15:30:00")
    private LocalDateTime createTime;
}
