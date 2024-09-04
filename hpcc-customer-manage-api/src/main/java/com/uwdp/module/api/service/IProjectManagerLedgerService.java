package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ProjectManagerLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectManagerLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectManagerLedgerDto;
import com.uwdp.module.api.vo.excel.ProjectManagerLedgerExcelExport;
import com.uwdp.module.api.vo.excel.ProjectManagerLedgerExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目经理台账 服务类
 * </p>
 *
 */
public interface IProjectManagerLedgerService {

    /**
     * 查询
     */
    ProjectManagerLedgerDto get(Long fdId);

    /**
     * 根据hrid查询
     */
    ProjectManagerLedgerDto detailByid(String hrId);

    /**
     * 通过主键list查询
     */
    List<ProjectManagerLedgerDto> listByIds(List<Long> fdIdList);

    /**
     * 分页查询
     */
    SearchResult<ProjectManagerLedgerDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ProjectManagerLedgerDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ProjectManagerLedgerAddCmd addCmd);

    /**
     * 更新
     */
    void update(ProjectManagerLedgerUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long fdId);

    /**
     * 批量删除
     */
    void batchDelete(String fdIds);

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
    ExcelParseDTO<ProjectManagerLedgerExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ProjectManagerLedgerExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ProjectManagerLedgerExcelExport> list, HttpServletResponse response);

}
