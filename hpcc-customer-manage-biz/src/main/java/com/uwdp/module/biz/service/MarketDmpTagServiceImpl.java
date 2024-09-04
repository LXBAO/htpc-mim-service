package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.google.common.collect.Maps;
import com.uwdp.module.api.service.IMarketDmpTagDetailService;
import com.uwdp.module.api.service.IMarketDmpTagService;
import com.uwdp.module.api.vo.cmd.MarketDmpTagAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDto;
import com.uwdp.module.api.vo.dto.searcher.MarketDmpTagWorkflowDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelExport;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.MarketDmpTagAssembler;
import com.uwdp.module.biz.infrastructure.entity.MarketDmpTagDetailDo;
import com.uwdp.module.biz.infrastructure.entity.MarketDmpTagDo;
import com.uwdp.module.biz.infrastructure.excel.MarketDmpTagExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.MarketDmpTagDetailRepository;
import com.uwdp.module.biz.infrastructure.repository.MarketDmpTagRepository;
import com.uwdp.module.biz.utils.MdmOrganizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
 * 市场部分公司年度指标 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MarketDmpTagServiceImpl implements IMarketDmpTagService {

    private final MarketDmpTagRepository marketDmpTagRepository;

    private final MarketDmpTagAssembler marketDmpTagAssembler;

    private final MarketDmpTagDetailRepository marketDmpTagDetailRepository;

    private final IMarketDmpTagDetailService marketDmpTagDetailService;

    private final BeanSearcher beanSearcher;

    private final MdmOrganizationUtil mdmOrganizationUtil;

    @Override
    public SearchResult<MarketDmpTagDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(MarketDmpTagDto.class, paraMap);
    }

    @Override
    public SearchResult<MarketDmpTagWorkflowDto> pageThatPassed(Map<String, Object> paraMap) {
        return beanSearcher.search(MarketDmpTagWorkflowDto.class, paraMap);
    }

    @Override
    public List<MarketDmpTagDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(MarketDmpTagDto.class, paraMap);
    }

    @Override
    public List<MarketDmpTagDto> listByIds(List<Long> idList) {
        List<MarketDmpTagDo> list = marketDmpTagRepository.list(new LambdaQueryWrapper<MarketDmpTagDo>()
                .in(MarketDmpTagDo::getId, idList));
        return marketDmpTagAssembler.toValueObjectList(list);
    }

    @Override
    public MarketDmpTagDto get(Long id) {
        MarketDmpTagDo marketDmpTagDo = marketDmpTagRepository.getOne(new LambdaQueryWrapper<MarketDmpTagDo>()
                .eq(MarketDmpTagDo::getId, id));
        MarketDmpTagDto marketDmpTagDTO = marketDmpTagAssembler.toValueObject(marketDmpTagDo);
        Map<String,Object> map = Maps.newHashMap();
        map.put("parentId",id);
        marketDmpTagDTO.setMarketDmpTagDetailDto(marketDmpTagDetailService.searchByParam(map));
        return marketDmpTagDTO;
    }

    @Override

    public void add(MarketDmpTagAddCmd cmd) throws Exception {
        if(cmd.getCreatedBy()!=null){
            cmd.setGroupFullCode(mdmOrganizationUtil.getGroupFullCode(cmd.getCreatedBy()));
        }
        MarketDmpTagDo marketDmpTagDo = marketDmpTagAssembler.toDO(cmd);
        boolean isSave  = marketDmpTagRepository.save(marketDmpTagDo);
        if(isSave && !CollectionUtils.isEmpty(cmd.getMarketDmpTagDetailList())){
            List<MarketDmpTagDetailDo> marketDmpTagDetailDos = new ArrayList<>(cmd.getMarketDmpTagDetailList().size());
            cmd.getMarketDmpTagDetailList().stream().forEach
                    (obj->{
                        obj.setParentId(marketDmpTagDo.getId());
                        MarketDmpTagDetailDo tag = new MarketDmpTagDetailDo();
                        BeanUtils.copyProperties(obj,tag);
                        marketDmpTagDetailDos.add(tag);
                    });

          marketDmpTagDetailRepository.saveBatch(marketDmpTagDetailDos);
        }
    }

    @Override
    public Long addThenReturnId(MarketDmpTagAddCmd cmd){
        MarketDmpTagDo marketDmpTagDo = marketDmpTagAssembler.toDO(cmd);
        boolean isSave  = marketDmpTagRepository.save(marketDmpTagDo);
        if(isSave && !CollectionUtils.isEmpty(cmd.getMarketDmpTagDetailList())){
            List<MarketDmpTagDetailDo> marketDmpTagDetailDos = new ArrayList<>(cmd.getMarketDmpTagDetailList().size());
            cmd.getMarketDmpTagDetailList().stream().forEach
                    (obj->{
                        obj.setParentId(marketDmpTagDo.getId());
                        MarketDmpTagDetailDo tag = new MarketDmpTagDetailDo();
                        BeanUtils.copyProperties(obj,tag);
                        marketDmpTagDetailDos.add(tag);
                    });

            marketDmpTagDetailRepository.saveBatch(marketDmpTagDetailDos);
        }
        if (isSave){
            return marketDmpTagDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(MarketDmpTagUpdateCmd updateCmd) {
        MarketDmpTagDto marketDmpTagDTO = this.get(updateCmd.getId());
        if (marketDmpTagDTO != null) {
            MarketDmpTagDo marketDmpTagDo = marketDmpTagAssembler.toDO(updateCmd);
            marketDmpTagRepository.updateById(marketDmpTagDo);

            //根据市场年度指标主表删除明显表
            marketDmpTagDetailRepository.remove(new LambdaQueryWrapper<MarketDmpTagDetailDo>()
                    .eq(MarketDmpTagDetailDo::getParentId, updateCmd.getId()));

            if(!CollectionUtils.isEmpty(updateCmd.getMarketDmpTagDetailList())){
                List<MarketDmpTagDetailDo> marketDmpTagDetailDos = new ArrayList<>(updateCmd.getMarketDmpTagDetailList().size());
                updateCmd.getMarketDmpTagDetailList().stream().forEach
                        (obj->{
                            obj.setParentId(updateCmd.getId());
                            MarketDmpTagDetailDo tag = new MarketDmpTagDetailDo();
                            BeanUtils.copyProperties(obj,tag);
                            marketDmpTagDetailDos.add(tag);
                        });

                marketDmpTagDetailRepository.saveBatch(marketDmpTagDetailDos);
            }
        }
    }

    @Override
    public void delete(Long id) {
        marketDmpTagRepository.remove(new LambdaQueryWrapper<MarketDmpTagDo>()
                .eq(MarketDmpTagDo::getId, id));
        marketDmpTagDetailRepository.remove(new LambdaQueryWrapper<MarketDmpTagDetailDo>()
                .eq(MarketDmpTagDetailDo::getParentId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            marketDmpTagRepository.remove(new LambdaQueryWrapper<MarketDmpTagDo>()
                    .in(MarketDmpTagDo::getId, primaryKeys));
            marketDmpTagDetailRepository.remove(new LambdaQueryWrapper<MarketDmpTagDetailDo>()
                    .in(MarketDmpTagDetailDo::getParentId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), MarketDmpTagExcelExport.class, "市场部分公司年度指标导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<MarketDmpTagExcelExport> searchResult = beanSearcher.searchAll(MarketDmpTagExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, MarketDmpTagExcelExport.class, "市场部分公司年度指标数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<MarketDmpTagExcelImport> excelParse(MultipartFile file) {
        MarketDmpTagExcelImportListener listener = new MarketDmpTagExcelImportListener();
        ExcelUtil.readFile(file, MarketDmpTagExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<MarketDmpTagExcelImport> list) {
        List<MarketDmpTagDo> marketDmpTagDoList = marketDmpTagAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(marketDmpTagDoList)) {
            marketDmpTagRepository.saveBatch(marketDmpTagDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<MarketDmpTagExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, MarketDmpTagExcelExport.class, "市场部分公司年度指标错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
