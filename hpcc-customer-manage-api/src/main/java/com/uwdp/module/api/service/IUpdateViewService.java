package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.UpdateViewAddCmd;
import com.uwdp.module.api.vo.cmd.UpdateViewUpdateCmd;
import com.uwdp.module.api.vo.dto.UpdateViewDto;
import com.uwdp.module.api.vo.excel.UpdateViewExcelExport;
import com.uwdp.module.api.vo.excel.UpdateViewExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 更新查看 服务类
 * </p>
 *
 */
public interface IUpdateViewService {

    /**
     * 查询
     */
    UpdateViewDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<UpdateViewDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<UpdateViewDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<UpdateViewDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(UpdateViewAddCmd addCmd);

    /**
     * 更新
     */
    void update(UpdateViewUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id);

    /**
     * 根据rowId删除
     */
    void deleteByRowId(String rowId);

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
    ExcelParseDTO<UpdateViewExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<UpdateViewExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<UpdateViewExcelExport> list, HttpServletResponse response);

}
