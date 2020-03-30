package com.gdpu.myfriday2.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gdpu.myfriday2.model.Permission;

import java.util.List;

/**
 * @Descriptin 获取菜单树
 * @Author AlanLiang
 * Date 2020/3/29 22:30
 * Version 1.0
 **/
public class PermissionTreeUtil {

    public static void getPermissionTree(long parentId, List<Permission> permissions, JSONArray result) {
        for (Permission permission : permissions) {
            if (permission.getParentId().equals(parentId)) {
                String string = JSONObject.toJSONString(permission);
                JSONObject parent = JSONObject.parseObject(string);
                result.add(parent);
                if (permissions.stream().anyMatch(p -> p.getParentId().equals(permission.getPermissionId()))) {
                    JSONArray child = new JSONArray();
                    parent.put("child", child);
                    getPermissionTree(permission.getPermissionId(), permissions, child);
                }
            }
        }
    }
}
