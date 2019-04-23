package com.game.qs.interceptor;

import com.game.qs.base.baseentity.BaseResult;
import com.game.qs.base.exception.SystemException;
import com.game.qs.configurer.AccountWebMvcConfigurer;
import com.game.qs.configurer.ApplicationConfigProperties;
import com.game.qs.constant.StrConst;
import com.game.qs.enum0.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zun.wei on 2019/2/21 14:58.
 * Description: 全局统一异常处理
 */
@Slf4j
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    @Resource
    private ApplicationConfigProperties applicationConfigProperties;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        BaseResult.Builder builder = BaseResult.getBuilder().setSuccess(false).setMessage(e.getMessage());

        if (e instanceof SystemException) {//业务失败的异常，如“账号或密码错误”
            builder.setCode(Code.FAIL);
            log.info(e.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            builder.setCode(Code.NOT_FOUND).setMessage(String.format("接口 [%s] 不存在", request.getRequestURI()));
            log.info(builder.buildJsonStr());
        } else if (e instanceof ServletException) {
            builder.setCode(Code.FAIL).setMessage(e.getMessage());
            log.info(builder.buildJsonStr());
        } else {
            builder.setCode(Code.INTERNAL_SERVER_ERROR).setMessage(String.format("接口 [%s] 内部错误，请联系管理员", request.getRequestURI()));
            String message;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                        request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        e.getMessage());
            } else {
                message = e.getMessage();
            }
            log.error(message, e);
        }

        // 如果不是开发环境，发生异常时，则全部返回拒绝访问
        if (!StrConst.DEV.equals(applicationConfigProperties.getEnv())) {
            builder.setSuccess(false).setCode(Code.ACCESS_DENIED).setMessage(Code.ACCESS_DENIED.msg);
        }
        AccountWebMvcConfigurer.responseResult(response, builder);
        return new ModelAndView();
    }
}
