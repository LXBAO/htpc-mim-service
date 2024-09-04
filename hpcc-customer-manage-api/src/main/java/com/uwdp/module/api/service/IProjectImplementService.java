package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ProjectBiddingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectImplementAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectImplementUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectImplementDto;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelExport;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目实施 服务类
 * </p>
 *
 */
public interface IProjectImplementService {

    /**
     * 查询
     */
    ProjectImplementDto get(Long id);

    /**
     * 查询
     */
    ProjectImplementDto getProjectNumber(String projectNumber);

    /**
     * 通过主键list查询
     */
    List<ProjectImplementDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ProjectImplementDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectImplementDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectImplementAddCmd addCmd);

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(ProjectImplementAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectImplementUpdateCmd updateCmd);

    void update2(ProjectImplementUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id,String projectNumber);

    /**
     * 批量删除
     */
    void batchDelete(String ids,String numbers);

    void entryFile(String ids);

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
    ExcelParseDTO<ProjectImplementExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectImplementExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectImplementExcelExport> list, HttpServletResponse response);

}
