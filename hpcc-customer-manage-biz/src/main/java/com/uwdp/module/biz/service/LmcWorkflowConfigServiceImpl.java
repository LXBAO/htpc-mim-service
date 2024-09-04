package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ILmcWorkflowConfigService;
import com.uwdp.module.api.vo.cmd.LmcWorkflowConfigAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowConfigUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowConfigDto;
import com.uwdp.module.biz.infrastructure.assembler.LmcWorkflowConfigAssembler;
import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowConfigDo;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowConfigRepository;
import com.uwdp.module.api.vo.excel.LmcWorkflowConfigExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowConfigExcelImport;
import com.uwdp.module.biz.infrastructure.excel.LmcWorkflowConfigExcelImportListener;
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
 * 流程配置表 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LmcWorkflowConfigServiceImpl implements ILmcWorkflowConfigService {

    private final LmcWorkflowConfigRepository lmcWorkflowConfigRepository;

    private final LmcWorkflowConfigAssembler lmcWorkflowConfigAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<LmcWorkflowConfigDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(LmcWorkflowConfigDto.class, paraMap);
    }

    @Override
    public List<LmcWorkflowConfigDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(LmcWorkflowConfigDto.class, paraMap);
    }

    @Override
    public List<LmcWorkflowConfigDto> listByIds(List<Long> idList) {
        List<LmcWorkflowConfigDo> list = lmcWorkflowConfigRepository.list(new LambdaQueryWrapper<LmcWorkflowConfigDo>()
                .in(LmcWorkflowConfigDo::getId, idList));
        return lmcWorkflowConfigAssembler.toValueObjectList(list);
    }

    @Override
    public LmcWorkflowConfigDto get(Long id) {
        LmcWorkflowConfigDo lmcWorkflowConfigDo = lmcWorkflowConfigRepository.getOne(new LambdaQueryWrapper<LmcWorkflowConfigDo>()
                .eq(LmcWorkflowConfigDo::getId, id));
        LmcWorkflowConfigDto lmcWorkflowConfigDTO = lmcWorkflowConfigAssembler.toValueObject(lmcWorkflowConfigDo);
        return lmcWorkflowConfigDTO;
    }

    @Override
    public void add(LmcWorkflowConfigAddCmd addCmd) {
        LmcWorkflowConfigDo lmcWorkflowConfigDo = lmcWorkflowConfigAssembler.toDO(addCmd);
        lmcWorkflowConfigRepository.save(lmcWorkflowConfigDo);
    }

    @Override
    public void update(LmcWorkflowConfigUpdateCmd updateCmd) {
        LmcWorkflowConfigDto lmcWorkflowConfigDTO = this.get(updateCmd.getId());
        if (lmcWorkflowConfigDTO != null) {
            LmcWorkflowConfigDo lmcWorkflowConfigDo = lmcWorkflowConfigAssembler.toDO(updateCmd);
            lmcWorkflowConfigRepository.updateById(lmcWorkflowConfigDo);
        }
    }

    @Override
    public void delete(Long id) {
        lmcWorkflowConfigRepository.remove(new LambdaQueryWrapper<LmcWorkflowConfigDo>()
                .eq(LmcWorkflowConfigDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            lmcWorkflowConfigRepository.remove(new LambdaQueryWrapper<LmcWorkflowConfigDo>()
                    .in(LmcWorkflowConfigDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), LmcWorkflowConfigExcelExport.class, "流程配置表导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<LmcWorkflowConfigExcelExport> searchResult = beanSearcher.searchAll(LmcWorkflowConfigExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, LmcWorkflowConfigExcelExport.class, "流程配置表数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<LmcWorkflowConfigExcelImport> excelParse(MultipartFile file) {
        LmcWorkflowConfigExcelImportListener listener = new LmcWorkflowConfigExcelImportListener();
        ExcelUtil.readFile(file, LmcWorkflowConfigExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<LmcWorkflowConfigExcelImport> list) {
        List<LmcWorkflowConfigDo> lmcWorkflowConfigDoList = lmcWorkflowConfigAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(lmcWorkflowConfigDoList)) {
            lmcWorkflowConfigRepository.saveBatch(lmcWorkflowConfigDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<LmcWorkflowConfigExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, LmcWorkflowConfigExcelExport.class, "流程配置表错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
