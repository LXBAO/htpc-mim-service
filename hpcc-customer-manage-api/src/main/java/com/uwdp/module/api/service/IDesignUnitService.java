package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.DesignUnitAddCmd;
import com.uwdp.module.api.vo.cmd.DesignUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.DesignUnitDto;
import com.uwdp.module.api.vo.excel.DesignUnitExcelExport;
import com.uwdp.module.api.vo.excel.DesignUnitExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 设计单位 服务类
 * </p>
 *
 */
public interface IDesignUnitService {

    /**
     * 查询
     */
    DesignUnitDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<DesignUnitDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<DesignUnitDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<DesignUnitDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(DesignUnitAddCmd addCmd);

    /**
     * 更新
     */
    void update(DesignUnitUpdateCmd updateCmd);

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
    ExcelParseDTO<DesignUnitExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<DesignUnitExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<DesignUnitExcelExport> list, HttpServletResponse response);

}
