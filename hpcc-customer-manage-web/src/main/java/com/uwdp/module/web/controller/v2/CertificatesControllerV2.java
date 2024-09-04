package com.uwdp.module.web.controller.v2;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.api.service.ICertificatesService;
import com.uwdp.module.api.vo.cmd.CertificatesAddCmd;
import com.uwdp.module.api.vo.cmd.CertificatesUpdateCmd;
import com.uwdp.module.api.vo.dto.CertificatesDto;
import com.uwdp.module.api.vo.excel.CertificatesExcelExport;
import com.uwdp.module.api.vo.excel.CertificatesExcelImport;
import com.uwdp.module.api.vo.query.CertificatesQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 荣誉证书 服务控制类
 * </p>
 *
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v2_0/module/certificates-v1-0-0")
@Api(tags = "荣誉证书")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "荣誉证书")
@Validated
public class CertificatesControllerV2 {

    private final ICertificatesService certificatesService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes="certificates")
    public PageResponse<CertificatesDto> page(CertificatesQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            map.put("projectName-op","ct");
            map.put("honoraryTitle-op","ct");
            map.put("awardTime-op","ct");
            SearchResult<CertificatesDto> search = certificatesService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes="certificates")
    public SingleResponse<CertificatesDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(certificatesService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
