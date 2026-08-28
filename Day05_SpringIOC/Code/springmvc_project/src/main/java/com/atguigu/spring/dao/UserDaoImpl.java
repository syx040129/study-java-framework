package com.atguigu.spring.dao;

import com.atguigu.spring.dao.UserDao;
import com.atguigu.spring.entity.SysUser;
import com.atguigu.spring.utils.SysUserDataUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public boolean deleteById(Long id) {
        return SysUserDataUtil.deleteUserById(id);
    }

    @Override
    public boolean updateById(Long id, SysUser sysUser) {
        return SysUserDataUtil.updateUserById(id, sysUser);
    }

    @Override
    public SysUser save(SysUser sysUser) {
        return SysUserDataUtil.saveUser(sysUser);
    }

    @Override
    public SysUser getById(Long id) {
        return SysUserDataUtil.getUserById(id);
    }

    @Override
    public List<SysUser> list(String username, String phone, Integer status) {
        return SysUserDataUtil.listUsers(username, phone, status);
    }

    @Override
    public boolean login(String username, String password) {
        return SysUserDataUtil.login(username, password);
    }
}