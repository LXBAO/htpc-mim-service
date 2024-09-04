package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IAttachmentService;
import com.uwdp.module.api.vo.cmd.AttachmentAddCmd;
import com.uwdp.module.api.vo.cmd.AttachmentUpdateCmd;
import com.uwdp.module.api.vo.dto.AttachmentDto;
import com.uwdp.module.api.vo.enums.AttachmentOrderType;
import com.uwdp.module.biz.infrastructure.assembler.AttachmentAssembler;
import com.uwdp.module.biz.infrastructure.entity.AttachmentDo;
import com.uwdp.module.biz.infrastructure.repository.AttachmentRepository;
import com.uwdp.module.api.vo.excel.AttachmentExcelExport;
import com.uwdp.module.api.vo.excel.AttachmentExcelImport;
import com.uwdp.module.biz.infrastructure.excel.AttachmentExcelImportListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements IAttachmentService {

    private final AttachmentRepository attachmentRepository;

    private final AttachmentAssembler attachmentAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<AttachmentDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(AttachmentDto.class, paraMap);
    }

    @Override
    public List<AttachmentDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(AttachmentDto.class, paraMap);
    }

    @Override
    public List<AttachmentDto> listByIds(List<Long> idList) {
        List<AttachmentDo> list = attachmentRepository.list(new LambdaQueryWrapper<AttachmentDo>()
                .in(AttachmentDo::getId, idList));
        return attachmentAssembler.toValueObjectList(list);
    }

    @Override
    public AttachmentDto get(Long id) {
        AttachmentDo attachmentDo = attachmentRepository.getOne(new LambdaQueryWrapper<AttachmentDo>()
                .eq(AttachmentDo::getId, id));
        AttachmentDto attachmentDTO = attachmentAssembler.toValueObject(attachmentDo);
        return attachmentDTO;
    }

    /**
     * 根据订单id查询附件
     * @param orderId
     * @return
     */
    @Override
    public List<AttachmentDto> getAttachmentListByOrderId(String orderId) {
        List<AttachmentDo> attachmentDoList = attachmentRepository.list(new LambdaQueryWrapper<AttachmentDo>()
                .eq(AttachmentDo::getOrderId, orderId));
        if(CollectionUtils.isEmpty(attachmentDoList)){
            return null;
        }
        List<AttachmentDto> list = attachmentAssembler.toValueObjectList(attachmentDoList);
        return list;
    }

    @Override
    public void add(AttachmentAddCmd addCmd) {
        AttachmentDo attachmentDo = attachmentAssembler.toDO(addCmd);
        attachmentRepository.save(attachmentDo);
    }

    /**
     * 附件保存
     * @param addCmdList
     * @param orderId
     */

    public void saveBatch(List<AttachmentAddCmd> addCmdList, String orderId, AttachmentOrderType type) {
        if(CollectionUtils.isEmpty(addCmdList)){
            return;
        }
        for(AttachmentAddCmd cmd: addCmdList){
            AttachmentDo attachmentDo = attachmentAssembler.toDO(cmd);
            attachmentDo.setOrderId(orderId);
            attachmentDo.setOrderType(type.getId());
            attachmentRepository.save(attachmentDo);
        }

    }

    /**
     * 根据orderId 删除文件
     * @param orderId
     */
    public void delBatchByOrderId(String orderId) {
        attachmentRepository.remove(new LambdaQueryWrapper<AttachmentDo>()
                .eq(AttachmentDo::getOrderId, orderId));

    }

    @Override
    public void update(AttachmentUpdateCmd updateCmd) {
        AttachmentDto attachmentDTO = this.get(updateCmd.getId());
        if (attachmentDTO != null) {
            AttachmentDo attachmentDo = attachmentAssembler.toDO(updateCmd);
            attachmentRepository.updateById(attachmentDo);
        }
    }

    @Override
    public void delete(Long id) {
        attachmentRepository.remove(new LambdaQueryWrapper<AttachmentDo>()
                .eq(AttachmentDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            attachmentRepository.remove(new LambdaQueryWrapper<AttachmentDo>()
                    .in(AttachmentDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), AttachmentExcelExport.class, "附件表导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<AttachmentExcelExport> searchResult = beanSearcher.searchAll(AttachmentExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, AttachmentExcelExport.class, "附件表数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<AttachmentExcelImport> excelParse(MultipartFile file) {
        AttachmentExcelImportListener listener = new AttachmentExcelImportListener();
        ExcelUtil.readFile(file, AttachmentExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<AttachmentExcelImport> list) {
        List<AttachmentDo> attachmentDoList = attachmentAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(attachmentDoList)) {
            attachmentRepository.saveBatch(attachmentDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<AttachmentExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, AttachmentExcelExport.class, "附件表错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
