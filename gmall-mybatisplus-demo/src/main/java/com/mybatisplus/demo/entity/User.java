package com.mybatisplus.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    @TableId(type = IdType.AUTO)  //自增策略;
    private Long id;
    private String name;
    private Integer age;
    private String email;

    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
        //@TableField(fill = FieldFill.UPDATE)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    //逻辑删除
    @TableLogic
    @TableField(value = "is_deleted")
    private Integer deleted;
}