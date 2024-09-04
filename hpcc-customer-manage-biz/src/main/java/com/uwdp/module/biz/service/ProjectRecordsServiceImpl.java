package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.service.IProjectRecordsService;
import com.uwdp.module.api.vo.cmd.ProjectRecordsAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.api.vo.dto.ProjectRecordsDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectRTBWSIDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectRecordsWorkflowDto;
import com.uwdp.module.biz.infrastructure.assembler.ProjectRecordsAssembler;
import com.uwdp.module.biz.infrastructure.entity.ProjectRecordsDo;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectRecordsRepository;
import com.uwdp.module.api.vo.excel.ProjectRecordsExcelExport;
import com.uwdp.module.api.vo.excel.ProjectRecordsExcelImport;
import com.uwdp.module.biz.infrastructure.excel.ProjectRecordsExcelImportListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 项目登记 服务实现类
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectRecordsServiceImpl implements IProjectRecordsService {

    private final ProjectRecordsRepository projectRecordsRepository;

    private final ProjectRecordsAssembler projectRecordsAssembler;

    private final BeanSearcher beanSearcher;

    private final IMdmPersonService mdmPersonService;
    private final IMdmOrganizationService mdmOrganizationService;

    @Autowired
    private IClientRoleService clientRoleService;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<ProjectRecordsWorkflowDto> pageByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        return beanSearcher.search(ProjectRecordsWorkflowDto.class, paraMap);
    }

    @Override
    public SearchResult<ProjectRecordsWorkflowDto> pageHeadlineByParam(Map<String, Object> paraMap) {
        Map<String, Object> cMap = new HashMap<>();
        for (String key : paraMap.keySet()){
            cMap.put("C." + key.replace("A.", ""), paraMap.get(key));
        }
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("C.location")){
            String s = paraMap.get("C.location").toString();
            if(s.endsWith("00")){
                //截取开头两位
                paraMap.put("C.location",s.substring(0,2));
                //以什么开头
                paraMap.put("C.location-op","sw");
            }
        }
        if(paraMap.containsKey("C.industryCategory")){
            String s = paraMap.get("C.industryCategory").toString();
            if(s.endsWith("00")){
                //截取开头两位
                paraMap.put("C.industryCategory",s.substring(0,3));
                //以什么开头
                paraMap.put("C.industryCategory-op","sw");
            }
        }
        if(paraMap.containsKey("C.createdTime")){
            String date = paraMap.get("C.createdTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("C.createdTime-0",date1);
            paraMap.put("C.createdTime-1",date2);
            paraMap.put("C.createdTime-op", "bt");
            paraMap.remove("C.createdTime");
        }

        if(!paraMap.containsKey("A.createdBy")){
            cMap.remove("C.createdBy");
        }
        paraMap.putAll(cMap);
        paraMap = MapUtils.builder(paraMap)
                .group("A")
                .put("B.IntAssistanceUnit", paraMap.get("userGroupFullCode"))
                .put("B.IntAssistanceUnit-op","ct")
                .groupExpr("(A|B)&C").build();

        log.info("paraMap={}", paraMap);
        return beanSearcher.search(ProjectRecordsWorkflowDto.class, paraMap);
    }

    @Override
    public SearchResult<ProjectRecordsWorkflowDto> pageHeadlineByParam0(Map<String, Object> paraMap) {
        return beanSearcher.search(ProjectRecordsWorkflowDto.class, paraMap);
    }

    @Override
    public List<ProjectRecordsDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectRecordsDto.class, paraMap);
    }

    @Override
    public List<ProjectRTBWSIDto> getProject(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectRTBWSIDto.class, paraMap);
    }

    @Override
    public List<ProjectRecordsDto> listByIds(List<Long> idList) {
        List<ProjectRecordsDo> list = projectRecordsRepository.list(new LambdaQueryWrapper<ProjectRecordsDo>()
                .in(ProjectRecordsDo::getId, idList));
        return projectRecordsAssembler.toValueObjectList(list);
    }

    @Override
    public Integer getProjectCount(String projectName, String location, String ownerUnit, String industryCategor) {
        return projectRecordsRepository.getProjectCount(projectName, location, ownerUnit, industryCategor);
    }

    @Override
    public ProjectRecordsDto get(Long id) {
        ProjectRecordsDo projectRecordsDo = projectRecordsRepository.getOne(new LambdaQueryWrapper<ProjectRecordsDo>()
                .eq(ProjectRecordsDo::getId, id));
        ProjectRecordsDto projectRecordsDTO = projectRecordsAssembler.toValueObject(projectRecordsDo);
        return projectRecordsDTO;
    }


    // 获取流水号
    public String getSerialNumber() {
        LambdaQueryWrapper<ProjectRecordsDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(ProjectRecordsDo::getProjectNumber).orderByDesc(ProjectRecordsDo::getCreatedTime);
        Optional<Integer> maxId = projectRecordsRepository.list(queryWrapper).stream()
                .map(ProjectRecordsDo::getProjectNumber)
                .map(projectNumer -> Integer.valueOf(projectNumer.substring(projectNumer.length() - 4)))
                .max(Integer::compareTo)
                .map(maxVal -> {
                    int nextVal = ++maxVal;
                    if (nextVal > 9999) {
                        throw new RuntimeException("项目编号达到最大9999上限");
                    }
                    return nextVal;
                });
        return String.format("%04d", maxId.orElse(1));
    }

    /* 生成项目编号 eg:项目编号：P202307120001：按照年重新计算 */
    public String generateProjectNumber() {
        LocalDate date = LocalDate.now();
        String dateStr = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));// 格式化为年月日
        String serialNumber = getSerialNumber();// 获取流水号
        return "P" + dateStr + serialNumber;// 拼接projectNumber
    }

    @Override
    public void add(ProjectRecordsAddCmd addCmd) {
        //设置申请人部门编号
        MdmPersonDto personDto = mdmPersonService.getPersonCodeDetail(addCmd.getCreatedBy());
        String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
        //通过t_mdmorganization查询出groupFullCode
        MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
        String fullCode = organizationDto.getGroupFullCode();

        addCmd.setGroupFullCode(fullCode);
        ProjectRecordsDo projectRecordsDo = projectRecordsAssembler.toDO(addCmd);
        /* 生成项目编号 eg:项目编号：P202307120001：按照年重新计算 */
        projectRecordsDo.setProjectNumber(generateProjectNumber());
        projectRecordsRepository.save(projectRecordsDo);
    }

    @Override
    public Long addThenReturnId(ProjectRecordsAddCmd addCmd) {
        ProjectRecordsDo projectRecordsDo = projectRecordsAssembler.toDO(addCmd);
        projectRecordsDo.setProjectNumber(generateProjectNumber());
        boolean save = projectRecordsRepository.save(projectRecordsDo);
        if (save) {
            return projectRecordsDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(ProjectRecordsUpdateCmd updateCmd) {
        ProjectRecordsDto projectRecordsDTO = this.get(updateCmd.getId());
        if (projectRecordsDTO != null) {
            ProjectRecordsDo projectRecordsDo = projectRecordsAssembler.toDO(updateCmd);
            projectRecordsRepository.updateById(projectRecordsDo);
        }
    }

    @Override
    public void delete(Long id) {
        projectRecordsRepository.remove(new LambdaQueryWrapper<ProjectRecordsDo>()
                .eq(ProjectRecordsDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectRecordsRepository.remove(new LambdaQueryWrapper<ProjectRecordsDo>()
                    .in(ProjectRecordsDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectRecordsExcelExport.class, "项目登记导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> paraMap, HttpServletResponse response) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("location")){
            String s = paraMap.get("location").toString();
            if(s.endsWith("00")){
                //截取开头两位
                paraMap.put("location",s.substring(0,2));
                //以什么开头
                paraMap.put("location-op","sw");
            }
        }
        if(paraMap.containsKey("industryCategory")){
            String s = paraMap.get("industryCategory").toString();
            if(s.endsWith("00")){
                //截取开头两位
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
        List<ProjectRecordsExcelExport> searchResult = beanSearcher.searchAll(ProjectRecordsExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, ProjectRecordsExcelExport.class, "项目登记数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectRecordsExcelImport> excelParse(MultipartFile file) {
        ProjectRecordsExcelImportListener listener = new ProjectRecordsExcelImportListener();
        ExcelUtil.readFile(file, ProjectRecordsExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectRecordsExcelImport> list) {
        List<ProjectRecordsDo> projectRecordsDoList = projectRecordsAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectRecordsDoList)) {
            projectRecordsRepository.saveBatch(projectRecordsDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectRecordsExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectRecordsExcelExport.class, "项目登记错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
