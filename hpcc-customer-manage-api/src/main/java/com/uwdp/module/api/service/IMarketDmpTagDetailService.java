package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.MarketDmpTagDetailAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDetailDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagDetailExcelExport;
import com.uwdp.module.api.vo.excel.MarketDmpTagDetailExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 指标明细 服务类
 * </p>
 *
 */
public interface IMarketDmpTagDetailService {

    /**
     * 查询
     */
    MarketDmpTagDetailDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<MarketDmpTagDetailDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<MarketDmpTagDetailDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<MarketDmpTagDetailDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(MarketDmpTagDetailAddCmd addCmd);

    /**
     * 更新
     */
    void update(MarketDmpTagDetailUpdateCmd updateCmd);

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
    ExcelParseDTO<MarketDmpTagDetailExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<MarketDmpTagDetailExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<MarketDmpTagDetailExcelExport> list, HttpServletResponse response);

}
