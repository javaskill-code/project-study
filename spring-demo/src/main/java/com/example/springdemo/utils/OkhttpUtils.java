package com.example.springdemo.utils;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class OkhttpUtils {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String CONTENT_TYPE = "Content-Type";

    @Autowired
    private OkHttpClient okHttpClient;

    /**
     * post方式
     *
     * @param url        请求url
     * @param bodyParams requestbody
     * @param headerMap  请求头信息
     * @return
     * @throws IOException
     */
    public Response postData(String url, Map<String, Object> bodyParams, Map<String, String> headerMap) throws Exception {
        //1构造RequestBody
        RequestBody body = setRequestBody(bodyParams, headerMap);
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder().post(body).url(url);
        //requestBuilder = requestBuilder.post(body).url(url);
        addHeaders(headerMap, requestBuilder);
        Request request = requestBuilder.build();
        //Request request = requestBuilder.post(body).addHeader(CONTENT_TYPE, HttpContentTypeEnum.JSON.contentTypeValue).url(url).build();
        //3 将Request封装为Call
        Call call = okHttpClient.newCall(request);
        //4 执行Call，得到response
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * post的请求参数，构造RequestBody
     *
     * @param bodyParams
     * @return
     */
    private RequestBody setRequestBody(Map<String, Object> bodyParams, Map<String, String> headerMap) throws Exception {
        //判断请求头中是否存在 content-type字段
        if (headerMap == null || !headerMap.containsKey(CONTENT_TYPE)) {
            throw new Exception("请求头信息配置中无 Content-Type配置，请先配置");
        }

        String contentType = headerMap.get(CONTENT_TYPE);
        if ("application/x-www-form-urlencoded".equals(contentType)) {
            //表单提交 就是key-value 都是字符串型
            //转换
            Map<String, String> strBodyParamMap = new HashMap<>();
            if (bodyParams != null && !bodyParams.isEmpty()) {
                bodyParams.forEach((key, value) -> {
                    if (value != null) {
                        strBodyParamMap.put(key, (String) value);
                    }
                });
            }
            return buildRequestBodyByMap(strBodyParamMap);
        } else {
            //json
            return buildRequestBodyByJson(com.alibaba.fastjson.JSON.toJSONString(bodyParams));
        }

    }

    /**
     * 表单方式提交构建
     *
     * @param bodyParams
     * @return
     */
    private RequestBody buildRequestBodyByMap(Map<String, String> bodyParams) {
        RequestBody body = null;
        okhttp3.FormBody.Builder formEncodingBuilder = new okhttp3.FormBody.Builder();
        if (bodyParams != null) {
            Iterator<String> iterator = bodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, bodyParams.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;
    }

    /**
     * json方式提交构建
     *
     * @param jsonStr
     * @return
     */
    private RequestBody buildRequestBodyByJson(String jsonStr) {
        return RequestBody.create(JSON, jsonStr);
    }

    /**
     * 添加header信息
     *
     * @param headerMap
     * @param builder
     * @return
     */
    private static Request.Builder addHeaders(Map<String, String> headerMap, Request.Builder builder) {
        if (headerMap != null && !headerMap.isEmpty()) {
            headerMap.forEach((key, value) -> builder.addHeader(key, value));
        }
        return builder;
    }
}
