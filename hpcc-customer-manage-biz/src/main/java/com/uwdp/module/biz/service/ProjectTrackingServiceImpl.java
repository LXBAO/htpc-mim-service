package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.github.pagehelper.PageHelper;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.AttachmentAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.cmd.ProjectTrackingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectTrackingUpdateCmd;
import com.uwdp.module.api.vo.dto.*;
import com.uwdp.module.api.vo.dto.searcher.ProjectTrackingWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectTrackingExcelExport;
import com.uwdp.module.api.vo.excel.ProjectTrackingExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.AttachmentAssembler;
import com.uwdp.module.biz.infrastructure.assembler.ProjectTrackingAssembler;
import com.uwdp.module.biz.infrastructure.entity.AttachmentDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectTrackingDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectTrackingExcelImportListener;
import com.uwdp.module.biz.infrastructure.mapper.ProjectTrackingMapper;
import com.uwdp.module.biz.infrastructure.repository.AttachmentRepository;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectRecordsRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectTrackingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 项目跟踪 服务实现类
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectTrackingServiceImpl implements IProjectTrackingService {

    private final ProjectTrackingRepository projectTrackingRepository;

    private final ProjectTrackingAssembler projectTrackingAssembler;

    private final BeanSearcher beanSearcher;

    @Autowired
    private ProjectTrackingMapper projectTrackingMapper;

    @Autowired
    private IClientRoleService clientRoleService;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    private final IMdmPersonService mdmPersonService;

    private final IMdmOrganizationService organizationService;

    private final AttachmentRepository attachmentRepository;

    private final AttachmentAssembler attachmentAssembler;

    @Autowired
    private IMdmOrganizationService mdmOrganizationService;

    @Override
    public SearchResult<ProjectTrackingDto> pageByParam(Map<String, Object> paraMap) {
        clientRoleService.queryUserRole(paraMap);
        return beanSearcher.search(ProjectTrackingDto.class, paraMap);
    }

    @Override
    public SearchResult<ProjectTrackingWorkflowDto> pageByParamTwo(Map<String, Object> paraMap) {
        Map<String, Object> cMap = new HashMap<>();
        for (String key : paraMap.keySet()){
            cMap.put("C." + key.replace("A.", ""), paraMap.get(key));
        }
        clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("industryChainCategory")){
            String s = paraMap.get("industryChainCategory").toString();
            if(s.endsWith("00")){
                //截取前三位
                paraMap.put("C.industryChainCategory",s.substring(0,3));
                //以什么开头
                paraMap.put("C.industryChainCategory-op","sw");
            }
        }
        if(paraMap.containsKey("trackDate")){
            String date = paraMap.get("trackDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("C.trackDate-0",date1);
            paraMap.put("C.trackDate-1",date2);
            paraMap.put("C.trackDate-op", "bt");
            paraMap.remove("C.trackDate");
            paraMap.remove("A.trackDate");
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

        return beanSearcher.search(ProjectTrackingWorkflowDto.class, paraMap);
    }

    @Override
    public SearchResult<ProjectTrackingWorkflowDto> projectEnablePage(Map<String, Object> paraMap) {
        return beanSearcher.search(ProjectTrackingWorkflowDto.class, paraMap);
    }

    @Override
    public List<ProjectTrackingDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectTrackingDto.class, paraMap);
    }

    @Override
    public List<ProjectTrackingDto> listByIds(List<Long> idList) {
        List<ProjectTrackingDo> list = projectTrackingRepository.list(new LambdaQueryWrapper<ProjectTrackingDo>()
                .in(ProjectTrackingDo::getId, idList));
        return projectTrackingAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectTrackingDto get(Long id) {
        ProjectTrackingDo projectTrackingDo = projectTrackingRepository.getOne(new LambdaQueryWrapper<ProjectTrackingDo>()
                .eq(ProjectTrackingDo::getId, id));
        ProjectTrackingDto projectTrackingDTO = projectTrackingAssembler.toValueObject(projectTrackingDo);
        projectTrackingDTO.setAttachmentDtos(attachmentGet(projectTrackingDTO.getUploadFile()));
        return projectTrackingDTO;
    }

    @Override
    public ProjectTrackingDto getProjectNumber(String projectNumber) {
        ProjectTrackingDo projectTrackingDo = projectTrackingRepository.getOne(new LambdaQueryWrapper<ProjectTrackingDo>()
                .eq(ProjectTrackingDo::getProjectNumber, projectNumber));
        ProjectTrackingDto projectTrackingDTO = projectTrackingAssembler.toValueObject(projectTrackingDo);
        return projectTrackingDTO;
    }

    private final IProjectRecordsService projectRecordsService;

    private final ProjectRecordsRepository projectRecordsRepository;

    @Override
    @Transactional
    public void add(ProjectTrackingAddCmd addCmd) {
        // 数据权限 添加创建人字段(保存创建人personCode)
        MdmPersonDto personDto = mdmPersonService.getPersonCodeDetail(addCmd.getCreatedBy());
        String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
        //通过t_mdmorganization查询出groupFullCode
        MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
        String fullCode = organizationDto.getGroupFullCode();
        addCmd.setGroupFullCode(fullCode);
        //添加项目登记内部协同单位
        addRecord(addCmd);
        addCmd.setUploadFile(attachmentAdd(addCmd.getAddCmdList()));
        ProjectTrackingDo projectTrackingDo = projectTrackingAssembler.toDO(addCmd);
        projectTrackingRepository.save(projectTrackingDo);
    }

    @Override
    public void addRecord(ProjectTrackingAddCmd addCmd) {
        //根据项目id查询
        ProjectRecordsDto projectRecordsDto = projectRecordsService.get(Long.valueOf(addCmd.getProjectNumber()));
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(addCmd.getProjectNumber()));
        // 如果内部协同单位不为空
        if (projectRecordsDto.getIntAssistanceUnit() != null && projectRecordsDto.getIntAssistanceUnitName() != null) {
            Set<String> intAssistanceUnitSet = new HashSet<>(Arrays.asList(projectRecordsDto.getIntAssistanceUnit().split(",")));
            Set<String> intAssistanceUnitNameSet = new HashSet<>(Arrays.asList(projectRecordsDto.getIntAssistanceUnitName().split(",")));

            // 添加新的单位编号和名称
            intAssistanceUnitSet.addAll(Arrays.asList(addCmd.getIntAssistanceUnit().split(",")));
            intAssistanceUnitNameSet.addAll(Arrays.asList(addCmd.getIntAssistanceUnitName().split(",")));

            //更新内部协同单位
            projectRecordsUpdateCmd.setIntAssistanceUnit(String.join(",", intAssistanceUnitSet));
            projectRecordsUpdateCmd.setIntAssistanceUnitName(String.join(",", intAssistanceUnitNameSet));
        } else {
            // 直接更新内部协同单位
            projectRecordsUpdateCmd.setIntAssistanceUnit(addCmd.getIntAssistanceUnit());
            projectRecordsUpdateCmd.setIntAssistanceUnitName(addCmd.getIntAssistanceUnitName());
        }
        projectRecordsService.update(projectRecordsUpdateCmd);
    }

    @Override
    @Transactional
    public Long addThenReturnId(ProjectTrackingAddCmd addCmd) {
        addRecord(addCmd);
        addCmd.setUploadFile(attachmentAdd(addCmd.getAddCmdList()));
        ProjectTrackingDo projectTrackingDo = projectTrackingAssembler.toDO(addCmd);
        boolean save = projectTrackingRepository.save(projectTrackingDo);
        if (save) {
            return projectTrackingDo.getId();
        }
        return 0L;
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
    public void update(ProjectTrackingUpdateCmd updateCmd) {
        updateCmd.setUploadFile(attachmentAdd(updateCmd.getAddCmdList()));
        ProjectTrackingDto projectTrackingDTO = this.get(updateCmd.getId());
        if (projectTrackingDTO != null) {
            ProjectTrackingDo projectTrackingDo = projectTrackingAssembler.toDO(updateCmd);
            projectTrackingRepository.updateById(projectTrackingDo);
        }
    }

    @Override
    public void delete(Long id,String projectNumber) {
        projectTrackingRepository.remove(new LambdaQueryWrapper<ProjectTrackingDo>()
                .eq(ProjectTrackingDo::getId, id));
        /*ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new  ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(projectNumber));
        projectRecordsUpdateCmd.setHideState("1");
        projectRecordsService.update(projectRecordsUpdateCmd);*/
    }

    @Override
    public void batchDelete(String ids,String numbers) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectTrackingRepository.remove(new LambdaQueryWrapper<ProjectTrackingDo>()
                    .in(ProjectTrackingDo::getId, primaryKeys));
        }
        /*String[] strings = Convert.toStrArray(numbers);
        List<ProjectRecordsDo> projectRecordsDos = new ArrayList<>(strings.length);
        for(String nums:strings){
            ProjectRecordsDo projectRecordsDo =new ProjectRecordsDo();
            projectRecordsDo.setId(Long.valueOf(nums));
            projectRecordsDo.setHideState("1");
            projectRecordsDos.add(projectRecordsDo);
        }
        projectRecordsRepository.saveOrUpdateBatch(projectRecordsDos);*/
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectTrackingExcelExport.class, "项目跟踪导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> paraMap, HttpServletResponse response) {
        clientRoleService.queryUserRole(paraMap);
        paraMap.put("trackItemName-op","ct");
        paraMap.put("personName-op","ct");
        paraMap.put("creator-op","ct");
        paraMap.put("formModel-op","ct");
        System.out.println(paraMap.containsKey("isForien")+"99911111111111");
        if(paraMap.containsKey("isForien")){
            String s = paraMap.get("isForien").toString();
            String s1 = "";
            if("0".equals(s)){
                s1 = "01201300";
            }else if("1".equals(s)){
                s1 = "01201301";
            }
            if(paraMap.containsKey("groupFullCode") || paraMap.containsKey("GROUPFULLCODE")){
                String s2 = paraMap.get("groupFullCode") != null ? paraMap.get("groupFullCode").toString() : paraMap.get("GROUPFULLCODE").toString();
                paraMap.put("GROUPFULLCODE",s1+"/"+s2);
            }else{
                paraMap.put("GROUPFULLCODE",s1);
            }
        }
        paraMap.put("GROUPFULLCODE-op","ct");

        if(paraMap.containsKey("industryChainCategory")){
            String s = paraMap.get("industryChainCategory").toString();
            if(s.endsWith("00")){
                //截取前三位
                paraMap.put("industryChainCategory",s.substring(0,3));
                //以什么开头
                paraMap.put("industryChainCategory-op","sw");
            }
        }
        if(paraMap.containsKey("trackDate")){
            String date = paraMap.get("trackDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("trackDate-0",date1);
            paraMap.put("trackDate-1",date2);
            paraMap.put("trackDate-op", "bt");
            paraMap.remove("trackDate");
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
        List<ProjectTrackingExcelExport> searchResult = beanSearcher.searchAll(ProjectTrackingExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, ProjectTrackingExcelExport.class, "项目跟踪数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectTrackingExcelImport> excelParse(MultipartFile file) {
        ProjectTrackingExcelImportListener listener = new ProjectTrackingExcelImportListener();
        ExcelUtil.readFile(file, ProjectTrackingExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectTrackingExcelImport> list) {
        List<ProjectTrackingDo> projectTrackingDoList = projectTrackingAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectTrackingDoList)) {
            projectTrackingRepository.saveBatch(projectTrackingDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectTrackingExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectTrackingExcelExport.class, "项目跟踪错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
