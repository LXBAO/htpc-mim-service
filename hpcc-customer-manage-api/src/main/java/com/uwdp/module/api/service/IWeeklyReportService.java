package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.WeeklyReportAddCmd;
import com.uwdp.module.api.vo.cmd.WeeklyReportUpdateCmd;
import com.uwdp.module.api.vo.dto.WeeklyReportDetailDto;
import com.uwdp.module.api.vo.dto.WeeklyReportDto;
import com.uwdp.module.api.vo.excel.WeeklyReportExcelExport;
import com.uwdp.module.api.vo.excel.WeeklyReportExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 周报主表信息 服务类
 * </p>
 *
 */
public interface IWeeklyReportService {
    /**
     * 根据填写人获取下周计划
     * @param personCode
     * @return
     */
    List<WeeklyReportDetailDto> getWeeklyReportDetailByPersonCode(Long personCode);

    /**
     * 查询
     */
    WeeklyReportDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<WeeklyReportDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<WeeklyReportDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<WeeklyReportDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(WeeklyReportAddCmd addCmd);

    /**
     * 更新
     */
    void update(WeeklyReportUpdateCmd updateCmd);

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
    ExcelParseDTO<WeeklyReportExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<WeeklyReportExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<WeeklyReportExcelExport> list, HttpServletResponse response);

}
