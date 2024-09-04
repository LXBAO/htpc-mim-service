package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsUpdateCmd;
import com.uwdp.module.api.vo.dto.PublicRelationsDto;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.dto.searcher.PublicRelationsHeadlineDto;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelExport;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 公关实施 服务类
 * </p>
 *
 */
public interface IPublicRelationsService {

    /**
     * 查询
     */
    PublicRelationsDto get(Long id);

    PublicRelationsDto getByVisitPlanId(Long visitPlanId);

    /**
     * 通过主键list查询
     */
    List<PublicRelationsDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<PublicRelationsDto> pageByParam(Map<String, Object> paraMap);

    List<VisitPlanDto> searchAll(Map<String, Object> paraMap);

    /**
     * 加标题分页查询
     */
    SearchResult<PublicRelationsHeadlineDto> pageHeadlineByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<PublicRelationsDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(PublicRelationsAddCmd addCmd);

    /**
     * 新增并返回新增数据的fdId
     */
    Long addThenReturnId(PublicRelationsAddCmd addCmd);

    /**
     * 更新
     */
    void update(PublicRelationsUpdateCmd updateCmd);

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
    ExcelParseDTO<PublicRelationsExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<PublicRelationsExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<PublicRelationsExcelExport> list, HttpServletResponse response);

}
