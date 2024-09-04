package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.CALedgerAddCmd;
import com.uwdp.module.api.vo.cmd.CALedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.CALedgerDto;
import com.uwdp.module.api.vo.dto.searcher.CALedgerCerInfoDto;
import com.uwdp.module.api.vo.excel.CALedgerExcelExport;
import com.uwdp.module.api.vo.excel.CALedgerExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * CA台账 服务类
 * </p>
 *
 */
public interface ICALedgerService {

    /**
     * 查询
     */
    CALedgerDto get(Long fdId);

    /**
     * 通过主键list查询
     */
    List<CALedgerDto> listByIds(List<Long> fdIdList);

    /**
     * 分页查询
     */
    SearchResult<CALedgerDto> pageByParam(Map<String, Object> paraMap);
    List<CALedgerCerInfoDto> searchAll(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<CALedgerDto> searchByParam(Map<String, Object> paraMap);

    CALedgerDto getPassword(Long fdId);

    /**
     * 新增
     */
    void add(CALedgerAddCmd addCmd);

    /**
     * 更新
     */
    void update(CALedgerUpdateCmd updateCmd);

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
    ExcelParseDTO<CALedgerExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<CALedgerExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<CALedgerExcelExport> list, HttpServletResponse response);

}
