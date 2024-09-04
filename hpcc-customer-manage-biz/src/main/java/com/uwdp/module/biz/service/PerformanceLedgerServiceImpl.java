package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IPerformanceLedgerService;
import com.uwdp.module.api.vo.cmd.PerformanceLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.PerformanceLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.PerformanceLedgerDto;
import com.uwdp.module.api.vo.excel.PerformanceLedgerExcelExport;
import com.uwdp.module.api.vo.excel.PerformanceLedgerExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.PerformanceLedgerAssembler;
import com.uwdp.module.biz.infrastructure.entity.PerformanceLedgerDo;
import com.uwdp.module.biz.infrastructure.excel.PerformanceLedgerExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.PerformanceLedgerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 业绩台账 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PerformanceLedgerServiceImpl implements IPerformanceLedgerService {

    private final PerformanceLedgerRepository performanceLedgerRepository;

    private final PerformanceLedgerAssembler performanceLedgerAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<PerformanceLedgerDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(PerformanceLedgerDto.class, paraMap);
    }

    @Override
    public List<PerformanceLedgerDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(PerformanceLedgerDto.class, paraMap);
    }

    @Override
    public List<PerformanceLedgerDto> listByIds(List<Long> fdIdList) {
        List<PerformanceLedgerDo> list = performanceLedgerRepository.list(new LambdaQueryWrapper<PerformanceLedgerDo>()
                .in(PerformanceLedgerDo::getFdId, fdIdList));
        return performanceLedgerAssembler.toValueObjectList(list);
    }

    @Override
    public PerformanceLedgerDto get(Long fdId) {
        PerformanceLedgerDo performanceLedgerDo = performanceLedgerRepository.getOne(new LambdaQueryWrapper<PerformanceLedgerDo>()
                .eq(PerformanceLedgerDo::getFdId, fdId));
        PerformanceLedgerDto performanceLedgerDTO = performanceLedgerAssembler.toValueObject(performanceLedgerDo);
        return performanceLedgerDTO;
    }

    @Override
    public void add(PerformanceLedgerAddCmd addCmd) {
        PerformanceLedgerDo performanceLedgerDo = performanceLedgerAssembler.toDO(addCmd);
        performanceLedgerRepository.save(performanceLedgerDo);
    }

    @Override
    public void update(PerformanceLedgerUpdateCmd updateCmd) {
        PerformanceLedgerDto performanceLedgerDTO = this.get(updateCmd.getFdId());
        if (performanceLedgerDTO != null) {
            PerformanceLedgerDo performanceLedgerDo = performanceLedgerAssembler.toDO(updateCmd);
            performanceLedgerRepository.updateById(performanceLedgerDo);
        }
    }

    @Override
    public void delete(Long fdId) {
        performanceLedgerRepository.remove(new LambdaQueryWrapper<PerformanceLedgerDo>()
                .eq(PerformanceLedgerDo::getFdId, fdId));
    }

    @Override
    public void batchDelete(String fdIds) {
        if (StringUtils.hasText(fdIds)) {
            List<Long> primaryKeys = StrUtil.split(fdIds, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            performanceLedgerRepository.remove(new LambdaQueryWrapper<PerformanceLedgerDo>()
                    .in(PerformanceLedgerDo::getFdId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), PerformanceLedgerExcelExport.class, "业绩台账导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<PerformanceLedgerExcelExport> searchResult = beanSearcher.searchAll(PerformanceLedgerExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, PerformanceLedgerExcelExport.class, "业绩台账数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<PerformanceLedgerExcelImport> excelParse(MultipartFile file) {
        PerformanceLedgerExcelImportListener listener = new PerformanceLedgerExcelImportListener();
        ExcelUtil.readFile(file, PerformanceLedgerExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<PerformanceLedgerExcelImport> list) {
        List<PerformanceLedgerDo> performanceLedgerDoList = performanceLedgerAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(performanceLedgerDoList)) {
            performanceLedgerRepository.saveBatch(performanceLedgerDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<PerformanceLedgerExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, PerformanceLedgerExcelExport.class, "业绩台账错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
