package com.game.qs.mapping;

import com.game.qs.constant.RoleTypeConst;

import static com.game.qs.constant.RoleTypeConst.Role.ANONYMOUS;

/**
 * Created by zun.wei on 2019/4/23 13:52.
 * Description:
 */
public enum PutMapping {

    /**
     * 手机号注册壹帐号接口
     */
    MEMBER_PHONE_REGISTER(ApiMapping.MEMBER_PHONE_REGISTER, ANONYMOUS),

    ;

    public String uri; // 接口后缀地址
    public RoleTypeConst.Role[] roles; // 允许访问接口的角色

    PutMapping(final String uri, RoleTypeConst.Role... roles) {
        this.roles = roles;
        this.uri = uri;
    }

}
