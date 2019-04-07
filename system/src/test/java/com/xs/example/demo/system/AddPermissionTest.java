package com.xs.example.demo.system;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.example.demo.security.user.entity.Permission;
import org.junit.Test;

import java.util.List;

/**
 * @author xieshuang
 * @date 2019-04-07 16:21
 */
public class AddPermissionTest extends SystemApplicationTests {

    @Test
    public void addTest(){
        String body = HttpRequest.get("http://127.0.0.1:2001/v2/api-docs").execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        JSONObject paths = jsonObject.getJSONObject("paths");
        JSONArray jsonArray = new JSONArray();
        paths.entrySet().stream().forEach(stringObjectEntry -> {
            JSONObject value = (JSONObject) stringObjectEntry.getValue();
            value.entrySet().forEach(stringObjectEntry1 -> {
                JSONObject post = (JSONObject) stringObjectEntry1.getValue();
                String summary = post.getString("summary");
                String operationId = post.getString("operationId");
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("url","/provider-base"+stringObjectEntry.getKey());
                jsonObject1.put("name",summary);
                jsonObject1.put("symbol",operationId);
                jsonArray.add(jsonObject1);
            });
        });
        System.out.println(jsonArray);
        List<Permission> permissionList = jsonArray.toJavaList(Permission.class);
        permissionList.forEach(permission -> {
            if (permission!=null){
                permission.setType(1);
                permission.setPid(1L);
                Permission permission1 = permission.selectOne(new LambdaQueryWrapper<Permission>().eq(Permission::getName, permission).eq(Permission::getSymbol, permission.getSymbol()));
                if (permission1==null){
                    permission.insert();
                }
            }
        });
    }
}
