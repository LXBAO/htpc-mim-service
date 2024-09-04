package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IUpdateViewDetailService;
import com.uwdp.module.api.vo.cmd.UpdateViewDetailAddCmd;
import com.uwdp.module.api.vo.cmd.UpdateViewDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.UpdateViewDetailDto;
import com.uwdp.module.api.vo.excel.UpdateViewDetailExcelExport;
import com.uwdp.module.api.vo.excel.UpdateViewDetailExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.UpdateViewDetailAssembler;
import com.uwdp.module.biz.infrastructure.entity.UpdateViewDetailDo;
import com.uwdp.module.biz.infrastructure.excel.UpdateViewDetailExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.UpdateViewDetailRepository;
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
 * 更新查看详情 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateViewDetailServiceImpl implements IUpdateViewDetailService {

    private final UpdateViewDetailRepository updateViewDetailRepository;

    private final UpdateViewDetailAssembler updateViewDetailAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<UpdateViewDetailDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(UpdateViewDetailDto.class, paraMap);
    }

    @Override
    public List<UpdateViewDetailDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(UpdateViewDetailDto.class, paraMap);
    }

    @Override
    public List<UpdateViewDetailDto> listByIds(List<Long> idList) {
        List<UpdateViewDetailDo> list = updateViewDetailRepository.list(new LambdaQueryWrapper<UpdateViewDetailDo>()
                .in(UpdateViewDetailDo::getId, idList));
        return updateViewDetailAssembler.toValueObjectList(list);
    }

    @Override
    public UpdateViewDetailDto get(Long id) {
        UpdateViewDetailDo updateViewDetailDo = updateViewDetailRepository.getOne(new LambdaQueryWrapper<UpdateViewDetailDo>()
                .eq(UpdateViewDetailDo::getId, id));
        UpdateViewDetailDto updateViewDetailDTO = updateViewDetailAssembler.toValueObject(updateViewDetailDo);
        return updateViewDetailDTO;
    }

    @Override
    public void add(UpdateViewDetailAddCmd addCmd) {
        UpdateViewDetailDo updateViewDetailDo = updateViewDetailAssembler.toDO(addCmd);
        updateViewDetailRepository.save(updateViewDetailDo);
    }

    @Override
    public void update(UpdateViewDetailUpdateCmd updateCmd) {
        UpdateViewDetailDto updateViewDetailDTO = this.get(updateCmd.getId());
        if (updateViewDetailDTO != null) {
            UpdateViewDetailDo updateViewDetailDo = updateViewDetailAssembler.toDO(updateCmd);
            updateViewDetailRepository.updateById(updateViewDetailDo);
        }
    }

    @Override
    public void delete(Long id) {
        updateViewDetailRepository.remove(new LambdaQueryWrapper<UpdateViewDetailDo>()
                .eq(UpdateViewDetailDo::getId, id));
    }

    @Override
    public void deleteByMdtId(String mdtId) {
        updateViewDetailRepository.remove(new LambdaQueryWrapper<UpdateViewDetailDo>()
                .eq(UpdateViewDetailDo::getMdtId, mdtId));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            updateViewDetailRepository.remove(new LambdaQueryWrapper<UpdateViewDetailDo>()
                    .in(UpdateViewDetailDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), UpdateViewDetailExcelExport.class, "更新查看详情导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<UpdateViewDetailExcelExport> searchResult = beanSearcher.searchAll(UpdateViewDetailExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, UpdateViewDetailExcelExport.class, "更新查看详情数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<UpdateViewDetailExcelImport> excelParse(MultipartFile file) {
        UpdateViewDetailExcelImportListener listener = new UpdateViewDetailExcelImportListener();
        ExcelUtil.readFile(file, UpdateViewDetailExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<UpdateViewDetailExcelImport> list) {
        List<UpdateViewDetailDo> updateViewDetailDoList = updateViewDetailAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(updateViewDetailDoList)) {
            updateViewDetailRepository.saveBatch(updateViewDetailDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<UpdateViewDetailExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, UpdateViewDetailExcelExport.class, "更新查看详情错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
