package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IDesignUnitService;
import com.uwdp.module.api.vo.cmd.DesignUnitAddCmd;
import com.uwdp.module.api.vo.cmd.DesignUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.DesignUnitDto;
import com.uwdp.module.api.vo.excel.DesignUnitExcelExport;
import com.uwdp.module.api.vo.excel.DesignUnitExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.DesignUnitAssembler;
import com.uwdp.module.biz.infrastructure.entity.DesignUnitDo;
import com.uwdp.module.biz.infrastructure.excel.DesignUnitExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.DesignUnitRepository;
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
 * 设计单位 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DesignUnitServiceImpl implements IDesignUnitService {

    private final DesignUnitRepository designUnitRepository;

    private final DesignUnitAssembler designUnitAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<DesignUnitDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(DesignUnitDto.class, paraMap);
    }

    @Override
    public List<DesignUnitDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(DesignUnitDto.class, paraMap);
    }

    @Override
    public List<DesignUnitDto> listByIds(List<Long> idList) {
        List<DesignUnitDo> list = designUnitRepository.list(new LambdaQueryWrapper<DesignUnitDo>()
                .in(DesignUnitDo::getId, idList));
        return designUnitAssembler.toValueObjectList(list);
    }

    @Override
    public DesignUnitDto get(Long id) {
        DesignUnitDo designUnitDo = designUnitRepository.getOne(new LambdaQueryWrapper<DesignUnitDo>()
                .eq(DesignUnitDo::getId, id));
        DesignUnitDto designUnitDTO = designUnitAssembler.toValueObject(designUnitDo);
        return designUnitDTO;
    }

    @Override
    public void add(DesignUnitAddCmd addCmd) {
        DesignUnitDo designUnitDo = designUnitAssembler.toDO(addCmd);
        designUnitRepository.save(designUnitDo);
    }

    @Override
    public void update(DesignUnitUpdateCmd updateCmd) {
        DesignUnitDto designUnitDTO = this.get(updateCmd.getId());
        if (designUnitDTO != null) {
            DesignUnitDo designUnitDo = designUnitAssembler.toDO(updateCmd);
            designUnitRepository.updateById(designUnitDo);
        }
    }

    @Override
    public void delete(Long id) {
        designUnitRepository.remove(new LambdaQueryWrapper<DesignUnitDo>()
                .eq(DesignUnitDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            designUnitRepository.remove(new LambdaQueryWrapper<DesignUnitDo>()
                    .in(DesignUnitDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), DesignUnitExcelExport.class, "设计单位导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<DesignUnitExcelExport> searchResult = beanSearcher.searchAll(DesignUnitExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, DesignUnitExcelExport.class, "设计单位数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<DesignUnitExcelImport> excelParse(MultipartFile file) {
        DesignUnitExcelImportListener listener = new DesignUnitExcelImportListener();
        ExcelUtil.readFile(file, DesignUnitExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<DesignUnitExcelImport> list) {
        List<DesignUnitDo> designUnitDoList = designUnitAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(designUnitDoList)) {
            designUnitRepository.saveBatch(designUnitDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<DesignUnitExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, DesignUnitExcelExport.class, "设计单位错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
