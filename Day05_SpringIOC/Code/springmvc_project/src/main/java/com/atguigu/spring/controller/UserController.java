package com.atguigu.spring.controller;

import com.atguigu.spring.entity.SysUser;
import com.atguigu.spring.service.UserService;
import com.atguigu.spring.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result getUserList(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer status) {
        List<SysUser> list = userService.getUserList(username, phone, status);
        return Result.success("查询成功！共找到" + list.size() + "条数据", list);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        SysUser user = userService.getUserById(id);
        if (user == null) {
            return Result.fail("查询失败：用户不存在！");
        }
        return Result.success("查询成功！", user);
    }

    @PostMapping
    public Result saveUser(@Valid @RequestBody SysUser user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + "：" + e.getDefaultMessage())
                    .collect(Collectors.joining("；"));
            return Result.fail(500, "参数校验失败：" + errorMsg);
        }
        SysUser savedUser = userService.saveUser(user);
        return Result.success("新增用户成功！", savedUser);
    }

    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Long id, @Valid @RequestBody SysUser user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldErrors().stream()
                    .map(e -> e.getField() + "：" + e.getDefaultMessage())
                    .collect(Collectors.joining("；"));
            return Result.fail(500, "参数校验失败：" + errorMsg);
        }
        boolean updated = userService.updateUserById(id, user);
        return updated ? Result.success("更新成功！") : Result.fail("更新失败：用户不存在！");
    }

    @DeleteMapping("/{id}")
    public Result removeUser(@PathVariable Long id) {
        boolean deleted = userService.removeUserById(id);
        return deleted ? Result.success("删除成功！") : Result.fail("删除失败：用户不存在！");
    }
}