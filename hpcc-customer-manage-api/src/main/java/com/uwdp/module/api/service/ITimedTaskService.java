package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.TimedTaskAddCmd;
import com.uwdp.module.api.vo.cmd.TimedTaskUpdateCmd;
import com.uwdp.module.api.vo.dto.TimedTaskDto;
import com.uwdp.module.api.vo.excel.TimedTaskExcelExport;
import com.uwdp.module.api.vo.excel.TimedTaskExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 */
public interface ITimedTaskService {

    /**
     * 查询
     */
    TimedTaskDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<TimedTaskDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<TimedTaskDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<TimedTaskDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(TimedTaskAddCmd addCmd);

    /**
     * 更新
     */
    void update(TimedTaskUpdateCmd updateCmd);

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
    ExcelParseDTO<TimedTaskExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<TimedTaskExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<TimedTaskExcelExport> list, HttpServletResponse response);

}
