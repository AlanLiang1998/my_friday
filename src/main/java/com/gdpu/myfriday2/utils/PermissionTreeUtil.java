package com.gdpu.myfriday2.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gdpu.myfriday2.model.Permission;

import java.util.List;

/**
 * @Descriptin TODO
 * @Author AlanLiang
 * Date 2020/3/24 21:43
 * Version 1.0
 **/
public class PermissionTreeUtil {
    public static void setPermissionTree(long parentId, List<Permission> permissions, JSONArray array) {
        for (Permission permission : permissions) {
            if (permission.getParentId().equals(parentId)) {
                String jsonString = JSONObject.toJSONString(permission);
                JSONObject parent = (JSONObject) JSONObject.parse(jsonString);
                array.add(parent);
                if (permissions.stream().filter(p -> p.getParentId().equals(permission.getPermissionId())).findAny() != null) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    setPermissionTree(permission.getPermissionId(), permissions, child);
                }
            }
        }
    }
}
