package com.uwdp.module.web.controller;

import com.alibaba.cola.dto.SingleResponse;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.IUserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lx
 * @data 2023/6/29 15:17
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module")
@Api(tags = "用户信息")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "用户信息")
@Validated
public class UserController {

    @Autowired private IUserService userService;

    @GetMapping("/get/user/info")
    public SingleResponse<String> getUserInfo(@RequestParam("appUserToken") String appUserToken){
        return SingleResponse.of(userService.getUserInfo(appUserToken));
    }
}
