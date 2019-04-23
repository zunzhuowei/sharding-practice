package com.game.qs.api;

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
 * Created by zun.wei on 2019/4/23 11:04.
 * Description: API 接口
 */
@RestController
@Api(value = "/api", tags = {"SHARDING-PRACTICE-API"})
@RequestMapping("/api")
public class ApiController extends BaseController {



}
