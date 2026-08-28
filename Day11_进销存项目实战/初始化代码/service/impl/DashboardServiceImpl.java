package com.atguigu.liteims.service.impl;

import com.atguigu.liteims.entity.SaleOrder;
import com.atguigu.liteims.mapper.ProductMapper;
import com.atguigu.liteims.mapper.SaleOrderMapper;
import com.atguigu.liteims.service.DashboardService;
import com.atguigu.liteims.vo.DashboardVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class DashboardServiceImpl implements DashboardService {

    //Dashboard不是一个实体类，没有对应的表和Mapper接口，所以不能继承ServiceImpl<M,E>

    @Autowired
    ProductMapper productMapper;

    @Autowired
    SaleOrderMapper saleOrderMapper;

    @Override
    public DashboardVO getStats() {
        DashboardVO dashboardVO = new DashboardVO();

        //1.封装商品总数量
        Long productCount = productMapper.selectCount(null);
        dashboardVO.setProductCount(productCount); //is_delete=0

        //2.封装今日订单数量
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        LambdaQueryWrapper<SaleOrder> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.between(SaleOrder::getCreateTime, start, end);
        Long orderCount = saleOrderMapper.selectCount(queryWrapper); //自动增加  is_delete=0

        //3.封装订单总金额
        BigDecimal totalSeles = saleOrderMapper.getTotalSales();
        dashboardVO.setTotalSales(totalSeles);

        return dashboardVO;
    }
}
