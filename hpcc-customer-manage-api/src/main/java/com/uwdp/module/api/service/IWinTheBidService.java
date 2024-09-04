package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.WinTheBidAddCmd;
import com.uwdp.module.api.vo.cmd.WinTheBidUpdateCmd;
import com.uwdp.module.api.vo.dto.WinTheBidDto;
import com.uwdp.module.api.vo.dto.searcher.WinTheBidWorkflowDto;
import com.uwdp.module.api.vo.excel.WinTheBidExcelExport;
import com.uwdp.module.api.vo.excel.WinTheBidExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 项目中标 服务类
 * </p>
 *
 */
public interface IWinTheBidService {

    /**
     * 查询
     */
    WinTheBidDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<WinTheBidDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<WinTheBidDto> pageByParam(Map<String, Object> paraMap);


    /**
     * 分页查询
     * @param paraMap
     * @return
     */
    SearchResult<WinTheBidWorkflowDto> workflowPageByParam(Map<String, Object> paraMap);


    /**
     * 条件查询
     */
    List<WinTheBidDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(WinTheBidAddCmd addCmd);
    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(WinTheBidAddCmd addCmd);

    /**
     * 更新
     */
    void update(WinTheBidUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id,String projectNumber);

    /**
     * 批量删除
     */
    void batchDelete(String ids,String numbers);

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
    ExcelParseDTO<WinTheBidExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<WinTheBidExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<WinTheBidExcelExport> list, HttpServletResponse response);

}
