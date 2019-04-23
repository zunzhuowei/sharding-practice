package com.game.qs.index;

import com.game.qs.annotation.LoginToken;
import com.game.qs.base.basecontroller.BaseController;
import com.game.qs.base.baseentity.BaseResult;
import com.game.qs.enum0.Code;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by zun.wei on 2019/4/13.
 */
@Controller
public class IndexController extends BaseController {


    @GetMapping("")
    public String index() {
        return "redirect:/swagger-ui.html";
    }


}
