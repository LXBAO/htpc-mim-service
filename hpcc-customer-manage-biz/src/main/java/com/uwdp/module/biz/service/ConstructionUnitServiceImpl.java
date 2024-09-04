package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IConstructionUnitService;
import com.uwdp.module.api.vo.cmd.ConstructionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.ConstructionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.ConstructionUnitDto;
import com.uwdp.module.api.vo.excel.ConstructionUnitExcelExport;
import com.uwdp.module.api.vo.excel.ConstructionUnitExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ConstructionUnitAssembler;
import com.uwdp.module.biz.infrastructure.entity.ConstructionUnitDo;
import com.uwdp.module.biz.infrastructure.excel.ConstructionUnitExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ConstructionUnitRepository;
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
 * 建设单位 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConstructionUnitServiceImpl implements IConstructionUnitService {

    private final ConstructionUnitRepository constructionUnitRepository;

    private final ConstructionUnitAssembler constructionUnitAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<ConstructionUnitDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ConstructionUnitDto.class, paraMap);
    }

    @Override
    public List<ConstructionUnitDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ConstructionUnitDto.class, paraMap);
    }

    @Override
    public List<ConstructionUnitDto> listByIds(List<Long> idList) {
        List<ConstructionUnitDo> list = constructionUnitRepository.list(new LambdaQueryWrapper<ConstructionUnitDo>()
                .in(ConstructionUnitDo::getId, idList));
        return constructionUnitAssembler.toValueObjectList(list);
    }

    @Override
    public ConstructionUnitDto get(Long id) {
        ConstructionUnitDo constructionUnitDo = constructionUnitRepository.getOne(new LambdaQueryWrapper<ConstructionUnitDo>()
                .eq(ConstructionUnitDo::getId, id));
        ConstructionUnitDto constructionUnitDTO = constructionUnitAssembler.toValueObject(constructionUnitDo);
        return constructionUnitDTO;
    }

    @Override
    public void add(ConstructionUnitAddCmd addCmd) {
        ConstructionUnitDo constructionUnitDo = constructionUnitAssembler.toDO(addCmd);
        constructionUnitRepository.save(constructionUnitDo);
    }

    @Override
    public void update(ConstructionUnitUpdateCmd updateCmd) {
        ConstructionUnitDto constructionUnitDTO = this.get(updateCmd.getId());
        if (constructionUnitDTO != null) {
            ConstructionUnitDo constructionUnitDo = constructionUnitAssembler.toDO(updateCmd);
            constructionUnitRepository.updateById(constructionUnitDo);
        }
    }

    @Override
    public void delete(Long id) {
        constructionUnitRepository.remove(new LambdaQueryWrapper<ConstructionUnitDo>()
                .eq(ConstructionUnitDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            constructionUnitRepository.remove(new LambdaQueryWrapper<ConstructionUnitDo>()
                    .in(ConstructionUnitDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ConstructionUnitExcelExport.class, "建设单位导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ConstructionUnitExcelExport> searchResult = beanSearcher.searchAll(ConstructionUnitExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ConstructionUnitExcelExport.class, "建设单位数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ConstructionUnitExcelImport> excelParse(MultipartFile file) {
        ConstructionUnitExcelImportListener listener = new ConstructionUnitExcelImportListener();
        ExcelUtil.readFile(file, ConstructionUnitExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ConstructionUnitExcelImport> list) {
        List<ConstructionUnitDo> constructionUnitDoList = constructionUnitAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(constructionUnitDoList)) {
            constructionUnitRepository.saveBatch(constructionUnitDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ConstructionUnitExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ConstructionUnitExcelExport.class, "建设单位错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
