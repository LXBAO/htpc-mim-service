package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IWeeklyReportDetailService;
import com.uwdp.module.api.vo.cmd.WeeklyReportDetailAddCmd;
import com.uwdp.module.api.vo.cmd.WeeklyReportDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.WeeklyReportDetailDto;
import com.uwdp.module.api.vo.excel.WeeklyReportDetailExcelExport;
import com.uwdp.module.api.vo.excel.WeeklyReportDetailExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.WeeklyReportDetailAssembler;
import com.uwdp.module.biz.infrastructure.entity.WeeklyReportDetailDo;
import com.uwdp.module.biz.infrastructure.excel.WeeklyReportDetailExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.WeeklyReportDetailRepository;
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
 * 周报明细表 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WeeklyReportDetailServiceImpl implements IWeeklyReportDetailService {

    private final WeeklyReportDetailRepository weeklyReportDetailRepository;

    private final WeeklyReportDetailAssembler weeklyReportDetailAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<WeeklyReportDetailDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(WeeklyReportDetailDto.class, paraMap);
    }

    @Override
    public List<WeeklyReportDetailDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(WeeklyReportDetailDto.class, paraMap);
    }

    @Override
    public List<WeeklyReportDetailDto> listByIds(List<Long> idList) {
        List<WeeklyReportDetailDo> list = weeklyReportDetailRepository.list(new LambdaQueryWrapper<WeeklyReportDetailDo>()
                .in(WeeklyReportDetailDo::getId, idList));
        return weeklyReportDetailAssembler.toValueObjectList(list);
    }

    @Override
    public WeeklyReportDetailDto get(Long id) {
        WeeklyReportDetailDo weeklyReportDetailDo = weeklyReportDetailRepository.getOne(new LambdaQueryWrapper<WeeklyReportDetailDo>()
                .eq(WeeklyReportDetailDo::getId, id));
        WeeklyReportDetailDto weeklyReportDetailDTO = weeklyReportDetailAssembler.toValueObject(weeklyReportDetailDo);
        return weeklyReportDetailDTO;
    }

    @Override
    public void add(WeeklyReportDetailAddCmd addCmd) {
        WeeklyReportDetailDo weeklyReportDetailDo = weeklyReportDetailAssembler.toDO(addCmd);
        weeklyReportDetailRepository.save(weeklyReportDetailDo);
    }

    @Override
    public void update(WeeklyReportDetailUpdateCmd updateCmd) {
        WeeklyReportDetailDto weeklyReportDetailDTO = this.get(updateCmd.getId());
        if (weeklyReportDetailDTO != null) {
            WeeklyReportDetailDo weeklyReportDetailDo = weeklyReportDetailAssembler.toDO(updateCmd);
            weeklyReportDetailRepository.updateById(weeklyReportDetailDo);
        }
    }

    @Override
    public void delete(Long id) {
        weeklyReportDetailRepository.remove(new LambdaQueryWrapper<WeeklyReportDetailDo>()
                .eq(WeeklyReportDetailDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            weeklyReportDetailRepository.remove(new LambdaQueryWrapper<WeeklyReportDetailDo>()
                    .in(WeeklyReportDetailDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), WeeklyReportDetailExcelExport.class, "周报明细表导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<WeeklyReportDetailExcelExport> searchResult = beanSearcher.searchAll(WeeklyReportDetailExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, WeeklyReportDetailExcelExport.class, "周报明细表数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<WeeklyReportDetailExcelImport> excelParse(MultipartFile file) {
        WeeklyReportDetailExcelImportListener listener = new WeeklyReportDetailExcelImportListener();
        ExcelUtil.readFile(file, WeeklyReportDetailExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<WeeklyReportDetailExcelImport> list) {
        List<WeeklyReportDetailDo> weeklyReportDetailDoList = weeklyReportDetailAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(weeklyReportDetailDoList)) {
            weeklyReportDetailRepository.saveBatch(weeklyReportDetailDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<WeeklyReportDetailExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, WeeklyReportDetailExcelExport.class, "周报明细表错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
