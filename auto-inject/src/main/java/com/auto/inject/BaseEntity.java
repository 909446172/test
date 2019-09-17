package com.auto.inject;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
  @Id private String id;
  @Field @CreatedBy private String createUserId;

  @Field @LastModifiedBy private String updateUserId;

  @Field @CreatedDate private LocalDateTime createTime; // 创建时间

  @Field @LastModifiedDate private LocalDateTime updateTime; // 修改时间
}