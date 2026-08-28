package com.atguigu.liteims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
@Schema(name = "SysUser", description = "系统用户信息")
public class SysUser { //系统用户

    @Schema(name = "id", title = "主键ID", description = "自动生成的用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(name = "username", title = "用户名", description = "登录使用的用户名（唯一）", requiredMode = Schema.RequiredMode.REQUIRED, example = "admin")
    private String username; //用户名称

    @Schema(name = "password", title = "密码", description = "加密后的用户密码", writeOnly = true, example = "e10adc3949ba59abbe56e057f20f883e")
    private String password; //密码

    @Schema(name = "nickname", title = "昵称", description = "用户显示用的昵称", example = "超级管理员")
    private String nickname; //昵称

    @Schema(name = "avatar", title = "头像地址", description = "用户头像的URL地址", example = "https://example.com/avatar.jpg")
    private String avatar; //头像地址

    @Schema(name = "email", title = "邮箱", description = "用户的电子邮箱地址", example = "admin@example.com")
    private String email; //电子邮件

    @Schema(name = "createTime", title = "创建时间", description = "用户账号创建时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime createTime; //创建时间

    @Schema(name = "updateTime", title = "更新时间", description = "用户信息最后更新时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime updateTime; //修改时间

    @Schema(name = "isDeleted", title = "逻辑删除", description = "软删除标记：0-正常，1-已删除", hidden = true)
    @TableLogic
    private Integer isDeleted; //逻辑删除：0-正常，1-已删除
}
