package com.game.qs.api;

import com.game.qs.base.basecontroller.BaseController;
import com.game.qs.mapping.ApiMapping;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zun.wei on 2019/4/23 11:04.
 * Description: API 接口
 */
@RestController
@Api(value = ApiMapping.API, tags = {"SHARDING-PRACTICE-API"})
@RequestMapping(ApiMapping.API)
public class ApiController extends BaseController {



}
