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
import com.uwdp.module.api.vo.cmd.WinTheBidAddCmd;
import com.uwdp.module.api.vo.cmd.WinTheBidUpdateCmd;
import com.uwdp.module.api.vo.dto.*;
import com.uwdp.module.api.vo.dto.searcher.WinTheBidWorkflowDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.api.vo.excel.WinTheBidExcelExport;
import com.uwdp.module.api.vo.excel.WinTheBidExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.AttachmentAssembler;
import com.uwdp.module.biz.infrastructure.assembler.WinTheBidAssembler;
import com.uwdp.module.biz.infrastructure.entity.AttachmentDo;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectRecordsDo;
import com.uwdp.module.biz.infrastructure.entity.WinTheBidDo;
import com.uwdp.module.biz.infrastructure.excel.WinTheBidExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.AttachmentRepository;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectRecordsRepository;
import com.uwdp.module.biz.infrastructure.repository.WinTheBidRepository;
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
 * 项目中标 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WinTheBidServiceImpl implements IWinTheBidService {

    private final WinTheBidRepository winTheBidRepository;

    private final WinTheBidAssembler winTheBidAssembler;

    private final IProjectRecordsService projectRecordsService;

    private final ProjectRecordsRepository projectRecordsRepository;

    private final BeanSearcher beanSearcher;

    private final AttachmentRepository attachmentRepository;

    private final AttachmentAssembler attachmentAssembler;

    private final IAttachmentService attachmentService;

    private final IMdmPersonService mdmPersonService;

    private final IMdmOrganizationService mdmOrganizationService;
    @Autowired
    private IClientRoleService clientRoleService;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<WinTheBidDto> pageByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        return beanSearcher.search(WinTheBidDto.class, paraMap);
    }

    @Override
    public SearchResult<WinTheBidWorkflowDto> workflowPageByParam(Map<String, Object> paraMap) {
        Map<String, Object> cMap = new HashMap<>();
        for (String key : paraMap.keySet()){
            cMap.put("C." + key.replace("A.", ""), paraMap.get(key));
        }
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("industryCategory")){
            String s = paraMap.get("industryCategory").toString();
            if(s.endsWith("00")){
                //截前三位
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

        if(paraMap.containsKey("data")){
            String date = paraMap.get("data").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("C.data-0",date1);
            paraMap.put("C.data-1",date2);
            paraMap.put("C.data-op", "bt");
            paraMap.remove("C.data");
            paraMap.remove("A.data");
        }
        if(!paraMap.containsKey("C.createdBy")){
            cMap.remove("C.createdBy");
        }
        paraMap.putAll(cMap);
        paraMap = MapUtils.builder(paraMap)
                .group("A")
                .put("B.IntAssistanceUnit", paraMap.get("userGroupFullCode"))
                .put("B.IntAssistanceUnit-op","ct")
                .groupExpr("(A|B)&C").build();

        return beanSearcher.search(WinTheBidWorkflowDto.class, paraMap);
    }


    @Override
    public List<WinTheBidDto> searchByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        return beanSearcher.searchAll(WinTheBidDto.class, paraMap);
    }

    @Override
    public List<WinTheBidDto> listByIds(List<Long> idList) {
        List<WinTheBidDo> list = winTheBidRepository.list(new LambdaQueryWrapper<WinTheBidDo>()
                .in(WinTheBidDo::getId, idList));
        return winTheBidAssembler.toValueObjectList(list);
    }

    @Override
    public WinTheBidDto get(Long id) {
        WinTheBidDo winTheBidDo = winTheBidRepository.getOne(new LambdaQueryWrapper<WinTheBidDo>()
                .eq(WinTheBidDo::getId, id));
        WinTheBidDto winTheBidDTO = winTheBidAssembler.toValueObject(winTheBidDo);
        winTheBidDTO.setAttachmentDtos(attachmentGet(winTheBidDTO.getFile()));
        return winTheBidDTO;
    }

    @Override
    public void add(WinTheBidAddCmd addCmd) {
        //设置申请人部门编号
        MdmPersonDto personDto = mdmPersonService.getPersonCodeDetail(addCmd.getCreatedBy());
        String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
        //通过t_mdmorganization查询出groupFullCode
        MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
        String fullCode = organizationDto.getGroupFullCode();

        addCmd.setGroupFullCode(fullCode);

        MdmPersonDto mdmPersonDto = mdmPersonService.get(addCmd.getCreatedBy());
        addCmd.setGroupBelongUnitName(mdmPersonDto.getGroupBelongUnitName());
        addCmd.setFile(attachmentAdd(addCmd.getAddCmdList()));
        WinTheBidDo winTheBidDo = winTheBidAssembler.toDO(addCmd);
        winTheBidRepository.save(winTheBidDo);

        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new  ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(addCmd.getItemNumber()));
        projectRecordsUpdateCmd.setHideState("3");
        projectRecordsService.update(projectRecordsUpdateCmd);


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
    public Long addThenReturnId(WinTheBidAddCmd addCmd) {
        addCmd.setFile(attachmentAdd(addCmd.getAddCmdList()));
        WinTheBidDo winTheBidDo = winTheBidAssembler.toDO(addCmd);
        boolean save = winTheBidRepository.save(winTheBidDo);
        if (save){
            return winTheBidDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(WinTheBidUpdateCmd updateCmd) {
        updateCmd.setFile(attachmentAdd(updateCmd.getAddCmdList()));
        WinTheBidDto winTheBidDTO = this.get(updateCmd.getId());
        if (winTheBidDTO != null) {
            WinTheBidDo winTheBidDo = winTheBidAssembler.toDO(updateCmd);
            winTheBidRepository.updateById(winTheBidDo);
        }
    }

    @Override
    public void delete(Long id,String projectNumber) {
        winTheBidRepository.remove(new LambdaQueryWrapper<WinTheBidDo>()
                .eq(WinTheBidDo::getId, id));
        ProjectRecordsUpdateCmd projectRecordsUpdateCmd = new  ProjectRecordsUpdateCmd();
        projectRecordsUpdateCmd.setId(Long.valueOf(projectNumber));
        projectRecordsUpdateCmd.setHideState("2");
        projectRecordsService.update(projectRecordsUpdateCmd);
    }

    @Override
    public void batchDelete(String ids,String numbers) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            winTheBidRepository.remove(new LambdaQueryWrapper<WinTheBidDo>()
                    .in(WinTheBidDo::getId, primaryKeys));
        }
        String[] strings = Convert.toStrArray(numbers);
        List<ProjectRecordsDo> projectRecordsDos = new ArrayList<>(strings.length);
        for(String nums:strings){
            ProjectRecordsDo projectRecordsDo =new ProjectRecordsDo();
            projectRecordsDo.setId(Long.valueOf(nums));
            projectRecordsDo.setHideState("2");
            projectRecordsDos.add(projectRecordsDo);
        }
        projectRecordsRepository.saveOrUpdateBatch(projectRecordsDos);
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), WinTheBidExcelExport.class, "项目中标导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> paraMap, HttpServletResponse response) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("industryCategory")){
            String s = paraMap.get("industryCategory").toString();
            if(s.endsWith("00")){
                //截前三位
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
        paraMap.put("GROUPFULLCODE-op","ct");
        paraMap.put("createdName-op","ct");
        List<WinTheBidExcelExport> searchResult = beanSearcher.searchAll(WinTheBidExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, WinTheBidExcelExport.class, "项目中标数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<WinTheBidExcelImport> excelParse(MultipartFile file) {
        WinTheBidExcelImportListener listener = new WinTheBidExcelImportListener();
        ExcelUtil.readFile(file, WinTheBidExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<WinTheBidExcelImport> list) {
        List<WinTheBidDo> winTheBidDoList = winTheBidAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(winTheBidDoList)) {
            winTheBidRepository.saveBatch(winTheBidDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<WinTheBidExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, WinTheBidExcelExport.class, "项目中标错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
