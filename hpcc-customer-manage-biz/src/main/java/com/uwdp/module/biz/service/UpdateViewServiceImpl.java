package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IUpdateViewService;
import com.uwdp.module.api.vo.cmd.UpdateViewAddCmd;
import com.uwdp.module.api.vo.cmd.UpdateViewUpdateCmd;
import com.uwdp.module.api.vo.dto.UpdateViewDto;
import com.uwdp.module.api.vo.excel.UpdateViewExcelExport;
import com.uwdp.module.api.vo.excel.UpdateViewExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.UpdateViewAssembler;
import com.uwdp.module.biz.infrastructure.entity.UpdateViewDo;
import com.uwdp.module.biz.infrastructure.excel.UpdateViewExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.UpdateViewRepository;
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
 * 更新查看 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateViewServiceImpl implements IUpdateViewService {

    private final UpdateViewRepository updateViewRepository;

    private final UpdateViewAssembler updateViewAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<UpdateViewDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(UpdateViewDto.class, paraMap);
    }

    @Override
    public List<UpdateViewDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(UpdateViewDto.class, paraMap);
    }

    @Override
    public List<UpdateViewDto> listByIds(List<Long> idList) {
        List<UpdateViewDo> list = updateViewRepository.list(new LambdaQueryWrapper<UpdateViewDo>()
                .in(UpdateViewDo::getId, idList));
        return updateViewAssembler.toValueObjectList(list);
    }

    @Override
    public UpdateViewDto get(Long id) {
        UpdateViewDo updateViewDo = updateViewRepository.getOne(new LambdaQueryWrapper<UpdateViewDo>()
                .eq(UpdateViewDo::getId, id));
        UpdateViewDto updateViewDTO = updateViewAssembler.toValueObject(updateViewDo);
        return updateViewDTO;
    }

    @Override
    public void add(UpdateViewAddCmd addCmd) {
        UpdateViewDo updateViewDo = updateViewAssembler.toDO(addCmd);
        updateViewRepository.save(updateViewDo);
    }

    @Override
    public void update(UpdateViewUpdateCmd updateCmd) {
        UpdateViewDto updateViewDTO = this.get(updateCmd.getId());
        if (updateViewDTO != null) {
            UpdateViewDo updateViewDo = updateViewAssembler.toDO(updateCmd);
            updateViewRepository.updateById(updateViewDo);
        }
    }

    @Override
    public void delete(Long id) {
        updateViewRepository.remove(new LambdaQueryWrapper<UpdateViewDo>()
                .eq(UpdateViewDo::getId, id));
    }

    @Override
    public void deleteByRowId(String rowId) {
        updateViewRepository.remove(new LambdaQueryWrapper<UpdateViewDo>()
                .eq(UpdateViewDo::getRowId, rowId));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            updateViewRepository.remove(new LambdaQueryWrapper<UpdateViewDo>()
                    .in(UpdateViewDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), UpdateViewExcelExport.class, "更新查看导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<UpdateViewExcelExport> searchResult = beanSearcher.searchAll(UpdateViewExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, UpdateViewExcelExport.class, "更新查看数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<UpdateViewExcelImport> excelParse(MultipartFile file) {
        UpdateViewExcelImportListener listener = new UpdateViewExcelImportListener();
        ExcelUtil.readFile(file, UpdateViewExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<UpdateViewExcelImport> list) {
        List<UpdateViewDo> updateViewDoList = updateViewAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(updateViewDoList)) {
            updateViewRepository.saveBatch(updateViewDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<UpdateViewExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, UpdateViewExcelExport.class, "更新查看错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
