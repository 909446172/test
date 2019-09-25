package demo.face.plus.faceplus.face.entity;

import lombok.Data;

/**
 * @Author zyy
 * @Date 2019/9/20 16:40
 * @Version 1.0
 */
@Data
public class Beauty {


    private Float value;
    private Float femaleScore;
    private Float maleScore;

    public Float getValue() {
        if (this.femaleScore > maleScore) {
            value = femaleScore;
        } else {
            value=maleScore;
        }
        return value;
    }


}
