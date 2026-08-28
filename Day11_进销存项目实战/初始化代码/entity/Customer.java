package com.atguigu.liteims.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("customer")
@Schema(name = "Customer", description = "客户信息实体") // 类级别描述
public class Customer {

    @Schema(name = "id", title = "主键ID", description = "系统自动生成的唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(name = "name", title = "客户名称", description = "客户公司全称", requiredMode = Schema.RequiredMode.REQUIRED, example = "某某科技有限公司")
    private String name; //某某公司

    @Schema(name = "contact", title = "联系人", description = "主要对接人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    private String contact; //联系人

    @Schema(name = "phone", title = "手机号", description = "联系人手机号码", pattern = "^1[3-9]\\d{9}$", example = "13800138000")
    private String phone; //手机号

    @Schema(name = "email", title = "邮箱", description = "官方电子邮箱", pattern = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)+\\.[A-Za-z]+$", example = "contact@example.com")
    private String email; //电子邮件

    @Schema(name = "address", title = "地址", description = "公司详细办公地址", example = "上海市浦东新区XX路123号")
    private String address; //公司地址

    @Schema(name = "createTime", title = "创建时间", description = "记录创建时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime createTime;

    @Schema(name = "updateTime", title = "更新时间", description = "最后修改时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime updateTime;

    @Schema(name = "isDeleted", title = "逻辑删除", description = "软删除标记（0=正常, 1=已删除）", hidden = true)
    @TableLogic
    @JsonIgnore
    private Integer isDeleted;
}
