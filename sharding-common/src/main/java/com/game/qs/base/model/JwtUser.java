package com.game.qs.base.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zun.wei on 2019/4/12 15:48.
 * Description:
 */
@Data
@Accessors(chain = true)
public class JwtUser implements Serializable {

    private String mid;

    private String phone;

    // 签名秘钥
    private String jwtKey;

    // 角色类型
    private List<Integer> roleType;

}
