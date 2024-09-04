package com.uwdp.module.biz.service;

import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.service.IQiYeWorkService;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.biz.utils.HttpUtil;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class QiYeWorkServiceImpl implements IQiYeWorkService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IMdmPersonService mdmPersonService;

    @Value("${qiyeweixin.appid}")
    private String APPID;

    @Value("${qiyeweixin.agentId}")
    private String AGENTID;

    @Value("${qiyeweixin.secret}")
    private String SECRET;

    @Value("${qiyeweixin.access-token-url}")
    private String ACCESSTOKENURL;

    @Value("${qiyeweixin.user-info-url}")
    private String USERINFOURL;

    @Value("${qiyeweixin.getuserdetailUrl}")
    private String GETUSERDETAILURL;

    @Value("${qiyeweixin.sendUrl}")
    private String SENDURL;

    @Value("${qiyeweixin.getuseridUrl}")
    private String GETUSERIDURL;

    @Override
    public Object getToken() {
        String access_token;
        try {
            access_token = (String) redisTemplate.opsForValue().get("shjh:mim:qy_access_token");
            log.info("企业微信获取token,{}", redisTemplate.opsForValue().get("shjh:mim:qy_access_token"));
            //获取access_token
            CloseableHttpClient httpClient = HttpClients.createDefault();
            log.info("企业微信的token:{}", access_token);
            if (StringUtil.isEmpty(access_token)) {
                log.info("去获取企业微信的token,请求地址:{}, corpid:{}, corpsecret:{}", ACCESSTOKENURL, APPID, SECRET);
                HttpGet httpGet = new HttpGet(ACCESSTOKENURL + "?corpid=" + APPID + "&corpsecret=" + SECRET);
                CloseableHttpResponse apiRes = httpClient.execute(httpGet);
                log.info("apiRes:{}", apiRes);
                HttpEntity entity = apiRes.getEntity();
                log.info("entity:{}", entity);
                String content = EntityUtils.toString(entity);
                log.info("content:{}", content);
                JSONObject jsonObject = new JSONObject().parseObject(content);
                log.info("jsonObject:{}", jsonObject);
                access_token = jsonObject.get("access_token").toString();
                log.info("access_token:{}", access_token);
                redisTemplate.opsForValue().set("shjh:mim:qy_access_token", access_token, 7100, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            return SingleResponse.buildFailure(e.toString(), e.getLocalizedMessage());
        }
        return access_token;
    }

    @Override
    public String getUserId(String phone) {
        Object token = getToken();
        String url = GETUSERIDURL + "?access_token=" + token;

        Map<String, Object> param = new HashMap<>();
        param.put("mobile", phone);

        log.info("【企业微信获取用户ID】传给企业微信的参数为：{}", JSON.toJSONString(param));
        String userdetailObj = HttpUtil.doPostJson(url, JSON.toJSONString(param));
        log.info("【企业微信获取用户ID】企业微信返回:{}", userdetailObj);

        JSONObject userDetailJson = JSONObject.parseObject(userdetailObj);

        return (String) userDetailJson.get("userid");
    }

    @Override
    public Object sendTextMsg(String phones) {
        Object token = getToken();
        String url = SENDURL + "?access_token=" + token;

        log.info("【企业微信发送应用消息】接受消息的用户是:{}", phones);

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("content", "这是一个测试应用消息<a href=\"https://www.baidu.com\">这是个链接</a>");

        Map<String, Object> param = new HashMap<>();
        param.put("touser", "RenZhuoYu|DaiRunNan");
        param.put("msgtype", "text");
        param.put("agentid", AGENTID);
        param.put("text", contentMap);

        log.info("【企业微信发送应用消息】传给企业微信的参数为：{}", JSON.toJSONString(param));
        String userdetailObj = HttpUtil.doPostJson(url, JSON.toJSONString(param));
        log.info("【企业微信发送应用消息】企业微信返回:{}", userdetailObj);

        return userdetailObj;
    }


    @Override
    public Object sendTextcard(String proInstId, String taskId, String type, String userNo, String userFullName) {
        Object token = getToken();
        String url = SENDURL + "?access_token=" + token;

        log.info("【企业微信发送应用图文消息】接受消息的用户编号是:{}", userNo);

        MdmPersonDto mdmPersonDto = mdmPersonService.getPersonCodeDetail(userNo);

        log.info("【企业微信发送应用图文消息】接受消息的用户手机号是:{}", mdmPersonDto.getTelephone());

        Map<String, String> textcardMap = new HashMap<>();
        textcardMap.put("title", "待办消息");
        textcardMap.put("description", "您有一条待办待处理，点击处理。");
        textcardMap.put("url",
                "https://mim.htpc.com.cn/uni-mim-ui/" +
                        "?proInstId=" + proInstId + "&taskId=" + taskId +
                        "&type=" + type + "&userNo=" + userNo + "&userFullName" + userFullName);

        List<Map<String, String>> articles = new ArrayList<>();
        articles.add(textcardMap);

        Map<String, Object> articlesMap = new HashMap<>();
        articlesMap.put("articles", articles);

        String userId = getUserId(mdmPersonDto.getTelephone());

        Map<String, Object> param = new HashMap<>();
        param.put("touser", "RenZhuoYu|DaiRunNan");
        param.put("msgtype", "news");
        param.put("agentid", AGENTID);
        param.put("news", articlesMap);

        log.info("【企业微信发送应用图文消息】传给企业微信的参数为：{}", JSON.toJSONString(param));
        String userdetailObj = HttpUtil.doPostJson(url, JSON.toJSONString(param));
        log.info("【企业微信发送应用图文消息】企业微信返回:{}", userdetailObj);

        return userdetailObj;
    }

    @Override
    public Object sendMiniprogramNoticeMsg(String phones) {
        Object token = getToken();
        String url = SENDURL + "?access_token=" + token;

        log.info("【企业微信发送应用消息】接受消息的用户是:{}", phones);

        Map<String, String> miniprogramNoticeMap = new HashMap<>();
        miniprogramNoticeMap.put("appid", AGENTID);
        miniprogramNoticeMap.put("page", "pages/index/index");
        miniprogramNoticeMap.put("title", "待办消息");
        miniprogramNoticeMap.put("description", "您有一条待办待处理，点击处理。");

        Map<String, Object> param = new HashMap<>();
        param.put("touser", "RenZhuoYu|DaiRunNan");
        param.put("msgtype", "miniprogram_notice");
        param.put("agentid", AGENTID);
        param.put("miniprogram_notice", miniprogramNoticeMap);

        String userdetailObj = HttpUtil.doPostJson(url, JSON.toJSONString(param));
        log.info("【企业微信发送应用消息】企业微信返回:{}", userdetailObj);

        return userdetailObj;
    }
}
