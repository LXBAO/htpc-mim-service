package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.LmcWorkflowConfigAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowConfigUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowConfigDto;
import com.uwdp.module.api.vo.excel.LmcWorkflowConfigExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowConfigExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 流程配置表 服务类
 * </p>
 *
 */
public interface ILmcWorkflowConfigService {

    /**
     * 查询
     */
    LmcWorkflowConfigDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<LmcWorkflowConfigDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<LmcWorkflowConfigDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<LmcWorkflowConfigDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(LmcWorkflowConfigAddCmd addCmd);

    /**
     * 更新
     */
    void update(LmcWorkflowConfigUpdateCmd updateCmd);

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
    ExcelParseDTO<LmcWorkflowConfigExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<LmcWorkflowConfigExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<LmcWorkflowConfigExcelExport> list, HttpServletResponse response);

}
