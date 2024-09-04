package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDto;
import com.uwdp.module.api.vo.dto.searcher.MarketDmpTagWorkflowDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelExport;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 市场部分公司年度指标 服务类
 * </p>
 *
 */
public interface IMarketDmpTagService {

    /**
     * 查询
     */
    MarketDmpTagDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<MarketDmpTagDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<MarketDmpTagDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 分页查询
     */
    SearchResult<MarketDmpTagWorkflowDto> pageThatPassed(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<MarketDmpTagDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(MarketDmpTagAddCmd addCmd) throws Exception;

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(MarketDmpTagAddCmd addCmd);

    /**
     * 更新
     */
    void update(MarketDmpTagUpdateCmd updateCmd);

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
    ExcelParseDTO<MarketDmpTagExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<MarketDmpTagExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<MarketDmpTagExcelExport> list, HttpServletResponse response);

}
