package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.MdmPersonAddCmd;
import com.uwdp.module.api.vo.cmd.MdmPersonUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.api.vo.excel.MdmPersonExcelExport;
import com.uwdp.module.api.vo.excel.MdmPersonExcelImport;
import com.uwdp.module.api.vo.query.MdmPersonQuery;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 主数据-人员 服务类
 * </p>
 *
 */
public interface IMdmPersonService {

    /**
     * 查询
     */
    MdmPersonDto get(Long id);

    SearchResult<MdmPersonDto> personInfoByGroupBelongUnitCode(MdmPersonQuery paramQuery, Map<String, String[]> paramsMap);

    MdmPersonDto get(String personCode);

    /**
     * 根据code查询
     */
    MdmPersonDto getPersonCodeDetail(String personCode);

    /**
     * 通过主键list查询
     */
    List<MdmPersonDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<MdmPersonDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<MdmPersonDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(MdmPersonAddCmd addCmd);

    /**
     * 更新
     */
    void update(MdmPersonUpdateCmd updateCmd);

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
    ExcelParseDTO<MdmPersonExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<MdmPersonExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<MdmPersonExcelExport> list, HttpServletResponse response);

}
