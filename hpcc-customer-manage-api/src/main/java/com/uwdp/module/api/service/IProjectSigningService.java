package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ProjectSigningAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSigningUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectSigningDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectSigningRecordsWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectSigningExcelExport;
import com.uwdp.module.api.vo.excel.ProjectSigningExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目签约 服务类
 * </p>
 *
 */
public interface IProjectSigningService {

    /**
     * 查询
     */
    ProjectSigningDto get(Long id);

    /**
     * 查询
     */
    ProjectSigningDto getProjectNumber(String projectNumber);

    /**
     * 通过主键list查询
     */
    List<ProjectSigningDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ProjectSigningDto> pageByParam(Map<String, Object> paraMap);


    SearchResult<ProjectSigningRecordsWorkflowDto> pageByParamTwo(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectSigningDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectSigningAddCmd addCmd);

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(ProjectSigningAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectSigningUpdateCmd updateCmd);

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
    ExcelParseDTO<ProjectSigningExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectSigningExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectSigningExcelExport> list, HttpServletResponse response);

}
