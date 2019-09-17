package com.demo.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ApiModel("关注")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document
@CompoundIndexes({
        @CompoundIndex(def = "{'follower.$id': 1, 'followed.$id': 1}", unique = true),
        @CompoundIndex(def = "{'follower.$id': 1, 'created_date': -1}"),
        @CompoundIndex(def = "{'followed.$id': 1, 'created_date': -1}")
})
public class Following {
    @ApiModelProperty(value = "标识", readOnly = true)
    private String id;

    @ApiModelProperty(position = 1, value = "关注者", required = true)
    @NonNull
    @DBRef
    private Ghost follower;

    @ApiModelProperty(position = 2, value = "被关注者", required = true)
    @NonNull
    @DBRef
    private Ghost followed;

    @ApiModelProperty(position = 3, value = "是否相互关注", readOnly = true)
    @NonNull
    private Boolean bidirectional;

    @ApiModelProperty(position = 4, value = "创建日期时间", readOnly = true)
    @CreatedDate
    private Date createdDate;

    @ApiModelProperty(position = 5, value = "最后修改日期时间")
    @JsonIgnore
    @LastModifiedDate
    private Date lastModifiedDate;

    @ApiModelProperty(position = 6, value = "最后每日刷新日期")
    @JsonIgnore
    private Date lastRefreshDate;
}
