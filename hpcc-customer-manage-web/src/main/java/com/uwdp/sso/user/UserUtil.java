package com.uwdp.sso.user;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.uwdp.config.CommonConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserUtil {

    private final CommonConfiguration commonConfiguration;

    /**
     * 根据request.headers中携带的X-User-Token到SSO查询用户信息
     * @param request HttpServletRequest对象
     * @return personCode 当前用户在SSO中的工号
     */
    public final String personCode(HttpServletRequest request){
        String xUserToken = request.getHeader("X-User-Token");
        String userInfoUrl = commonConfiguration.getUserInfoUrl();
        String personCode = "";
        HttpResponse userInfoResp = HttpRequest.get(userInfoUrl)
                .header("x-token", xUserToken)
                .execute();
        if (userInfoResp.isOk()){
            String body = userInfoResp.body();
            log.info(">>>>>>>>根据request.headers中携带的X-User-Token:{} | 返回体:{}",xUserToken,body);
            JSONObject jsonBody = JSON.parseObject(body);
            JSONObject data = jsonBody.getJSONObject("data");
            if (data.containsKey("personCode")) {
                personCode = data.getString("personCode");
            }
        }
        log.info(">>>>>>>>根据request.headers中携带的X-User-Token:{} | 到SSO查询用户信息:{}",xUserToken,personCode);
        return personCode;
    }

    /**
     * 根据request.headers中携带的X-User-Token到SSO查询用户信息
     * @param request HttpServletRequest对象
     * @return personName 当前用户在SSO中的名称
     */
    public final String personName(HttpServletRequest request) throws Exception{
        String xUserToken = request.getHeader("X-User-Token");
        String userInfoUrl = commonConfiguration.getUserInfoUrl();
        String personName = "";
        HttpResponse userInfoResp = HttpRequest.get(userInfoUrl)
                .header("x-token", xUserToken)
                .execute();
        if (userInfoResp.isOk()){
            String body = userInfoResp.body();
            log.info(">>>>>>>>根据request.headers中携带的X-User-Token:{} | 返回体:{}",xUserToken,body);
            JSONObject jsonBody = JSON.parseObject(body);
            JSONObject data = jsonBody.getJSONObject("data");
            if (data.containsKey("personName")) {
                personName = data.getString("personName");
            }
        }
        log.info(">>>>>>>>根据request.headers中携带的X-User-Token:{} | 到SSO查询用户信息:{}",xUserToken,personName);
        return personName;
    }

}
