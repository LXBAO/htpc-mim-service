package com.uwdp.module.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.MdmPostAddCmd;
import com.uwdp.module.api.vo.cmd.MdmPostUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmPostDto;
import com.uwdp.module.api.vo.excel.MdmPostExcelExport;
import com.uwdp.module.api.vo.excel.MdmPostExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 主数据-岗位 服务类
 * </p>
 *
 */
public interface IMdmPostService {

    /**
     * 查询
     */
    MdmPostDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<MdmPostDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<MdmPostDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<MdmPostDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(MdmPostAddCmd addCmd);

    /**
     * 更新
     */
    void update(MdmPostUpdateCmd updateCmd);

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
    ExcelParseDTO<MdmPostExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<MdmPostExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<MdmPostExcelExport> list, HttpServletResponse response);

}
