package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.MdmOrganizationAddCmd;
import com.uwdp.module.api.vo.cmd.MdmOrganizationUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.excel.MdmOrganizationExcelExport;
import com.uwdp.module.api.vo.excel.MdmOrganizationExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 主数据-组织 服务类
 * </p>
 *
 */
public interface IMdmOrganizationService {
    /**
     * 根据集团code 获取下级分部code
     * @param groupBelongUnitCode
     * @return
     */
    List<String> getGroupListByGroupBelongUnitCode(String groupBelongUnitCode);

    /**
     * 查询
     */
    MdmOrganizationDto get(Long id);

    /**
     * 查询
     */
    MdmOrganizationDto getGroupCode(String groupCode);

    /**
     * 通过主键list查询
     */
    List<MdmOrganizationDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<MdmOrganizationDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<MdmOrganizationDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(MdmOrganizationAddCmd addCmd);

    /**
     * 更新
     */
    void update(MdmOrganizationUpdateCmd updateCmd);

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
    ExcelParseDTO<MdmOrganizationExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<MdmOrganizationExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<MdmOrganizationExcelExport> list, HttpServletResponse response);

}
