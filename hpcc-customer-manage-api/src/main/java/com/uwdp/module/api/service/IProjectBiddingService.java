package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ProjectBiddingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectBiddingUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectBiddingDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectBiddingWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectBiddingExcelExport;
import com.uwdp.module.api.vo.excel.ProjectBiddingExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目投标 服务类
 * </p>
 *
 */
public interface IProjectBiddingService {

    /**
     * 查询
     */
    ProjectBiddingDto get(Long id);

    /**
     * 根据项目编号
     */
    ProjectBiddingDto getProjectNumber(String projectNumber);

    /**
     * 通过主键list查询
     */
    List<ProjectBiddingDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ProjectBiddingDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 新分页查询
     */
    SearchResult<ProjectBiddingWorkflowDto> pageById(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectBiddingDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectBiddingAddCmd addCmd);

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(ProjectBiddingAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectBiddingUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id, String projectNumber);

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
    ExcelParseDTO<ProjectBiddingExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectBiddingExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectBiddingExcelExport> list, HttpServletResponse response);

}
