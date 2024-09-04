package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowTimelineDto;
import com.uwdp.module.api.vo.excel.LmcWorkflowTimelineExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowTimelineExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 流程时间线 服务类
 * </p>
 *
 */
public interface ILmcWorkflowTimelineService {

    /**
     * 查询
     */
    LmcWorkflowTimelineDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<LmcWorkflowTimelineDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<LmcWorkflowTimelineDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<LmcWorkflowTimelineDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(LmcWorkflowTimelineAddCmd addCmd);

    /**
     * 更新
     */
    void update(LmcWorkflowTimelineUpdateCmd updateCmd);

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
    ExcelParseDTO<LmcWorkflowTimelineExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<LmcWorkflowTimelineExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<LmcWorkflowTimelineExcelExport> list, HttpServletResponse response);

}
