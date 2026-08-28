package com.atguigu.springmvc.util;

import com.atguigu.springmvc.entities.SysUser;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用户内存数据工具类（模拟数据库CRUD操作，用于SpringMVC参数接收讲解）
 * 核心设计：
 * 1. 静态HashMap存储用户数据（模拟数据库表）
 * 2. 原子类AtomicLong生成自增ID（模拟数据库自增主键，保证线程安全）
 * 3. 静态代码块初始化测试数据（项目启动时自动执行）
 * 4. 封装5个核心方法，覆盖查询/新增/更新/删除场景
 */
public class SysUserDataUtil {

    // 1. 静态Map：Key=用户ID，Value=用户对象（模拟数据库表存储）
    private static final Map<Long, SysUser> USER_MAP = new HashMap<>();

    // 2. 原子类：生成自增ID（线程安全，替代数据库自增主键）
    private static final AtomicLong AUTO_INCREMENT_ID = new AtomicLong(1);

    // 3. 静态代码块：项目启动时初始化3条测试数据
    static {
        // 初始化用户1：zhangsan（状态正常）
        SysUser user1 = new SysUser();
        user1.setId(AUTO_INCREMENT_ID.getAndIncrement()); // ID=1
        user1.setUsername("zhangsan");
        user1.setPassword("$2a$10$xxxxxx"); // 模拟BCrypt加密后的密码
        user1.setPhone("13800138000");
        user1.setAvatar("https://example.com/avatar/1.jpg");
        user1.setStatus(1); // 正常状态
        user1.setRoleId(1L);
        user1.setCreateTime(new Date());
        user1.setUpdateTime(new Date());

        // 初始化用户2：lisi（状态禁用）
        SysUser user2 = new SysUser();
        user2.setId(AUTO_INCREMENT_ID.getAndIncrement()); // ID=2
        user2.setUsername("lisi");
        user2.setPassword("$2a$10$yyyyyy");
        user2.setPhone("13900139000");
        user2.setAvatar("https://example.com/avatar/2.jpg");
        user2.setStatus(0); // 禁用状态
        user2.setRoleId(2L);
        user2.setCreateTime(new Date());
        user2.setUpdateTime(new Date());

        // 初始化用户3：zhangsan888（状态正常，无头像）
        SysUser user3 = new SysUser();
        user3.setId(AUTO_INCREMENT_ID.getAndIncrement()); // ID=3
        user3.setUsername("zhangsan888");
        user3.setPassword("$2a$10$zzzzzz");
        user3.setPhone("13700137000");
        user3.setAvatar(null); // 无头像
        user3.setStatus(1); // 正常状态
        user3.setRoleId(1L);
        user3.setCreateTime(new Date());
        user3.setUpdateTime(new Date());

        // 将测试数据存入Map
        USER_MAP.put(user1.getId(), user1);
        USER_MAP.put(user2.getId(), user2);
        USER_MAP.put(user3.getId(), user3);
    }

    // ========== 核心业务方法（模拟数据库CRUD） ==========

    /**
     * 条件查询用户列表
     * 支持：username模糊匹配、phone精确匹配、status精确匹配（参数可为null，null则不参与筛选）
     * @param username 用户名（模糊匹配）
     * @param phone 手机号（精确匹配）
     * @param status 账号状态（0-禁用/1-正常，精确匹配）
     * @return 符合条件的用户列表
     */
    public static List<SysUser> listUsers(String username, String phone, Integer status) {
        List<SysUser> resultList = new ArrayList<>();
        // 遍历所有用户，按条件筛选
        for (SysUser user : USER_MAP.values()) {
            // 1. 用户名模糊匹配（非空时筛选）
            if (username != null && !username.isEmpty() && !user.getUsername().contains(username)) {
                continue;
            }
            // 2. 手机号精确匹配（非空时筛选）
            if (phone != null && !phone.isEmpty() && !phone.equals(user.getPhone())) {
                continue;
            }
            // 3. 状态精确匹配（非空时筛选）
            if (status != null && !status.equals(user.getStatus())) {
                continue;
            }
            // 符合所有条件，加入结果集
            resultList.add(user);
        }
        return resultList;
    }

    /**
     * 根据ID查询用户详情
     * @param id 用户唯一ID
     * @return 对应ID的用户对象（null表示用户不存在）
     */
    public static SysUser getUserById(Long id) {
        return USER_MAP.get(id);
    }

    /**
     * 根据ID删除用户
     * @param id 用户唯一ID
     * @return true=删除成功，false=用户不存在
     */
    public static boolean deleteUserById(Long id) {
        if (USER_MAP.containsKey(id)) {
            USER_MAP.remove(id);
            return true;
        }
        return false;
    }

    /**
     * 根据ID更新用户（仅更新非null字段，模拟“部分更新”场景）
     * @param id 待更新用户的ID
     * @param updateUser 待更新的字段（非null字段才生效）
     * @return true=更新成功，false=用户不存在
     */
    public static boolean updateUserById(Long id, SysUser updateUser) {
        // 校验用户是否存在
        if (!USER_MAP.containsKey(id)) {
            return false;
        }
        // 获取原用户对象，仅更新非null字段
        SysUser oldUser = USER_MAP.get(id);
        if (updateUser.getUsername() != null) oldUser.setUsername(updateUser.getUsername());
        if (updateUser.getPassword() != null) oldUser.setPassword(updateUser.getPassword());
        if (updateUser.getPhone() != null) oldUser.setPhone(updateUser.getPhone());
        if (updateUser.getAvatar() != null) oldUser.setAvatar(updateUser.getAvatar());
        if (updateUser.getStatus() != null) oldUser.setStatus(updateUser.getStatus());
        if (updateUser.getRoleId() != null) oldUser.setRoleId(updateUser.getRoleId());
        oldUser.setUpdateTime(new Date()); // 刷新更新时间

        // 把更新后的用户放回Map
        USER_MAP.put(id, oldUser);
        return true;
    }

    /**
     * 新增用户（自动生成ID和时间字段）
     * @param user 待新增的用户对象（无需设置ID，自动生成）
     * @return 新增后的用户对象（含自动生成的ID）
     */
    public static SysUser saveUser(SysUser user) {
        // 生成自增ID
        Long newId = AUTO_INCREMENT_ID.getAndIncrement();
        // 补全必要字段
        user.setId(newId);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        // 默认状态：未设置则为正常（1）
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
        // 存入Map
        USER_MAP.put(newId, user);
        return user;
    }

    // 测试方法：验证工具类核心功能（可选，运行main方法即可测试）
    public static void main(String[] args) {
        // 1. 测试条件查询：查询用户名含"zhangsan"、状态为1的用户（预期2条）
        List<SysUser> list = SysUserDataUtil.listUsers("zhangsan", null, 1);
        System.out.println("条件查询结果条数：" + list.size());

        // 2. 测试查详情：查询ID=1的用户（预期username=zhangsan）
        SysUser user = SysUserDataUtil.getUserById(1L);
        System.out.println("ID=1的用户名：" + user.getUsername());

        // 3. 测试新增：新增wangwu用户（预期ID=4）
        SysUser newUser = new SysUser();
        newUser.setUsername("wangwu");
        newUser.setPassword("$2a$10$aaaaaa");
        newUser.setPhone("13600136000");
        SysUser savedUser = SysUserDataUtil.saveUser(newUser);
        System.out.println("新增用户ID：" + savedUser.getId());

        // 4. 测试更新：将ID=4的用户状态改为0（预期更新成功）
        SysUser updateUser = new SysUser();
        updateUser.setStatus(0);
        boolean updateFlag = SysUserDataUtil.updateUserById(4L, updateUser);
        System.out.println("更新是否成功：" + updateFlag);

        // 5. 测试删除：删除ID=4的用户（预期删除成功）
        boolean deleteFlag = SysUserDataUtil.deleteUserById(4L);
        System.out.println("删除是否成功：" + deleteFlag);
    }
}