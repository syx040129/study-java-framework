package com.atguigu.springmvc.entities;

import lombok.Data;

import java.util.Date;

/**
 * 系统用户实体类（对应sys_user表）
 */
@Data // 自动生成get/set/toString/equals/hashCode等方法，需引入lombok依赖
public class SysUser {
    private Long id;         // 用户唯一ID
    private String username; // 用户名
    private String password; // 密码（BCrypt加密存储）
    private String phone;    // 手机号（唯一）
    private String avatar;   // 用户头像URL
    private Integer status;  // 账号状态：0-禁用，1-正常
    private Long roleId;     // 角色ID（关联角色表）
    private Date createTime; // 创建时间
    private Date updateTime; // 更新时间
}