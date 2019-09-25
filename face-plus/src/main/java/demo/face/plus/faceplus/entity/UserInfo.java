package demo.face.plus.faceplus.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @Author zyy
 * @Date 2019/9/19 19:42
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    @Id
    @Indexed
    private String id;

    private String userId;

    @DBRef
    private UserFaceInfo userFaceInfo;

    public UserInfo(String userid, UserFaceInfo userFaceInfo) {
        this.userId = userid;
        this.userFaceInfo = userFaceInfo;
    }

    public UserInfo(String userid) {
        this.userId = userid;
    }
}
