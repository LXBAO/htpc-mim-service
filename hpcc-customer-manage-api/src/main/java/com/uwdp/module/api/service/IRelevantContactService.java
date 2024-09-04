package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.RelevantContactAddCmd;
import com.uwdp.module.api.vo.cmd.RelevantContactUpdateCmd;
import com.uwdp.module.api.vo.dto.RelevantContactDto;
import com.uwdp.module.api.vo.excel.RelevantContactExcelExport;
import com.uwdp.module.api.vo.excel.RelevantContactExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 客户相关联系人 服务类
 * </p>
 *
 */
public interface IRelevantContactService {

    /**
     * 查询
     */
    RelevantContactDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<RelevantContactDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<RelevantContactDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<RelevantContactDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(RelevantContactAddCmd addCmd);

    /**
     * 更新
     */
    void update(RelevantContactUpdateCmd updateCmd);

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
    ExcelParseDTO<RelevantContactExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<RelevantContactExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<RelevantContactExcelExport> list, HttpServletResponse response);

}
