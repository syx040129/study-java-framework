package com.atguigu.spring.dao;

import com.atguigu.spring.entity.SysUser;
import java.util.List;

public interface UserDao {
    boolean deleteById(Long id);
    boolean updateById(Long id, SysUser sysUser);
    SysUser save(SysUser sysUser);
    SysUser getById(Long id);
    List<SysUser> list(String username, String phone, Integer status);
    boolean login(String username, String password);
}