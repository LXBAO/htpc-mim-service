package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.DataAddCmd;
import com.uwdp.module.api.vo.cmd.DataUpdateCmd;
import com.uwdp.module.api.vo.dto.DataDto;
import com.uwdp.module.api.vo.excel.DataExcelExport;
import com.uwdp.module.api.vo.excel.DataExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 下拉列表维护 服务类
 * </p>
 *
 */
public interface IDataService {

    /**
     * 查询
     */
    DataDto get(Long id);

    /**
     * 查询资质信息\ca认证平台信息\招标平台信息
     */
    DataDto getBy(int type);

    /**
     * 通过主键list查询
     */
    List<DataDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<DataDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<DataDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(DataAddCmd addCmd);

    /**
     * 更新
     */
    void update(DataUpdateCmd updateCmd);

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
    ExcelParseDTO<DataExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<DataExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<DataExcelExport> list, HttpServletResponse response);

}
