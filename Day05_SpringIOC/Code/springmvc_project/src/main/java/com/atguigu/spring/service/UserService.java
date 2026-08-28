package com.atguigu.spring.service;

import com.atguigu.spring.entity.SysUser;
import java.util.List;

public interface UserService {
    boolean removeUserById(Long id);
    boolean updateUserById(Long id, SysUser sysUser);
    SysUser saveUser(SysUser sysUser);
    SysUser getUserById(Long id);
    List<SysUser> getUserList(String username, String phone, Integer status);
    boolean login(String username, String password);
}