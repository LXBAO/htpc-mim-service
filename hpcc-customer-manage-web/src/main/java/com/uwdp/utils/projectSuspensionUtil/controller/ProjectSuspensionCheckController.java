package com.uwdp.utils.projectSuspensionUtil.controller;

import com.alibaba.cola.dto.SingleResponse;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.utils.projectSuspensionUtil.service.ProjectSuspensionCheckServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 项目中止校验控制类
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/suspension/check")
@Api(tags = "项目中止校验")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "项目中止校验")
@Validated
@Slf4j
public class ProjectSuspensionCheckController {

    private final ProjectSuspensionCheckServiceImpl suspensionCheckService;

    @GetMapping("/checkIsNull")
    @ApiOperation(value = "详情接口", notes="certificates")
    public SingleResponse<Boolean> checkIsNull(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") String id) {
        try {
            return SingleResponse.of(suspensionCheckService.checkIsNull(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/checkWorkflow")
    @ApiOperation(value = "详情接口", notes="certificates")
    public SingleResponse<Map> checkWorkflow(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") String id) {
        try {
            return SingleResponse.of(suspensionCheckService.checkWorkflow(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/getProjectNum")
    @ApiOperation(value = "详情接口", notes="certificates")
    public SingleResponse<Map> getProjectNum(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") String id) {
        try {
            return SingleResponse.of(suspensionCheckService.getProjectNum(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
