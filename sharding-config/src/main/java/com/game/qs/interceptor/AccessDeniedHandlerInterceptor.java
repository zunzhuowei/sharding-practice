package com.game.qs.interceptor;

import com.game.qs.base.baseentity.BaseResult;
import com.game.qs.configurer.AccountWebMvcConfigurer;
import com.game.qs.enum0.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zun.wei on 2019/2/21 14:50.
 * Description: 拒绝访问拦截器
 */
@Slf4j
@Component
public class AccessDeniedHandlerInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.warn("access denied this api [{}]", request.getRequestURI());
        BaseResult.Builder builder = BaseResult.getBuilder().setCode(Code.ACCESS_DENIED);
        AccountWebMvcConfigurer.responseResult(response, builder);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
