package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.service.IVisitPlanService;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.dto.searcher.CALedgerCerInfoDto;
import com.uwdp.module.api.vo.dto.searcher.VisitPlanCliNameDto;
import com.uwdp.module.biz.infrastructure.assembler.VisitPlanAssembler;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.entity.PublicRelationsDo;
import com.uwdp.module.biz.infrastructure.entity.VisitPlanDo;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.VisitPlanRepository;
import com.uwdp.module.api.vo.excel.VisitPlanExcelExport;
import com.uwdp.module.api.vo.excel.VisitPlanExcelImport;
import com.uwdp.module.biz.infrastructure.excel.VisitPlanExcelImportListener;
import com.uwdp.module.biz.utils.MdmOrganizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 公关关系计划 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class VisitPlanServiceImpl implements IVisitPlanService {

    private final VisitPlanRepository visitPlanRepository;

    private final VisitPlanAssembler visitPlanAssembler;

    private final BeanSearcher beanSearcher;

    private final IMdmPersonService mdmPersonService;
    private final IMdmOrganizationService mdmOrganizationService;

    @Autowired
    private IClientRoleService clientRoleService;

    @Autowired
    private MdmOrganizationUtil mdmOrganizationUtil;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<VisitPlanDto> pageByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("adviceDate")){
            String date = paraMap.get("adviceDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("adviceDate-0",date1);
            paraMap.put("adviceDate-1",date2);
            paraMap.put("adviceDate-op", "bt");
            paraMap.remove("adviceDate");
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
        return beanSearcher.search(VisitPlanDto.class, paraMap);
    }

    @Override
    public List<VisitPlanDto> searchAll(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(VisitPlanDto.class, paraMap);
    }

    /**
     * 显示客户名称的分页查询
     */
    @Override
    public SearchResult<VisitPlanCliNameDto> pageByNameParam(Map<String, Object> paraMap){
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("regionalHeadquarter")){
            String s = paraMap.get("regionalHeadquarter").toString();
            if(s.endsWith("00")){
                //截取开头两位
                paraMap.put("regionalHeadquarter",s.substring(0,2));
                //以什么开头
                paraMap.put("regionalHeadquarter-op","sw");
            }
        }
        if(paraMap.containsKey("adviceDate")){
            String date = paraMap.get("adviceDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("adviceDate-0",date1);
            paraMap.put("adviceDate-1",date2);
            paraMap.put("adviceDate-op", "bt");
            paraMap.remove("adviceDate");
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
        return beanSearcher.search(VisitPlanCliNameDto.class, paraMap);
    }

    @Override
    public List<VisitPlanDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(VisitPlanDto.class, paraMap);
    }

    @Override
    public List<VisitPlanDto> listByIds(List<Long> idList) {
        List<VisitPlanDo> list = visitPlanRepository.list(new LambdaQueryWrapper<VisitPlanDo>()
                .in(VisitPlanDo::getId, idList));
        return visitPlanAssembler.toValueObjectList(list);
    }

    @Override
    public VisitPlanDto get(Long id) {
        VisitPlanDo visitPlanDo = visitPlanRepository.getOne(new LambdaQueryWrapper<VisitPlanDo>()
                .eq(VisitPlanDo::getId, id));
        VisitPlanDto visitPlanDTO = visitPlanAssembler.toValueObject(visitPlanDo);
        return visitPlanDTO;
    }

    @Override
    public void add(VisitPlanAddCmd addCmd) {
        //设置申请人部门编号
        MdmPersonDto personDto = mdmPersonService.getPersonCodeDetail(addCmd.getCreatedBy());
        String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
        //通过t_mdmorganization查询出groupFullCode
        MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
        String fullCode = organizationDto.getGroupFullCode();

        addCmd.setGroupFullCode(fullCode);
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        addCmd=(VisitPlanAddCmd)mdmOrganizationUtil.getMdmOrganization(addCmd,personCode);
        VisitPlanDo visitPlanDo = visitPlanAssembler.toDO(addCmd);
        visitPlanRepository.save(visitPlanDo);
    }

    @Override
    public Long addThenReturnId(VisitPlanAddCmd addCmd) {
        VisitPlanDo VisitPlanDo = visitPlanAssembler.toDO(addCmd);
        boolean save = visitPlanRepository.save(VisitPlanDo);
        if (save){
            return VisitPlanDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(VisitPlanUpdateCmd updateCmd) {
        VisitPlanDto visitPlanDTO = this.get(updateCmd.getId());
        if (visitPlanDTO != null) {
            VisitPlanDo visitPlanDo = visitPlanAssembler.toDO(updateCmd);
            visitPlanRepository.updateById(visitPlanDo);
        }
    }

    @Override
    public void updateConferenceLeadersInWorkflow(VisitPlanUpdateCmd updateCmd) {
        VisitPlanDto visitPlanDTO = this.get(updateCmd.getId());
        String currentConferenceLeaderName = updateCmd.getCurrentConferenceLeaderName(); /*当前用户的名称 controller传来的*/
        if (visitPlanDTO == null) {
            return;
        }
        String old = visitPlanDTO.getConferenceLeaderName(); /*已保存的 确认参会的出行领导名称*/
        String newStr = "";
        // 1:是 ; 2:否 。
        if ("2".equals(updateCmd.getIfConference())) {
            if ( StringUtils.hasLength(old) && old.contains(currentConferenceLeaderName) ){
                if (Objects.equals(old,currentConferenceLeaderName)){
                    // do nothing
                } else if (old.startsWith(currentConferenceLeaderName) && old.contains(",")) {
                    // 截取第一个逗号之后的子字符串
                    newStr = old.substring(old.indexOf(",")+1);
                } else {
                    newStr = old.replace(currentConferenceLeaderName,"");
                }
                if (newStr.startsWith(",")) {
                    newStr = newStr.substring(1);
                }
                if (newStr.endsWith(",")) {
                    newStr = newStr.substring(0, newStr.length() - 1);
                }
            }else {
                newStr = old;
            }
        }else if ("1".equals(updateCmd.getIfConference())){
            if ( StringUtils.isEmpty(old) ){
                newStr = currentConferenceLeaderName;
            } else if (old.contains(currentConferenceLeaderName)) {
                newStr = old;
            } else {
                newStr = old + "," + currentConferenceLeaderName;
            }
        }

        updateCmd.setConferenceLeaderName(newStr);

        VisitPlanDo visitPlanDo = visitPlanAssembler.toDO(updateCmd);
        visitPlanRepository.updateById(visitPlanDo);
    }
    @Override
    public void updateConferenceLeadersInWorkflow(TaskCall taskCall) {

        Map<String, Object> formData = taskCall.getFormData();
        String id = (String) formData.get("id");
        String ifConference = String.valueOf(formData.get("ifConference"));
        Map<String, Object> taskFormData = taskCall.getTaskFormData();
        String userFullName = taskCall.getAssignee().getUserFullName(); /*当前用户的名称*/
        log.info(">>>>>>>>>>任务回调修改ifConference字段接收到回调参数TaskCall:id:{},ifConference:{},taskFormData:{}",id,ifConference,taskFormData);
        if (!StringUtils.hasLength(id) || !StringUtils.hasLength(userFullName) || !StringUtils.hasLength(ifConference)){
            return;
        }
        VisitPlanDo updaetVPDo = visitPlanRepository.getById(Long.valueOf(id));
        if (updaetVPDo == null) {
            return;
        }
        String old = updaetVPDo.getConferenceLeaderName(); /*已保存的 确认参会的出行领导名称*/
        String newStr = "";
        // 1:是 ; 2:否 。
        if ("2".equals(ifConference)) {
            if ( StringUtils.hasLength(old) && old.contains(userFullName) ){
                if (Objects.equals(old,userFullName)){
                    // do nothing
                } else if (old.startsWith(userFullName) && old.contains(",")) {
                    // 截取第一个逗号之后的子字符串
                    newStr = old.substring(old.indexOf(",")+1);
                } else {
                    newStr = old.replace(userFullName,"");
                }
                if (newStr.startsWith(",")) {
                    newStr = newStr.substring(1);
                }
                if (newStr.endsWith(",")) {
                    newStr = newStr.substring(0, newStr.length() - 1);
                }
            }else {
                newStr = old;
            }
        }else if ("1".equals(ifConference)){
            if ( StringUtils.hasLength(old) ){
                newStr = userFullName;
            } else if (old.contains(userFullName)) {
                newStr = old;
            } else {
                newStr = old + "," + userFullName;
            }
        }

        updaetVPDo.setConferenceLeaderName(newStr);

        visitPlanRepository.updateById(updaetVPDo);
    }

    @Override
    public void delete(Long id) {
        visitPlanRepository.remove(new LambdaQueryWrapper<VisitPlanDo>()
                .eq(VisitPlanDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            visitPlanRepository.remove(new LambdaQueryWrapper<VisitPlanDo>()
                    .in(VisitPlanDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), VisitPlanExcelExport.class, "公关关系计划导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> paraMap, HttpServletResponse response) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("regionalHeadquarter")){
            String s = paraMap.get("regionalHeadquarter").toString();
            if(s.endsWith("00")){
                //截取开头两位
                paraMap.put("regionalHeadquarter",s.substring(0,2));
                //以什么开头
                paraMap.put("regionalHeadquarter-op","sw");
            }
        }
        if(paraMap.containsKey("adviceDate")){
            String date = paraMap.get("adviceDate").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("adviceDate-0",date1);
            paraMap.put("adviceDate-1",date2);
            paraMap.put("adviceDate-op", "bt");
            paraMap.remove("adviceDate");
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
        paraMap.put("activityAddress-op","ct");
        paraMap.put("fdName-op","ct");
        paraMap.put("title-op","ct");
        paraMap.put("travelLeader-op","ct");
        paraMap.put("travelLeaderName-op","ct");
        paraMap.put("createdByName-op","ct");
        paraMap.put("GROUPFULLCODE-op","ct");
        List<VisitPlanExcelExport> searchResult = beanSearcher.searchAll(VisitPlanExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, VisitPlanExcelExport.class, "公关关系计划数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<VisitPlanExcelImport> excelParse(MultipartFile file) {
        VisitPlanExcelImportListener listener = new VisitPlanExcelImportListener();
        ExcelUtil.readFile(file, VisitPlanExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<VisitPlanExcelImport> list) {
        List<VisitPlanDo> visitPlanDoList = visitPlanAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(visitPlanDoList)) {
            visitPlanRepository.saveBatch(visitPlanDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<VisitPlanExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, VisitPlanExcelExport.class, "公关关系计划错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
