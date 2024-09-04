package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.SupervisionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.SupervisionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.SupervisionUnitDto;
import com.uwdp.module.api.vo.excel.SupervisionUnitExcelExport;
import com.uwdp.module.api.vo.excel.SupervisionUnitExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 监理单位 服务类
 * </p>
 *
 */
public interface ISupervisionUnitService {

    /**
     * 查询
     */
    SupervisionUnitDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<SupervisionUnitDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<SupervisionUnitDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<SupervisionUnitDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(SupervisionUnitAddCmd addCmd);

    /**
     * 更新
     */
    void update(SupervisionUnitUpdateCmd updateCmd);

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
    ExcelParseDTO<SupervisionUnitExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<SupervisionUnitExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<SupervisionUnitExcelExport> list, HttpServletResponse response);

}
