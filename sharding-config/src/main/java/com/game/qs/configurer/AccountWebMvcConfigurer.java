package com.game.qs.configurer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.game.qs.base.baseentity.BaseResult;
import com.game.qs.constant.IntConst;
import com.game.qs.constant.StrConst;
import com.game.qs.interceptor.AccessDeniedHandlerInterceptor;
import com.game.qs.interceptor.GlobalExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Spring MVC 配置
 */
@Slf4j
@Configuration
public class AccountWebMvcConfigurer implements WebMvcConfigurer {


    @Resource
    private ApplicationConfigProperties applicationConfigProperties;

    @Resource
    private GlobalExceptionResolver globalExceptionResolver;

    @Resource
    private AccessDeniedHandlerInterceptor accessDeniedHandlerInterceptor;


    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,//保留空的字段
                SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
                SerializerFeature.WriteNullNumberAsZero);//Number null -> 0
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName(StrConst.UTF_8));
        converters.add(converter);
    }


    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(globalExceptionResolver);
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(StrConst.MAPPING_ALL)
                .allowedOrigins(StrConst.STAR)
                .allowedHeaders(StrConst.STAR)
                .allowedMethods(StrConst.STAR)
                .allowCredentials(true)
                .maxAge(IntConst.DAY);
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
        String env = applicationConfigProperties.getEnv(); // 激活的环境配置
        if (!StringUtils.equals(StrConst.DEV, env) && !StringUtils.equals(StrConst.LOCAL, env)) {
            // 拒绝访问拦截器
            registry.addInterceptor(accessDeniedHandlerInterceptor).addPathPatterns(StrConst.MAPPING_ALL)
                    .excludePathPatterns(StrConst.MAPPING_API, StrConst.MAPPING_OPEN_API);

        }

/*

        if (!StringUtils.equals(StrConst.DEV, env)) { //开发环境忽略签名认证
            // 签名校验拦截器
            // 校验 jwt token 拦截器
            registry.addInterceptor(authenticationInterceptor).addPathPatterns(StrConst.MAPPING_API)
                    .excludePathPatterns(StrConst.MAPPING_OPEN_API);

            // open api 拦截器
            registry.addInterceptor(openApiInterceptor).addPathPatterns(StrConst.MAPPING_OPEN_API)
                    .excludePathPatterns(StrConst.MAPPING_API);

        }
*/

    }

    /**
     * 添加静态资源--过滤swagger-api (开源的在线API文档)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //过滤swagger
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v2/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");

        //registry.addResourceHandler("/csrf")
        //        .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");
        //registry.addResourceHandler("/");

    }

    /**
     *  响应结果
     */
    public static void responseResult(HttpServletResponse response, BaseResult.Builder builder) {
        response.setCharacterEncoding(StrConst.UTF_8);
        response.setHeader(HTTP.CONTENT_TYPE, StrConst.CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
        // response.setStatus(builder.build().getCode());
        try {
            response.getWriter().write(JSON.toJSONString(builder.build()));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

}
