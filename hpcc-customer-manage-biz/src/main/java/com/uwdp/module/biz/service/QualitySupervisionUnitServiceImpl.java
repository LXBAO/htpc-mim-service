package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IQualitySupervisionUnitService;
import com.uwdp.module.api.vo.cmd.QualitySupervisionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.QualitySupervisionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.QualitySupervisionUnitDto;
import com.uwdp.module.api.vo.excel.QualitySupervisionUnitExcelExport;
import com.uwdp.module.api.vo.excel.QualitySupervisionUnitExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.QualitySupervisionUnitAssembler;
import com.uwdp.module.biz.infrastructure.entity.QualitySupervisionUnitDo;
import com.uwdp.module.biz.infrastructure.excel.QualitySupervisionUnitExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.QualitySupervisionUnitRepository;
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
 * 质量监督单位 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QualitySupervisionUnitServiceImpl implements IQualitySupervisionUnitService {

    private final QualitySupervisionUnitRepository qualitySupervisionUnitRepository;

    private final QualitySupervisionUnitAssembler qualitySupervisionUnitAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<QualitySupervisionUnitDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(QualitySupervisionUnitDto.class, paraMap);
    }

    @Override
    public List<QualitySupervisionUnitDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(QualitySupervisionUnitDto.class, paraMap);
    }

    @Override
    public List<QualitySupervisionUnitDto> listByIds(List<Long> idList) {
        List<QualitySupervisionUnitDo> list = qualitySupervisionUnitRepository.list(new LambdaQueryWrapper<QualitySupervisionUnitDo>()
                .in(QualitySupervisionUnitDo::getId, idList));
        return qualitySupervisionUnitAssembler.toValueObjectList(list);
    }

    @Override
    public QualitySupervisionUnitDto get(Long id) {
        QualitySupervisionUnitDo qualitySupervisionUnitDo = qualitySupervisionUnitRepository.getOne(new LambdaQueryWrapper<QualitySupervisionUnitDo>()
                .eq(QualitySupervisionUnitDo::getId, id));
        QualitySupervisionUnitDto qualitySupervisionUnitDTO = qualitySupervisionUnitAssembler.toValueObject(qualitySupervisionUnitDo);
        return qualitySupervisionUnitDTO;
    }

    @Override
    public void add(QualitySupervisionUnitAddCmd addCmd) {
        QualitySupervisionUnitDo qualitySupervisionUnitDo = qualitySupervisionUnitAssembler.toDO(addCmd);
        qualitySupervisionUnitRepository.save(qualitySupervisionUnitDo);
    }

    @Override
    public void update(QualitySupervisionUnitUpdateCmd updateCmd) {
        QualitySupervisionUnitDto qualitySupervisionUnitDTO = this.get(updateCmd.getId());
        if (qualitySupervisionUnitDTO != null) {
            QualitySupervisionUnitDo qualitySupervisionUnitDo = qualitySupervisionUnitAssembler.toDO(updateCmd);
            qualitySupervisionUnitRepository.updateById(qualitySupervisionUnitDo);
        }
    }

    @Override
    public void delete(Long id) {
        qualitySupervisionUnitRepository.remove(new LambdaQueryWrapper<QualitySupervisionUnitDo>()
                .eq(QualitySupervisionUnitDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            qualitySupervisionUnitRepository.remove(new LambdaQueryWrapper<QualitySupervisionUnitDo>()
                    .in(QualitySupervisionUnitDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), QualitySupervisionUnitExcelExport.class, "质量监督单位导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<QualitySupervisionUnitExcelExport> searchResult = beanSearcher.searchAll(QualitySupervisionUnitExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, QualitySupervisionUnitExcelExport.class, "质量监督单位数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<QualitySupervisionUnitExcelImport> excelParse(MultipartFile file) {
        QualitySupervisionUnitExcelImportListener listener = new QualitySupervisionUnitExcelImportListener();
        ExcelUtil.readFile(file, QualitySupervisionUnitExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<QualitySupervisionUnitExcelImport> list) {
        List<QualitySupervisionUnitDo> qualitySupervisionUnitDoList = qualitySupervisionUnitAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(qualitySupervisionUnitDoList)) {
            qualitySupervisionUnitRepository.saveBatch(qualitySupervisionUnitDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<QualitySupervisionUnitExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, QualitySupervisionUnitExcelExport.class, "质量监督单位错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
