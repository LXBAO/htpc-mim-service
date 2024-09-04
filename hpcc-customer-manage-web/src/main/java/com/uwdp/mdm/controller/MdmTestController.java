package com.uwdp.mdm.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.net.ceec.hpcc.mdm.sdk.enums.MdmDataTypeEnum;
import cn.net.ceec.hpcc.mdm.sdk.util.MdmUtil;
import cn.net.ceec.hpcc.mdm.sdk.vo.*;
import com.alibaba.cola.dto.Response;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.uwdp.config.AttachmentConfiguration;
import com.uwdp.mdm.service.QueryMdmService;
import com.uwdp.mdm.service.SynMdm2MimService;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.service.IMdmPostService;
import com.uwdp.module.api.vo.cmd.MdmPersonUpdateCmd;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import com.uwdp.utils.MdmPersonUtils;
import com.uwdp.workflow.service.SubmitAddProjectImplementServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/accept")
@Api(tags = "accept")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "accept")
@Validated
public class MdmTestController {

    private final MdmUtil mdmUtil;

    private final IMdmPersonService mdmPersonService;
    private final IMdmOrganizationService mdmOrganizationService;
    private final IMdmPostService mdmPostService;
    private final SynMdm2MimService synMdm2MimService;
    private final QueryMdmService queryMdmService;
    private final SubmitAddProjectImplementServiceImpl saip;

    public static final String APP_SECRET_DEV = "f44bb49cad7143808c2d4e7ae1e5709e";
    public static final String APP_SECRET_PROD = "d79df1a88d30435db21d9e0f4d90b89c";

    @PostMapping({"/test"})
    @ApiOperation(value = "接收通知", notes = "accept")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response test(@RequestBody MdmNoticeMessage noticeMessage) {
        log.info("接受到湖南火电公司主数据数据下发请求: {}", noticeMessage);
        // 封装查询请求参数，这里数据下发时，是根据id查询数据详情。
        CommonQuery commonQuery = new CommonQuery();
        commonQuery.setId(noticeMessage.getMdmId());
        BaseQueryRequest baseQueryRequest = new BaseQueryRequest();
        baseQueryRequest.setRequestId(IdUtil.simpleUUID());
        baseQueryRequest.setData(commonQuery);
        // 发起查询（读）请求，解析对象需要接入方根据接口文档自己定义，这里就先用HashMap进行演示。
        BaseSingleResponse<HashMap> baseSingleResponse = mdmUtil.readToGet(noticeMessage.getCallbackUrl(), baseQueryRequest, HashMap.class);
        HashMap result = baseSingleResponse.getData();
        // 接入方处理数据的逻辑，这里演示只做打印处理。
        log.info("通过订阅主数据接口获取下发的数据: {}\n{}", noticeMessage.getMdmType(), result);
        Response response = BaseResponse.buildSuccess();
        return response;
    }


    @GetMapping({"/test1"})
    @ApiOperation(value = "接收通知", notes = "accept")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response test1() {
//        log.info("接受到湖南火电公司主数据数据下发请求: {}", noticeMessage);
        MdmNoticeMessage mdmNoticeMessage = new MdmNoticeMessage();
        mdmNoticeMessage.setMdmId("1658750224341954561");
        mdmNoticeMessage.setMdmType("mdm-person");
        mdmNoticeMessage.setCallbackUrl("https://uwdp-api-dev.htpc.com.cn/hpcc-mdm-openapi/v1/mdm-person/detail");
        mdmNoticeMessage.setVersion("1");
        mdmNoticeMessage.setRecordId("1");
        mdmNoticeMessage.setLastUpdateTime("2023-06-26");
        // 封装查询请求参数，这里数据下发时，是根据id查询数据详情。
        CommonQuery commonQuery = new CommonQuery();
        commonQuery.setId(mdmNoticeMessage.getMdmId());
        BaseQueryRequest baseQueryRequest = new BaseQueryRequest();
        baseQueryRequest.setRequestId(IdUtil.simpleUUID());
        baseQueryRequest.setData(commonQuery);
        // 发起查询（读）请求，解析对象需要接入方根据接口文档自己定义，这里就先用HashMap进行演示。
        BaseSingleResponse<MdmPersonUpdateCmd> baseSingleResponse = mdmUtil.readToGet(mdmNoticeMessage.getCallbackUrl(), baseQueryRequest, MdmPersonUpdateCmd.class);
        MdmPersonUpdateCmd personUpdateCmd = baseSingleResponse.getData();
        // 接入方处理数据的逻辑，这里演示只做打印处理。
        log.info("通过订阅主数据接口获取下发的数据: {}\n{}", mdmNoticeMessage.getMdmType(), personUpdateCmd);
        mdmPersonService.update(personUpdateCmd);
        Response response = BaseResponse.buildSuccess();
        return response;
    }




    public static void main(String[] args) {
        JSONObject requestBody = new JSONObject();
        requestBody.set("pageIndex",1);
        requestBody.set("pageSize",10);
        requestBody.set("data", new JSONObject());
        requestBody.set("requestId", UUID.randomUUID());
        long timestamp = System.currentTimeMillis();
        String appKey = "MIM";//替换真实值
        String appSecret = APP_SECRET_PROD;//替换真实值
        String saltValue = "6da2bd017c574150bd9ee5039e19d8bf";//盐值 对接系统自己定义
        String sign = SecureUtil.md5(StrUtil.join(StrUtil.UNDERLINE, appKey, timestamp, JSONUtil.toJsonStr(requestBody), saltValue, appSecret));
        String url = "https://uwdp-api.htpc.com.cn/hpcc-mdm-openapi/v1/mdm-person/page";

        String body = HttpRequest.post(url)
                .header("appKey", appKey)
                .header("appSecret", appSecret)
                .header("saltValue", saltValue)
                .header("sign", sign)
                .header("timestamp", String.valueOf(timestamp))
                .body(requestBody.toString())
                .execute().body();
        JSONObject parse = JSONUtil.parseObj(body);
        System.out.println(parse);
//        Object totalCount = parse.get("totalCount");
//        System.out.println(totalCount);

    }

    @RequestMapping("/test2131")
    public void test3(){
//        synMdm2MimService.initMdmPerson2Mim();
    }

    @Autowired
    private  AttachmentConfiguration attachmentConfiguration;
    @RequestMapping("/value")
    public void test4(){

        String url = attachmentConfiguration.getUploadUrl();
        System.out.println(url);
        log.info("====读取到配置文件中的uploadUrl为: ",url);
    }


    @Autowired
    private MdmPersonRepository mdmPersonRepository;
    @RequestMapping("/value/{code}")
    public void test5(@PathVariable String code){

        LambdaQueryWrapper<MdmPersonDo> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.select(MdmPersonDo::getPersonName);
        queryWrapper.eq(MdmPersonDo::getPersonCode,code);
        MdmPersonDo mdmPersonDo = mdmPersonRepository.list(queryWrapper).get(0);
        System.out.println(mdmPersonDo);
    }


    @Autowired
    private MdmPersonUtils mdmPersonUtils;
    @RequestMapping("/util")
    public void test6(){
        String groupFullCode = mdmPersonUtils.checkIfOrganizationExist(
                mdmPersonUtils.checkIfPersonCodeExist("77265").getGroupBelongDepartmentCode()
        ).getGroupFullCode();
        System.out.println(groupFullCode);
    }


    @ResponseBody
    @GetMapping("/gate")
    public String gate(String appUserToken){
//        synMdm2MimService.initMdmStats2Mim(MdmDataTypeEnum.MDM_PERSON);
//        synMdm2MimService.initMdmStats2Mim(MdmDataTypeEnum.MDM_ORGANIZATION);
//        synMdm2MimService.initMdmStats2Mim(MdmDataTypeEnum.MDM_POST);
        synMdm2MimService.updateMdmStats2Mim(MdmDataTypeEnum.MDM_PERSON);
        synMdm2MimService.updateMdmStats2Mim(MdmDataTypeEnum.MDM_ORGANIZATION);
//        ProcessCall compo = saip.compo();
//        saip.handlePass(compo);
        return "nihao";
    }

}

