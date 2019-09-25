package com.demo.mongodb.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    String id;
    @NonNull
    String firstname;
    @NonNull
    String username;

    @DBRef
    @NonNull
    List<Provider> provider;

    @CreatedDate
    Date time;


}
