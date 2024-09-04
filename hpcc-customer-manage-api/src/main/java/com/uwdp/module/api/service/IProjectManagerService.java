package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ProjectManagerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectManagerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectManagerDto;
import com.uwdp.module.api.vo.excel.ProjectManagerExcelExport;
import com.uwdp.module.api.vo.excel.ProjectManagerExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目经理 服务类
 * </p>
 *
 */
public interface IProjectManagerService {

    /**
     * 查询
     */
    ProjectManagerDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<ProjectManagerDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ProjectManagerDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectManagerDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectManagerAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectManagerUpdateCmd updateCmd);

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
    ExcelParseDTO<ProjectManagerExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectManagerExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectManagerExcelExport> list, HttpServletResponse response);

}
