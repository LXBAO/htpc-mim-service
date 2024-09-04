package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ILmcWorkflowVariableService;
import com.uwdp.module.api.vo.cmd.LmcWorkflowVariableAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowVariableUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowVariableDto;
import com.uwdp.module.biz.infrastructure.assembler.LmcWorkflowVariableAssembler;
import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowVariableDo;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowVariableRepository;
import com.uwdp.module.api.vo.excel.LmcWorkflowVariableExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowVariableExcelImport;
import com.uwdp.module.biz.infrastructure.excel.LmcWorkflowVariableExcelImportListener;
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
 * 流程表表单数据 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LmcWorkflowVariableServiceImpl implements ILmcWorkflowVariableService {

    private final LmcWorkflowVariableRepository lmcWorkflowVariableRepository;

    private final LmcWorkflowVariableAssembler lmcWorkflowVariableAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<LmcWorkflowVariableDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(LmcWorkflowVariableDto.class, paraMap);
    }

    @Override
    public List<LmcWorkflowVariableDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(LmcWorkflowVariableDto.class, paraMap);
    }

    @Override
    public List<LmcWorkflowVariableDto> listByIds(List<Long> idList) {
        List<LmcWorkflowVariableDo> list = lmcWorkflowVariableRepository.list(new LambdaQueryWrapper<LmcWorkflowVariableDo>()
                .in(LmcWorkflowVariableDo::getId, idList));
        return lmcWorkflowVariableAssembler.toValueObjectList(list);
    }

    @Override
    public LmcWorkflowVariableDto get(Long id) {
        LmcWorkflowVariableDo lmcWorkflowVariableDo = lmcWorkflowVariableRepository.getOne(new LambdaQueryWrapper<LmcWorkflowVariableDo>()
                .eq(LmcWorkflowVariableDo::getId, id));
        LmcWorkflowVariableDto lmcWorkflowVariableDTO = lmcWorkflowVariableAssembler.toValueObject(lmcWorkflowVariableDo);
        return lmcWorkflowVariableDTO;
    }

    @Override
    public void add(LmcWorkflowVariableAddCmd addCmd) {
        LmcWorkflowVariableDo lmcWorkflowVariableDo = lmcWorkflowVariableAssembler.toDO(addCmd);
        lmcWorkflowVariableRepository.save(lmcWorkflowVariableDo);
    }

    @Override
    public void update(LmcWorkflowVariableUpdateCmd updateCmd) {
        LmcWorkflowVariableDto lmcWorkflowVariableDTO = this.get(updateCmd.getId());
        if (lmcWorkflowVariableDTO != null) {
            LmcWorkflowVariableDo lmcWorkflowVariableDo = lmcWorkflowVariableAssembler.toDO(updateCmd);
            lmcWorkflowVariableRepository.updateById(lmcWorkflowVariableDo);
        }
    }

    @Override
    public void delete(Long id) {
        lmcWorkflowVariableRepository.remove(new LambdaQueryWrapper<LmcWorkflowVariableDo>()
                .eq(LmcWorkflowVariableDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            lmcWorkflowVariableRepository.remove(new LambdaQueryWrapper<LmcWorkflowVariableDo>()
                    .in(LmcWorkflowVariableDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), LmcWorkflowVariableExcelExport.class, "流程表表单数据导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<LmcWorkflowVariableExcelExport> searchResult = beanSearcher.searchAll(LmcWorkflowVariableExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, LmcWorkflowVariableExcelExport.class, "流程表表单数据数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<LmcWorkflowVariableExcelImport> excelParse(MultipartFile file) {
        LmcWorkflowVariableExcelImportListener listener = new LmcWorkflowVariableExcelImportListener();
        ExcelUtil.readFile(file, LmcWorkflowVariableExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<LmcWorkflowVariableExcelImport> list) {
        List<LmcWorkflowVariableDo> lmcWorkflowVariableDoList = lmcWorkflowVariableAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(lmcWorkflowVariableDoList)) {
            lmcWorkflowVariableRepository.saveBatch(lmcWorkflowVariableDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<LmcWorkflowVariableExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, LmcWorkflowVariableExcelExport.class, "流程表表单数据错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
