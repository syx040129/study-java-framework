package com.atguigu.liteims.service.impl;

import com.atguigu.liteims.dto.SysUserDTO;
import com.atguigu.liteims.entity.SysUser;
import com.atguigu.liteims.mapper.SysUserMapper;
import com.atguigu.liteims.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser login(SysUserDTO sysUserDTO) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>();
        queryWrapper.eq(SysUser::getUsername, sysUserDTO.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }
}
