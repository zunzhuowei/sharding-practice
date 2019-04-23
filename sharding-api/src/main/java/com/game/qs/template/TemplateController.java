package com.game.qs.template;

import com.game.qs.annotation.LoginToken;
import com.game.qs.base.basecontroller.BaseController;
import com.game.qs.base.baseentity.BaseResult;
import com.game.qs.enum0.Code;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zun.wei on 2019/4/23 11:17.
 * Description: 模板接口
 */
@RestController
@Api(value = "/template", tags = {"TEMPLATE-API"})
@RequestMapping("/template")
public class TemplateController extends BaseController {

    @ApiOperation(value = "手机号注册壹帐号 获取验证码接口", notes = "获取验证码接口")
//    @ApiImplicitParams({//, paramType = "form",, paramType = "path"
//            @ApiImplicitParam(name = "page", value = "当前页码", required = true, paramType = "path", dataType = "int"),
//            @ApiImplicitParam(name = "size", value = "页面大小", required = true, paramType = "path", dataType = "int"),
//            @ApiImplicitParam(name = "q", value = "查询关键字", required = true, paramType = "path", dataType = "String")
//    })
    @ApiResponses({
            @ApiResponse(code = -400, message = "请求参数没填好"),
            @ApiResponse(code = -401, message = "请求路径未授权或签名失败"),
            @ApiResponse(code = -403, message = "请求路径被禁止"),
            @ApiResponse(code = -404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = -500, message = "服务器内部错误"),
            @ApiResponse(code = 0, message = "请求成功", response = BaseResult.class),
    })
    @LoginToken
    @PostMapping("xxxx")
    public BaseResult getRegisterMemberPhoneCode() {
        return BaseResult.getBuilder().setCode(Code.ERROR_0).build();
        //String mailUserName = applicationConfigProperties.getMailUserName();
        //return accountOneRegisterService.generateCheckCode(registerReq, mailUserName);
    }

}
