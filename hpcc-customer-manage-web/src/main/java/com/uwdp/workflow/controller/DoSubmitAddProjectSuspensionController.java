package com.uwdp.workflow.controller;

import com.alibaba.cola.dto.Response;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.jdbcutils.DatabaseReaderUtil;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionAddCmd;
import com.uwdp.utils.StringUtils;
import com.uwdp.workflow.service.SubmitAddProjectSuspensionServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hpcc-customer-manage/v1_0/workflow/project-suspension")
public class DoSubmitAddProjectSuspensionController {

    private final SubmitAddProjectSuspensionServiceImpl suspensionService;

    private final DatabaseReaderUtil readerUtil;

    private final StringUtils stringUtils;

    @PostMapping("/submit")
    @ApiOperation(value = "项目中止发起流程接口", notes = "submit")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated ProjectSuspensionAddCmd addCmd) {
        try {
            Response add = suspensionService.add(addCmd);
            String findSql="select projectPhase from t_projectrecords where id='"+addCmd.getProjectId()+"'";
            List<Map> list=readerUtil.findData("local",findSql);
            String projectPhase=stringUtils.getStr(list.get(0).get("projectphase"));
            String updateSql="update t_projectrecords set projectPhase='7',projectPhaseOld='"+projectPhase+"' where id='"+addCmd.getProjectId()+"'";
            readerUtil.operationData("local",updateSql);
            return add;
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/reStartProcess")
    @ApiOperation(value = "流程驳回后重新推送流程接口", notes = "reStartProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response reStartProcess(@RequestBody @Validated ProjectSuspensionAddCmd addCmd) {
        try {
            Response response = suspensionService.reStartProcess(addCmd);
            String findSql="select projectPhase from t_projectrecords where id='"+addCmd.getProjectId()+"'";
            List<Map> list=readerUtil.findData("local",findSql);
            String projectPhase=stringUtils.getStr(list.get(0).get("projectphase"));
            String updateSql="update t_projectrecords set projectPhase='7',projectPhaseOld='"+projectPhase+"' where id='"+addCmd.getProjectId()+"'";
            readerUtil.operationData("local",updateSql);
            return response;
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/draftProcess")
    @ApiOperation(value = "草稿状态推送流程接口", notes = "draftProcess")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response draftProcess(@RequestBody @Validated ProjectSuspensionAddCmd addCmd) {
        try {
            Response response = suspensionService.draftProcess(addCmd);
            String findSql="select projectPhase from t_projectrecords where id='"+addCmd.getProjectId()+"'";
            List<Map> list=readerUtil.findData("local",findSql);
            String projectPhase=stringUtils.getStr(list.get(0).get("projectphase"));
            String updateSql="update t_projectrecords set projectPhase='7',projectPhaseOld='"+projectPhase+"' where id='"+addCmd.getProjectId()+"'";
            readerUtil.operationData("local",updateSql);
            return response;
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

}
