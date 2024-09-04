package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.CerInfoAddCmd;
import com.uwdp.module.api.vo.cmd.CerInfoUpdateCmd;
import com.uwdp.module.api.vo.dto.CerInfoDto;
import com.uwdp.module.api.vo.excel.CerInfoExcelExport;
import com.uwdp.module.api.vo.excel.CerInfoExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 证书信息 服务类
 * </p>
 *
 */
public interface ICerInfoService {

    /**
     * 查询
     */
    CerInfoDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<CerInfoDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<CerInfoDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<CerInfoDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(CerInfoAddCmd addCmd);

    /**
     * 更新
     */
    void update(CerInfoUpdateCmd updateCmd);

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
    ExcelParseDTO<CerInfoExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<CerInfoExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<CerInfoExcelExport> list, HttpServletResponse response);

}
