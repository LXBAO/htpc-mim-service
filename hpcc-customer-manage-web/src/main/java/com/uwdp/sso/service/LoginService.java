package com.uwdp.sso.service;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@Service
public class LoginService {

    public static final String doLogingAddr = "https://auth.htpc.com.cn/oauth2/doLogin"; /*第一步调用dologin接口，从response的header里获取satoken这个cookie*/

    public static final String doAuthorizeAddr = "https://auth.htpc.com.cn/oauth2/authorize";   /*第二步调用/oauth2/authorize 接口，header里传token*/

    public static final String client_id = "acc49039d2df474d9a1f24a45e1ce8c2"; /*应用id*/

    public static final String response_type = "token"; /*固定值*/

    public static final String redirect_uri = "http://localhost:8888";   /*重定向地址*/

    /*name : 前端平台的用户账号名*/
    public String login(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "u"+name);
        paramMap.put("pwd", ".@37uWx(;");
        paramMap.put("client_id", client_id);
        paramMap.put("response_type", response_type);
        paramMap.put("redirect_uri", redirect_uri);

        log.info("====正在登录:『"+name+"』====");

        HttpResponse response = HttpRequest.get(doLogingAddr).form(paramMap).execute();

        JSONObject jsonObject = JSONUtil.parseObj(response.body());
        String code = jsonObject.getStr("code");

        /*第一次dologin成功获取到satoken*/
        if (code.equals("200")) {
            HttpCookie satoken = response.getCookie("satoken");

            log.info("====dologin登录成功,satoken:『"+satoken+"』====");

            HashMap<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("response_type", response_type);
            paramMap2.put("client_id", client_id);
            paramMap2.put("redirect_uri", redirect_uri);

            HttpResponse response2 = HttpRequest.get(doAuthorizeAddr)
                    .header(Header.COOKIE, satoken.toString())
                    .form(paramMap2)
                    .execute();

            JSON parse = JSONUtil.parse(response2.headers());
            Object location = parse.getByPath(Header.LOCATION.getValue());  /*包含token的重定向地址*/

            if (location instanceof JSONArray){
                JSONArray array = (JSONArray) location;
                Object o = array.get(0);

                log.info("====authorize成功,即将重定向至:『"+o.toString()+"』====");

                return o.toString();
            }

            log.info("====authorize失败,响应体:『"+response2.body()+"』====");
        }

        log.info("====dologin登录失败『"+name+"』====");

        return null;
    }
}