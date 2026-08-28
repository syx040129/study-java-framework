package com.atguigu.springmvc.controller;

import com.atguigu.springmvc.entities.SysUser;
import com.atguigu.springmvc.util.SysUserDataUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("demo")
public class SysUserController {
    // 需求1：查询用户列表接口实践&体验
    @GetMapping("list")
    public List<SysUser>getUserList(String username,String phone,Integer status){
        System.out.println("===== 用户列表查询 =====");
        System.out.println("查询条件：username = " + username + ", phone = " + phone + ", status = " + status);

        List<SysUser> sysUserList = SysUserDataUtil.listUsers(username, phone, status);
        System.out.println("查询结果条数：" + sysUserList.size());
        return sysUserList;
    }

    //需求2：根据id查询用户详情接口实现&路径参数
    @GetMapping("{id}")
    public SysUser findById(@PathVariable Long id){
        SysUser sysUser = SysUserDataUtil.getUserById(id);
        System.out.println("根据ID查询用户详情：" + sysUser);
        return sysUser;
    }

    //需求3：新增用户信息接口
    @PostMapping("user")
    public SysUser saveUser(@RequestBody SysUser user){
        System.out.println("接收的JSON参数：" + user);
        // 调用模拟工具类保存用户（自动生成ID、创建/更新时间）
        SysUserDataUtil.saveUser(user);
        System.out.println("返回的完整用户数据：" + user);
        // 返回新增后的完整用户信息（自动转为JSON响应给前端）
        return user;
    }

    //需求4：根据id更新用户信息接口实现
    @PutMapping("{id}")
    public SysUser updateUser(@PathVariable Long id,@RequestBody SysUser sysUser){
        SysUserDataUtil.updateUserById(id,sysUser);
        return sysUser;
    }

    //需求5：根据id删除用户信息接口实现
    @DeleteMapping("{id}")
    public Map<String,Object> removeUser(@PathVariable Long id) {
        Map<String,Object> map = new HashMap<String,Object>();
        boolean deleted = SysUserDataUtil.deleteUserById(id);
        map.put("success", deleted);
        map.put("message", (deleted?"删除id为%d用户成功过！":"删除id为%s用户失败").formatted(id));
        return map;
    }

}
