package com.game.qs.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zun.wei on 2019/4/15 18:06.
 * Description: 角色值常量
 */
public interface RoleTypeConst {

//    1.1 超级管理员      administrator
//    1.2 管理员          admin
//    1.3 游戏商          gamePartner
//    1.4 合作商户        mallPartner
//    1.5 普通用户        member
//    1.6 未登录用户      anonymous

    /**
     * 1.0 不存在的角色
     */
    int NOT_EXIST_ROLE = -1;

    /**
     * 1.1 超级管理员
     */
    int ADMINISTRATOR = 1;

    /**
     * 1.2 管理员
     */
    int ADMIN = 2;

    /**
     * 1.3 游戏商
     */
    int GAME_PARTNER = 3;

    /**
     * 1.4 合作商户
     */
    int MALL_PARTNER = 4;

    /**
     * 1.5 普通用户
     */
    int MEMBER = 5;

    /**
     * 1.6 未登录用户
     */
    int ANONYMOUS = 6;


    enum Role {
        /**
         * 不存在的角色
         */
        NOT_EXIST_ROLE(RoleTypeConst.NOT_EXIST_ROLE),

        /**
         * 超级管理员
         */
        ADMINISTRATOR(RoleTypeConst.ADMINISTRATOR),

        /**
         * 管理员
         */
        ADMIN(RoleTypeConst.ADMIN),

        /**
         * 游戏商
         */
        GAME_PARTNER(RoleTypeConst.GAME_PARTNER),

        /**
         * 合作商户
         */
        MALL_PARTNER(RoleTypeConst.MALL_PARTNER),

        /**
         * 普通用户
         */
        MEMBER(RoleTypeConst.MEMBER),

        /**
         * 未登录用户
         */
        ANONYMOUS(RoleTypeConst.ANONYMOUS),
        ;

        public Integer CODE;

        Role(Integer code) {
            this.CODE = code;
        }
    }

    /**
     *  根据角色类型列表获取角色列表
     * @param roleTypes 角色类型列表
     * @return 角色列表
     */
    static List<Role> getRolesByRoleTypes(List<Integer> roleTypes) {
        List<Role> resultRoles = new ArrayList<>();
        roleTypes.forEach(e -> {
            switch (e) {
                case RoleTypeConst.ADMINISTRATOR:
                    resultRoles.add(Role.ADMINISTRATOR);
                    break;

                case RoleTypeConst.ADMIN:
                    resultRoles.add(Role.ADMIN);
                    break;

                case RoleTypeConst.GAME_PARTNER:
                    resultRoles.add(Role.GAME_PARTNER);
                    break;

                case RoleTypeConst.MALL_PARTNER:
                    resultRoles.add(Role.MALL_PARTNER);
                    break;

                case RoleTypeConst.MEMBER:
                    resultRoles.add(Role.MEMBER);
                    break;

                case RoleTypeConst.ANONYMOUS:
                    resultRoles.add(Role.ANONYMOUS);
                    break;

                case RoleTypeConst.NOT_EXIST_ROLE:
                    resultRoles.add(Role.NOT_EXIST_ROLE);
            }
        });
        return resultRoles;
    }

}
