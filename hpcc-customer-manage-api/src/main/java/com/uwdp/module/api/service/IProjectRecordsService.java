package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectRecordsDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectRTBWSIDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectRecordsWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectRecordsExcelExport;
import com.uwdp.module.api.vo.excel.ProjectRecordsExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目登记 服务类
 * </p>
 *
 */
public interface IProjectRecordsService {

    /**
     * 查询
     */
    ProjectRecordsDto get(Long id);

    /**
     * 查询所有项目信息
     * @param paraMap
     * @return
     */
    List<ProjectRTBWSIDto> getProject(Map<String, Object> paraMap);

    /**
     * 通过主键list查询
     */
    List<ProjectRecordsDto> listByIds(List<Long> idList);

    /**
     * 通过条件查询条数
     */
    Integer getProjectCount(String projectName, String location,String ownerUnit, String industryCategor);

    /**
     * 分页查询
     */
    SearchResult<ProjectRecordsWorkflowDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 分页查询
     */
    SearchResult<ProjectRecordsWorkflowDto> pageHeadlineByParam(Map<String, Object> paraMap);

    SearchResult<ProjectRecordsWorkflowDto> pageHeadlineByParam0(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectRecordsDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectRecordsAddCmd addCmd);

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(ProjectRecordsAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectRecordsUpdateCmd updateCmd);

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
    ExcelParseDTO<ProjectRecordsExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectRecordsExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectRecordsExcelExport> list, HttpServletResponse response);

}
