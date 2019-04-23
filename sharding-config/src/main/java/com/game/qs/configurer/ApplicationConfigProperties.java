package com.game.qs.configurer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "game.qs")
public class ApplicationConfigProperties {


    private String signSecret; //密钥，自己修改

    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件

    private String jwtSecret;

}
