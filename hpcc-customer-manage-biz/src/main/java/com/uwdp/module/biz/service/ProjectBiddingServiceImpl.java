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
import com.uwdp.module.api.vo.cmd.ProjectBiddingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectBiddingUpdateCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.api.vo.dto.ProjectBiddingDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectBiddingWorkflowDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.api.vo.excel.ProjectBiddingExcelExport;
import com.uwdp.module.api.vo.excel.ProjectBiddingExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ProjectBiddingAssembler;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectBiddingDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectRecordsDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectBiddingExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectBiddingRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectRecordsRepository;
import com.uwdp.module.biz.utils.MdmOrganizationUtil;
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
 * 项目投标 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectBiddingServiceImpl implements IProjectBiddingService {

    private final ProjectBiddingRepository projectBiddingRepository;

    private final ProjectRecordsRepository projectRecordsRepository;

    private final ProjectBiddingAssembler projectBiddingAssembler;

    private final BeanSearcher beanSearcher;

    private final IAttachmentService attachmentService;

    private final IProjectRecordsService projectRecordsService;

    private final ILinkmanService linkmanService;


    @Autowired
    private MdmOrganizationUtil mdmOrganizationUtil;
    @Autowired
    private IClientRoleService clientRoleService;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<ProjectBiddingDto> pageByParam(Map<String, Object> paraMap) {
        Map<String, Object> cMap = new HashMap<>();
        for (String key : paraMap.keySet()){
            cMap.put("C." + key.replace("A.", ""), paraMap.get(key));
        }
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("projectDate")){
            String date = paraMap.get("projectDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("C.projectDate-0",date1);
            paraMap.put("C.projectDate-1",date2);
            paraMap.put("C.projectDate-op", "bt");
            paraMap.remove("C.projectDate");
            paraMap.remove("A.projectDate");
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

        return beanSearcher.search(ProjectBiddingDto.class, paraMap);
    }

    @Override
    public SearchResult<ProjectBiddingWorkflowDto> pageById(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("projectDate")){
            String date = paraMap.get("projectDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("projectDate-0",date1);
            paraMap.put("projectDate-1",date2);
            paraMap.put("projectDate-op", "bt");
            paraMap.remove("projectDate");
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
        return beanSearcher.search(ProjectBiddingWorkflowDto.class, paraMap);
    }

    @Override
    public List<ProjectBiddingDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectBiddingDto.class, paraMap);
    }

    @Override
    public List<ProjectBiddingDto> listByIds(List<Long> idList) {
        List<ProjectBiddingDo> list = projectBiddingRepository.list(new LambdaQueryWrapper<ProjectBiddingDo>()
                .in(ProjectBiddingDo::getId, idList));
        return projectBiddingAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectBiddingDto get(Long id) {
        ProjectBiddingDo projectBiddingDo = projectBiddingRepository.getOne(new LambdaQueryWrapper<ProjectBiddingDo>()
                .eq(ProjectBiddingDo::getId, id));
        ProjectBiddingDto projectBiddingDTO = projectBiddingAssembler.toValueObject(projectBiddingDo);
        Map<String,Object> map = new HashMap<>();
        map.put("orderType",AttachmentOrderType.PROJECT_BIDDING.getId());
        map.put("orderId",id);
        projectBiddingDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        return projectBiddingDTO;
    }

    @Override
    public ProjectBiddingDto getProjectNumber(String projectNumber) {
        ProjectBiddingDo projectBiddingDo = projectBiddingRepository.getOne(new LambdaQueryWrapper<ProjectBiddingDo>()
                .eq(ProjectBiddingDo::getProjectNumber, projectNumber));
        ProjectBiddingDto projectBiddingDTO = projectBiddingAssembler.toValueObject(projectBiddingDo);
        return projectBiddingDTO;
    }

    @Override
    public void add(ProjectBiddingAddCmd addCmd) {
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new  ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(addCmd.getProjectNumber()));
        projectRecordsUpdateCmd.setHideState("2");
        projectRecordsService.update(projectRecordsUpdateCmd);
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        String groupFullCode=mdmOrganizationUtil.getGroupFullCode(personCode);
        addCmd.setGroupFullCode(groupFullCode);

        ProjectBiddingDo projectBiddingDo = projectBiddingAssembler.toDO(addCmd);

        boolean save = projectBiddingRepository.save(projectBiddingDo);
        if (save){
            attachmentService.saveBatch(addCmd.getAddCmdList(),projectBiddingDo.getId().toString(),AttachmentOrderType.PROJECT_BIDDING);
        }

    }

    @Override
    public Long addThenReturnId(ProjectBiddingAddCmd addCmd) {
        ProjectBiddingDo projectBiddingDo = projectBiddingAssembler.toDO(addCmd);
        boolean save = projectBiddingRepository.save(projectBiddingDo);
        if (save){
            return projectBiddingDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(ProjectBiddingUpdateCmd updateCmd) {
        ProjectBiddingDto projectBiddingDTO = this.get(updateCmd.getId());
        if (projectBiddingDTO != null) {
            ProjectBiddingDo projectBiddingDo = projectBiddingAssembler.toDO(updateCmd);
            projectBiddingRepository.updateById(projectBiddingDo);
            //先根据项目投标id删除附件
            attachmentService.delBatchByOrderId(projectBiddingDo.getId().toString());
            //后保存
            attachmentService.saveBatch(updateCmd.getAddCmdList(),projectBiddingDo.getId().toString(),AttachmentOrderType.PROJECT_BIDDING);
        }
    }

    @Override
    public void delete(Long id,String projectNumber) {
        projectBiddingRepository.remove(new LambdaQueryWrapper<ProjectBiddingDo>()
                .eq(ProjectBiddingDo::getId, id));
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new  ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(projectNumber));
        projectRecordsUpdateCmd.setHideState("1");
        projectRecordsService.update(projectRecordsUpdateCmd);
        linkmanService.deleteById(projectNumber);
    }

    @Override
    public void batchDelete(String ids,String numbers) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectBiddingRepository.remove(new LambdaQueryWrapper<ProjectBiddingDo>()
                    .in(ProjectBiddingDo::getId, primaryKeys));

        }
        String[] strings = Convert.toStrArray(numbers);
        List<ProjectRecordsDo> projectRecordsDos = new ArrayList<>(strings.length);
        for(String nums:strings){
            ProjectRecordsDo projectRecordsDo =new ProjectRecordsDo();
            projectRecordsDo.setId(Long.valueOf(nums));
            projectRecordsDo.setHideState("1");
            projectRecordsDos.add(projectRecordsDo);
        }
        projectRecordsRepository.saveOrUpdateBatch(projectRecordsDos);
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectBiddingExcelExport.class, "项目投标导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> paraMap, HttpServletResponse response) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("projectDate")){
            String date = paraMap.get("projectDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("projectDate-0",date1);
            paraMap.put("projectDate-1",date2);
            paraMap.put("projectDate-op", "bt");
            paraMap.remove("projectDate");
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
        paraMap.put("createdByName-op","ct");
        paraMap.put("businessName-op","ct");
        paraMap.put("GROUPFULLCODE-op","ct");
        List<ProjectBiddingExcelExport> searchResult = beanSearcher.searchAll(ProjectBiddingExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, ProjectBiddingExcelExport.class, "项目投标数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectBiddingExcelImport> excelParse(MultipartFile file) {
        ProjectBiddingExcelImportListener listener = new ProjectBiddingExcelImportListener();
        ExcelUtil.readFile(file, ProjectBiddingExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectBiddingExcelImport> list) {
        List<ProjectBiddingDo> projectBiddingDoList = projectBiddingAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectBiddingDoList)) {
            projectBiddingRepository.saveBatch(projectBiddingDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectBiddingExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectBiddingExcelExport.class, "项目投标错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
