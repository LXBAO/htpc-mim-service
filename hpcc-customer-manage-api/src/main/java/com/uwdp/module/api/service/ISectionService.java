package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.SectionAddCmd;
import com.uwdp.module.api.vo.cmd.SectionUpdateCmd;
import com.uwdp.module.api.vo.dto.SectionDto;
import com.uwdp.module.api.vo.excel.SectionExcelExport;
import com.uwdp.module.api.vo.excel.SectionExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 标段 服务类
 * </p>
 *
 */
public interface ISectionService {

    /**
     * 查询
     */
    SectionDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<SectionDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<SectionDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<SectionDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(SectionAddCmd addCmd);

    /**
     * 更新
     */
    void update(SectionUpdateCmd updateCmd);

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
    ExcelParseDTO<SectionExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<SectionExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<SectionExcelExport> list, HttpServletResponse response);

}
