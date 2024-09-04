package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectSuspensionDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectSuspensionWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectSuspensionExcelExport;
import com.uwdp.module.api.vo.excel.ProjectSuspensionExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目中止 服务类
 * </p>
 *
 */
public interface IProjectSuspensionService {

    /**
     * 查询
     */
    ProjectSuspensionDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<ProjectSuspensionDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ProjectSuspensionDto> pageByParam(Map<String, Object> paraMap);    /**

     * 分页查询
     */
    SearchResult<ProjectSuspensionWorkflowDto> pageThatPassed(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectSuspensionDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectSuspensionAddCmd addCmd);

    /**
     * 新增
     */
    Long addThenReturnId(ProjectSuspensionAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectSuspensionUpdateCmd updateCmd);

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
    ExcelParseDTO<ProjectSuspensionExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectSuspensionExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectSuspensionExcelExport> list, HttpServletResponse response);

}
