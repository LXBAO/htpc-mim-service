package com.uwdp.module.web.controller;

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
import com.uwdp.config.AttachmentConfiguration;
import com.uwdp.module.api.service.IAttachmentService;
import com.uwdp.module.api.vo.cmd.AttachmentAddCmd;
import com.uwdp.module.api.vo.cmd.AttachmentUpdateCmd;
import com.uwdp.module.api.vo.dto.AttachmentDto;
import com.uwdp.module.api.vo.excel.AttachmentExcelExport;
import com.uwdp.module.api.vo.excel.AttachmentExcelImport;
import com.uwdp.module.api.vo.query.AttachmentQuery;
import com.uwdp.utils.FileUtils;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;
import java.util.List;

/**
 * <p>
 * 附件表 服务控制类
 * </p>
 */
@RestController
@RequestMapping("/hpcc-customer-manage/v1_0/module/attachment-v1-0-0")
@Api(tags = "附件表")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "附件表")
@Validated
@Slf4j
public class AttachmentController {

    private final IAttachmentService attachmentService;
    private final AttachmentConfiguration attachmentConfiguration;


    @PostMapping("/upload")
    public SingleResponse<String> upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest req) throws IOException {
        try {
            String url = attachmentConfiguration.getUploadUrl();
            Integer maxSize = attachmentConfiguration.getMaxSize();
            String filePath = FileUtils.upload(uploadFile, url, maxSize);
            return SingleResponse.of(filePath);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @GetMapping("/download")
    public void download(@RequestParam("url") String url, @RequestParam("name") String name, HttpServletResponse response) throws IOException {
        try {
            log.info("文件开始下载：" + url);
            FileInputStream fileInputStream = new FileInputStream(new File(url));
            response.setHeader("content-disposition", "attachment;filename=" + name);
            ;
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8"));
            response.setCharacterEncoding("UTF-8");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            int len = 0;

            //设置一个缓冲区，大小取决于文件内容的大小
            byte[] buffer = new byte[1024];
            //每次读入缓冲区的数据，直到缓冲区无数据
            while ((len = fileInputStream.read(buffer)) != -1) {
                //输出缓冲区的数据
                servletOutputStream.write(buffer, 0, len);
            }
            servletOutputStream.close();
            fileInputStream.close();

            log.info("文件下载成功：" + url);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询接口", notes = "attachment")
    public PageResponse<AttachmentDto> page(AttachmentQuery paramQuery, HttpServletRequest request) {
        try {
            Map<String, Object> map = MapUtils.flat(request.getParameterMap());
            SearchResult<AttachmentDto> search = attachmentService.pageByParam(map);
            return PageResponse.of(search.getDataList(), search.getTotalCount().intValue(),
                    paramQuery.getPageSize(), paramQuery.getPageIndex());
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping("/detail")
    @ApiOperation(value = "详情接口", notes = "attachment")
    public SingleResponse<AttachmentDto> get(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            return SingleResponse.of(attachmentService.get(id));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    /**
     * 根据订单信息获取附件
     *
     * @param addCmd
     * @return
     */
    @PostMapping("/getAttachmentListByOrderId")
    @ApiOperation(value = "根据订单id获取附件", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.ADD)
    public SingleResponse<List<AttachmentDto>> getAttachmentListByOrderId(@RequestBody @Validated AttachmentAddCmd addCmd) {
        try {
            return SingleResponse.of(attachmentService.getAttachmentListByOrderId(addCmd.getOrderId()));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增接口", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.ADD)
    public Response add(@RequestBody @Validated AttachmentAddCmd addCmd) {
        try {
            attachmentService.add(addCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "编辑接口", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.UPDATE)
    public Response update(@RequestBody @Validated AttachmentUpdateCmd updateCmd) {
        try {
            attachmentService.update(updateCmd);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除接口", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response delete(@ApiParam(value = "主键", required = true) @RequestParam(name = "id") Long id) {
        try {
            attachmentService.delete(id);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除接口, ids以逗号分隔多个id", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.DELETE)
    public Response batchDelete(@ApiParam(value = "主键列表, 多个主键以逗号分割", required = true) @RequestParam(name = "ids") String ids) {
        try {
            attachmentService.batchDelete(ids);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @GetMapping({"/excel/template"})
    @ApiOperation(value = "下载Excel模板", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelTemplate(HttpServletResponse response) {
        try {
            attachmentService.excelTemplate(response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/export"})
    @ApiOperation(value = "导出Excel数据, 最多3000条", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.EXPORT)
    public void excelExport(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = MapUtils.flat(request.getParameterMap());
        try {
            attachmentService.excelExport(map, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/parse"})
    @ApiOperation(value = "解析上传的Excel数据", notes = "attachment")
    public SingleResponse<ExcelParseDTO<AttachmentExcelImport>> excelParse(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return SingleResponse.of(attachmentService.excelParse(file));
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/import"})
    @ApiOperation(value = "导入处理后的Excel数据", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.IMPORT)
    public Response excelImport(@RequestBody @Valid List<AttachmentExcelImport> list) {
        try {
            attachmentService.excelImport(list);
            return Response.buildSuccess();
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }

    @PostMapping({"/excel/error/download"})
    @ApiOperation(value = "导出错误的数据", notes = "attachment")
    @OperationLog(type = OperateTypeEnum.DOWNLOAD)
    public void excelErrorDownload(@RequestBody List<AttachmentExcelExport> list, HttpServletResponse response) {
        try {
            attachmentService.excelErrorDownload(list, response);
        } catch (Exception e) {
            throw new BizRuntimeException(e);
        }
    }
}
