package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.RequestLogAddCmd;
import com.uwdp.module.api.vo.cmd.RequestLogUpdateCmd;
import com.uwdp.module.api.vo.dto.RequestLogDto;
import com.uwdp.module.api.vo.excel.RequestLogExcelExport;
import com.uwdp.module.api.vo.excel.RequestLogExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 流程日志 服务类
 * </p>
 *
 */
public interface IRequestLogService {

    /**
     * 查询
     */
    RequestLogDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<RequestLogDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<RequestLogDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<RequestLogDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(RequestLogAddCmd addCmd);

    /**
     * 更新
     */
    void update(RequestLogUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id);

    /**
     * 批量删除
     */
    void batchDelete(String ids);

    /**
     * 下载Excel模板
     */
    void excelTemplate(HttpServletResponse response);

    /**
     * 导出Excel数据
     */
    void excelExport(Map<String, Object> map, HttpServletResponse response);

    /**
     * 解析上传的Excel数据
     */
    ExcelParseDTO<RequestLogExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<RequestLogExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<RequestLogExcelExport> list, HttpServletResponse response);

}
