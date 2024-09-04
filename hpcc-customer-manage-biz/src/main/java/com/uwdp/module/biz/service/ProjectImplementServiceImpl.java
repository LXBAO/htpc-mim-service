package com.uwdp.module.biz.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.*;
import com.uwdp.module.api.vo.dto.ProjectImplementDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectRTBWSIDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.biz.infrastructure.assembler.ProjectImplementAssembler;
import com.uwdp.module.biz.infrastructure.entity.ProjectImplementDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectRecordsDo;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectImplementRepository;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelExport;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelImport;
import com.uwdp.module.biz.infrastructure.excel.ProjectImplementExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ProjectRecordsRepository;
import com.uwdp.module.biz.utils.MdmOrganizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 项目实施 服务实现类
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectImplementServiceImpl implements IProjectImplementService {

    private final ProjectImplementRepository projectImplementRepository;

    private final ProjectRecordsRepository projectRecordsRepository;

    private final ProjectImplementAssembler projectImplementAssembler;

    private final BeanSearcher beanSearcher;

    private final IAttachmentService attachmentService;

    private final IProjectRecordsService projectRecordsService;

    private final IClientRoleService clientRoleService;

    private final IPerformanceManagementService performanceManagementService;

    @Autowired
    private MdmOrganizationUtil mdmOrganizationUtil;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<ProjectImplementDto> pageByParam(Map<String, Object> paraMap) {
        Map<String, Object> cMap = new HashMap<>();
        for (String key : paraMap.keySet()){
            cMap.put("C." + key.replace("A.", ""), paraMap.get(key));
        }
        paraMap = clientRoleService.queryUserRole(paraMap);
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

        if(paraMap.containsKey("registerDate")){
            String date = paraMap.get("registerDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("C.registerDate-0",date1);
            paraMap.put("C.registerDate-1",date2);
            paraMap.put("C.registerDate-op", "bt");
            paraMap.remove("C.registerDate");
            paraMap.remove("A.registerDate");
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

        return beanSearcher.search(ProjectImplementDto.class, paraMap);
    }

    @Override
    public List<ProjectImplementDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectImplementDto.class, paraMap);
    }

    @Override
    public List<ProjectImplementDto> listByIds(List<Long> idList) {
        List<ProjectImplementDo> list = projectImplementRepository.list(new LambdaQueryWrapper<ProjectImplementDo>()
                .in(ProjectImplementDo::getId, idList));
        return projectImplementAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectImplementDto get(Long id) {
        ProjectImplementDo projectImplementDo = projectImplementRepository.getOne(new LambdaQueryWrapper<ProjectImplementDo>()
                .eq(ProjectImplementDo::getId, id));
        ProjectImplementDto projectImplementDTO = projectImplementAssembler.toValueObject(projectImplementDo);
        Map<String, Object> map = new HashMap<>();
        map.put("orderType", AttachmentOrderType.PROJECT_IMPLEMENT.getId());
        map.put("orderId", id);
        projectImplementDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        return projectImplementDTO;
    }

    @Override
    public ProjectImplementDto getProjectNumber(String projectNumber) {
        ProjectImplementDo projectImplementDo = projectImplementRepository.getOne(new LambdaQueryWrapper<ProjectImplementDo>()
                .eq(ProjectImplementDo::getProjectNumber, projectNumber));
        ProjectImplementDto projectImplementDTO = projectImplementAssembler.toValueObject(projectImplementDo);
        return projectImplementDTO;
    }

    @Override
    public void add(ProjectImplementAddCmd addCmd) {
        addCmd.setInGear("未归档");
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(addCmd.getProjectNumber()));
        projectRecordsUpdateCmd.setHideState("5");
        projectRecordsService.update(projectRecordsUpdateCmd);

        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        String groupFullCode = mdmOrganizationUtil.getGroupFullCode(personCode);
        addCmd.setGroupFullCode(groupFullCode);

        ProjectImplementDo projectImplementDo = projectImplementAssembler.toDO(addCmd);
        boolean save = projectImplementRepository.save(projectImplementDo);
        if (save) {
            attachmentService.saveBatch(addCmd.getAddCmdList(), projectImplementDo.getId().toString(), AttachmentOrderType.PROJECT_IMPLEMENT);
        }
    }

    @Override
    public Long addThenReturnId(ProjectImplementAddCmd addCmd) {
        ProjectImplementDo projectImplementDo = projectImplementAssembler.toDO(addCmd);
        boolean save = projectImplementRepository.save(projectImplementDo);
        if (save) {
            return projectImplementDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(ProjectImplementUpdateCmd updateCmd) {
        ProjectImplementDto projectImplementDTO = this.get(updateCmd.getId());
        if (projectImplementDTO != null) {
            ProjectImplementDo projectImplementDo = projectImplementAssembler.toDO(updateCmd);
            projectImplementRepository.updateById(projectImplementDo);
            //先根据项目实施id删除附件
            attachmentService.delBatchByOrderId(projectImplementDo.getId().toString());
            //后保存
            attachmentService.saveBatch(updateCmd.getAddCmdList(), projectImplementDo.getId().toString(), AttachmentOrderType.PROJECT_IMPLEMENT);
        }
    }

    @Override
    public void update2(ProjectImplementUpdateCmd updateCmd) {
        ProjectImplementDo projectImplement = projectImplementRepository.getOne(new LambdaQueryWrapper<ProjectImplementDo>()
                .eq(ProjectImplementDo::getProjectNumber, updateCmd.getProjectNumber()));
        if (projectImplement != null) {
            updateCmd.setId(projectImplement.getId());
            ProjectImplementDo projectImplementDo = projectImplementAssembler.toDO(updateCmd);
            projectImplementRepository.updateById(projectImplementDo);
        }
    }

    @Override
    public void delete(Long id, String projectNumber) {
        projectImplementRepository.remove(new LambdaQueryWrapper<ProjectImplementDo>()
                .eq(ProjectImplementDo::getId, id));
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(projectNumber));
        projectRecordsUpdateCmd.setHideState("4");
        projectRecordsService.update(projectRecordsUpdateCmd);
    }

    @Override
    public void batchDelete(String ids, String numbers) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectImplementRepository.remove(new LambdaQueryWrapper<ProjectImplementDo>()
                    .in(ProjectImplementDo::getId, primaryKeys));
        }
        String[] strings = Convert.toStrArray(numbers);
        List<ProjectRecordsDo> projectRecordsDos = new ArrayList<>(strings.length);
        for (String nums : strings) {
            ProjectRecordsDo projectRecordsDo = new ProjectRecordsDo();
            projectRecordsDo.setId(Long.valueOf(nums));
            projectRecordsDo.setHideState("4");
            projectRecordsDos.add(projectRecordsDo);
        }
        projectRecordsRepository.saveOrUpdateBatch(projectRecordsDos);
    }


    @Override
    @Transactional
    public void entryFile(String ids) {
        String[] strings = Convert.toStrArray(ids);
        for (String id : strings) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", Long.valueOf(id));
            List<ProjectRTBWSIDto> project = projectRecordsService.getProject(map);
            PerformanceManagementAddCmd cmd = new PerformanceManagementAddCmd();
            ProjectImplementUpdateCmd projectImplementUpdateCmd = new ProjectImplementUpdateCmd();

            for (ProjectRTBWSIDto projectRTBWSIDto : project) {
                System.out.println(projectRTBWSIDto + "================================");
                cmd.setCreatedBy(projectRTBWSIDto.getCreatedBy());
                cmd.setCreatedName(projectRTBWSIDto.getCreatedName());
                cmd.setProjectName(projectRTBWSIDto.getProjectName()); //项目名称
                cmd.setProjectNameEnglish("没有数据");//工程项目名(外文)
                cmd.setImplementingUnit(projectRTBWSIDto.getConstructionUnit());//实施单位
                //cmd.setUseQualification(projectRTBWSIDto.getQualificationOrOtherwise());//使用资质
                cmd.setMoney(String.valueOf(projectRTBWSIDto.getContractAmount()));//合同金额(万元)
                cmd.setMoneyUS("没有数据");//合同金额(万美元)
                cmd.setFinishedWorkerMoney("没有数据");//竣/完工结算金额(万元)
                cmd.setIndustryCategory(projectRTBWSIDto.getIndustryCategory());//业务类别
                cmd.setEngineeringGrade(projectRTBWSIDto.getImportantType());//工程等级
                //cmd.setIndustryChainCategory(projectRTBWSIDto.getIndustryChainCategory());//产业链类别
                cmd.setNewIndustryCategory(projectRTBWSIDto.getNewIndustry());//新产业类别
                cmd.setBusinessModel(projectRTBWSIDto.getBusinessModel());//商业模式
                cmd.setNationality(projectRTBWSIDto.getLocation());//所在国别(地区)
                cmd.setProjectLandProvince("projectRTBWSIDto.");//项目所在地省(国名)
                cmd.setProjectCity("projectRTBWSIDto.");//项目所在地市(国外省市)
                cmd.setDateOfSigning(projectRTBWSIDto.getTimeOfSigning());//合同签订日期
                cmd.setStartTime(projectRTBWSIDto.getCommencementOfContract());//合同工期开始时间
                cmd.setEndTime(projectRTBWSIDto.getContractEndTime());//合同工期结束时间
                cmd.setPhysicalIndicatorOne(projectRTBWSIDto.getMainPhysicalQuantityIndexOne());//实物量指标1
                cmd.setMainphysicalIndicatorOne(projectRTBWSIDto.getPrincipalPhysicalQuantityOne());//主要实物量1
                cmd.setStartOfConstructionPeriod(String.valueOf(projectRTBWSIDto.getWorkDate()));//实施工期开工时间
                cmd.setPhysicalIndicatorTwo(projectRTBWSIDto.getMainPhysicalQuantityIndexTwo());//实物量指标2
                cmd.setMainphysicalIndicatorTwo(projectRTBWSIDto.getPrincipalPhysicalQuantityTwo());//主要实物量2
                //cmd.setCompletionOfProject();//实施工期完工时间
                //cmd.setAcceptanceTime();//完工验收时间
                //cmd.setCompletionAcceptanceTime();//竣工验收时间
                cmd.setCompletionOfCompletion("没有数据");//是否办理竣工结算
                cmd.setQualityAssessment("没有数据");//竣工质量评定等级
                //cmd.setFilingTime();//档案归档时间
                cmd.setWinnerlCass("没有数据");//工程获奖情况家级
                cmd.setWinningProvince("没有数据");//工程获奖时间省级
                cmd.setWinningCity("没有数据");//工程获奖时间市级
                cmd.setLiteralDescription("没有数据");//实物量指标(其他)文字描述
                //cmd.setEngineeringCondition(projectRTBWSIDto.getProjectSituation());//项目基本情况或工程概括
                cmd.setTechnicalIndex("没有数据");//主要技术指标
                cmd.setPostponement("没有数据");//导致工程竣工延期情况
                cmd.setInputInformation("没有数据");//工程业绩录入情况
                cmd.setSaveEntryLocation("没有数据");//档案保存录入和地址
                cmd.setRemark(projectRTBWSIDto.getRemark());//备注
                performanceManagementService.add(cmd);

                projectImplementUpdateCmd.setInGear("已归档");
                projectImplementUpdateCmd.setProjectNumber(id);
                update2(projectImplementUpdateCmd);
            }

        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectImplementExcelExport.class, "项目实施导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> paraMap, HttpServletResponse response) {
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
        paraMap.put("projectName-op","ct");
        paraMap.put("registrationUnit-op","ct");
        paraMap.put("createdByName-op","ct");
        paraMap.put("GROUPFULLCODE-op","ct");
        List<ProjectImplementExcelExport> searchResult = beanSearcher.searchAll(ProjectImplementExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, ProjectImplementExcelExport.class, "项目实施数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectImplementExcelImport> excelParse(MultipartFile file) {
        ProjectImplementExcelImportListener listener = new ProjectImplementExcelImportListener();
        ExcelUtil.readFile(file, ProjectImplementExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectImplementExcelImport> list) {
        List<ProjectImplementDo> projectImplementDoList = projectImplementAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectImplementDoList)) {
            projectImplementRepository.saveBatch(projectImplementDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectImplementExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectImplementExcelExport.class, "项目实施错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
