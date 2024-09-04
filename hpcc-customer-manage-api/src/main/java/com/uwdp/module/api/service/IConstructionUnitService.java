package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ConstructionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.ConstructionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.ConstructionUnitDto;
import com.uwdp.module.api.vo.excel.ConstructionUnitExcelExport;
import com.uwdp.module.api.vo.excel.ConstructionUnitExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 建设单位 服务类
 * </p>
 *
 */
public interface IConstructionUnitService {

    /**
     * 查询
     */
    ConstructionUnitDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<ConstructionUnitDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ConstructionUnitDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ConstructionUnitDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ConstructionUnitAddCmd addCmd);

    /**
     * 更新
     */
    void update(ConstructionUnitUpdateCmd updateCmd);

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
    ExcelParseDTO<ConstructionUnitExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ConstructionUnitExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ConstructionUnitExcelExport> list, HttpServletResponse response);

}
