package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.service.IProjectSuspensionService;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.api.vo.dto.ProjectSuspensionDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectSuspensionWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectSuspensionExcelExport;
import com.uwdp.module.api.vo.excel.ProjectSuspensionExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ProjectSuspensionAssembler;
import com.uwdp.module.biz.infrastructure.entity.ProjectSuspensionDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectSuspensionExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ProjectSuspensionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 项目中止 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectSuspensionServiceImpl implements IProjectSuspensionService {

    private final ProjectSuspensionRepository projectSuspensionRepository;

    private final ProjectSuspensionAssembler projectSuspensionAssembler;

    private final BeanSearcher beanSearcher;

    private final IMdmPersonService mdmPersonService;
    private final IMdmOrganizationService mdmOrganizationService;

    @Override
    public SearchResult<ProjectSuspensionDto> pageByParam(Map<String, Object> paraMap) {
        if(paraMap.containsKey("suspensionTime")){
            String date = paraMap.get("suspensionTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("suspensionTime-0",date1);
            paraMap.put("suspensionTime-1",date2);
            paraMap.put("suspensionTime-op", "bt");
            paraMap.remove("suspensionTime");
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
        return beanSearcher.search(ProjectSuspensionDto.class, paraMap);
    }

    @Override
    public SearchResult<ProjectSuspensionWorkflowDto> pageThatPassed(Map<String, Object> paraMap) {
        Map<String, Object> cMap = new HashMap<>();
        for (String key : paraMap.keySet()){
            cMap.put("C." + key.replace("A.", ""), paraMap.get(key));
        }
        if (paraMap.containsKey("groupFullCode")){
            paraMap.put("groupFullCode-op","ct");
        }
        if(paraMap.containsKey("suspensionTime")){
            String date = paraMap.get("suspensionTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("C.suspensionTime-0",date1);
            paraMap.put("C.suspensionTime-1",date2);
            paraMap.put("C.suspensionTime-op", "bt");
            paraMap.remove("C.suspensionTime");
            paraMap.remove("A.suspensionTime");
        }
        if(paraMap.containsKey("createdTime")){
            String date = paraMap.get("createdTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("C.createdTime-0",date1);
            paraMap.put("C.createdTime-1",date2);
            paraMap.put("C.createdTime-op", "bt");
            paraMap.remove("C.createdTime");
            paraMap.remove("A.createdTime");
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

        return beanSearcher.search(ProjectSuspensionWorkflowDto.class, paraMap);
    }

    @Override
    public List<ProjectSuspensionDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectSuspensionDto.class, paraMap);
    }

    @Override
    public List<ProjectSuspensionDto> listByIds(List<Long> idList) {
        List<ProjectSuspensionDo> list = projectSuspensionRepository.list(new LambdaQueryWrapper<ProjectSuspensionDo>()
                .in(ProjectSuspensionDo::getId, idList));
        return projectSuspensionAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectSuspensionDto get(Long id) {
        ProjectSuspensionDo projectSuspensionDo = projectSuspensionRepository.getOne(new LambdaQueryWrapper<ProjectSuspensionDo>()
                .eq(ProjectSuspensionDo::getId, id));
        ProjectSuspensionDto projectSuspensionDTO = projectSuspensionAssembler.toValueObject(projectSuspensionDo);
        return projectSuspensionDTO;
    }

    @Override
    public void add(ProjectSuspensionAddCmd addCmd) {
        //设置申请人部门编号
        MdmPersonDto personDto = mdmPersonService.getPersonCodeDetail(addCmd.getCreatedBy());
        String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
        //通过t_mdmorganization查询出groupFullCode
        MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
        String fullCode = organizationDto.getGroupFullCode();

        addCmd.setGroupFullCode(fullCode);

        ProjectSuspensionDo projectSuspensionDo = projectSuspensionAssembler.toDO(addCmd);
        projectSuspensionRepository.save(projectSuspensionDo);
    }

    @Override
    public Long addThenReturnId(ProjectSuspensionAddCmd addCmd) {
        ProjectSuspensionDo projectSuspensionDo = projectSuspensionAssembler.toDO(addCmd);
        boolean save =projectSuspensionRepository.save(projectSuspensionDo);
        if (save){
            return projectSuspensionDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(ProjectSuspensionUpdateCmd updateCmd) {
        ProjectSuspensionDto projectSuspensionDTO = this.get(updateCmd.getId());
        if (projectSuspensionDTO != null) {
            ProjectSuspensionDo projectSuspensionDo = projectSuspensionAssembler.toDO(updateCmd);
            projectSuspensionRepository.updateById(projectSuspensionDo);
        }
    }

    @Override
    public void delete(Long id) {
        projectSuspensionRepository.remove(new LambdaQueryWrapper<ProjectSuspensionDo>()
                .eq(ProjectSuspensionDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectSuspensionRepository.remove(new LambdaQueryWrapper<ProjectSuspensionDo>()
                    .in(ProjectSuspensionDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectSuspensionExcelExport.class, "项目中止导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ProjectSuspensionExcelExport> searchResult = beanSearcher.searchAll(ProjectSuspensionExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ProjectSuspensionExcelExport.class, "项目中止数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectSuspensionExcelImport> excelParse(MultipartFile file) {
        ProjectSuspensionExcelImportListener listener = new ProjectSuspensionExcelImportListener();
        ExcelUtil.readFile(file, ProjectSuspensionExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectSuspensionExcelImport> list) {
        List<ProjectSuspensionDo> projectSuspensionDoList = projectSuspensionAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectSuspensionDoList)) {
            projectSuspensionRepository.saveBatch(projectSuspensionDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectSuspensionExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectSuspensionExcelExport.class, "项目中止错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
