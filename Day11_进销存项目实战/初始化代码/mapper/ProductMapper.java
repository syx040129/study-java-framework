package com.atguigu.liteims.mapper;

import com.atguigu.liteims.entity.Product;
import com.atguigu.liteims.vo.ProductVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {


    IPage<ProductVO> findPage(IPage<ProductVO> page, String name, Long categoryId);
}
