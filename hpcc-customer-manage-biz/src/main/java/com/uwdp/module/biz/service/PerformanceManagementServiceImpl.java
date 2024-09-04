package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.service.IPerformanceManagementService;
import com.uwdp.module.api.vo.cmd.PerformanceManagementAddCmd;
import com.uwdp.module.api.vo.cmd.PerformanceManagementUpdateCmd;
import com.uwdp.module.api.vo.dto.PerformanceManagementDto;
import com.uwdp.module.api.vo.excel.PerformanceManagementExcelExport;
import com.uwdp.module.api.vo.excel.PerformanceManagementExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.PerformanceManagementAssembler;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.entity.PerformanceManagementDo;
import com.uwdp.module.biz.infrastructure.excel.PerformanceManagementExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.PerformanceManagementRepository;
import com.uwdp.module.biz.utils.MdmOrganizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 业绩管理 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PerformanceManagementServiceImpl implements IPerformanceManagementService {

    private final PerformanceManagementRepository performanceManagementRepository;

    private final PerformanceManagementAssembler performanceManagementAssembler;

    private final BeanSearcher beanSearcher;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    private final MdmOrganizationUtil mdmOrganizationUtil;

    @Autowired
    private IClientRoleService clientRoleService;

    @Override
    public SearchResult<PerformanceManagementDto> pageByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("industryCategory")){
            String s = paraMap.get("industryCategory").toString();
            if(s.endsWith("00")){
                //截取前三位
                paraMap.put("industryCategory",s.substring(0,3));
                //以什么开头
                paraMap.put("industryCategory-op","sw");
            }
        }
        if(paraMap.containsKey("createdTime")){
            String date = paraMap.get("createdTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("createdTime-0",date1);
            paraMap.put("createdTime-1",date2);
            paraMap.put("createdTime-op", "bt");
            paraMap.remove("createdTime");
        }
        return beanSearcher.search(PerformanceManagementDto.class, paraMap);
    }

    @Override
    public List<PerformanceManagementDto> searchByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        return beanSearcher.searchAll(PerformanceManagementDto.class, paraMap);
    }

    @Override
    public List<PerformanceManagementDto> listByIds(List<Long> idList) {
        List<PerformanceManagementDo> list = performanceManagementRepository.list(new LambdaQueryWrapper<PerformanceManagementDo>()
                .in(PerformanceManagementDo::getId, idList));
        return performanceManagementAssembler.toValueObjectList(list);
    }

    @Override
    public PerformanceManagementDto get(Long id) {
        PerformanceManagementDo performanceManagementDo = performanceManagementRepository.getOne(new LambdaQueryWrapper<PerformanceManagementDo>()
                .eq(PerformanceManagementDo::getId, id));
        PerformanceManagementDto performanceManagementDTO = performanceManagementAssembler.toValueObject(performanceManagementDo);
        return performanceManagementDTO;
    }

    @Override
    public void add(PerformanceManagementAddCmd addCmd) {
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        String groupFullCode = mdmOrganizationUtil.getGroupFullCode(personCode);
        addCmd.setGroupFullCode(groupFullCode);

        PerformanceManagementDo performanceManagementDo = performanceManagementAssembler.toDO(addCmd);
        performanceManagementRepository.save(performanceManagementDo);
    }

    @Override
    public void update(PerformanceManagementUpdateCmd updateCmd) {
        PerformanceManagementDto performanceManagementDTO = this.get(updateCmd.getId());
        if (performanceManagementDTO != null) {
            PerformanceManagementDo performanceManagementDo = performanceManagementAssembler.toDO(updateCmd);
            performanceManagementRepository.updateById(performanceManagementDo);
        }
    }

    @Override
    public void delete(Long id) {
        performanceManagementRepository.remove(new LambdaQueryWrapper<PerformanceManagementDo>()
                .eq(PerformanceManagementDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            performanceManagementRepository.remove(new LambdaQueryWrapper<PerformanceManagementDo>()
                    .in(PerformanceManagementDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), PerformanceManagementExcelExport.class, "业绩管理导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<PerformanceManagementExcelExport> searchResult = beanSearcher.searchAll(PerformanceManagementExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, PerformanceManagementExcelExport.class, "业绩管理数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<PerformanceManagementExcelImport> excelParse(MultipartFile file) {
        PerformanceManagementExcelImportListener listener = new PerformanceManagementExcelImportListener();
        ExcelUtil.readFile(file, PerformanceManagementExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<PerformanceManagementExcelImport> list) {
        List<PerformanceManagementDo> performanceManagementDoList = performanceManagementAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(performanceManagementDoList)) {
            performanceManagementRepository.saveBatch(performanceManagementDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<PerformanceManagementExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, PerformanceManagementExcelExport.class, "业绩管理错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
