package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.AttachmentAddCmd;
import com.uwdp.module.api.vo.cmd.AttachmentUpdateCmd;
import com.uwdp.module.api.vo.dto.AttachmentDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.api.vo.excel.AttachmentExcelExport;
import com.uwdp.module.api.vo.excel.AttachmentExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 附件表 服务类
 * </p>
 *
 */
public interface IAttachmentService {

    void saveBatch(List<AttachmentAddCmd> addCmdList, String orderId, AttachmentOrderType type);

    void delBatchByOrderId(String orderId);

    List<AttachmentDto> getAttachmentListByOrderId(String orderId);
    /**
     * 查询
     */
    AttachmentDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<AttachmentDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<AttachmentDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<AttachmentDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(AttachmentAddCmd addCmd);

    /**
     * 更新
     */
    void update(AttachmentUpdateCmd updateCmd);

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
    ExcelParseDTO<AttachmentExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<AttachmentExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<AttachmentExcelExport> list, HttpServletResponse response);

}
