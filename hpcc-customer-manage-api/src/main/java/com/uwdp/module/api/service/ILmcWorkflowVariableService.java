package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.LmcWorkflowVariableAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowVariableUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowVariableDto;
import com.uwdp.module.api.vo.excel.LmcWorkflowVariableExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowVariableExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 流程表表单数据 服务类
 * </p>
 *
 */
public interface ILmcWorkflowVariableService {

    /**
     * 查询
     */
    LmcWorkflowVariableDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<LmcWorkflowVariableDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<LmcWorkflowVariableDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<LmcWorkflowVariableDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(LmcWorkflowVariableAddCmd addCmd);

    /**
     * 更新
     */
    void update(LmcWorkflowVariableUpdateCmd updateCmd);

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
    ExcelParseDTO<LmcWorkflowVariableExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<LmcWorkflowVariableExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<LmcWorkflowVariableExcelExport> list, HttpServletResponse response);

}
