package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.DBConfigAddCmd;
import com.uwdp.module.api.vo.cmd.DBConfigUpdateCmd;
import com.uwdp.module.api.vo.dto.DBConfigDto;
import com.uwdp.module.api.vo.excel.DBConfigExcelExport;
import com.uwdp.module.api.vo.excel.DBConfigExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 数据库连接配置 服务类
 * </p>
 *
 */
public interface IDBConfigService {

    /**
     * 查询
     */
    DBConfigDto get(Long fdId);

    /**
     * 通过主键list查询
     */
    List<DBConfigDto> listByIds(List<Long> fdIdList);

    /**
     * 分页查询
     */
    SearchResult<DBConfigDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<DBConfigDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(DBConfigAddCmd addCmd);

    /**
     * 更新
     */
    void update(DBConfigUpdateCmd updateCmd);

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
    ExcelParseDTO<DBConfigExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<DBConfigExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<DBConfigExcelExport> list, HttpServletResponse response);

}
