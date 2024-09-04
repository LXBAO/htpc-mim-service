package com.uwdp.module.web.controller.v2;



import com.alibaba.fastjson2.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.ri.cpbb2.idaas.open.adapter.api.dto.tenant.grant.consult.ConsultGrantDTO;
import com.gientech.ri.cpbb2.idaas.open.adapter.api.tenant.TenantUserApi;
import com.google.common.collect.Lists;
import com.uwdp.config.QiChaChaConfiguration;
import com.uwdp.config.QiYeWeiXinConfiguration;
import com.uwdp.module.api.service.IEnterpriseCheckRecordService;
import com.uwdp.module.api.service.IQiYeWorkService;
import com.uwdp.module.api.vo.cmd.EnterpriseCheckRecordAddCmd;
import com.uwdp.module.api.vo.dto.EnterpriseCheckRecordDto;
import com.uwdp.module.biz.infrastructure.entity.EnterpriseCheckRecordDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.repository.EnterpriseCheckRecordRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import com.uwdp.uac.IdassAuthProcessUtil;
import com.uwdp.utils.HttpUtils;
import com.uwdp.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author lx
 * @data 2023/7/13 14:25
 */
@RestController
@RequestMapping("/hpcc-customer-manage/out/side/interface")
@Api(tags = "外部接口ctl")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "外部接口ctl")
@Validated
@Slf4j
@Configuration
public class OutSideInterfaceApi {

//    @Autowired
//    private QiChaChaConfiguration qiChaChaConfiguration;

//    @Autowired
//    private QiYeWeiXinConfiguration qiYeWeiXinConfiguration;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MdmPersonRepository personRepository;

    @Autowired
    private TenantUserApi tenantuserApi;

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

    @Value("${qichacha.appKey}")
    private String APPKEY;

    @Value("${qichacha.secretKey}")
    private String SECRETKEY;

    @Value("${qichacha.nameSearchUrl}")
    private String NAMESEARCHURL;

    @Value("${qichacha.fuzzySearchUrl}")
    private String FUZZYSEARCHURL;

    private final IEnterpriseCheckRecordService enterpriseCheckRecordService;

    private final EnterpriseCheckRecordRepository enterpriseCheckRecordRepository;

    @Autowired
    private IQiYeWorkService qiYeWorkService;
    /**
     * 根据搜索关键字获取 名称列表
     *
     * @param name
     * @return
     * @throws Exception
     */
    @GetMapping("/qichacha/nameSearch")
    @ApiOperation(value = "企查查模糊查询", notes = "")
    public SingleResponse<String> qichachaNameSearch(@RequestParam("name") String name) {
        try {
            log.info("企查查模糊查询 {}", name);
            Long timespan = System.currentTimeMillis() / 1000;
            String signStr = APPKEY + timespan + SECRETKEY;
            String sign = MD5Utils.md5Hex(signStr.getBytes()).toUpperCase();
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(NAMESEARCHURL + "?key=" + APPKEY + "&searchName=" + name);
            httpGet.addHeader("Token", sign);
            httpGet.addHeader("Timespan", timespan.toString());
            CloseableHttpResponse apiRes = httpClient.execute(httpGet);
            HttpEntity entity = apiRes.getEntity();
            String content = EntityUtils.toString(entity);
            return SingleResponse.of(content);
        } catch (Exception e) {
            return SingleResponse.buildFailure(e.toString(), e.getLocalizedMessage());
        }
    }

    /**
     * 根据企业名称获取 详细信息
     *
     * @param searchKey
     * @return
     * @throws Exception
     */
    @GetMapping("/qichacha/fuzzySearch")
    @ApiOperation(value = "企查查精确查询", notes = "")
    public SingleResponse<Map> qichachaFuzzySearch(@RequestParam("searchKey") String searchKey) {
        try {
            log.info("企查查精确查询 {}", searchKey);
            EnterpriseCheckRecordDto enterpriseCheckRecordDto = enterpriseCheckRecordService.getName(searchKey);
            Map<String, Object> map = new HashMap<String, Object>();
            //判断searchKey存不存在
            if (enterpriseCheckRecordDto!=null){
                log.info("企查查进入了本地存储 {}",enterpriseCheckRecordDto.toString());
                map.put("bd",enterpriseCheckRecordDto);
            }else {
                Long timespan = System.currentTimeMillis() / 1000;
                String signStr = APPKEY + timespan + SECRETKEY;
                String sign = MD5Utils.md5Hex(signStr.getBytes()).toUpperCase();
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet httpGet = new HttpGet(FUZZYSEARCHURL + "?key=" + APPKEY + "&searchKey=" + searchKey);
                httpGet.addHeader("Token", sign);
                httpGet.addHeader("Timespan", timespan.toString());
                CloseableHttpResponse apiRes = httpClient.execute(httpGet);
                HttpEntity entity = apiRes.getEntity();
                String content = EntityUtils.toString(entity);
                map.put("qcc",content);

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(content);
                JSONArray result = JSONArray.parseArray(rootNode.get("Result").toString());
                List<EnterpriseCheckRecordDo> enterpriseCheckRecordDoList = result.toList(EnterpriseCheckRecordDo.class);
                enterpriseCheckRecordRepository.saveBatch(enterpriseCheckRecordDoList);
            }


            return SingleResponse.of(map);
        } catch (Exception e) {
            return SingleResponse.buildFailure(e.toString(), e.getLocalizedMessage());
        }
    }

    @GetMapping("/qiyeweixin/authorize")
    @ApiOperation(value = "企业微信授权登录", notes = "")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String getCodeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + APPID
                + "&redirect_uri=" + "https://mim.htpc.com.cn/uni-mim-ui/"
                + "&response_type=code"
                + "&scope=snsapi_privateinfo"
                + "&agentid=" + AGENTID
                + "&state=STATE#wechat_redirect";
        log.info("转发的URL:{}", getCodeUrl);
        response.sendRedirect(getCodeUrl);
    }

    @GetMapping("/qiyeweixin/getUserInfo")
    @ApiOperation(value = "企业微信获取用户信息", notes = "")
    public SingleResponse<Map<String, Object>> QiYeWeiXinGetUserInfo(@RequestParam("code") String code) {
        try {
            log.info("企业微信获取token,{}", redisTemplate.opsForValue().get("shjh:mim:qy_access_token"));
            //获取access_token
            String access_token = (String) redisTemplate.opsForValue().get("shjh:mim:qy_access_token");
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
            log.info("企业微信获取用户信息 {}", code);
            //根据token 获取用户信息
            HttpGet httpGet2 = new HttpGet(USERINFOURL + "?access_token=" + access_token + "&code=" + code);
            CloseableHttpResponse apiRes2 = httpClient.execute(httpGet2);
            log.info("微信返回:{}", apiRes2);
            HttpEntity entity2 = apiRes2.getEntity();
            log.info("微信返回entity2:{}", entity2);
            String content2 = EntityUtils.toString(entity2);
            log.info("用户信息:{}", content2);
            JSONObject userJson = JSONObject.parseObject(content2);
            String userTicket = (String) userJson.get("user_ticket");
            log.info("用户ticket:{}", userTicket);

            // 获取用户详细信息
            String getuserdetailUrl = GETUSERDETAILURL + "?access_token=" + access_token;
            Map<String, String> param = new HashMap<>();
            param.put("user_ticket", userTicket);
            param.put("access_token", access_token);

            String userdetailObj = HttpUtils.doPostJson(getuserdetailUrl, JSON.toJSONString(param));
            log.info("用户详细信息:{}", userdetailObj);
            JSONObject userDetailJson = JSONObject.parseObject(userdetailObj);
            String mobile = (String) userDetailJson.get("mobile");
            log.info("用户手机号:{}", mobile);

            Map<String, String> userTokenMap = new HashMap<>();
            userTokenMap.put("mobile", mobile);

            MdmPersonDo userInfo = personRepository.getOne(new LambdaQueryWrapper<MdmPersonDo>()
                    .eq(MdmPersonDo::getTelephone, mobile)
                    .last(" LIMIT 1"));

            Map<String, Object> resMap = new HashMap<>();
            resMap.put("userInfo", userInfo);
            resMap.put("token", JwtUtil.getToken(userTokenMap));

            log.info("查询用户信息:{}", userInfo);
            SingleResponse<ConsultGrantDTO> singleResponse = tenantuserApi.consultGrant(userInfo.getPersonCode());
            log.info("查询用户权限,业权返回:{}", singleResponse);


            cn.hutool.json.JSONObject permissionResponse = JSONUtil.parseObj(singleResponse);
            Map<String,List<String>> permissionMap = IdassAuthProcessUtil.listPermission4IdassJson(permissionResponse);
            //获取菜单权限
            List<String> menulList = permissionMap.get(IdassAuthProcessUtil.PERMISSION_TYPE_MENU);
            menulList =  Objects.nonNull(menulList) ? menulList : Lists.newArrayList();
            resMap.put("menuResources", menulList);
            resMap.put("roles", permissionMap.get("roles"));

            List<String> pointResourcesList = permissionMap.get(IdassAuthProcessUtil.PERMISSION_TYPE_PERMISSION_POINT);
            pointResourcesList =  Objects.nonNull(pointResourcesList) ? pointResourcesList : Lists.newArrayList();
            log.info("通用点权限集合:{}", pointResourcesList);
            resMap.put("pointResourcesList", pointResourcesList);

            return SingleResponse.of(resMap);

        } catch (Exception e) {
            return SingleResponse.buildFailure(e.toString(), e.getLocalizedMessage());
        }

    }

    @GetMapping("/qiyeweixin/deleteQyToken")
    @ApiOperation(value = "删除redisKey", notes = "")
    public SingleResponse<Object> deleteRedisKey(@RequestParam("key") String key) {
        return SingleResponse.of(redisTemplate.delete(key));
    }

    @GetMapping("/qiyeweixin/queryQyToken")
    @ApiOperation(value = "获取redisKey", notes = "")
    public SingleResponse<Object> queryQyToken(@RequestParam("key") String key) {
        log.info("key是否存在：",redisTemplate.hasKey(key));
        return SingleResponse.of(redisTemplate.opsForValue().get(key));
    }

    @GetMapping("/qiyeweixin/sendTextMsg")
    @ApiOperation(value = "获取redisKey", notes = "")
    public SingleResponse<Object> sendTextMsg(@RequestParam("key") String key) {
        return SingleResponse.of(qiYeWorkService.sendTextMsg(""));
    }

    @GetMapping("/qiyeweixin/sendMiniprogramNoticeMsg")
    @ApiOperation(value = "获取redisKey", notes = "")
    public SingleResponse<Object> sendMiniprogramNoticeMsg(@RequestParam("key") String key) {
        return SingleResponse.of(qiYeWorkService.sendMiniprogramNoticeMsg(""));
    }

    @GetMapping("/qiyeweixin/sendTextcard")
    @ApiOperation(value = "获取redisKey", notes = "")
    public SingleResponse<Object> sendTextcard(@RequestParam("proInstId")String proInstId,
                                               @RequestParam("taskId")String taskId,
                                               @RequestParam(name = "type") String type,
                                               @RequestParam("userNo")String userNo,
                                               @RequestParam("userFullName")String userFullName) {
        return SingleResponse.of(qiYeWorkService.sendTextcard(proInstId, taskId, type, userNo, userFullName));
    }

    @GetMapping("/qiyeweixin/getUserId")
    @ApiOperation(value = "获取redisKey", notes = "")
    public SingleResponse<Object> getUserId(@RequestParam("key") String key) {
        return SingleResponse.of(qiYeWorkService.getUserId(key));
    }
//
//    @GetMapping("/queryMenu")
//    public Object queryMenu(){
////        log.info("获取用户菜单权限：{}", tenantBusinessPositionRestClient.getGrant("77265"));
//        cn.hutool.json.JSONObject permissionResponse = JSONUtil.parseObj(tenantuserApi.consultGrant("77265"));
//        log.info("获取权限原始数据:{}", permissionResponse);
//        return SingleResponse.of(IdassAuthProcessUtil.listPermission4IdassJson(permissionResponse));
//
//    }

}
