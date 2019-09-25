package demo.face.plus.faceplus.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

/**
 * @Author zyy
 * @Date 2019/9/19 9:13
 * @Version 1.0
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class UserFaceInfo {
    @Id
    @Indexed
    private String id;

    @DBRef
    @NonNull
    private FacesetInfo facesetInfo;

    @DBRef
    private List<FaceInfo> faceInfo;

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

    public UserFaceInfo(@NonNull FacesetInfo facesetInfo, List<FaceInfo> faceInfo, @NonNull @Min(0) @Max(1) Integer logic, @NonNull Location location) {
        this.facesetInfo = facesetInfo;
        this.faceInfo = faceInfo;
        this.logic = logic;
        this.location = location;
    }
}
