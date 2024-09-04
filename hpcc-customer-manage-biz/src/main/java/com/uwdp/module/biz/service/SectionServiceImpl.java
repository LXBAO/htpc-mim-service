package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ISectionService;
import com.uwdp.module.api.vo.cmd.SectionAddCmd;
import com.uwdp.module.api.vo.cmd.SectionUpdateCmd;
import com.uwdp.module.api.vo.dto.SectionDto;
import com.uwdp.module.api.vo.excel.SectionExcelExport;
import com.uwdp.module.api.vo.excel.SectionExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.SectionAssembler;
import com.uwdp.module.biz.infrastructure.entity.SectionDo;
import com.uwdp.module.biz.infrastructure.excel.SectionExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.SectionRepository;
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
 * 标段 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements ISectionService {

    private final SectionRepository sectionRepository;

    private final SectionAssembler sectionAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<SectionDto> pageByParam(Map<String, Object> paraMap) {
        paraMap.remove("createdBy");
        return beanSearcher.search(SectionDto.class, paraMap);
    }

    @Override
    public List<SectionDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(SectionDto.class, paraMap);
    }

    @Override
    public List<SectionDto> listByIds(List<Long> idList) {
        List<SectionDo> list = sectionRepository.list(new LambdaQueryWrapper<SectionDo>()
                .in(SectionDo::getId, idList));
        return sectionAssembler.toValueObjectList(list);
    }

    @Override
    public SectionDto get(Long id) {
        SectionDo sectionDo = sectionRepository.getOne(new LambdaQueryWrapper<SectionDo>()
                .eq(SectionDo::getId, id));
        SectionDto sectionDTO = sectionAssembler.toValueObject(sectionDo);
        return sectionDTO;
    }

    @Override
    public void add(SectionAddCmd addCmd) {
        SectionDo sectionDo = sectionAssembler.toDO(addCmd);
        sectionRepository.save(sectionDo);
    }

    @Override
    public void update(SectionUpdateCmd updateCmd) {
        SectionDto sectionDTO = this.get(updateCmd.getId());
        if (sectionDTO != null) {
            SectionDo sectionDo = sectionAssembler.toDO(updateCmd);
            sectionRepository.updateById(sectionDo);
        }
    }

    @Override
    public void delete(Long id) {
        sectionRepository.remove(new LambdaQueryWrapper<SectionDo>()
                .eq(SectionDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            sectionRepository.remove(new LambdaQueryWrapper<SectionDo>()
                    .in(SectionDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), SectionExcelExport.class, "标段导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<SectionExcelExport> searchResult = beanSearcher.searchAll(SectionExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, SectionExcelExport.class, "标段数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<SectionExcelImport> excelParse(MultipartFile file) {
        SectionExcelImportListener listener = new SectionExcelImportListener();
        ExcelUtil.readFile(file, SectionExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<SectionExcelImport> list) {
        List<SectionDo> sectionDoList = sectionAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(sectionDoList)) {
            sectionRepository.saveBatch(sectionDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<SectionExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, SectionExcelExport.class, "标段错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
