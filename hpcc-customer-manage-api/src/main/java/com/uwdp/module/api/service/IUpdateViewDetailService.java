package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.UpdateViewDetailAddCmd;
import com.uwdp.module.api.vo.cmd.UpdateViewDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.UpdateViewDetailDto;
import com.uwdp.module.api.vo.excel.UpdateViewDetailExcelExport;
import com.uwdp.module.api.vo.excel.UpdateViewDetailExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 更新查看详情 服务类
 * </p>
 *
 */
public interface IUpdateViewDetailService {

    /**
     * 查询
     */
    UpdateViewDetailDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<UpdateViewDetailDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<UpdateViewDetailDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<UpdateViewDetailDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(UpdateViewDetailAddCmd addCmd);

    /**
     * 更新
     */
    void update(UpdateViewDetailUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id);

    /**
     * 根据mdtId删除
     */
    void deleteByMdtId(String mdtId);

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
    ExcelParseDTO<UpdateViewDetailExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<UpdateViewDetailExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<UpdateViewDetailExcelExport> list, HttpServletResponse response);

}
