package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IRelevantContactService;
import com.uwdp.module.api.vo.cmd.RelevantContactAddCmd;
import com.uwdp.module.api.vo.cmd.RelevantContactUpdateCmd;
import com.uwdp.module.api.vo.dto.RelevantContactDto;
import com.uwdp.module.biz.infrastructure.assembler.RelevantContactAssembler;
import com.uwdp.module.biz.infrastructure.entity.RelevantContactDo;
import com.uwdp.module.biz.infrastructure.repository.RelevantContactRepository;
import com.uwdp.module.api.vo.excel.RelevantContactExcelExport;
import com.uwdp.module.api.vo.excel.RelevantContactExcelImport;
import com.uwdp.module.biz.infrastructure.excel.RelevantContactExcelImportListener;
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
 * 客户相关联系人 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RelevantContactServiceImpl implements IRelevantContactService {

    private final RelevantContactRepository relevantContactRepository;

    private final RelevantContactAssembler relevantContactAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<RelevantContactDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(RelevantContactDto.class, paraMap);
    }

    @Override
    public List<RelevantContactDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(RelevantContactDto.class, paraMap);
    }

    @Override
    public List<RelevantContactDto> listByIds(List<Long> idList) {
        List<RelevantContactDo> list = relevantContactRepository.list(new LambdaQueryWrapper<RelevantContactDo>()
                .in(RelevantContactDo::getId, idList));
        return relevantContactAssembler.toValueObjectList(list);
    }

    @Override
    public RelevantContactDto get(Long id) {
        RelevantContactDo relevantContactDo = relevantContactRepository.getOne(new LambdaQueryWrapper<RelevantContactDo>()
                .eq(RelevantContactDo::getId, id));
        RelevantContactDto relevantContactDTO = relevantContactAssembler.toValueObject(relevantContactDo);
        return relevantContactDTO;
    }

    @Override
    public void add(RelevantContactAddCmd addCmd) {
        RelevantContactDo relevantContactDo = relevantContactAssembler.toDO(addCmd);
        relevantContactRepository.save(relevantContactDo);
    }

    @Override
    public void update(RelevantContactUpdateCmd updateCmd) {
        RelevantContactDto relevantContactDTO = this.get(updateCmd.getId());
        if (relevantContactDTO != null) {
            RelevantContactDo relevantContactDo = relevantContactAssembler.toDO(updateCmd);
            relevantContactRepository.updateById(relevantContactDo);
        }
    }

    @Override
    public void delete(Long id) {
        relevantContactRepository.remove(new LambdaQueryWrapper<RelevantContactDo>()
                .eq(RelevantContactDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            relevantContactRepository.remove(new LambdaQueryWrapper<RelevantContactDo>()
                    .in(RelevantContactDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), RelevantContactExcelExport.class, "客户相关联系人导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<RelevantContactExcelExport> searchResult = beanSearcher.searchAll(RelevantContactExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, RelevantContactExcelExport.class, "客户相关联系人数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<RelevantContactExcelImport> excelParse(MultipartFile file) {
        RelevantContactExcelImportListener listener = new RelevantContactExcelImportListener();
        ExcelUtil.readFile(file, RelevantContactExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<RelevantContactExcelImport> list) {
        List<RelevantContactDo> relevantContactDoList = relevantContactAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(relevantContactDoList)) {
            relevantContactRepository.saveBatch(relevantContactDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<RelevantContactExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, RelevantContactExcelExport.class, "客户相关联系人错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
