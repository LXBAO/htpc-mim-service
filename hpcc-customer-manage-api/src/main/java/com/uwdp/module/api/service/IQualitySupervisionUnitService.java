package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.QualitySupervisionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.QualitySupervisionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.QualitySupervisionUnitDto;
import com.uwdp.module.api.vo.excel.QualitySupervisionUnitExcelExport;
import com.uwdp.module.api.vo.excel.QualitySupervisionUnitExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 质量监督单位 服务类
 * </p>
 *
 */
public interface IQualitySupervisionUnitService {

    /**
     * 查询
     */
    QualitySupervisionUnitDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<QualitySupervisionUnitDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<QualitySupervisionUnitDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<QualitySupervisionUnitDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(QualitySupervisionUnitAddCmd addCmd);

    /**
     * 更新
     */
    void update(QualitySupervisionUnitUpdateCmd updateCmd);

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
    ExcelParseDTO<QualitySupervisionUnitExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<QualitySupervisionUnitExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<QualitySupervisionUnitExcelExport> list, HttpServletResponse response);

}
