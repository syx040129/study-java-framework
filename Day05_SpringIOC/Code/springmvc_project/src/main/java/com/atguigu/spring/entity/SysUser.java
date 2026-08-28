package com.atguigu.spring.entity;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;

@Data
public class SysUser {
    private Long id;
    private String password;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度需在2-20位之间")
    private String username;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误（需为11位合法手机号）")
    private String phone;

    private String avatar;

    @Min(value = 0, message = "用户状态仅支持0（禁用）或1（正常）")
    @Max(value = 1, message = "用户状态仅支持0（禁用）或1（正常）")
    private Integer status = 1;

    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    private Date createTime;
    private Date updateTime;
}