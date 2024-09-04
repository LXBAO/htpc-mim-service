package com.uwdp.module.web.controller.v2;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.query.PromptQuery;
import com.uwdp.utils.NoToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 信息提示 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/prompt-v1-0-0")
@Api(tags = "信息提示")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "信息提示")
@Validated
public class PromptControllerV2 {

    private final IPromptService promptService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="prompt")
    public PageResponse<PromptDto> page(PromptQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            List<PromptDto> promptList = promptService.searchByParam(map);
            return PageResponse.of(promptList, promptList.size(), paramQuery.getPageSize(), paramQuery.getPageIndex());

        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="prompt")
    public SingleResponse<PromptDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(promptService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detailByQid")
    @ApiOperation(value = "详情接口", notes="prompt")
    public SingleResponse<PromptDto> getByQid(@ApiParam(value = "主键", required = true) @RequestParam(name = "qid") Long qid) {
        try {
            return SingleResponse.of(promptService.getByQid(qid));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @NoToken
    @GetMapping("/haveReadByOa")
    @ApiOperation(value = "将证书提醒设置成已阅", notes="prompt")
    public SingleResponse<Object> haveReadByOa(@RequestParam(name = "id") Long id,
                                               @RequestParam(name = "promptId") String promptId,
                                               @RequestParam(name = "flag") Integer flag) {
        try {
            return SingleResponse.of(promptService.haveReadByOa(id, promptId, flag));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
