package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.InList;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.*;

import com.uwdp.module.api.vo.cmd.ClientRoleEntityAddCmd;
import com.uwdp.module.api.vo.cmd.ClientRoleEntityUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;

import com.uwdp.module.api.vo.dto.MdmBpDeptDto;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.api.vo.excel.ClientRoleEntityExcelExport;
import com.uwdp.module.api.vo.excel.ClientRoleEntityExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ClientRoleEntityAssembler;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.entity.MdmBpDeptDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.excel.ClientRoleEntityExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 */
@Slf4j
@RequiredArgsConstructor
@Service("clientRoleService")
public class ClientRoleServiceImpl  implements IClientRoleService {
//public class ClientRoleServiceImpl extends ServiceImpl<ClientRoleMapper, ClientRoleEntityDto> implements IClientRoleService {

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    private final ClientRoleEntityAssembler clientRoleEntityAssembler;

    private final BeanSearcher beanSearcher;

    @Autowired
    private IMdmBpDeptService bpDeptService;

    @Autowired
    private IMdmPersonService mdmPersonService;

    @Autowired
    private IMdmOrganizationService mdmOrganizationService;

    @Override
    public SearchResult<ClientRoleEntityDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ClientRoleEntityDto.class, paraMap);
    }

    @Override
    public List<ClientRoleEntityDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ClientRoleEntityDto.class, paraMap);
    }

    @Override
    public List<ClientRoleEntityDto> listByIds(List<Integer> idList) {
        List<ClientRoleEntityDo> list = clientRoleEntityRepository.list(new LambdaQueryWrapper<ClientRoleEntityDo>()
                .in(ClientRoleEntityDo::getId, idList));
        return clientRoleEntityAssembler.toValueObjectList(list);
    }

    @Override
    public ClientRoleEntityDto get(Integer id) {
        ClientRoleEntityDo clientRoleEntityDo = clientRoleEntityRepository.getOne(new LambdaQueryWrapper<ClientRoleEntityDo>()
                .eq(ClientRoleEntityDo::getId, id));
        ClientRoleEntityDto clientRoleEntityDTO = clientRoleEntityAssembler.toValueObject(clientRoleEntityDo);
        return clientRoleEntityDTO;
    }

    @Override
    public void add(ClientRoleEntityAddCmd addCmd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTimeString = LocalDateTime.now().format(formatter);
        System.out.println("Formatted DateTime: " + formattedDateTimeString);

        LocalDateTime formattedDateTime = LocalDateTime.parse(formattedDateTimeString, formatter);
        System.out.println("Parsed DateTime: " + formattedDateTime);

        addCmd.setCreatedTime(formattedDateTime);
        ClientRoleEntityDo clientRoleEntityDo = clientRoleEntityAssembler.toDO(addCmd);
        clientRoleEntityRepository.save(clientRoleEntityDo);
    }

    @Override
    public void update(ClientRoleEntityUpdateCmd updateCmd) {
        ClientRoleEntityDto clientRoleEntityDTO = this.get(updateCmd.getId());
        if (clientRoleEntityDTO != null) {
            ClientRoleEntityDo clientRoleEntityDo = clientRoleEntityAssembler.toDO(updateCmd);
            clientRoleEntityRepository.updateById(clientRoleEntityDo);
        }
    }

    @Override
    public void delete(Integer id) {
        clientRoleEntityRepository.remove(new LambdaQueryWrapper<ClientRoleEntityDo>()
                .eq(ClientRoleEntityDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Integer> primaryKeys = StrUtil.split(ids, ",").stream().map(Integer::valueOf).collect(Collectors.toList());
            clientRoleEntityRepository.remove(new LambdaQueryWrapper<ClientRoleEntityDo>()
                    .in(ClientRoleEntityDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ClientRoleEntityExcelExport.class, "权限表导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ClientRoleEntityExcelExport> searchResult = beanSearcher.searchAll(ClientRoleEntityExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ClientRoleEntityExcelExport.class, "权限表数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ClientRoleEntityExcelImport> excelParse(MultipartFile file) {
        ClientRoleEntityExcelImportListener listener = new ClientRoleEntityExcelImportListener();
        ExcelUtil.readFile(file, ClientRoleEntityExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ClientRoleEntityExcelImport> list) {
        List<ClientRoleEntityDo> clientRoleEntityDoList = clientRoleEntityAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(clientRoleEntityDoList)) {
            clientRoleEntityRepository.saveBatch(clientRoleEntityDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ClientRoleEntityExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ClientRoleEntityExcelExport.class, "权限表错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public Map<String, Object> queryUserRole(Map<String, Object> paraMap) {
        /*QueryWrapper<ClientRoleEntityDo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_id", paraMap.get("createdBy") + ",");
        queryWrapper.eq("status", 0);
        List<ClientRoleEntityDo> clientInfoDtos = clientRoleEntityRepository.list(queryWrapper);
        if (clientInfoDtos != null) {
            for (ClientRoleEntityDo clientInfoDto : clientInfoDtos) {
                Integer role = clientInfoDto.getRole();
                paraMap.remove("createdBy");
                if (1 == role) {
                    String deptId = clientInfoDto.getDeptId();
                    String[] depts = deptId.split(",");
                    for (int i = 0; i < depts.length; i++) {
                        paraMap.put("GROUPFULLCODE-" + i, "%" + depts[i] + "%");
                    }
                    paraMap.put("GROUPFULLCODE-op", "ol");    // in: 包含，sw: 以...开头，ew: 以...结尾
                    paraMap.put("GROUPFULLCODE-ic", true);    // 同时可以指定是否可以忽略大小写
                }else if(0 == role){
                    paraMap.remove("createdBy");
                }
            }
        }*/
        if(ObjectUtils.isEmpty(paraMap.get("pageIndex")) && ObjectUtils.isEmpty(paraMap.get("pageSize"))) {
            paraMap.put("pageIndex", paraMap.get("A.pageIndex"));
            paraMap.put("pageSize", paraMap.get("A.pageSize"));
        }

        String createBy = String.valueOf(paraMap.get("createdBy") == null? paraMap.get("A.createdBy") : paraMap.get("createdBy"));
        String [] pointResourcesList;

        /**
         * 项目跟踪用到，协助部门
         */
        MdmPersonDto personDto;
        if(!ObjectUtils.isEmpty(paraMap.get("createdBy")) || !ObjectUtils.isEmpty(paraMap.get("A.createdBy"))) {
            personDto = mdmPersonService.getPersonCodeDetail(createBy);
            String groupBelongDepartmentCode = personDto.getGroupBelongDepartmentCode();
            //通过t_mdmorganization查询出groupFullCode
            MdmOrganizationDto organizationDto = mdmOrganizationService.getGroupCode(groupBelongDepartmentCode);
            String fullCode = organizationDto.getGroupFullCode();
            paraMap.put("userGroupFullCode", fullCode);
        }
//        request.getHeader("pointResourcesList");
        if(paraMap.get("pointResourcesList") != null || paraMap.get("A.pointResourcesList") != null) {
            pointResourcesList = String.valueOf(paraMap.get("pointResourcesList") == null?paraMap.get("A.pointResourcesList"):paraMap.get("pointResourcesList")).split(",");
            log.info("通用点集合：{}", pointResourcesList);
            Map<String, Object> map = MapUtils.builder()
                    .field(MdmBpDeptDo::getBpCode, pointResourcesList).op(InList.class)
                    .build();
            List<MdmBpDeptDto> bpDeptDtos = bpDeptService.searchByParam(map);
            log.info("通用点对应部门集合：{}", bpDeptDtos);
            if(bpDeptDtos == null || bpDeptDtos.size() == 0){
                paraMap.put("A.createdBy", createBy);
                return paraMap;
            }
            if(!paraMap.containsKey("isForien")) {
                paraMap.put("A.isForien-0", 0);
                paraMap.put("A.isForien-1", 1);
                paraMap.put("A.isForien-op", "il");
            }
            // 如果有所有部门权限，则直接返回
            for (MdmBpDeptDto mdmBpDeptDto : bpDeptDtos) {
                Integer scope = mdmBpDeptDto.getScope();
                if (scope == 0) {
                    paraMap.remove("createdBy");
                    paraMap.remove("A.createdBy");
                    return paraMap;
                }
            }
            List<String> groupFullCodeList = new ArrayList<>();

            int num = -1;
            for (MdmBpDeptDto dto : bpDeptDtos) {
                paraMap.remove("createdBy");
                paraMap.remove("pointResourcesList");
                paraMap.remove("A.createdBy");
                paraMap.remove("A.pointResourcesList");
                // 如果是本部门通用点
                if (dto.getScope() == 2) {
                    // 数据权限 添加创建人字段(保存创建人personCode)
                    personDto = mdmPersonService.get(createBy);
                    String groupFullCode = mdmOrganizationService.getGroupCode(
                            personDto.getGroupBelongDepartmentCode()
                    ).getGroupFullCode();
                    log.info("权限查询-用户的部门为：{}", groupFullCode);
                    num ++;
                    paraMap.put("A.groupFullCode-" + num, "%" + groupFullCode + "%");
                } else if (dto.getScope() == 3) {
                    String area = dto.getArea();
                    paraMap.put("A.owningRegion", area);
                } else if(dto.getScope() == 4){
                    String domain = dto.getDomain();
                    paraMap.put("A.industryCategory", domain);
                    //以什么开头
                    paraMap.put("A.industryCategory-op","sw");
                }else {
                    String deptId = dto.getDeptCodes();
                    String[] depts = deptId.split(",");
                    for (int i = 0; i < depts.length; i++) {
                        num++;
                        paraMap.put("A.groupFullCode-" + num, "%" + depts[i] + "%");
                        groupFullCodeList.add(depts[i]);
                    }
                }
                paraMap.put("A.groupFullCode-op", "ol");    // in: 包含，sw: 以...开头，ew: 以...结尾
                paraMap.put("A.groupFullCode-ic", true);    // 同时可以指定是否可以忽略大小写
            }

            paraMap.put("A.groupFullCodeList", groupFullCodeList);
        }
        log.info("通用点组装后条件为：{}", paraMap);
        return paraMap;
    }
}