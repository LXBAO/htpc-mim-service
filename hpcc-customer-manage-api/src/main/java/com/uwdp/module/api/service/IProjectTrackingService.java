package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ProjectBiddingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectTrackingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectTrackingUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectTrackingDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectTrackingWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectTrackingExcelExport;
import com.uwdp.module.api.vo.excel.ProjectTrackingExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目跟踪 服务类
 * </p>
 *
 */
public interface IProjectTrackingService {

    /**
     * 查询
     */
    ProjectTrackingDto get(Long id);

    /**
     * 查询
     */
    ProjectTrackingDto getProjectNumber(String projectNumber);

    /**
     * 通过主键list查询
     */
    List<ProjectTrackingDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ProjectTrackingDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 分页查询
     */
    SearchResult<ProjectTrackingWorkflowDto> pageByParamTwo(Map<String, Object> paraMap);
    /**
     * 分页查询
     */
    SearchResult<ProjectTrackingWorkflowDto> projectEnablePage(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectTrackingDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectTrackingAddCmd addCmd);

    /**
     * 新增项目登记内部协同单位
     */
    void addRecord(ProjectTrackingAddCmd addCmd);

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(ProjectTrackingAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectTrackingUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id,String projectNumber);

    /**
     * 批量删除
     */
    void batchDelete(String ids,String numbers);

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
    ExcelParseDTO<ProjectTrackingExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectTrackingExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectTrackingExcelExport> list, HttpServletResponse response);

}
