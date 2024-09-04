package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ISupervisionUnitService;
import com.uwdp.module.api.vo.cmd.SupervisionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.SupervisionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.SupervisionUnitDto;
import com.uwdp.module.api.vo.excel.SupervisionUnitExcelExport;
import com.uwdp.module.api.vo.excel.SupervisionUnitExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.SupervisionUnitAssembler;
import com.uwdp.module.biz.infrastructure.entity.SupervisionUnitDo;
import com.uwdp.module.biz.infrastructure.excel.SupervisionUnitExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.SupervisionUnitRepository;
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
 * 监理单位 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SupervisionUnitServiceImpl implements ISupervisionUnitService {

    private final SupervisionUnitRepository supervisionUnitRepository;

    private final SupervisionUnitAssembler supervisionUnitAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<SupervisionUnitDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(SupervisionUnitDto.class, paraMap);
    }

    @Override
    public List<SupervisionUnitDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(SupervisionUnitDto.class, paraMap);
    }

    @Override
    public List<SupervisionUnitDto> listByIds(List<Long> idList) {
        List<SupervisionUnitDo> list = supervisionUnitRepository.list(new LambdaQueryWrapper<SupervisionUnitDo>()
                .in(SupervisionUnitDo::getId, idList));
        return supervisionUnitAssembler.toValueObjectList(list);
    }

    @Override
    public SupervisionUnitDto get(Long id) {
        SupervisionUnitDo supervisionUnitDo = supervisionUnitRepository.getOne(new LambdaQueryWrapper<SupervisionUnitDo>()
                .eq(SupervisionUnitDo::getId, id));
        SupervisionUnitDto supervisionUnitDTO = supervisionUnitAssembler.toValueObject(supervisionUnitDo);
        return supervisionUnitDTO;
    }

    @Override
    public void add(SupervisionUnitAddCmd addCmd) {
        SupervisionUnitDo supervisionUnitDo = supervisionUnitAssembler.toDO(addCmd);
        supervisionUnitRepository.save(supervisionUnitDo);
    }

    @Override
    public void update(SupervisionUnitUpdateCmd updateCmd) {
        SupervisionUnitDto supervisionUnitDTO = this.get(updateCmd.getId());
        if (supervisionUnitDTO != null) {
            SupervisionUnitDo supervisionUnitDo = supervisionUnitAssembler.toDO(updateCmd);
            supervisionUnitRepository.updateById(supervisionUnitDo);
        }
    }

    @Override
    public void delete(Long id) {
        supervisionUnitRepository.remove(new LambdaQueryWrapper<SupervisionUnitDo>()
                .eq(SupervisionUnitDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            supervisionUnitRepository.remove(new LambdaQueryWrapper<SupervisionUnitDo>()
                    .in(SupervisionUnitDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), SupervisionUnitExcelExport.class, "监理单位导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<SupervisionUnitExcelExport> searchResult = beanSearcher.searchAll(SupervisionUnitExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, SupervisionUnitExcelExport.class, "监理单位数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<SupervisionUnitExcelImport> excelParse(MultipartFile file) {
        SupervisionUnitExcelImportListener listener = new SupervisionUnitExcelImportListener();
        ExcelUtil.readFile(file, SupervisionUnitExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<SupervisionUnitExcelImport> list) {
        List<SupervisionUnitDo> supervisionUnitDoList = supervisionUnitAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(supervisionUnitDoList)) {
            supervisionUnitRepository.saveBatch(supervisionUnitDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<SupervisionUnitExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, SupervisionUnitExcelExport.class, "监理单位错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
