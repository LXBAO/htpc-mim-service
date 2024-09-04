package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.uwdp.module.api.vo.cmd.ProjectEnableAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectEnableDto;
import com.uwdp.module.api.vo.excel.ProjectEnableExcelExport;
import com.uwdp.module.api.vo.excel.ProjectEnableExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目赋能 服务类
 * </p>
 *
 */
public interface IProjectEnableService {

    /**
     * 查询
     */
    ProjectEnableDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<ProjectEnableDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ProjectEnableDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectEnableDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectEnableAddCmd addCmd);

    /**
     * 新增
     */
    Long addThenReturnId(ProjectEnableAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectEnableUpdateCmd updateCmd);

    /**
     * 在流程中更新领导评分建议
     */
    void updateScoreList(ProjectEnableUpdateCmd updateCmd);

    /**
     * 任务回调后更新领导评分建议
     */
    void updateScoreList(TaskCall taskCall);

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
    ExcelParseDTO<ProjectEnableExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectEnableExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectEnableExcelExport> list, HttpServletResponse response);

}
