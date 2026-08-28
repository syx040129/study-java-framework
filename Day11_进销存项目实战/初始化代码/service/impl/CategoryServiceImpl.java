package com.atguigu.liteims.service.impl;

import com.atguigu.liteims.entity.Category;
import com.atguigu.liteims.mapper.CategoryMapper;
import com.atguigu.liteims.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
