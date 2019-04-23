package com.game.qs.base.basecontroller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * Created by zun.wei on 2018/8/3.
 *  控制层基类
 */
@Data
public class BaseController implements Serializable {

    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件

}
