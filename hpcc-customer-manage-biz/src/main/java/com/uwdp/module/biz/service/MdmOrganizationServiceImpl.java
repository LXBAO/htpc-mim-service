package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.OrLike;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.vo.cmd.MdmOrganizationAddCmd;
import com.uwdp.module.api.vo.cmd.MdmOrganizationUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.biz.infrastructure.assembler.MdmOrganizationAssembler;
import com.uwdp.module.biz.infrastructure.entity.MdmOrganizationDo;
import com.uwdp.module.biz.infrastructure.repository.MdmOrganizationRepository;
import com.uwdp.module.api.vo.excel.MdmOrganizationExcelExport;
import com.uwdp.module.api.vo.excel.MdmOrganizationExcelImport;
import com.uwdp.module.biz.infrastructure.excel.MdmOrganizationExcelImportListener;
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
 * 主数据-组织 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MdmOrganizationServiceImpl implements IMdmOrganizationService {

    private final MdmOrganizationRepository mdmOrganizationRepository;

    private final MdmOrganizationAssembler mdmOrganizationAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<MdmOrganizationDto> pageByParam(Map<String, Object> paraMap) {

        return beanSearcher.search(MdmOrganizationDto.class, paraMap);
    }

    public List<String> getGroupListByGroupBelongUnitCode(String groupBelongUnitCode){
        List<MdmOrganizationDo> list =  mdmOrganizationRepository.list(new LambdaQueryWrapper<MdmOrganizationDo>().likeRight(MdmOrganizationDo::getGroupCode,groupBelongUnitCode));
        return list.stream().map(obj-> {return obj.getGroupCode();}).collect(Collectors.toList());
    }

    @Override
    public List<MdmOrganizationDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(MdmOrganizationDto.class, paraMap);
    }

    @Override
    public List<MdmOrganizationDto> listByIds(List<Long> idList) {
        List<MdmOrganizationDo> list = mdmOrganizationRepository.list(new LambdaQueryWrapper<MdmOrganizationDo>()
                .in(MdmOrganizationDo::getId, idList));
        return mdmOrganizationAssembler.toValueObjectList(list);
    }

    @Override
    public MdmOrganizationDto get(Long id) {
        MdmOrganizationDo mdmOrganizationDo = mdmOrganizationRepository.getOne(new LambdaQueryWrapper<MdmOrganizationDo>()
                .eq(MdmOrganizationDo::getId, id));
        MdmOrganizationDto mdmOrganizationDTO = mdmOrganizationAssembler.toValueObject(mdmOrganizationDo);
        return mdmOrganizationDTO;
    }

    @Override
    public MdmOrganizationDto getGroupCode(String groupCode) {
        MdmOrganizationDo mdmOrganizationDo = mdmOrganizationRepository.getOne(new LambdaQueryWrapper<MdmOrganizationDo>()
                .eq(MdmOrganizationDo::getGroupCode, groupCode));
        MdmOrganizationDto mdmOrganizationDTO = mdmOrganizationAssembler.toValueObject(mdmOrganizationDo);
        return mdmOrganizationDTO;
    }

    @Override
    public void add(MdmOrganizationAddCmd addCmd) {
        MdmOrganizationDo mdmOrganizationDo = mdmOrganizationAssembler.toDO(addCmd);
        mdmOrganizationRepository.save(mdmOrganizationDo);
    }

    @Override
    public void update(MdmOrganizationUpdateCmd updateCmd) {
        MdmOrganizationDto mdmOrganizationDTO = this.get(updateCmd.getId());
        if (mdmOrganizationDTO != null) {
            MdmOrganizationDo mdmOrganizationDo = mdmOrganizationAssembler.toDO(updateCmd);
            mdmOrganizationRepository.updateById(mdmOrganizationDo);
        }
    }

    @Override
    public void delete(Long id) {
        mdmOrganizationRepository.remove(new LambdaQueryWrapper<MdmOrganizationDo>()
                .eq(MdmOrganizationDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            mdmOrganizationRepository.remove(new LambdaQueryWrapper<MdmOrganizationDo>()
                    .in(MdmOrganizationDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), MdmOrganizationExcelExport.class, "主数据-组织导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<MdmOrganizationExcelExport> searchResult = beanSearcher.searchAll(MdmOrganizationExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, MdmOrganizationExcelExport.class, "主数据-组织数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<MdmOrganizationExcelImport> excelParse(MultipartFile file) {
        MdmOrganizationExcelImportListener listener = new MdmOrganizationExcelImportListener();
        ExcelUtil.readFile(file, MdmOrganizationExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<MdmOrganizationExcelImport> list) {
        List<MdmOrganizationDo> mdmOrganizationDoList = mdmOrganizationAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(mdmOrganizationDoList)) {
            mdmOrganizationRepository.saveBatch(mdmOrganizationDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<MdmOrganizationExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, MdmOrganizationExcelExport.class, "主数据-组织错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
