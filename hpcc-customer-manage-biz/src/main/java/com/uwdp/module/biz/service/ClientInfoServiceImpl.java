package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.SearcherBuilder;
import com.ejlchina.searcher.convertor.EnumFieldConvertor;
import com.ejlchina.searcher.implement.DefaultBeanReflector;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IClientInfoService;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.ClientInfoUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientInfoDto;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.biz.infrastructure.assembler.ClientInfoAssembler;
import com.uwdp.module.biz.infrastructure.entity.ClientInfoDo;
import com.uwdp.module.api.vo.dto.searcher.ClientInfoWorkflowDto;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.entity.MdmOrganizationDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.repository.ClientInfoRepository;
import com.uwdp.module.api.vo.excel.ClientInfoExcelExport;
import com.uwdp.module.api.vo.excel.ClientInfoExcelImport;
import com.uwdp.module.biz.infrastructure.excel.ClientInfoExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmOrganizationRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import com.uwdp.module.biz.utils.MdmOrganizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 客户信息总表 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ClientInfoServiceImpl implements IClientInfoService {

    private final ClientInfoRepository clientInfoRepository;

    private final MdmPersonRepository mdmPersonRepository;

    private final MdmOrganizationRepository mdmOrganizationRepository;

    private final ClientInfoAssembler clientInfoAssembler;

    private final MdmPersonServiceImpl mdmPersonService;

    private final MdmOrganizationServiceImpl mdmOrganizationService;

    private final BeanSearcher beanSearcher;

    @Autowired
    private IClientRoleService clientRoleService;
    @Autowired
    private MdmOrganizationUtil mdmOrganizationUtil;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<ClientInfoDto> pageByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);

        return beanSearcher.search(ClientInfoDto.class, paraMap);
    }

    @Override
    public SearchResult<ClientInfoWorkflowDto> pageThatPassed(Map<String, Object> paraMap) {
        String createdBy = paraMap.get("createdBy").toString();
        Map<String, Object> cMap = new HashMap<>();
        for (String key : paraMap.keySet()){
            cMap.put("C." + key.replace("A.", ""), paraMap.get(key));
        }
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("isForien")){
            String s = paraMap.get("isForien").toString();
            String s1 = "";
            if("0".equals(s)){
                s1 = "01201300";
            }else if("1".equals(s)){
                s1 = "01201301";
            }
            if(paraMap.containsKey("groupFullCode")){
                String s2 = paraMap.get("groupFullCode").toString();
                paraMap.put("C.groupFullCode",s1+"/"+s2);
            }else{
                paraMap.put("C.groupFullCode",s1);
            }
        }
        paraMap.put("C.groupFullCode-op","ct");
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
                .put("B.createdBy", createdBy)
                .groupExpr("(A|B)&C").build();

//        paraMap = MapUtils.builder().put("createdBy", createdBy).build();

        log.info("paraMap={}", paraMap);
        return beanSearcher.search(ClientInfoWorkflowDto.class, paraMap);
    }

    @Override
    public List<ClientInfoDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ClientInfoDto.class, paraMap);
    }

    @Override
    public List<ClientInfoDto> listByIds(List<Long> fdIdList) {
        List<ClientInfoDo> list = clientInfoRepository.list(new LambdaQueryWrapper<ClientInfoDo>()
                .in(ClientInfoDo::getFdId, fdIdList));
        return clientInfoAssembler.toValueObjectList(list);
    }

    @Override
    public Integer getClientInfoCount(String fdName, String fdUnit) {
        return clientInfoRepository.getClientInfoCount(fdName,fdUnit);
    }

    @Override
    public ClientInfoDto get(Long fdId) {
        ClientInfoDo clientInfoDo = clientInfoRepository.getOne(new LambdaQueryWrapper<ClientInfoDo>()
                .eq(ClientInfoDo::getFdId, fdId));
        ClientInfoDto clientInfoDTO = clientInfoAssembler.toValueObject(clientInfoDo);
        return clientInfoDTO;
    }

    @Override
    public void add(ClientInfoAddCmd addCmd) {
        //设置申请人部门编号
        MdmPersonDto personDto = mdmPersonService.getPersonCodeDetail(addCmd.getCreatedBy());
        String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
        //通过t_mdmorganization查询出groupFullCode
        MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
        String fullCode = organizationDto.getGroupFullCode();

        addCmd.setGroupFullCode(fullCode);

        // 数据权限 添加创建人字段(保存创建人personCode)
//        String personCode = "77265";
        String personCode = addCmd.getCreatedBy();
        addCmd=(ClientInfoAddCmd)mdmOrganizationUtil.getMdmOrganization(addCmd,personCode);
        ClientInfoDo clientInfoDo = clientInfoAssembler.toDO(addCmd);
        clientInfoRepository.save(clientInfoDo);
    }

    @Override
    public Long addThenReturnId(ClientInfoAddCmd addCmd) {
        ClientInfoDo clientInfoDo = clientInfoAssembler.toDO(addCmd);
        boolean save = clientInfoRepository.save(clientInfoDo);
        if (save){
            return clientInfoDo.getFdId();
        }
        return 0L;
    }

    @Override
    public void update(ClientInfoUpdateCmd updateCmd) {
        ClientInfoDto clientInfoDTO = this.get(updateCmd.getFdId());
        if (clientInfoDTO != null) {
            ClientInfoDo clientInfoDo = clientInfoAssembler.toDO(updateCmd);
            clientInfoRepository.updateById(clientInfoDo);
        }
    }

    @Override
    public void delete(Long fdId) {
        clientInfoRepository.remove(new LambdaQueryWrapper<ClientInfoDo>()
                .eq(ClientInfoDo::getFdId, fdId));
    }

    @Override
    public void batchDelete(String fdIds) {
        if (StringUtils.hasText(fdIds)) {
            List<Long> primaryKeys = StrUtil.split(fdIds, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            clientInfoRepository.remove(new LambdaQueryWrapper<ClientInfoDo>()
                    .in(ClientInfoDo::getFdId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ClientInfoExcelExport.class, "客户信息总表导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> paraMap, HttpServletResponse response) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("isForien")){
            String s = paraMap.get("isForien").toString();
            String s1 = "";
            if("0".equals(s)){
                s1 = "01201300";
            }else if("1".equals(s)){
                s1 = "01201301";
            }
            if(paraMap.containsKey("GROUPFULLCODE")){
                String s2 = paraMap.get("GROUPFULLCODE").toString();
                paraMap.put("GROUPFULLCODE",s1+"/"+s2);
            }else{
                paraMap.put("GROUPFULLCODE",s1);
            }
        }
        paraMap.put("GROUPFULLCODE-op","ct");
        if(paraMap.containsKey("createdTime")){
            String date = paraMap.get("createdTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("createdTime-0",date1);
            paraMap.put("createdTime-1",date2);
            paraMap.put("createdTime-op", "bt");
            paraMap.remove("createdTime");
        }
        paraMap.put("fdName-op","ct");
        paraMap.put("fdAffiliatedUser-op","ct");
        paraMap.put("fdUnit-op","ct");
        paraMap.put("fdClientClassify-op","ct");
        paraMap.put("fdContactPerson-op","ct");
        paraMap.put("createdByName-op","ct");
        List<ClientInfoExcelExport> searchResult = beanSearcher.searchAll(ClientInfoExcelExport.class, paraMap);
        ExcelUtil.downloadFile(searchResult, ClientInfoExcelExport.class, "客户信息总表数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ClientInfoExcelImport> excelParse(MultipartFile file) {
        ClientInfoExcelImportListener listener = new ClientInfoExcelImportListener();
        ExcelUtil.readFile(file, ClientInfoExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ClientInfoExcelImport> list) {
        List<ClientInfoDo> clientInfoDoList = clientInfoAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(clientInfoDoList)) {
            clientInfoRepository.saveBatch(clientInfoDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ClientInfoExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ClientInfoExcelExport.class, "客户信息总表错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
