package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IMarketDmpTagDetailService;
import com.uwdp.module.api.vo.cmd.MarketDmpTagDetailAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDetailDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagDetailExcelExport;
import com.uwdp.module.api.vo.excel.MarketDmpTagDetailExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.MarketDmpTagDetailAssembler;
import com.uwdp.module.biz.infrastructure.entity.MarketDmpTagDetailDo;
import com.uwdp.module.biz.infrastructure.excel.MarketDmpTagDetailExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.MarketDmpTagDetailRepository;
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
 * 指标明细 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MarketDmpTagDetailServiceImpl implements IMarketDmpTagDetailService {

    private final MarketDmpTagDetailRepository marketDmpTagDetailRepository;

    private final MarketDmpTagDetailAssembler marketDmpTagDetailAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<MarketDmpTagDetailDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(MarketDmpTagDetailDto.class, paraMap);
    }

    @Override
    public List<MarketDmpTagDetailDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(MarketDmpTagDetailDto.class, paraMap);
    }

    @Override
    public List<MarketDmpTagDetailDto> listByIds(List<Long> idList) {
        List<MarketDmpTagDetailDo> list = marketDmpTagDetailRepository.list(new LambdaQueryWrapper<MarketDmpTagDetailDo>()
                .in(MarketDmpTagDetailDo::getId, idList));
        return marketDmpTagDetailAssembler.toValueObjectList(list);
    }

    @Override
    public MarketDmpTagDetailDto get(Long id) {
        MarketDmpTagDetailDo marketDmpTagDetailDo = marketDmpTagDetailRepository.getOne(new LambdaQueryWrapper<MarketDmpTagDetailDo>()
                .eq(MarketDmpTagDetailDo::getId, id));
        MarketDmpTagDetailDto marketDmpTagDetailDTO = marketDmpTagDetailAssembler.toValueObject(marketDmpTagDetailDo);
        return marketDmpTagDetailDTO;
    }

    @Override
    public void add(MarketDmpTagDetailAddCmd addCmd) {
        MarketDmpTagDetailDo marketDmpTagDetailDo = marketDmpTagDetailAssembler.toDO(addCmd);
        marketDmpTagDetailRepository.save(marketDmpTagDetailDo);
    }

    @Override
    public void update(MarketDmpTagDetailUpdateCmd updateCmd) {
        MarketDmpTagDetailDto marketDmpTagDetailDTO = this.get(updateCmd.getId());
        if (marketDmpTagDetailDTO != null) {
            MarketDmpTagDetailDo marketDmpTagDetailDo = marketDmpTagDetailAssembler.toDO(updateCmd);
            marketDmpTagDetailRepository.updateById(marketDmpTagDetailDo);
        }
    }

    @Override
    public void delete(Long id) {
        marketDmpTagDetailRepository.remove(new LambdaQueryWrapper<MarketDmpTagDetailDo>()
                .eq(MarketDmpTagDetailDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            marketDmpTagDetailRepository.remove(new LambdaQueryWrapper<MarketDmpTagDetailDo>()
                    .in(MarketDmpTagDetailDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), MarketDmpTagDetailExcelExport.class, "指标明细导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<MarketDmpTagDetailExcelExport> searchResult = beanSearcher.searchAll(MarketDmpTagDetailExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, MarketDmpTagDetailExcelExport.class, "指标明细数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<MarketDmpTagDetailExcelImport> excelParse(MultipartFile file) {
        MarketDmpTagDetailExcelImportListener listener = new MarketDmpTagDetailExcelImportListener();
        ExcelUtil.readFile(file, MarketDmpTagDetailExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<MarketDmpTagDetailExcelImport> list) {
        List<MarketDmpTagDetailDo> marketDmpTagDetailDoList = marketDmpTagDetailAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(marketDmpTagDetailDoList)) {
            marketDmpTagDetailRepository.saveBatch(marketDmpTagDetailDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<MarketDmpTagDetailExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, MarketDmpTagDetailExcelExport.class, "指标明细错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
