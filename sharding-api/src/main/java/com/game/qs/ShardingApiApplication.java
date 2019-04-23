package com.game.qs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.game.qs.constant.ProjectConstant.BASE_MAPPER_SCAN;

/**
 * Created by zun.wei on 2019/4/23 10:16.
 * Description: 启动类
 */
@MapperScan(BASE_MAPPER_SCAN)
@SpringBootApplication
public class ShardingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingApiApplication.class, args);
    }

}
