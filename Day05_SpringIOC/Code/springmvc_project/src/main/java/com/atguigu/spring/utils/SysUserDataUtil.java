package com.atguigu.spring.utils;

import com.atguigu.spring.entity.SysUser;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class SysUserDataUtil {
    private static final Map<Long, SysUser> USER_MAP = new HashMap<>();
    private static final AtomicLong AUTO_INCREMENT_ID = new AtomicLong(1);

    static {
        SysUser user1 = new SysUser();
        user1.setId(AUTO_INCREMENT_ID.getAndIncrement());
        user1.setUsername("zhangsan");
        user1.setPassword("123456");
        user1.setPhone("13800138000");
        user1.setAvatar("https://example.com/avatar/1.jpg");
        user1.setStatus(1);
        user1.setRoleId(1L);
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());

        SysUser user2 = new SysUser();
        user2.setId(AUTO_INCREMENT_ID.getAndIncrement());
        user2.setUsername("lisi");
        user2.setPassword("123456");
        user2.setPhone("13900139000");
        user2.setAvatar("https://example.com/avatar/2.jpg");
        user2.setStatus(0);
        user2.setRoleId(2L);
        user2.setCreateTime(new Date());
        user2.setUpdateTime(new Date());

        USER_MAP.put(user1.getId(), user1);
        USER_MAP.put(user2.getId(), user2);
    }

    public static List<SysUser> listUsers(String username, String phone, Integer status) {
        List<SysUser> resultList = new ArrayList<>();
        for (SysUser user : USER_MAP.values()) {
            if (username != null && !username.isEmpty() && !user.getUsername().contains(username)) continue;
            if (phone != null && !phone.isEmpty() && !phone.equals(user.getPhone())) continue;
            if (status != null && !status.equals(user.getStatus())) continue;
            resultList.add(user);
        }
        return resultList;
    }

    public static SysUser getUserById(Long id) {
        return USER_MAP.get(id);
    }

    public static boolean deleteUserById(Long id) {
        return USER_MAP.remove(id) != null;
    }

    public static boolean updateUserById(Long id, SysUser updateUser) {
        if (!USER_MAP.containsKey(id)) return false;
        SysUser oldUser = USER_MAP.get(id);
        if (updateUser.getUsername() != null) oldUser.setUsername(updateUser.getUsername());
        if (updateUser.getPhone() != null) oldUser.setPhone(updateUser.getPhone());
        if (updateUser.getAvatar() != null) oldUser.setAvatar(updateUser.getAvatar());
        if (updateUser.getStatus() != null) oldUser.setStatus(updateUser.getStatus());
        if (updateUser.getRoleId() != null) oldUser.setRoleId(updateUser.getRoleId());
        oldUser.setUpdateTime(new Date());
        USER_MAP.put(id, oldUser);
        return true;
    }

    public static SysUser saveUser(SysUser user) {
        Long newId = AUTO_INCREMENT_ID.getAndIncrement();
        user.setId(newId);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        if (user.getStatus() == null) user.setStatus(1);
        if (user.getPassword() == null) user.setPassword("123456");
        USER_MAP.put(newId, user);
        return user;
    }

    public static boolean login(String username, String password) {
        if (username == null || password == null) return false;
        for (SysUser user : USER_MAP.values()) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}