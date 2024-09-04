package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanUpdateCmd;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.dto.searcher.CALedgerCerInfoDto;
import com.uwdp.module.api.vo.dto.searcher.VisitPlanCliNameDto;
import com.uwdp.module.api.vo.excel.VisitPlanExcelExport;
import com.uwdp.module.api.vo.excel.VisitPlanExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 公关关系计划 服务类
 * </p>
 *
 */
public interface IVisitPlanService {

    /**
     * 查询
     */
    VisitPlanDto get(Long id);

    /**
     * 通过主键list查询
     */
    List<VisitPlanDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<VisitPlanDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 查询所有计划信息
     * @param paraMap
     * @return
     */
    List<VisitPlanDto> searchAll(Map<String, Object> paraMap);

    /**
     * 显示客户名称的分页查询
     */
    SearchResult<VisitPlanCliNameDto> pageByNameParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<VisitPlanDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(VisitPlanAddCmd addCmd);

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(VisitPlanAddCmd addCmd);

    /**
     * 更新
     */
    void update(VisitPlanUpdateCmd updateCmd);

    /**
     * 在工作流审批流程中更新参会领导
     * 根据ifConference字段判断是否参会 1:是 ; 2:否 。
     * 在conferenceLeaderName字段保存确认参会的领导姓名
     */
    void updateConferenceLeadersInWorkflow(VisitPlanUpdateCmd updateCmd);
    /**
     * 在任务回调后更新参会领导
     * 根据ifConference字段判断是否参会 1:是 ; 2:否 。
     * 在conferenceLeaderName字段保存确认参会的领导姓名
     */
    void updateConferenceLeadersInWorkflow(TaskCall taskCall);

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
    ExcelParseDTO<VisitPlanExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<VisitPlanExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<VisitPlanExcelExport> list, HttpServletResponse response);

}
