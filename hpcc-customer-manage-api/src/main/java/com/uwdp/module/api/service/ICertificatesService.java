package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.CertificatesAddCmd;
import com.uwdp.module.api.vo.cmd.CertificatesUpdateCmd;
import com.uwdp.module.api.vo.dto.CertificatesDto;
import com.uwdp.module.api.vo.excel.CertificatesExcelExport;
import com.uwdp.module.api.vo.excel.CertificatesExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 荣誉证书 服务类
 * </p>
 *
 */
public interface ICertificatesService {

    /**
     * 查询
     */
    CertificatesDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<CertificatesDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<CertificatesDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<CertificatesDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(CertificatesAddCmd addCmd);

    /**
     * 更新
     */
    void update(CertificatesUpdateCmd updateCmd);

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
    ExcelParseDTO<CertificatesExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<CertificatesExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<CertificatesExcelExport> list, HttpServletResponse response);

}
