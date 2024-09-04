package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.LmcWorkflowAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowDto;
import com.uwdp.module.api.vo.excel.LmcWorkflowExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 流程表 服务类
 * </p>
 *
 */
public interface ILmcWorkflowService {

    /**
     * 查询
     */
    LmcWorkflowDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<LmcWorkflowDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<LmcWorkflowDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<LmcWorkflowDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(LmcWorkflowAddCmd addCmd);

    /**
     * 更新
     */
    void update(LmcWorkflowUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id);

    /**
     * 根据bizId删除
     */
    void deleteByBizId(String bizId);

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
    ExcelParseDTO<LmcWorkflowExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<LmcWorkflowExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<LmcWorkflowExcelExport> list, HttpServletResponse response);

}
