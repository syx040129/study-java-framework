package com.atguigu.spring.controller;

import com.atguigu.spring.entity.SysUserLoginDto;
import com.atguigu.spring.service.UserService;
import com.atguigu.spring.utils.Result;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(HttpSession session, @Valid @RequestBody SysUserLoginDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(500, "账号和密码格式不符合要求！");
        }
        boolean logined = userService.login(userDto.getUsername(), userDto.getPassword());
        if (!logined) {
            return Result.fail("账号或密码错误！");
        }
        session.setAttribute("username", userDto.getUsername());
        return Result.success("登录成功！");
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("username");
        session.invalidate();
        return Result.success("退出登录成功！");
    }
}