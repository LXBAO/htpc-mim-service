package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.excel.PromptExcelExport;
import com.uwdp.module.api.vo.excel.PromptExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 信息提示 服务类
 * </p>
 *
 */
public interface IPromptService {

    /**
     * 查询
     */
    PromptDto get(Long id);

    /**
     * 查询提醒信息详情
     */
    PromptDto getByQid(Long qid,String promptPath);

    /**
     * 查询提醒信息详情
     */
    PromptDto getByQid(Long qid);

    /**
     * 通过主键list查询
     */
    List<PromptDto> listByIds(List<Long> idList);

    /**
     * 分页查询
     */
    SearchResult<PromptDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<PromptDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    PromptAddCmd add(PromptAddCmd addCmd);

    /**
     * 更新
     */
    void update(PromptUpdateCmd updateCmd);

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
    ExcelParseDTO<PromptExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<PromptExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<PromptExcelExport> list, HttpServletResponse response);

    /**
     * 将证书提醒设置成已阅
     *
     * @param id
     * @param promptId
     */
    String haveReadByOa(Long id, String promptId, Integer flag);

    /**
     * 将即将过期消息推至OA
     *
     * @param id
     * @param isremark 8 待阅  2 已办
     * @param promptId
     */
    String toBeDoneToOa(Long id, String isremark, String promptId, Integer flag, Integer isRead);
}
