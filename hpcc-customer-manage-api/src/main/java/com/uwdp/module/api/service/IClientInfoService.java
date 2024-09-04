package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.ClientInfoUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientInfoDto;
import com.uwdp.module.api.vo.dto.searcher.ClientInfoWorkflowDto;
import com.uwdp.module.api.vo.excel.ClientInfoExcelExport;
import com.uwdp.module.api.vo.excel.ClientInfoExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 客户信息总表 服务类
 * </p>
 *
 */
public interface IClientInfoService {

    /**
     * 查询
     */
    ClientInfoDto get(Long fdId);

    /**
     * 通过主键list查询
     */
    List<ClientInfoDto> listByIds(List<Long> fdIdList);

    /**
     * 通过条件查询条数
     */
    Integer getClientInfoCount(String fdName,String fdUnit);


    /**
     * 分页查询
     */
    SearchResult<ClientInfoDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 只返回审批通过的客户
     */
    SearchResult<ClientInfoWorkflowDto> pageThatPassed(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ClientInfoDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ClientInfoAddCmd addCmd);

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(ClientInfoAddCmd addCmd);

    /**
     * 更新
     */
    void update(ClientInfoUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Long fdId);

    /**
     * 批量删除
     */
    void batchDelete(String fdIds);

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
    ExcelParseDTO<ClientInfoExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ClientInfoExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ClientInfoExcelExport> list, HttpServletResponse response);

}
