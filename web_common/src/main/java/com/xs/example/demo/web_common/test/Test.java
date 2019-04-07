/*
package com.xs.example.demo.web_common.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {

    public static void main(String[] args) {
        String body = HttpRequest.get("http://127.0.0.1:8081/provider-base/v2/api-docs").execute().body();
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
                jsonObject1.put("permission",operationId);
                jsonArray.add(jsonObject1);
            });
        });
        System.out.println(jsonArray);
    }
}
*/
