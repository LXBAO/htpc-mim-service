package com.uwdp.module.biz.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.AttachmentAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.cmd.ProjectSigningAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSigningUpdateCmd;
import com.uwdp.module.api.vo.dto.*;
import com.uwdp.module.api.vo.dto.searcher.ProjectSigningRecordsWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectSigningExcelExport;
import com.uwdp.module.api.vo.excel.ProjectSigningExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.AttachmentAssembler;
import com.uwdp.module.biz.infrastructure.assembler.ProjectSigningAssembler;
import com.uwdp.module.biz.infrastructure.entity.AttachmentDo;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectRecordsDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectSigningDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectSigningExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.AttachmentRepository;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectRecordsRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectSigningRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 项目签约 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectSigningServiceImpl implements IProjectSigningService {

    private final ProjectSigningRepository projectSigningRepository;

    private final ProjectSigningAssembler projectSigningAssembler;

    private final BeanSearcher beanSearcher;

    private final IProjectRecordsService projectRecordsService;

    private final ProjectRecordsRepository projectRecordsRepository;

    private final AttachmentAssembler attachmentAssembler;

    private final AttachmentRepository attachmentRepository;

    private final IMdmOrganizationService mdmOrganizationService;

    @Autowired
    private IClientRoleService clientRoleService;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<ProjectSigningDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ProjectSigningDto.class, paraMap);
    }

    @Override
    public SearchResult<ProjectSigningRecordsWorkflowDto> pageByParamTwo(Map<String, Object> paraMap) {
        Map<String, Object> cMap = new HashMap<>();
        for (String key : paraMap.keySet()){
            cMap.put("C." + key.replace("A.", ""), paraMap.get(key));
        }
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("location")){
            String s = paraMap.get("location").toString();
            if(s.endsWith("00")){
                //截取开头两位
                paraMap.put("C.location",s.substring(0,2));
                //以什么开头
                paraMap.put("C.location-op","sw");
            }
        }
        if(paraMap.containsKey("industryCategory")){
            String s = paraMap.get("industryCategory").toString();
            if(s.endsWith("00")){
                //截取前三位
                paraMap.put("C.industryCategory",s.substring(0,3));
                //以什么开头
                paraMap.put("C.industryCategory-op","sw");
            }
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

        if(paraMap.containsKey("timeOfSigning")){
            String date = paraMap.get("timeOfSigning").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("C.timeOfSigning-0",date1);
            paraMap.put("C.timeOfSigning-1",date2);
            paraMap.put("C.timeOfSigning-op", "bt");
            paraMap.remove("C.timeOfSigning");
            paraMap.remove("A.timeOfSigning");
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

        return beanSearcher.search(ProjectSigningRecordsWorkflowDto.class, paraMap);
    }

    @Override
    public List<ProjectSigningDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectSigningDto.class, paraMap);
    }

    @Override
    public List<ProjectSigningDto> listByIds(List<Long> idList) {
        List<ProjectSigningDo> list = projectSigningRepository.list(new LambdaQueryWrapper<ProjectSigningDo>()
                .in(ProjectSigningDo::getId, idList));
        return projectSigningAssembler.toValueObjectList(list);
    }


    @Override
    public ProjectSigningDto get(Long id) {
        ProjectSigningDo projectSigningDo = projectSigningRepository.getOne(new LambdaQueryWrapper<ProjectSigningDo>()
                .eq(ProjectSigningDo::getId, id));
        ProjectSigningDto projectSigningDTO = projectSigningAssembler.toValueObject(projectSigningDo);
        projectSigningDTO.setAttachmentDtos(attachmentGet(projectSigningDTO.getAttachmentUploading()));
        return projectSigningDTO;
    }

    @Override
    public ProjectSigningDto getProjectNumber(String projectNumber) {
        ProjectSigningDo projectSigningDo = projectSigningRepository.getOne(new LambdaQueryWrapper<ProjectSigningDo>()
                .eq(ProjectSigningDo::getProjectNumber, projectNumber));
        ProjectSigningDto projectSigningDTO = projectSigningAssembler.toValueObject(projectSigningDo);
        return projectSigningDTO;
    }

    private final IMdmPersonService mdmPersonService;

    @Override
    public void add(ProjectSigningAddCmd addCmd) {
        //设置申请人部门编号
        MdmPersonDto personDto = mdmPersonService.getPersonCodeDetail(addCmd.getCreatedBy());
        String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
        //通过t_mdmorganization查询出groupFullCode
        MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
        String fullCode = organizationDto.getGroupFullCode();

        addCmd.setGroupFullCode(fullCode);
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new  ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(addCmd.getProjectNumber()));
        projectRecordsUpdateCmd.setHideState("4");
        projectRecordsService.update(projectRecordsUpdateCmd);

        MdmPersonDto mdmPersonDto = mdmPersonService.get(addCmd.getCreatedBy());
        addCmd.setAttachmentUploading(attachmentAdd(addCmd.getAddCmdList()));
        addCmd.setGroupBelongUnitName(mdmPersonDto.getGroupBelongUnitName());

        ProjectSigningDo projectSigningDo = projectSigningAssembler.toDO(addCmd);

        projectSigningRepository.save(projectSigningDo);
    }

    private List<AttachmentDto> attachmentGet(String str) {
        if (str != null && !"".equals(str)){
            List<AttachmentDo> list = attachmentRepository.list(new LambdaQueryWrapper<AttachmentDo>()
                    .in(AttachmentDo::getId, Arrays.asList(str.split(","))));
            return attachmentAssembler.toValueObjectList(list);
        }
        return null;
    }

    private String attachmentAdd(List<AttachmentAddCmd> list) {
        if(list != null && list.size() > 0){
            StringBuilder str = new StringBuilder();
            for(AttachmentAddCmd materialsFile : list){
                AttachmentDo attachmentDo = attachmentAssembler.toDO(materialsFile);
                attachmentRepository.saveOrUpdate(attachmentDo);
                str.append(attachmentDo.getId()).append(",");
            }
            return str.substring(0, str.length() - 1);
        }
        return "";
    }

    @Override
    public Long addThenReturnId(ProjectSigningAddCmd addCmd) {
        addCmd.setAttachmentUploading(attachmentAdd(addCmd.getAddCmdList()));
        ProjectSigningDo projectSigningDo = projectSigningAssembler.toDO(addCmd);
        boolean save = projectSigningRepository.save(projectSigningDo);
        if (save){
            return projectSigningDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(ProjectSigningUpdateCmd updateCmd) {
        updateCmd.setAttachmentUploading(attachmentAdd(updateCmd.getAddCmdList()));
        ProjectSigningDto projectSigningDTO = this.get(updateCmd.getId());
        if (projectSigningDTO != null) {
            ProjectSigningDo projectSigningDo = projectSigningAssembler.toDO(updateCmd);
            projectSigningRepository.updateById(projectSigningDo);
        }
    }

    @Override
    public void delete(Long id,String projectNumber) {
        projectSigningRepository.remove(new LambdaQueryWrapper<ProjectSigningDo>()
                .eq(ProjectSigningDo::getId, id));
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new  ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(projectNumber));
        projectRecordsUpdateCmd.setHideState("3");
        projectRecordsService.update(projectRecordsUpdateCmd);

    }

    @Override
    public void batchDelete(String ids,String numbers) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectSigningRepository.remove(new LambdaQueryWrapper<ProjectSigningDo>()
                    .in(ProjectSigningDo::getId, primaryKeys));
        }
        String[] strings = Convert.toStrArray(numbers);
        List<ProjectRecordsDo> projectRecordsDos = new ArrayList<>(strings.length);
        for(String nums:strings){
            ProjectRecordsDo projectRecordsDo =new ProjectRecordsDo();
            projectRecordsDo.setId(Long.valueOf(nums));
            projectRecordsDo.setHideState("3");
            projectRecordsDos.add(projectRecordsDo);
        }
        projectRecordsRepository.saveOrUpdateBatch(projectRecordsDos);

    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectSigningExcelExport.class, "项目签约导入模板", response);
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

        if(paraMap.containsKey("timeOfSigning")){
            String date = paraMap.get("timeOfSigning").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("timeOfSigning-0",date1);
            paraMap.put("timeOfSigning-1",date2);
            paraMap.put("timeOfSigning-op", "bt");
            paraMap.remove("timeOfSigning");
        }
        paraMap.put("contractName-op","ct");
        paraMap.put("projectName-op","ct");
        paraMap.put("createdName-op","ct");
        paraMap.put("GROUPFULLCODE-op","ct");
        List<ProjectSigningExcelExport> searchResult = beanSearcher.searchAll(ProjectSigningExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, ProjectSigningExcelExport.class, "项目签约数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectSigningExcelImport> excelParse(MultipartFile file) {
        ProjectSigningExcelImportListener listener = new ProjectSigningExcelImportListener();
        ExcelUtil.readFile(file, ProjectSigningExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectSigningExcelImport> list) {
        List<ProjectSigningDo> projectSigningDoList = projectSigningAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectSigningDoList)) {
            projectSigningRepository.saveBatch(projectSigningDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectSigningExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectSigningExcelExport.class, "项目签约错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
