package com.atguigu.liteims.service.impl;

import com.atguigu.liteims.entity.Product;
import com.atguigu.liteims.mapper.ProductMapper;
import com.atguigu.liteims.service.ProductService;
import com.atguigu.liteims.vo.ProductVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public IPage<ProductVO> findPage(Integer page, Integer size, String name, Long categoryId) {
        return productMapper.findPage(new Page<>(page, size),name,categoryId);
    }
}
