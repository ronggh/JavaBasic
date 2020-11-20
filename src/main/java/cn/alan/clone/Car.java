package cn.alan.clone;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    // 品牌
    private String brand;
    // 最高时速
    private int maxSpeed;
}
