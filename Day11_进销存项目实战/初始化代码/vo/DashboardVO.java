package com.atguigu.liteims.vo;

import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

//VO： Value Object  or   View Object 值对象或视图对象：用于封装页面加载数据。
@Data
@Schema(name = "DashboardVO", description = "仪表盘数据视图对象") // 类级别描述
public class DashboardVO {

    @Schema(name = "productCount", title = "商品总量", description = "当前系统中所有商品的总数量", example = "1500")
    private Long productCount; //商品数量

    @Schema(name = "todayOrderCount", title = "今日订单量", description = "当天新产生的订单总数", example = "42")
    private Long todayOrderCount; //今日订单数量

    @Schema(name = "totalSales", title = "总销售额", description = "当日累计成交金额（单位：元）", example = "89999.00")
    private BigDecimal totalSales; //总销售额
}
