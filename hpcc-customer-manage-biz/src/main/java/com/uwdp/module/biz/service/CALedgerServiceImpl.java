package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IAttachmentService;
import com.uwdp.module.api.service.ICALedgerService;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.vo.cmd.CALedgerAddCmd;
import com.uwdp.module.api.vo.cmd.CALedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.CALedgerDto;
import com.uwdp.module.api.vo.dto.searcher.CALedgerCerInfoDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.api.vo.excel.CALedgerExcelExport;
import com.uwdp.module.api.vo.excel.CALedgerExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.CALedgerAssembler;
import com.uwdp.module.biz.infrastructure.entity.CALedgerDo;
import com.uwdp.module.biz.infrastructure.excel.CALedgerExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.CALedgerRepository;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.utils.AseUtil;
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
 * CA台账 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CALedgerServiceImpl implements ICALedgerService {

    private final CALedgerRepository cALedgerRepository;

    private final CALedgerAssembler cALedgerAssembler;

    private final BeanSearcher beanSearcher;

    private final MdmOrganizationUtil mdmOrganizationUtil;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Autowired
    private IClientRoleService clientRoleService;

    @Override
    public SearchResult<CALedgerDto> pageByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("fdPlatformValidity")){
            String date = paraMap.get("fdPlatformValidity").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("fdPlatformValidity-0",date1);
            paraMap.put("fdPlatformValidity-1",date2);
            paraMap.put("fdPlatformValidity-op", "bt");
            paraMap.remove("fdPlatformValidity");
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
        SearchResult<CALedgerDto> search = beanSearcher.search(CALedgerDto.class, paraMap);
        List<CALedgerDto> dataList = search.getDataList();
        dataList.forEach( it->{
            it.setFdPassword("********");
        });
        return search;
    }

    @Override
    public List<CALedgerCerInfoDto> searchAll(Map<String, Object> paraMap) {
        List<CALedgerCerInfoDto> caLedgerCerInfoDtos = beanSearcher.searchAll(CALedgerCerInfoDto.class, paraMap);
        caLedgerCerInfoDtos.forEach( it->{
            it.setFdPassword("********");
        });
        return caLedgerCerInfoDtos;
    }

    @Override
    public List<CALedgerDto> searchByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        List<CALedgerDto> caLedgerDtos = beanSearcher.searchAll(CALedgerDto.class, paraMap);
        caLedgerDtos.forEach( it->{
            it.setFdPassword("********");
        });
        return caLedgerDtos;
    }

    @Override
    public List<CALedgerDto> listByIds(List<Long> fdIdList) {
        List<CALedgerDo> list = cALedgerRepository.list(new LambdaQueryWrapper<CALedgerDo>()
                .in(CALedgerDo::getFdId, fdIdList));
        list.forEach( it->{
            it.setFdPassword("********");
        });
        return cALedgerAssembler.toValueObjectList(list);
    }

    private final IAttachmentService attachmentService;

    @Override
    public CALedgerDto get(Long fdId) {
        CALedgerDo cALedgerDo = cALedgerRepository.getOne(new LambdaQueryWrapper<CALedgerDo>()
                .eq(CALedgerDo::getFdId, fdId));
        CALedgerDto cALedgerDTO = cALedgerAssembler.toValueObject(cALedgerDo);
        Map<String,Object> map = new HashMap<>();
        map.put("orderType", AttachmentOrderType.CA_LEDGER.getId());
        map.put("orderId",fdId);
        cALedgerDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        cALedgerDTO.setFdPassword("********");
        return cALedgerDTO;
    }
    @Override
    public CALedgerDto getPassword(Long fdId) {
        CALedgerDo cALedgerDo = cALedgerRepository.getOne(new LambdaQueryWrapper<CALedgerDo>()
                .eq(CALedgerDo::getFdId, fdId));
        CALedgerDto cALedgerDTO = cALedgerAssembler.toValueObject(cALedgerDo);
        Map<String,Object> map = new HashMap<>();
        map.put("orderType", AttachmentOrderType.CA_LEDGER.getId());
        map.put("orderId",fdId);
        cALedgerDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        cALedgerDTO.setFdPassword(AseUtil.decryptStr(cALedgerDTO.getFdPassword()));
        return cALedgerDTO;
    }

    @Override
    public void add(CALedgerAddCmd addCmd) {
        addCmd.setFdPassword(AseUtil.encryptBase64(addCmd.getFdPassword()));
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        String groupFullCode=mdmOrganizationUtil.getGroupFullCode(personCode);
        addCmd.setGroupFullCode(groupFullCode);

        CALedgerDo cALedgerDo = cALedgerAssembler.toDO(addCmd);
        cALedgerRepository.save(cALedgerDo);
        attachmentService.saveBatch(addCmd.getAddCmdList(),cALedgerDo.getFdId().toString(), AttachmentOrderType.CA_LEDGER);
    }

    @Override
    public void update(CALedgerUpdateCmd updateCmd) {
        updateCmd.setFdPassword(AseUtil.encryptBase64(updateCmd.getFdPassword()));
        CALedgerDto cALedgerDTO = this.get(updateCmd.getFdId());
        if (cALedgerDTO != null) {
            CALedgerDo cALedgerDo = cALedgerAssembler.toDO(updateCmd);
            cALedgerRepository.updateById(cALedgerDo);
            //先根据id删除附件
            attachmentService.delBatchByOrderId(cALedgerDo.getFdId().toString());
            //后保存
            attachmentService.saveBatch(updateCmd.getAddCmdList(),cALedgerDo.getFdId().toString(),AttachmentOrderType.CA_LEDGER);
        }
    }

    @Override
    public void delete(Long fdId) {
        cALedgerRepository.remove(new LambdaQueryWrapper<CALedgerDo>()
                .eq(CALedgerDo::getFdId, fdId));
    }

    @Override
    public void batchDelete(String fdIds) {
        if (StringUtils.hasText(fdIds)) {
            List<Long> primaryKeys = StrUtil.split(fdIds, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            cALedgerRepository.remove(new LambdaQueryWrapper<CALedgerDo>()
                    .in(CALedgerDo::getFdId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), CALedgerExcelExport.class, "CA台账导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<CALedgerExcelExport> searchResult = beanSearcher.searchAll(CALedgerExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, CALedgerExcelExport.class, "CA台账数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<CALedgerExcelImport> excelParse(MultipartFile file) {
        CALedgerExcelImportListener listener = new CALedgerExcelImportListener();
        ExcelUtil.readFile(file, CALedgerExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<CALedgerExcelImport> list) {
        List<CALedgerDo> cALedgerDoList = cALedgerAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(cALedgerDoList)) {
            cALedgerRepository.saveBatch(cALedgerDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<CALedgerExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, CALedgerExcelExport.class, "CA台账错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
