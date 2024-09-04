package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ContractPartyAddCmd;
import com.uwdp.module.api.vo.cmd.ContractPartyUpdateCmd;
import com.uwdp.module.api.vo.dto.ContractPartyDto;
import com.uwdp.module.api.vo.excel.ContractPartyExcelExport;
import com.uwdp.module.api.vo.excel.ContractPartyExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 合同甲方 服务类
 * </p>
 *
 */
public interface IContractPartyService {

    /**
     * 查询
     */
    ContractPartyDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<ContractPartyDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<ContractPartyDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ContractPartyDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ContractPartyAddCmd addCmd);

    /**
     * 更新
     */
    void update(ContractPartyUpdateCmd updateCmd);

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
    ExcelParseDTO<ContractPartyExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ContractPartyExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ContractPartyExcelExport> list, HttpServletResponse response);

}
