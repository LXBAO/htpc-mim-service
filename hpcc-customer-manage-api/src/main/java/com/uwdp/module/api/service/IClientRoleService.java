package com.uwdp.module.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.uwdp.module.api.vo.cmd.ClientRoleEntityAddCmd;
import com.uwdp.module.api.vo.cmd.ClientRoleEntityUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.api.vo.excel.ClientRoleEntityExcelExport;
import com.uwdp.module.api.vo.excel.ClientRoleEntityExcelImport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 
 *权限表 服务类
 * @author qis
 * @email qis@gmail.com
 * @date 2023-06-28 15:34:38
 */
public interface IClientRoleService{
//public interface IClientRoleService extends IService<ClientRoleEntityDto> {

    /**
     * 查询
     */
    ClientRoleEntityDto get(Integer id);

    /**
     * 通过主键list查询
     */
    List<ClientRoleEntityDto> listByIds(List<Integer> idList);

    /**
     * 分页查询
     */
    SearchResult<ClientRoleEntityDto> pageByParam(Map<String, Object> paraMap);

    /**
     * 条件查询
     */
    List<ClientRoleEntityDto> searchByParam(Map<String, Object> paraMap);

    /**
     * 新增
     */
    void add(ClientRoleEntityAddCmd addCmd);

    /**
     * 更新
     */
    void update(ClientRoleEntityUpdateCmd updateCmd);

    /**
     * 删除
     */
    void delete(Integer id);

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
    ExcelParseDTO<ClientRoleEntityExcelImport> excelParse(MultipartFile file);

    /**
     * 导入处理后的Excel数据
     */
    void excelImport(List<ClientRoleEntityExcelImport> list);

    /**
     * 错误数据下载
     */
    void excelErrorDownload(List<ClientRoleEntityExcelExport> list, HttpServletResponse response);

    /**
     * 获取用户权限
     * @return 组装成查询条件
     */
    Map<String, Object> queryUserRole(Map<String, Object> paraMap);
}

