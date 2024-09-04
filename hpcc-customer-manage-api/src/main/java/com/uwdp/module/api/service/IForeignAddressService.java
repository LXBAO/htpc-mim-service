package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ForeignAddressAddCmd;
import com.uwdp.module.api.vo.cmd.ForeignAddressUpdateCmd;
import com.uwdp.module.api.vo.dto.ForeignAddressDto;
import com.uwdp.module.api.vo.excel.ForeignAddressExcelExport;
import com.uwdp.module.api.vo.excel.ForeignAddressExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 国外地址 服务类
 * </p>
 *
 */
public interface IForeignAddressService {

    /**
     * 查询
     */
    ForeignAddressDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<ForeignAddressDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ForeignAddressDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ForeignAddressDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ForeignAddressAddCmd addCmd);

    /**
     * 更新
     */
    void update(ForeignAddressUpdateCmd updateCmd);

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
    ExcelParseDTO<ForeignAddressExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ForeignAddressExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ForeignAddressExcelExport> list, HttpServletResponse response);

}
