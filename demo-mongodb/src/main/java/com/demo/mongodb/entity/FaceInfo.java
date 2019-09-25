package com.demo.mongodb.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class FaceInfo {

    @Id
    @Indexed
    private String id;

    @NonNull
    private String imgUrl;

    @NonNull
    private Integer age;

    @NonNull
    private String gender;

    @NonNull
    private Float beauty;

    @NonNull
    private Integer logic;

    @NonNull
    private String token;


    @CreatedDate
    private Date createDateTime;

    @LastModifiedDate
    private Date modifyDateTime;

}
