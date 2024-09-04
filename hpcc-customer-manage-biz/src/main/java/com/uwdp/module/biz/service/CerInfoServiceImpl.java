package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IAttachmentService;
import com.uwdp.module.api.service.ICerInfoService;
import com.uwdp.module.api.vo.cmd.CerInfoAddCmd;
import com.uwdp.module.api.vo.cmd.CerInfoUpdateCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.dto.CerInfoDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.api.vo.excel.CerInfoExcelExport;
import com.uwdp.module.api.vo.excel.CerInfoExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.CerInfoAssembler;
import com.uwdp.module.biz.infrastructure.entity.CerInfoDo;
import com.uwdp.module.biz.infrastructure.excel.CerInfoExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.CerInfoRepository;
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
 * 证书信息 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CerInfoServiceImpl implements ICerInfoService {

    private final CerInfoRepository cerInfoRepository;

    private final CerInfoAssembler cerInfoAssembler;

    private final BeanSearcher beanSearcher;

    private final IAttachmentService attachmentService;

    @Autowired
    private MdmOrganizationUtil mdmOrganizationUtil;

    @Override
    public SearchResult<CerInfoDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(CerInfoDto.class, paraMap);
    }

    @Override
    public List<CerInfoDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(CerInfoDto.class, paraMap);
    }

    @Override
    public List<CerInfoDto> listByIds(List<Long> idList) {
        List<CerInfoDo> list = cerInfoRepository.list(new LambdaQueryWrapper<CerInfoDo>()
                .in(CerInfoDo::getId, idList));
        return cerInfoAssembler.toValueObjectList(list);
    }

    @Override
    public CerInfoDto get(Long id) {
        CerInfoDo cerInfoDo = cerInfoRepository.getOne(new LambdaQueryWrapper<CerInfoDo>()
                .eq(CerInfoDo::getId, id));
        CerInfoDto cerInfoDTO = cerInfoAssembler.toValueObject(cerInfoDo);
        Map<String,Object> map = new HashMap<>();
        map.put("orderType",AttachmentOrderType.CER_INFO.getId());
        map.put("orderId",id);
        cerInfoDTO.setAttachmentDtos(attachmentService.searchByParam(map));
        return cerInfoDTO;
    }

    @Override
    public void add(CerInfoAddCmd addCmd) {
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        addCmd=(CerInfoAddCmd)mdmOrganizationUtil.getMdmOrganization(addCmd,personCode);
        CerInfoDo cerInfoDo = cerInfoAssembler.toDO(addCmd);
        boolean save = cerInfoRepository.save(cerInfoDo);
        if (save){
            attachmentService.saveBatch(addCmd.getAddCmdList(),cerInfoDo.getId().toString(), AttachmentOrderType.CER_INFO);
        }
    }

    @Override
    public void update(CerInfoUpdateCmd updateCmd) {
        CerInfoDto cerInfoDTO = this.get(updateCmd.getId());
        if (cerInfoDTO != null) {
            CerInfoDo cerInfoDo = cerInfoAssembler.toDO(updateCmd);
            cerInfoRepository.updateById(cerInfoDo);
            //先根据公关实施计划id删除附件
            attachmentService.delBatchByOrderId(cerInfoDo.getId().toString());
            //后保存
            attachmentService.saveBatch(updateCmd.getAddCmdList(),cerInfoDo.getId().toString(),AttachmentOrderType.CER_INFO);
        }
    }

    @Override
    public void delete(Long id) {
        cerInfoRepository.remove(new LambdaQueryWrapper<CerInfoDo>()
                .eq(CerInfoDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            cerInfoRepository.remove(new LambdaQueryWrapper<CerInfoDo>()
                    .in(CerInfoDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), CerInfoExcelExport.class, "证书信息导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<CerInfoExcelExport> searchResult = beanSearcher.searchAll(CerInfoExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, CerInfoExcelExport.class, "证书信息数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<CerInfoExcelImport> excelParse(MultipartFile file) {
        CerInfoExcelImportListener listener = new CerInfoExcelImportListener();
        ExcelUtil.readFile(file, CerInfoExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<CerInfoExcelImport> list) {
        List<CerInfoDo> cerInfoDoList = cerInfoAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(cerInfoDoList)) {
            cerInfoRepository.saveBatch(cerInfoDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<CerInfoExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, CerInfoExcelExport.class, "证书信息错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
