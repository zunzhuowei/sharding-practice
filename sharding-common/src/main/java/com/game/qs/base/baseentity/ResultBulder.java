package com.game.qs.base.baseentity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by zun.wei on 2019/4/12 19:35.
 * Description: 结果集构建者
 */
public class ResultBulder implements Serializable {

    private Map<String,Object> map;


    public ResultBulder build(String key, Object value) {
        if (Objects.isNull(map))
            map = new HashMap<>();
        map.put(key, value);
        return this;
    }

    public Map<String, Object> finishBuild() {
        return this.map;
    }

}
