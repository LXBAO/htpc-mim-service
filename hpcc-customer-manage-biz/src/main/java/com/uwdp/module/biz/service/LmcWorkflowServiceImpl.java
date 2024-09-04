package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ILmcWorkflowService;
import com.uwdp.module.api.vo.cmd.LmcWorkflowAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowDto;
import com.uwdp.module.biz.infrastructure.assembler.LmcWorkflowAssembler;
import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowDo;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowRepository;
import com.uwdp.module.api.vo.excel.LmcWorkflowExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowExcelImport;
import com.uwdp.module.biz.infrastructure.excel.LmcWorkflowExcelImportListener;
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
 * 流程表 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LmcWorkflowServiceImpl implements ILmcWorkflowService {

    private final LmcWorkflowRepository lmcWorkflowRepository;

    private final LmcWorkflowAssembler lmcWorkflowAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<LmcWorkflowDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(LmcWorkflowDto.class, paraMap);
    }

    @Override
    public List<LmcWorkflowDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(LmcWorkflowDto.class, paraMap);
    }

    @Override
    public List<LmcWorkflowDto> listByIds(List<Long> idList) {
        List<LmcWorkflowDo> list = lmcWorkflowRepository.list(new LambdaQueryWrapper<LmcWorkflowDo>()
                .in(LmcWorkflowDo::getId, idList));
        return lmcWorkflowAssembler.toValueObjectList(list);
    }

    @Override
    public LmcWorkflowDto get(Long id) {
        LmcWorkflowDo lmcWorkflowDo = lmcWorkflowRepository.getOne(new LambdaQueryWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getId, id));
        LmcWorkflowDto lmcWorkflowDTO = lmcWorkflowAssembler.toValueObject(lmcWorkflowDo);
        return lmcWorkflowDTO;
    }

    @Override
    public void add(LmcWorkflowAddCmd addCmd) {
        LmcWorkflowDo lmcWorkflowDo = lmcWorkflowAssembler.toDO(addCmd);
        lmcWorkflowRepository.save(lmcWorkflowDo);
    }

    @Override
    public void update(LmcWorkflowUpdateCmd updateCmd) {
        LmcWorkflowDto lmcWorkflowDTO = this.get(updateCmd.getId());
        if (lmcWorkflowDTO != null) {
            LmcWorkflowDo lmcWorkflowDo = lmcWorkflowAssembler.toDO(updateCmd);
            lmcWorkflowRepository.updateById(lmcWorkflowDo);
        }
    }

    @Override
    public void delete(Long id) {
        lmcWorkflowRepository.remove(new LambdaQueryWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getId, id));
    }

    @Override
    public void deleteByBizId(String bizId) {
        lmcWorkflowRepository.remove(new LambdaQueryWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getBizId, bizId));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            lmcWorkflowRepository.remove(new LambdaQueryWrapper<LmcWorkflowDo>()
                    .in(LmcWorkflowDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), LmcWorkflowExcelExport.class, "流程表导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<LmcWorkflowExcelExport> searchResult = beanSearcher.searchAll(LmcWorkflowExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, LmcWorkflowExcelExport.class, "流程表数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<LmcWorkflowExcelImport> excelParse(MultipartFile file) {
        LmcWorkflowExcelImportListener listener = new LmcWorkflowExcelImportListener();
        ExcelUtil.readFile(file, LmcWorkflowExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<LmcWorkflowExcelImport> list) {
        List<LmcWorkflowDo> lmcWorkflowDoList = lmcWorkflowAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(lmcWorkflowDoList)) {
            lmcWorkflowRepository.saveBatch(lmcWorkflowDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<LmcWorkflowExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, LmcWorkflowExcelExport.class, "流程表错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
