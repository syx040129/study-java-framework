package com.atguigu.liteims.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//DTO 用于接口调用，参数接收
//用于登录
@Data
@Schema(name = "SysUserDTO", description = "用于封装登录表单")
public class SysUserDTO {
    @Schema(name = "username", title = "用户名", description = "登录使用的用户名（唯一）", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    private String username; //用户名称

    @Schema(name = "password", title = "密码", description = "加密后的用户密码", writeOnly = true, requiredMode = Schema.RequiredMode.REQUIRED, example = "e10adc3949ba59abbe56e057f20f883e")
    private String password; //密码
}
