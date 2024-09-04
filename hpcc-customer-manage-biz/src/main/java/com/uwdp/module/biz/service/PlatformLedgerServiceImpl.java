package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IAttachmentService;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.service.IPlatformLedgerService;
import com.uwdp.module.api.vo.cmd.PlatformLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.PlatformLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.PlatformLedgerDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.api.vo.excel.PlatformLedgerExcelExport;
import com.uwdp.module.api.vo.excel.PlatformLedgerExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.PlatformLedgerAssembler;
import com.uwdp.module.biz.infrastructure.entity.PlatformLedgerDo;
import com.uwdp.module.biz.infrastructure.excel.PlatformLedgerExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.PlatformLedgerRepository;
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
 * 平台台账 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlatformLedgerServiceImpl implements IPlatformLedgerService {

    private final PlatformLedgerRepository platformLedgerRepository;

    private final PlatformLedgerAssembler platformLedgerAssembler;

    private final BeanSearcher beanSearcher;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    private final MdmOrganizationUtil mdmOrganizationUtil;

    @Autowired
    private IClientRoleService clientRoleService;

    @Override
    public SearchResult<PlatformLedgerDto> pageByParam(Map<String, Object> paraMap) {
//        paraMap = clientRoleService.queryUserRole(paraMap);
        paraMap.remove("createdBy");
        if(paraMap.containsKey("createdTime")){
            String date = paraMap.get("createdTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("createdTime-0",date1);
            paraMap.put("createdTime-1",date2);
            paraMap.put("createdTime-op", "bt");
            paraMap.remove("createdTime");
        }
        SearchResult<PlatformLedgerDto> search = beanSearcher.search(PlatformLedgerDto.class, paraMap);
        List<PlatformLedgerDto> dataList = search.getDataList();
        dataList.forEach( it->{
            it.setFdPassword("********");
        });
        return search;
    }

    @Override
    public List<PlatformLedgerDto> searchAll(Map<String, Object> paraMap) {
        List<PlatformLedgerDto> platformLedgerDtos = beanSearcher.searchAll(PlatformLedgerDto.class, paraMap);
        platformLedgerDtos.forEach( it->{
            it.setFdPassword("********");
        });
        return platformLedgerDtos;
    }

    @Override
    public List<PlatformLedgerDto> searchByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        List<PlatformLedgerDto> platformLedgerDtos = beanSearcher.searchAll(PlatformLedgerDto.class, paraMap);
        platformLedgerDtos.forEach( it->{
                it.setFdPassword("********");
        });
        return platformLedgerDtos;
    }

    @Override
    public List<PlatformLedgerDto> listByIds(List<Long> fdIdList) {
        List<PlatformLedgerDo> list = platformLedgerRepository.list(new LambdaQueryWrapper<PlatformLedgerDo>()
                .in(PlatformLedgerDo::getFdId, fdIdList));
        list.forEach( it->{
                it.setFdPassword("********");
        });
        return platformLedgerAssembler.toValueObjectList(list);
    }

    private final IAttachmentService attachmentService;

    @Override
    public PlatformLedgerDto get(Long fdId) {
        PlatformLedgerDo platformLedgerDo = platformLedgerRepository.getOne(new LambdaQueryWrapper<PlatformLedgerDo>()
                .eq(PlatformLedgerDo::getFdId, fdId));
        PlatformLedgerDto platformLedgerDTO = platformLedgerAssembler.toValueObject(platformLedgerDo);
        Map<String,Object> map = new HashMap<>();
        map.put("orderType", AttachmentOrderType.PLATFORM_LEDGER.getId());
        map.put("orderId",fdId);
        platformLedgerDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        platformLedgerDTO.setFdPassword("********");
        return platformLedgerDTO;
    }

    @Override
    public PlatformLedgerDto getPassword(Long fdId) {
        PlatformLedgerDo platformLedgerDo = platformLedgerRepository.getOne(new LambdaQueryWrapper<PlatformLedgerDo>()
                .eq(PlatformLedgerDo::getFdId, fdId));
        PlatformLedgerDto platformLedgerDTO = platformLedgerAssembler.toValueObject(platformLedgerDo);
        Map<String,Object> map = new HashMap<>();
        map.put("orderType", AttachmentOrderType.PLATFORM_LEDGER.getId());
        map.put("orderId",fdId);
        platformLedgerDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        platformLedgerDTO.setFdPassword(AseUtil.decryptStr(platformLedgerDTO.getFdPassword()));
        return platformLedgerDTO;
    }

    @Override
    public void add(PlatformLedgerAddCmd addCmd) {
        addCmd.setFdPassword(AseUtil.encryptBase64(addCmd.getFdPassword()));
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        String groupFullCode=mdmOrganizationUtil.getGroupFullCode(personCode);
        addCmd.setGroupFullCode(groupFullCode);

        PlatformLedgerDo platformLedgerDo = platformLedgerAssembler.toDO(addCmd);
        platformLedgerRepository.save(platformLedgerDo);
        attachmentService.saveBatch(addCmd.getAddCmdList(),platformLedgerDo.getFdId().toString(), AttachmentOrderType.PLATFORM_LEDGER);
    }

    @Override
    public void update(PlatformLedgerUpdateCmd updateCmd) {
        updateCmd.setFdPassword(AseUtil.encryptBase64(updateCmd.getFdPassword()));
        PlatformLedgerDto platformLedgerDTO = this.get(updateCmd.getFdId());
        if (platformLedgerDTO != null) {
            PlatformLedgerDo platformLedgerDo = platformLedgerAssembler.toDO(updateCmd);
            platformLedgerRepository.updateById(platformLedgerDo);
            //先根据id删除附件
            attachmentService.delBatchByOrderId(platformLedgerDo.getFdId().toString());
            //后保存
            attachmentService.saveBatch(updateCmd.getAddCmdList(),platformLedgerDo.getFdId().toString(),AttachmentOrderType.PLATFORM_LEDGER);
        }
    }

    @Override
    public void delete(Long fdId) {
        platformLedgerRepository.remove(new LambdaQueryWrapper<PlatformLedgerDo>()
                .eq(PlatformLedgerDo::getFdId, fdId));
    }

    @Override
    public void batchDelete(String fdIds) {
        if (StringUtils.hasText(fdIds)) {
            List<Long> primaryKeys = StrUtil.split(fdIds, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            platformLedgerRepository.remove(new LambdaQueryWrapper<PlatformLedgerDo>()
                    .in(PlatformLedgerDo::getFdId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), PlatformLedgerExcelExport.class, "平台台账导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<PlatformLedgerExcelExport> searchResult = beanSearcher.searchAll(PlatformLedgerExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, PlatformLedgerExcelExport.class, "平台台账数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<PlatformLedgerExcelImport> excelParse(MultipartFile file) {
        PlatformLedgerExcelImportListener listener = new PlatformLedgerExcelImportListener();
        ExcelUtil.readFile(file, PlatformLedgerExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<PlatformLedgerExcelImport> list) {
        List<PlatformLedgerDo> platformLedgerDoList = platformLedgerAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(platformLedgerDoList)) {
            platformLedgerRepository.saveBatch(platformLedgerDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<PlatformLedgerExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, PlatformLedgerExcelExport.class, "平台台账错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
