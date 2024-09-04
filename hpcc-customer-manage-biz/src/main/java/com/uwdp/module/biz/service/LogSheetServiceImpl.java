package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ILogSheetService;
import com.uwdp.module.api.vo.cmd.LogSheetAddCmd;
import com.uwdp.module.api.vo.cmd.LogSheetUpdateCmd;
import com.uwdp.module.api.vo.dto.LogSheetDto;
import com.uwdp.module.api.vo.excel.LogSheetExcelExport;
import com.uwdp.module.api.vo.excel.LogSheetExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.LogSheetAssembler;
import com.uwdp.module.biz.infrastructure.entity.LogSheetDo;
import com.uwdp.module.biz.infrastructure.excel.LogSheetExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.LogSheetRepository;
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
 * 历史记录表 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LogSheetServiceImpl implements ILogSheetService {

    private final LogSheetRepository logSheetRepository;

    private final LogSheetAssembler logSheetAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<LogSheetDto> pageByParam(Map<String, Object> paraMap) {
        paraMap.remove("createdBy");
        return beanSearcher.search(LogSheetDto.class, paraMap);
    }

    @Override
    public List<LogSheetDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(LogSheetDto.class, paraMap);
    }

    @Override
    public List<LogSheetDto> listByIds(List<Long> idList) {
        List<LogSheetDo> list = logSheetRepository.list(new LambdaQueryWrapper<LogSheetDo>()
                .in(LogSheetDo::getId, idList));
        return logSheetAssembler.toValueObjectList(list);
    }

    @Override
    public LogSheetDto get(Long id) {
        LogSheetDo logSheetDo = logSheetRepository.getOne(new LambdaQueryWrapper<LogSheetDo>()
                .eq(LogSheetDo::getId, id));
        LogSheetDto logSheetDTO = logSheetAssembler.toValueObject(logSheetDo);
        return logSheetDTO;
    }

    @Override
    public void add(LogSheetAddCmd addCmd) {
        LogSheetDo logSheetDo = logSheetAssembler.toDO(addCmd);
        logSheetRepository.save(logSheetDo);
    }

    @Override
    public void update(LogSheetUpdateCmd updateCmd) {
        LogSheetDto logSheetDTO = this.get(updateCmd.getId());
        if (logSheetDTO != null) {
            LogSheetDo logSheetDo = logSheetAssembler.toDO(updateCmd);
            logSheetRepository.updateById(logSheetDo);
        }
    }

    @Override
    public void delete(Long id) {
        logSheetRepository.remove(new LambdaQueryWrapper<LogSheetDo>()
                .eq(LogSheetDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            logSheetRepository.remove(new LambdaQueryWrapper<LogSheetDo>()
                    .in(LogSheetDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), LogSheetExcelExport.class, "历史记录表导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<LogSheetExcelExport> searchResult = beanSearcher.searchAll(LogSheetExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, LogSheetExcelExport.class, "历史记录表数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<LogSheetExcelImport> excelParse(MultipartFile file) {
        LogSheetExcelImportListener listener = new LogSheetExcelImportListener();
        ExcelUtil.readFile(file, LogSheetExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<LogSheetExcelImport> list) {
        List<LogSheetDo> logSheetDoList = logSheetAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(logSheetDoList)) {
            logSheetRepository.saveBatch(logSheetDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<LogSheetExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, LogSheetExcelExport.class, "历史记录表错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
