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
@TableName("category")
@Schema(name = "Category", description = "商品分类信息管理") // 类级别描述
public class Category {

    @Schema(name = "id", title = "主键ID", description = "系统自动生成的唯一分类标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(name = "name", title = "分类名称", description = "分类的显示名称（如：电子产品、服装鞋帽）", requiredMode = Schema.RequiredMode.REQUIRED, example = "智能手机")
    private String name;

    @Schema(name = "sort", title = "排序值", description = "分类在前端展示的顺序（数值越小越靠前）", example = "10")
    private Integer sort;

    @Schema(name = "description", title = "分类描述", description = "分类的详细说明或备注信息", example = "包含各类品牌智能手机及配件")
    private String description;

    @Schema(name = "status", title = "状态", description = "分类上下架状态：0-下架，1-上架", allowableValues = {"0", "1"}, example = "1")
    private Integer status;

    @Schema(name = "createTime", title = "创建时间", description = "分类记录首次创建时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime createTime;

    @Schema(name = "updateTime", title = "更新时间", description = "分类信息最后修改时间", readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private LocalDateTime updateTime;

    @Schema(name = "isDeleted", title = "逻辑删除", description = "软删除标记：0=正常，1=已删除", hidden = true)
    @TableLogic
    private Integer isDeleted;
}
