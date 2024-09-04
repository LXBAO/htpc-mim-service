package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ProjectChiefEngineerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectChiefEngineerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectChiefEngineerDto;
import com.uwdp.module.api.vo.excel.ProjectChiefEngineerExcelExport;
import com.uwdp.module.api.vo.excel.ProjectChiefEngineerExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目总工 服务类
 * </p>
 *
 */
public interface IProjectChiefEngineerService {

    /**
     * 查询
     */
    ProjectChiefEngineerDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<ProjectChiefEngineerDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ProjectChiefEngineerDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectChiefEngineerDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectChiefEngineerAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectChiefEngineerUpdateCmd updateCmd);

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
    ExcelParseDTO<ProjectChiefEngineerExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectChiefEngineerExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectChiefEngineerExcelExport> list, HttpServletResponse response);

}
