package com.atguigu.liteims.service.impl;

import com.atguigu.liteims.entity.Customer;
import com.atguigu.liteims.mapper.CustomerMapper;
import com.atguigu.liteims.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
}
