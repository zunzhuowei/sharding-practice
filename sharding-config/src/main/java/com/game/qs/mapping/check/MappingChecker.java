package com.game.qs.mapping.check;

import com.game.qs.base.exception.SystemException;
import com.game.qs.constant.RoleTypeConst;
import com.game.qs.constant.StrConst;
import com.game.qs.mapping.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.game.qs.constant.RoleTypeConst.Role.NOT_EXIST_ROLE;
import static com.game.qs.mapping.ApiMapping.API;


/**
 * Created by zun.wei on 2019/4/16 9:17.
 * Description: 请求映射检查者
 */
@Slf4j
@Component
public class MappingChecker implements Serializable {

    // 角色与请求uri 映射容器
    private static final Map<String, RoleTypeConst.Role[]> ROLE_CONTAINER = new ConcurrentHashMap<>();

    public MappingChecker() {
        Field[] fields = ApiMapping.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();

            try {
                String fieldValue = field.get(fieldName) + StrConst.EMPTY_STR;
                RoleTypeConst.Role[] role = this.checkUri(fieldValue);
                // 跳过 /api
                if (StringUtils.equals(API, fieldValue)) {
                    continue;
                }
                log.trace("MappingChecker fieldName fieldValue role --:{}, {}, {}", fieldName, fieldValue, role);

                // 如果 uri 为未配置角色权限，则抛出异常
                if (Arrays.stream(role).anyMatch(e -> e == NOT_EXIST_ROLE)) {
                    throw new SystemException("ApiMapping not mapping [" + fieldValue + "] role permissions!");
                }
                // 初始化 角色与请求uri 映射容器
                ROLE_CONTAINER.put(fieldValue, role);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        ROLE_CONTAINER.forEach((k, v) -> log.trace("ROLE_CONTAINER ---::[{}],[{}]", k, v));
    }


    /**
     * 根据 request uri 获取该 uri 地址需要的角色权限
     *
     * @param uri request uri
     * @return -1 表示请求的uri 地址为配置；其他表示对应的角色权限值
     */
    private RoleTypeConst.Role[] getRoleTypeByUri(String uri) {
        RoleTypeConst.Role[] role = ROLE_CONTAINER.get(uri);
        return Objects.isNull(role) ? new RoleTypeConst.Role[]{NOT_EXIST_ROLE} : role;
    }

    /**
     * 检查 uri 权限是否已经配置
     *
     * @param uri request uri
     */
    private RoleTypeConst.Role[] checkUri(String uri) {
        // post
        Optional<PostMapping> targetPostApi = Arrays.stream(PostMapping.values())
                .filter(e -> StringUtils.equals(e.uri, uri))
                .findFirst();

        return targetPostApi.map(e -> e.roles).orElseGet(() -> {
            // get
            Optional<GetMapping> targetGetApi = Arrays.stream(GetMapping.values())
                    .filter(e -> StringUtils.equals(e.uri, uri))
                    .findFirst();

            return targetGetApi.map(e -> e.roles).orElseGet(() -> {
                // put
                Optional<PutMapping> targetPutApi = Arrays.stream(PutMapping.values())
                        .filter(e -> StringUtils.equals(e.uri, uri))
                        .findFirst();

                return targetPutApi.map(e -> e.roles).orElseGet(() -> {
                    // delete
                    Optional<DeleteMapping> targetDeleteApi = Arrays.stream(DeleteMapping.values())
                            .filter(e -> StringUtils.equals(e.uri, uri))
                            .findFirst();

                    return targetDeleteApi.map(e -> e.roles).orElse(new RoleTypeConst.Role[]{NOT_EXIST_ROLE});
                });
            });
        });
    }


    /**
     * 是否可以访问某个 uri 地址
     *
     * @param uri   request uri
     * @param roles 角色值列表
     */
    public boolean isCanVisit(String uri, List<Integer> roles) {
        // 获取访问该 uri 所需要的角色
        RoleTypeConst.Role[] role = this.getRoleTypeByUri(uri);
        if (Objects.isNull(roles) || roles.isEmpty()) {
            log.trace("MappingChecker isCanVisit List<Integer> roles is null or empty !");
        }

        // 获取该用户所拥有的所有角色身份
        List<RoleTypeConst.Role> hadRoles = RoleTypeConst.getRolesByRoleTypes(roles);
        for (RoleTypeConst.Role value : role) {
            for (RoleTypeConst.Role hadRole : hadRoles) {
                // 如果请求的 uri 该有的角色权限，请求者也有该身份，则允许访问。
                if (value == hadRole) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取 uri 权限列表
     *
     * @param roleTypes 角色类型列表
     * @return uri 权限列表
     */
    public Set<String> getUriPermissions(List<Integer> roleTypes) {
        Set<String> set = new HashSet<>();
        ROLE_CONTAINER.forEach((uri, roles) -> {
            boolean isCan = this.isCanVisit(uri, roleTypes);
            if (isCan) set.add(uri);
        });
        return set;
    }

}
