package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IAttachmentService;
import com.uwdp.module.api.service.ICertificatesService;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.vo.cmd.CerInfoAddCmd;
import com.uwdp.module.api.vo.cmd.CertificatesAddCmd;
import com.uwdp.module.api.vo.cmd.CertificatesUpdateCmd;
import com.uwdp.module.api.vo.dto.CertificatesDto;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.biz.infrastructure.assembler.CertificatesAssembler;
import com.uwdp.module.biz.infrastructure.entity.CertificatesDo;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.repository.CertificatesRepository;
import com.uwdp.module.api.vo.excel.CertificatesExcelExport;
import com.uwdp.module.api.vo.excel.CertificatesExcelImport;
import com.uwdp.module.biz.infrastructure.excel.CertificatesExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
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
 * 荣誉证书 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CertificatesServiceImpl implements ICertificatesService {

    private final CertificatesRepository certificatesRepository;

    private final CertificatesAssembler certificatesAssembler;

    private final BeanSearcher beanSearcher;

    private final IAttachmentService attachmentService;

    private final IClientRoleService clientRoleService;

    @Autowired
    private MdmOrganizationUtil mdmOrganizationUtil;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    @Override
    public SearchResult<CertificatesDto> pageByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("awardTime")){
            String date = paraMap.get("awardTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("awardTime-0",date1);
            paraMap.put("awardTime-1",date2);
            paraMap.put("awardTime-op", "bt");
            paraMap.remove("awardTime");
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
        return beanSearcher.search(CertificatesDto.class, paraMap);
    }

    @Override
    public List<CertificatesDto> searchByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        return beanSearcher.searchAll(CertificatesDto.class, paraMap);
    }

    @Override
    public List<CertificatesDto> listByIds(List<Long> idList) {
        List<CertificatesDo> list = certificatesRepository.list(new LambdaQueryWrapper<CertificatesDo>()
                .in(CertificatesDo::getId, idList));
        return certificatesAssembler.toValueObjectList(list);
    }

    @Override
    public CertificatesDto get(Long id) {
        CertificatesDo certificatesDo = certificatesRepository.getOne(new LambdaQueryWrapper<CertificatesDo>()
                .eq(CertificatesDo::getId, id));
        CertificatesDto certificatesDTO = certificatesAssembler.toValueObject(certificatesDo);
        Map<String,Object> map = new HashMap<>();
        map.put("orderType", AttachmentOrderType.CERTIFICATES.getId());
        map.put("orderId",id);
        certificatesDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        return certificatesDTO;
    }

    @Override
    public void add(CertificatesAddCmd addCmd) {
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        String groupFullCode=mdmOrganizationUtil.getGroupFullCode(personCode);
        addCmd.setGroupFullCode(groupFullCode);

        CertificatesDo certificatesDo = certificatesAssembler.toDO(addCmd);
        boolean save = certificatesRepository.save(certificatesDo);
        if (save){
            attachmentService.saveBatch(addCmd.getAddCmdList(),certificatesDo.getId().toString(), AttachmentOrderType.CERTIFICATES);
        }
    }

    @Override
    public void update(CertificatesUpdateCmd updateCmd) {
        CertificatesDto certificatesDTO = this.get(updateCmd.getId());
        if (certificatesDTO != null) {
            CertificatesDo certificatesDo = certificatesAssembler.toDO(updateCmd);
            certificatesRepository.updateById(certificatesDo);
            //先根据荣誉证书计划id删除附件
            attachmentService.delBatchByOrderId(certificatesDo.getId().toString());
            //后保存
            attachmentService.saveBatch(updateCmd.getAddCmdList(),certificatesDo.getId().toString(),AttachmentOrderType.CERTIFICATES);
        }
    }

    @Override
    public void delete(Long id) {
        certificatesRepository.remove(new LambdaQueryWrapper<CertificatesDo>()
                .eq(CertificatesDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            certificatesRepository.remove(new LambdaQueryWrapper<CertificatesDo>()
                    .in(CertificatesDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), CertificatesExcelExport.class, "荣誉证书导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<CertificatesExcelExport> searchResult = beanSearcher.searchAll(CertificatesExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, CertificatesExcelExport.class, "荣誉证书数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<CertificatesExcelImport> excelParse(MultipartFile file) {
        CertificatesExcelImportListener listener = new CertificatesExcelImportListener();
        ExcelUtil.readFile(file, CertificatesExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<CertificatesExcelImport> list) {
        List<CertificatesDo> certificatesDoList = certificatesAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(certificatesDoList)) {
            certificatesRepository.saveBatch(certificatesDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<CertificatesExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, CertificatesExcelExport.class, "荣誉证书错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
