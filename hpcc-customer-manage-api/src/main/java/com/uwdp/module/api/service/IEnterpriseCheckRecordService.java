package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.EnterpriseCheckRecordAddCmd;
import com.uwdp.module.api.vo.cmd.EnterpriseCheckRecordUpdateCmd;
import com.uwdp.module.api.vo.dto.EnterpriseCheckRecordDto;
import com.uwdp.module.api.vo.excel.EnterpriseCheckRecordExcelExport;
import com.uwdp.module.api.vo.excel.EnterpriseCheckRecordExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 企查查记录 服务类
 * </p>
 *
 */
public interface IEnterpriseCheckRecordService {

    /**
     * 查询
     */
    EnterpriseCheckRecordDto get(Long id);

    EnterpriseCheckRecordDto getName(String name);

    /**
     * 通过主键list查询
     */
    List<EnterpriseCheckRecordDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<EnterpriseCheckRecordDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<EnterpriseCheckRecordDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(EnterpriseCheckRecordAddCmd addCmd);

    /**
     * 更新
     */
    void update(EnterpriseCheckRecordUpdateCmd updateCmd);

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
    ExcelParseDTO<EnterpriseCheckRecordExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<EnterpriseCheckRecordExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<EnterpriseCheckRecordExcelExport> list, HttpServletResponse response);

}
