package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.PerformanceLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.PerformanceLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.PerformanceLedgerDto;
import com.uwdp.module.api.vo.excel.PerformanceLedgerExcelExport;
import com.uwdp.module.api.vo.excel.PerformanceLedgerExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 业绩台账 服务类
 * </p>
 *
 */
public interface IPerformanceLedgerService {

    /**
     * 查询
     */
    PerformanceLedgerDto get(Long fdId);

    /**
     * 通过主键list查询
     */
    List<PerformanceLedgerDto> listByIds(List<Long> fdIdList);

    /**
     * 分页查询
     */
    SearchResult<PerformanceLedgerDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<PerformanceLedgerDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(PerformanceLedgerAddCmd addCmd);

    /**
     * 更新
     */
    void update(PerformanceLedgerUpdateCmd updateCmd);

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
    ExcelParseDTO<PerformanceLedgerExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<PerformanceLedgerExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<PerformanceLedgerExcelExport> list, HttpServletResponse response);

}
