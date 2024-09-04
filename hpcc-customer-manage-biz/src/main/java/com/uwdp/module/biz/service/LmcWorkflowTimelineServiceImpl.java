package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ILmcWorkflowTimelineService;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowTimelineDto;
import com.uwdp.module.biz.infrastructure.assembler.LmcWorkflowTimelineAssembler;
import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowTimelineDo;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowTimelineRepository;
import com.uwdp.module.api.vo.excel.LmcWorkflowTimelineExcelExport;
import com.uwdp.module.api.vo.excel.LmcWorkflowTimelineExcelImport;
import com.uwdp.module.biz.infrastructure.excel.LmcWorkflowTimelineExcelImportListener;
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
 * 流程时间线 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LmcWorkflowTimelineServiceImpl implements ILmcWorkflowTimelineService {

    private final LmcWorkflowTimelineRepository lmcWorkflowTimelineRepository;

    private final LmcWorkflowTimelineAssembler lmcWorkflowTimelineAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<LmcWorkflowTimelineDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(LmcWorkflowTimelineDto.class, paraMap);
    }

    @Override
    public List<LmcWorkflowTimelineDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(LmcWorkflowTimelineDto.class, paraMap);
    }

    @Override
    public List<LmcWorkflowTimelineDto> listByIds(List<Long> idList) {
        List<LmcWorkflowTimelineDo> list = lmcWorkflowTimelineRepository.list(new LambdaQueryWrapper<LmcWorkflowTimelineDo>()
                .in(LmcWorkflowTimelineDo::getId, idList));
        return lmcWorkflowTimelineAssembler.toValueObjectList(list);
    }

    @Override
    public LmcWorkflowTimelineDto get(Long id) {
        LmcWorkflowTimelineDo lmcWorkflowTimelineDo = lmcWorkflowTimelineRepository.getOne(new LambdaQueryWrapper<LmcWorkflowTimelineDo>()
                .eq(LmcWorkflowTimelineDo::getId, id));
        LmcWorkflowTimelineDto lmcWorkflowTimelineDTO = lmcWorkflowTimelineAssembler.toValueObject(lmcWorkflowTimelineDo);
        return lmcWorkflowTimelineDTO;
    }

    @Override
    public void add(LmcWorkflowTimelineAddCmd addCmd) {
        LmcWorkflowTimelineDo lmcWorkflowTimelineDo = lmcWorkflowTimelineAssembler.toDO(addCmd);
        lmcWorkflowTimelineRepository.save(lmcWorkflowTimelineDo);
    }

    @Override
    public void update(LmcWorkflowTimelineUpdateCmd updateCmd) {
        LmcWorkflowTimelineDto lmcWorkflowTimelineDTO = this.get(updateCmd.getId());
        if (lmcWorkflowTimelineDTO != null) {
            LmcWorkflowTimelineDo lmcWorkflowTimelineDo = lmcWorkflowTimelineAssembler.toDO(updateCmd);
            lmcWorkflowTimelineRepository.updateById(lmcWorkflowTimelineDo);
        }
    }

    @Override
    public void delete(Long id) {
        lmcWorkflowTimelineRepository.remove(new LambdaQueryWrapper<LmcWorkflowTimelineDo>()
                .eq(LmcWorkflowTimelineDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            lmcWorkflowTimelineRepository.remove(new LambdaQueryWrapper<LmcWorkflowTimelineDo>()
                    .in(LmcWorkflowTimelineDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), LmcWorkflowTimelineExcelExport.class, "流程时间线导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<LmcWorkflowTimelineExcelExport> searchResult = beanSearcher.searchAll(LmcWorkflowTimelineExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, LmcWorkflowTimelineExcelExport.class, "流程时间线数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<LmcWorkflowTimelineExcelImport> excelParse(MultipartFile file) {
        LmcWorkflowTimelineExcelImportListener listener = new LmcWorkflowTimelineExcelImportListener();
        ExcelUtil.readFile(file, LmcWorkflowTimelineExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<LmcWorkflowTimelineExcelImport> list) {
        List<LmcWorkflowTimelineDo> lmcWorkflowTimelineDoList = lmcWorkflowTimelineAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(lmcWorkflowTimelineDoList)) {
            lmcWorkflowTimelineRepository.saveBatch(lmcWorkflowTimelineDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<LmcWorkflowTimelineExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, LmcWorkflowTimelineExcelExport.class, "流程时间线错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
