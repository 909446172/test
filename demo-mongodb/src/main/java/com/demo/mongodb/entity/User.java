package com.demo.mongodb.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.EntityListeners;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class User   {
    @Id
    @NonNull
    String id;
    @NonNull
    String firstname;
    @NonNull
    String username;

    @DBRef(db = "database")
    Provider provider;

    @CreatedDate
    Date time ;


}
