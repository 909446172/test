package demo.face.plus.faceplus.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @Author zyy
 * @Date 2019/9/18 11:12
 * @Version 1.0
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class FacesetInfo  {

    @Id
    @Indexed
    private String id;

    @NonNull
    private String name;


    @Max(9999)
    @Min(0)
    @NonNull
    private Integer size;



    @NonNull
    private  AreaLimit areaLimit;

    @NonNull
    private Location beforeLocation;


    @NonNull
    @Min(0)
    @Max(1)
    private Integer logic;

    @NonNull
    @GeoSpatialIndexed
    private Location location;

    @CreatedDate
    private Date createDateTime;

    @LastModifiedDate
    private Date modifyDateTime;



}
