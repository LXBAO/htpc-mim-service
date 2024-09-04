package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsUpdateCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanUpdateCmd;
import com.uwdp.module.api.vo.dto.*;
import com.uwdp.module.api.vo.dto.searcher.PublicRelationsHeadlineDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.biz.infrastructure.assembler.PublicRelationsAssembler;
import com.uwdp.module.biz.infrastructure.entity.*;
import com.uwdp.module.biz.infrastructure.repository.*;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelExport;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelImport;
import com.uwdp.module.biz.infrastructure.excel.PublicRelationsExcelImportListener;
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
 * 公关实施 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PublicRelationsServiceImpl implements IPublicRelationsService {

    private final PublicRelationsRepository publicRelationsRepository;

    private final PublicRelationsAssembler publicRelationsAssembler;

    private final IAttachmentService attachmentService;

    private final BeanSearcher beanSearcher;

    private final MdmPersonRepository mdmPersonRepository;

    private final MdmOrganizationRepository mdmOrganizationRepository;

    private final IMdmPersonService mdmPersonService;
    private final IMdmOrganizationService mdmOrganizationService;

    @Autowired
    private MdmOrganizationUtil mdmOrganizationUtil;

    @Autowired
    private IClientRoleService clientRoleService;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<PublicRelationsDto> pageByParam(Map<String, Object> paraMap) {

        paraMap = clientRoleService.queryUserRole(paraMap);

        return beanSearcher.search(PublicRelationsDto.class, paraMap);
    }

    @Override
    public List<VisitPlanDto> searchAll(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(VisitPlanDto.class, paraMap);
    }

    @Override
    public SearchResult<PublicRelationsHeadlineDto> pageHeadlineByParam(Map<String, Object> paraMap){
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("activityProvinceAndCity")){
            String s = paraMap.get("activityProvinceAndCity").toString();
            if(s.endsWith("00")){
                //截取前两位
                paraMap.put("activityProvinceAndCity",s.substring(0,2));
                //以什么开头
                paraMap.put("activityProvinceAndCity-op","sw");
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
        return beanSearcher.search(PublicRelationsHeadlineDto.class, paraMap);
    }

    @Override
    public List<PublicRelationsDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(PublicRelationsDto.class, paraMap);
    }

    @Override
    public List<PublicRelationsDto> listByIds(List<Long> idList) {
        List<PublicRelationsDo> list = publicRelationsRepository.list(new LambdaQueryWrapper<PublicRelationsDo>()
                .in(PublicRelationsDo::getId, idList));
        return publicRelationsAssembler.toValueObjectList(list);
    }

    @Override
    public PublicRelationsDto get(Long id) {
        PublicRelationsDo publicRelationsDo = publicRelationsRepository.getOne(new LambdaQueryWrapper<PublicRelationsDo>()
                .eq(PublicRelationsDo::getId, id));
        PublicRelationsDto publicRelationsDTO = publicRelationsAssembler.toValueObject(publicRelationsDo);
        Map<String,Object> map = new HashMap<>();
        map.put("orderType",AttachmentOrderType.PUBLIC_RELATION.getId());
        map.put("orderId",id);
       publicRelationsDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        return publicRelationsDTO;
    }

    @Override
    public PublicRelationsDto getByVisitPlanId(Long visitPlanId) {
        PublicRelationsDo publicRelationsDo = publicRelationsRepository.getOne(new LambdaQueryWrapper<PublicRelationsDo>()
                .eq(PublicRelationsDo::getVisitPlanId, visitPlanId));
        PublicRelationsDto publicRelationsDTO = publicRelationsAssembler.toValueObject(publicRelationsDo);

        return publicRelationsDTO;
    }

    private final VisitPlanRepository visitPlanRepository;

    @Override
    public void add(PublicRelationsAddCmd addCmd) {
        //设置申请人部门编号
        MdmPersonDto personDto = mdmPersonService.getPersonCodeDetail(addCmd.getCreatedBy());
        String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
        //通过t_mdmorganization查询出groupFullCode
        MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
        String fullCode = organizationDto.getGroupFullCode();

        addCmd.setGroupFullCode(fullCode);
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        addCmd=(PublicRelationsAddCmd)mdmOrganizationUtil.getMdmOrganization(addCmd,personCode);
        PublicRelationsDo publicRelationsDo = publicRelationsAssembler.toDO(addCmd);
        boolean save = publicRelationsRepository.save(publicRelationsDo);
        if (save){
            attachmentService.saveBatch(addCmd.getAddCmdList(),publicRelationsDo.getId().toString(), AttachmentOrderType.PUBLIC_RELATION);
            //公关反馈保存后更改公关计划关联状态
            visitPlanRepository.update(new LambdaUpdateWrapper<VisitPlanDo>()
                    .eq(VisitPlanDo::getId,addCmd.getVisitPlanId())
                    .set(VisitPlanDo::getCiteStatus,"1"));
        }
    }

    @Override
    public Long addThenReturnId(PublicRelationsAddCmd addCmd) {
        //公关反馈保存后更改公关计划关联状态
        visitPlanRepository.update(new LambdaUpdateWrapper<VisitPlanDo>()
                .eq(VisitPlanDo::getId,addCmd.getVisitPlanId())
                .set(VisitPlanDo::getCiteStatus,"1"));
        PublicRelationsDo publicRelationsDo = publicRelationsAssembler.toDO(addCmd);
        boolean save = publicRelationsRepository.save(publicRelationsDo);
        if (save){
            return publicRelationsDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(PublicRelationsUpdateCmd updateCmd) {
        PublicRelationsDto publicRelationsDTO = this.get(updateCmd.getId());
        if (publicRelationsDTO != null) {
            PublicRelationsDo publicRelationsDo = publicRelationsAssembler.toDO(updateCmd);
            publicRelationsRepository.updateById(publicRelationsDo);
            //先根据公关实施计划id删除附件
            attachmentService.delBatchByOrderId(publicRelationsDo.getId().toString());
            //后保存
            attachmentService.saveBatch(updateCmd.getAddCmdList(),publicRelationsDo.getId().toString(),AttachmentOrderType.PUBLIC_RELATION);
        }
    }

    @Override
    public void delete(Long id) {
        PublicRelationsDto publicRelationsDto=get(id);
        //公关反馈保存后更改公关计划关联状态
        visitPlanRepository.update(new LambdaUpdateWrapper<VisitPlanDo>()
                .eq(VisitPlanDo::getId,publicRelationsDto.getVisitPlanId())
                .set(VisitPlanDo::getCiteStatus,"0"));

        publicRelationsRepository.remove(new LambdaQueryWrapper<PublicRelationsDo>()
                .eq(PublicRelationsDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            for (Long id:primaryKeys) {
                PublicRelationsDto publicRelationsDto=get(id);
                //公关反馈保存后更改公关计划关联状态
                visitPlanRepository.update(new LambdaUpdateWrapper<VisitPlanDo>()
                        .eq(VisitPlanDo::getId,publicRelationsDto.getVisitPlanId())
                        .set(VisitPlanDo::getCiteStatus,"0"));
            }
            publicRelationsRepository.remove(new LambdaQueryWrapper<PublicRelationsDo>()
                    .in(PublicRelationsDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), PublicRelationsExcelExport.class, "公关实施导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> paraMap, HttpServletResponse response) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("activityProvinceAndCity")){
            String s = paraMap.get("activityProvinceAndCity").toString();
            if(s.endsWith("00")){
                //截取前两位
                paraMap.put("activityProvinceAndCity",s.substring(0,2));
                //以什么开头
                paraMap.put("activityProvinceAndCity-op","sw");
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
        paraMap.put("dutyUnit-op","ct");
        paraMap.put("createdByName-op","ct");
        paraMap.put("activityAddress-op","ct");
        paraMap.put("results-op","ct");
        paraMap.put("GROUPFULLCODE-op","ct");
        List<PublicRelationsExcelExport> searchResult = beanSearcher.searchAll(PublicRelationsExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, PublicRelationsExcelExport.class, "公关实施数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<PublicRelationsExcelImport> excelParse(MultipartFile file) {
        PublicRelationsExcelImportListener listener = new PublicRelationsExcelImportListener();
        ExcelUtil.readFile(file, PublicRelationsExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<PublicRelationsExcelImport> list) {
        List<PublicRelationsDo> publicRelationsDoList = publicRelationsAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(publicRelationsDoList)) {
            publicRelationsRepository.saveBatch(publicRelationsDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<PublicRelationsExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, PublicRelationsExcelExport.class, "公关实施错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
