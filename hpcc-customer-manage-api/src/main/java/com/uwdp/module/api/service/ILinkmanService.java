package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.LinkmanAddCmd;
import com.uwdp.module.api.vo.cmd.LinkmanUpdateCmd;
import com.uwdp.module.api.vo.dto.LinkmanDto;
import com.uwdp.module.api.vo.excel.LinkmanExcelExport;
import com.uwdp.module.api.vo.excel.LinkmanExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 联系人 服务类
 * </p>
 *
 */
public interface ILinkmanService {

    /**
     * 查询
     */
    LinkmanDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<LinkmanDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<LinkmanDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<LinkmanDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(LinkmanAddCmd addCmd);

    /**
     * 更新
     */
    void update(LinkmanUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long id);

    /**
     * 根据项目id删除
     */
    void deleteById(String biddingId);

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
    ExcelParseDTO<LinkmanExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<LinkmanExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<LinkmanExcelExport> list, HttpServletResponse response);

}
