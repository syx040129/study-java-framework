package com.atguigu.spring.service;

import com.atguigu.spring.dao.UserDao;
import com.atguigu.spring.entity.SysUser;
import com.atguigu.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean removeUserById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public boolean updateUserById(Long id, SysUser sysUser) {
        if (userDao.getById(id) == null) return false;
        return userDao.updateById(id, sysUser);
    }

    @Override
    public SysUser saveUser(SysUser sysUser) {
        return userDao.save(sysUser);
    }

    @Override
    public SysUser getUserById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<SysUser> getUserList(String username, String phone, Integer status) {
        return userDao.list(username, phone, status);
    }

    @Override
    public boolean login(String username, String password) {
        return userDao.login(username, password);
    }
}