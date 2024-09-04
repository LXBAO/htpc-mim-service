package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.MdmBpDeptAddCmd;
import com.uwdp.module.api.vo.cmd.MdmBpDeptUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmBpDeptDto;
import com.uwdp.module.api.vo.excel.MdmBpDeptExcelExport;
import com.uwdp.module.api.vo.excel.MdmBpDeptExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 主数据-业务编码所对应部门 服务类
 * </p>
 *
 */
public interface IMdmBpDeptService {

    /**
     * 查询
     */
    MdmBpDeptDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<MdmBpDeptDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<MdmBpDeptDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<MdmBpDeptDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(MdmBpDeptAddCmd addCmd);

    /**
     * 更新
     */
    void update(MdmBpDeptUpdateCmd updateCmd);

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
    ExcelParseDTO<MdmBpDeptExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<MdmBpDeptExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<MdmBpDeptExcelExport> list, HttpServletResponse response);

}
