package com.demo.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@ApiModel("小鬼")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document
@CompoundIndexes({
        @CompoundIndex(def = "{'user_id': 1}", unique = true),
        @CompoundIndex(def = "{'location': '2dsphere', 'last_post_date': -1}"),
        @CompoundIndex(def = "{'face_enabled': 1, 'virtual': 1, 'gender': 1, 'location': '2dsphere', 'last_action_date': 1}")
})
public class Ghost {

    @ApiModelProperty(value = "标识", readOnly = true)
    @NonNull
    private String id;

    @ApiModelProperty(position = 1, value = "是否注册", readOnly = true)
    private Boolean enabled = true;

    @ApiModelProperty(position = 2, value = "是否激活脸", readOnly = true)
    private Boolean faceEnabled = false;

    @ApiModelProperty(position = 3, value = "是否客服虚拟的")
    @JsonIgnore
    private Boolean virtual = false;

    @ApiModelProperty(position = 4, value = "用户标识", readOnly = true)
    private String userId;

    @ApiModelProperty(position = 5, value = "云信用户标识", readOnly = true)
    private String imId;

    @ApiModelProperty(position = 6, value = "经纬度", allowableValues = "range[2,2]", example = "[113.943549,22.548181]")
    private List<Double> location;

    @ApiModelProperty(position = 7, value = "出生日期", required = true)
    @NotNull
    private Date birthday;



    @ApiModelProperty(position = 9, value = "昵称", required = true, allowableValues = "range[0,20]")

    private String nickname;

    @ApiModelProperty(position = 10, value = "头像地址", required = true, allowableValues = "range[1,256]")

    private String avatar;

    private String whatIsUp;


    private String industry;


    private String school;

    @ApiModelProperty(position = 14, value = "开启谁能看到我", readOnly = true)
    private Boolean openToEnabled = false;



    @ApiModelProperty(position = 17, value = "最新照片地址", readOnly = true)
    private List<String> album;

    @ApiModelProperty(position = 18, value = "最后发表此刻标识")
    @JsonIgnore
    private String lastPostId;

    @ApiModelProperty(position = 19, value = "最后发表此刻时间")
    @JsonIgnore
    private Date lastPostDate;

    @ApiModelProperty(position = 20, value = "最后活动时间")
    @JsonIgnore
    private Date lastActionDate = new Date();

    @ApiModelProperty(position = 21, value = "每日计数器最后更新日期")
    @JsonIgnore
    private Date lastRollDate = new Date();

    @ApiModelProperty(position = 22, value = "每日心动匹配总数")
    @JsonIgnore
    private Integer dailyTotalMatches = 0;

    @ApiModelProperty(position = 23, value = "每日心动喜欢总数")
    @JsonIgnore
    private Integer dailyTotalLoves = 0;

    @ApiModelProperty(position = 24, value = "创建日期时间")
    @CreatedDate
    private Date createdDate;

    @ApiModelProperty(position = 25, value = "最后修改日期时间")
    @LastModifiedDate
    @JsonIgnore
    private Date lastModifiedDate;

    @ApiModelProperty(position = 26, value = "此刻总数", readOnly = true)
    private Integer totalPosts = 0;

    @ApiModelProperty(position = 27, value = "关注我的总数", readOnly = true)
    private Integer totalFollowers = 0;

    @ApiModelProperty(position = 28, value = "被我关注的总数", readOnly = true)
    private Integer totalFollowings = 0;

    @ApiModelProperty(position = 29, value = "收到的打招呼总数", readOnly = true)
    private Integer totalGreetings = 0;





    @ApiModelProperty(position = 32, value = "脸创建日期时间")
    @JsonIgnore
    private Date faceEnablingDate;

    @ApiModelProperty(position = 33, value = "最后遇见时间")
    @JsonIgnore
    private Date lastEncounterDate;



    @ApiModelProperty(position = 36, value = "奖金池", readOnly = true)
    private Double reward = 0D;

    @ApiModelProperty(position = 37, value = "合约奖金池", readOnly = true)
    private Double totalRewards = 0D;

    @ApiModelProperty(position = 38, value = "注册奖金", readOnly = true)
    @Transient
    private Double registerReward  = 0.1;
    @ApiModelProperty(position = 39, value = "激活脸奖金", readOnly = true)
    @Transient
    private Double faceEnablingReward = 0.1;

    @ApiModelProperty(position = 40, value = "短信邀请奖金", readOnly = true)
    @Transient
    private Double invitationReward = 0.1;



    @ApiModelProperty(position = 42, value = "查询者是否屏蔽被查询者", readOnly = true)
    @Transient
    private Boolean blocked;

    @ApiModelProperty(position = 43, value = "是否不让查询者看被查询者的此刻", readOnly = true)
    @Transient
    private Boolean postHidden;

    @ApiModelProperty(position = 44, value = "查询者是否关注过此被查询者", readOnly = true)
    @Transient
    private Boolean followed = false;

    @ApiModelProperty(position = 45, value = "是否相互关注", readOnly = true)
    @Transient
    private Boolean bidirectional;

    @ApiModelProperty(position = 46, value = "查询者是否今日UP过被查询者", readOnly = true)
    @Transient
    private Boolean liked = false;

    @ApiModelProperty(position = 47, value = "今日查询者提升被查询者的Up value", readOnly = true)
    @Transient
    private Double likeIndexIncrement;

    @ApiModelProperty(position = 48, value = "今日被查询者提升查询者的Up value", readOnly = true)
    @Transient
    private Double likeIndexIncrementReturned;


    @ApiModelProperty(position = 50, value = "查询者是否和被查询者互为好友", readOnly = true)
    @Transient
    private Boolean coupled;

    @ApiModelProperty(position = 51, value = "查询者是否和被查询者是否在200米内", readOnly = true)
    @Transient
    private Boolean neighbour;

    @JsonIgnore
    public Point getPoint() {
        return new Point(location.get(0), location.get(1));
    }


}
