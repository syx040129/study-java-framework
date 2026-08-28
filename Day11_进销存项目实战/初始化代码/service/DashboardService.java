package com.atguigu.liteims.service;


import com.atguigu.liteims.vo.DashboardVO;

public interface DashboardService {
    /**
     * 仪表盘统计
     * @return  包含：商品数量，今日订单数量，总订单金额
     */
    DashboardVO getStats();
}
