package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.PerformanceManagementAddCmd;
import com.uwdp.module.api.vo.cmd.PerformanceManagementUpdateCmd;
import com.uwdp.module.api.vo.dto.PerformanceManagementDto;
import com.uwdp.module.api.vo.excel.PerformanceManagementExcelExport;
import com.uwdp.module.api.vo.excel.PerformanceManagementExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 业绩管理 服务类
 * </p>
 *
 */
public interface IPerformanceManagementService {

    /**
     * 查询
     */
    PerformanceManagementDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<PerformanceManagementDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<PerformanceManagementDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<PerformanceManagementDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(PerformanceManagementAddCmd addCmd);

    /**
     * 更新
     */
    void update(PerformanceManagementUpdateCmd updateCmd);

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
    ExcelParseDTO<PerformanceManagementExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<PerformanceManagementExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<PerformanceManagementExcelExport> list, HttpServletResponse response);

}
