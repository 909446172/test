package com.demo.mongodb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document("local")
public class Local {
    String name;
    @GeoSpatialIndexed
    Location location;

    @PersistenceConstructor
    public Local(String name, Location location) {
        this.name = name;
        this.location = location;
    }
}