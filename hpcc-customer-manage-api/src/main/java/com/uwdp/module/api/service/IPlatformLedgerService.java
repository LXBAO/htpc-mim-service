package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.PlatformLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.PlatformLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.PlatformLedgerDto;
import com.uwdp.module.api.vo.excel.PlatformLedgerExcelExport;
import com.uwdp.module.api.vo.excel.PlatformLedgerExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 平台台账 服务类
 * </p>
 *
 */
public interface IPlatformLedgerService {

    /**
     * 查询
     */
    PlatformLedgerDto get(Long fdId);

    /**
     * 通过主键list查询
     */
    List<PlatformLedgerDto> listByIds(List<Long> fdIdList);

    /**
     * 分页查询
     */
    SearchResult<PlatformLedgerDto> pageByParam(Map<String, Object> paraMap);

    List<PlatformLedgerDto> searchAll(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<PlatformLedgerDto> searchByParam(Map<String, Object> paraMap);

    PlatformLedgerDto getPassword(Long fdId);

    /**
     * 新增
     */
    void add(PlatformLedgerAddCmd addCmd);

    /**
     * 更新
     */
    void update(PlatformLedgerUpdateCmd updateCmd);

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
    ExcelParseDTO<PlatformLedgerExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<PlatformLedgerExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<PlatformLedgerExcelExport> list, HttpServletResponse response);

}
