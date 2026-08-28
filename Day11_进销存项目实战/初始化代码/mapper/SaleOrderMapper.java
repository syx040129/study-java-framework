package com.atguigu.liteims.mapper;

import com.atguigu.liteims.entity.SaleOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Select;

@Mapper
public interface SaleOrderMapper extends BaseMapper<SaleOrder> {

    /**
     * 获取订单总额度
     * @return
     */
    BigDecimal getTotalSales();
}
