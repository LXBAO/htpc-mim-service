package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IMdmPostService;
import com.uwdp.module.api.vo.cmd.MdmPostAddCmd;
import com.uwdp.module.api.vo.cmd.MdmPostUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmPostDto;
import com.uwdp.module.biz.infrastructure.assembler.MdmPostAssembler;
import com.uwdp.module.biz.infrastructure.entity.MdmPostDo;
import com.uwdp.module.biz.infrastructure.repository.MdmPostRepository;
import com.uwdp.module.api.vo.excel.MdmPostExcelExport;
import com.uwdp.module.api.vo.excel.MdmPostExcelImport;
import com.uwdp.module.biz.infrastructure.excel.MdmPostExcelImportListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 主数据-岗位 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MdmPostServiceImpl implements IMdmPostService {

    private final MdmPostRepository mdmPostRepository;

    private final MdmPostAssembler mdmPostAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<MdmPostDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(MdmPostDto.class, paraMap);
    }

    @Override
    public List<MdmPostDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(MdmPostDto.class, paraMap);
    }

    @Override
    public List<MdmPostDto> listByIds(List<Long> idList) {
        List<MdmPostDo> list = mdmPostRepository.list(new LambdaQueryWrapper<MdmPostDo>()
                .in(MdmPostDo::getId, idList));
        return mdmPostAssembler.toValueObjectList(list);
    }

    @Override
    public MdmPostDto get(Long id) {
        MdmPostDo mdmPostDo = mdmPostRepository.getOne(new LambdaQueryWrapper<MdmPostDo>()
                .eq(MdmPostDo::getId, id));
        MdmPostDto mdmPostDTO = mdmPostAssembler.toValueObject(mdmPostDo);
        return mdmPostDTO;
    }

    @Override
    public void add(MdmPostAddCmd addCmd) {
        MdmPostDo mdmPostDo = mdmPostAssembler.toDO(addCmd);
        mdmPostRepository.save(mdmPostDo);
    }

    @Override
    public void update(MdmPostUpdateCmd updateCmd) {
        MdmPostDto mdmPostDTO = this.get(updateCmd.getId());
        if (mdmPostDTO != null) {
            MdmPostDo mdmPostDo = mdmPostAssembler.toDO(updateCmd);
            mdmPostRepository.updateById(mdmPostDo);
        }
    }

    @Override
    public void delete(Long id) {
        mdmPostRepository.remove(new LambdaQueryWrapper<MdmPostDo>()
                .eq(MdmPostDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            mdmPostRepository.remove(new LambdaQueryWrapper<MdmPostDo>()
                    .in(MdmPostDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), MdmPostExcelExport.class, "主数据-岗位导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<MdmPostExcelExport> searchResult = beanSearcher.searchAll(MdmPostExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, MdmPostExcelExport.class, "主数据-岗位数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<MdmPostExcelImport> excelParse(MultipartFile file) {
        MdmPostExcelImportListener listener = new MdmPostExcelImportListener();
        ExcelUtil.readFile(file, MdmPostExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<MdmPostExcelImport> list) {
        List<MdmPostDo> mdmPostDoList = mdmPostAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(mdmPostDoList)) {
            mdmPostRepository.saveBatch(mdmPostDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<MdmPostExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, MdmPostExcelExport.class, "主数据-岗位错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

}
