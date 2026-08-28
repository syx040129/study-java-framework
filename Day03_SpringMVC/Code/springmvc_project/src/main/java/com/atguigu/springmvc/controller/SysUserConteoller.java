package com.atguigu.springmvc.controller;

import com.atguigu.springmvc.entities.SysUser;
import com.atguigu.springmvc.util.SysUserDataUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class SysUserConteoller {

    @GetMapping("list")
    public List<SysUser> findSysUser(SysUser sysUser){
        System.out.println("username"+sysUser.getUsername()+"phone"+sysUser.getPhone()+"statys"+sysUser.getStatus());
        List<SysUser>sysUserList= SysUserDataUtil.listUsers(sysUser.getUsername(),sysUser.getPhone(),sysUser.getStatus());
        return sysUserList;
    }

    @GetMapping("/data")
    @ResponseBody
    public Object paramFormWithDefault(
            @RequestParam("name") String name,  // 默认required=true，必传
            @RequestParam(value = "stuAge", required = true, defaultValue = "18") int age
    ) {
        System.out.println("name = " + name + ", age = " + age);
        return name + "：" + age;
    }
}
