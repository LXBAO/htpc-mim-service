package com.uwdp.mdm.controller;

import cn.hutool.core.util.IdUtil;
import cn.net.ceec.hpcc.mdm.sdk.enums.MdmDataTypeEnum;
import cn.net.ceec.hpcc.mdm.sdk.util.MdmUtil;
import cn.net.ceec.hpcc.mdm.sdk.vo.*;
import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.workflow.sdk.core.call.event.ProcessCallEvent;
import com.uwdp.mdm.service.QueryMdmService;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.service.IMdmPostService;
import com.uwdp.module.api.vo.cmd.MdmPersonAddCmd;
import com.uwdp.workflow.util.FunctionalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/mdm/accept")
@Api(tags = "accept")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "accept")
@Validated
public class MdmStatController {

    private final MdmUtil mdmUtil;

    private final IMdmPersonService mdmPersonService;
    private final IMdmOrganizationService mdmOrganizationService;
    private final IMdmPostService mdmPostService;
    private final QueryMdmService queryMdmService;

    @PostMapping({"/msg"})
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

        String mdmType = noticeMessage.getMdmType();
        if (MdmDataTypeEnum.MDM_PERSON.name().equals(mdmType)) {
//            mdmPersonService.get(Long.valueOf(noticeMessage.getMdmId());
//            if (mdmPersonDto!=null){
//
//            }
//            BaseSingleResponse<HashMap> baseSingleResponse = mdmUtil.readToGet(noticeMessage.getCallbackUrl(), baseQueryRequest, MdmPersonAddCmd.class);
//            HashMap result = baseSingleResponse.getData();
//            mdmPersonService.add();
        } else if (MdmDataTypeEnum.MDM_ORGANIZATION.name().equals(mdmType)) {

        }else if (MdmDataTypeEnum.MDM_POST.name().equals(mdmType)) {

        }
        BaseSingleResponse<HashMap> baseSingleResponse = mdmUtil.readToGet(noticeMessage.getCallbackUrl(), baseQueryRequest, HashMap.class);
        HashMap result = baseSingleResponse.getData();


//        Consumer<ProcessCallEvent> processCallConsumer = callBackTimeMap.get(callBackTime);
//        FunctionalUtil.predicateAndAccept(processCallConsumer, Objects::nonNull, t -> t.accept(processCallEvent));
        // 接入方处理数据的逻辑，这里演示只做打印处理。
        log.info("通过订阅主数据接口获取下发的数据: {}\n{}", noticeMessage.getMdmType(), result);
        Response response = BaseResponse.buildSuccess();
        return response;
    }



    @ResponseBody
    @GetMapping({"/getOrganizationTree"})
    @ApiOperation(value = "获取单位部门树状json", notes = "getOrganizationTree")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public String getOrganizationTree() {
        return queryMdmService.get().toJSONString();
    }

}
