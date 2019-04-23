package com.game.qs.swagger;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.game.qs.constant.StrConst.API_PATH_REGEX;
import static com.game.qs.constant.StrConst.OPEN_API_PATH_REGEX;
import static com.game.qs.constant.StrConst.TEST_PATH_REGEX;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by zun.wei on 2018/8/3 17:09.
 * Description: api文档 配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${game.qs.swagger.index}")
    private int swaggerIndex;

    @Value("${game.qs.swagger.hosts}")
    private String swaggerHost;

    @Value("${game.qs.swagger.scan}")
    private String scan;

    /**
     *  全部自定义 控制层 API
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("All API")
                .apiInfo(apiInfo())
                .host(getHost()) // 接口请求域名/ip地址
                .select()
                //指定包扫描路径，如果不指定扫描全部@controller
                .apis(RequestHandlerSelectors.basePackage(scan))
                .paths(PathSelectors.any())
                .build();
    }


    /**
     *  默认 控制层 API
     */
    @Bean
    public Docket defaultDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host(getHost()) // 接口请求域名/ip地址
                .select()
                .paths(defaultPathsRex())
                //pathMapping("/xcy-web")会统一在该接口分组下的接口前加上前缀 "/xcy-web"
                .build().pathMapping("/");
    }

    /**
     *  api 控制层 API
     */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API")
                .apiInfo(apiInfo())
                .host(getHost()) // 接口请求域名/ip地址
                .select()
                .paths(apiPathsRex())
                .build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("外部接口文档")
                //服务条款网址
                .termsOfServiceUrl("https://www.baidu.com")
                .version("1.0")
                .contact(new Contact("zhangsan","https://www.baidu.com","123456789@qq.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

    // 获取 host
    private String getHost() {
        String [] hosts = swaggerHost.split(",");
        return hosts[swaggerIndex];
    }

    // 以 /api 开头的所有访问
    private Predicate<String> apiPathsRex() {
        return or(
                regex(API_PATH_REGEX)
        );
    }

    // 以 /openApi,/api 开头的所有访问
    private Predicate<String> defaultPathsRex() {
        return or(
                regex(OPEN_API_PATH_REGEX),
                regex(API_PATH_REGEX)
        );
    }

}
