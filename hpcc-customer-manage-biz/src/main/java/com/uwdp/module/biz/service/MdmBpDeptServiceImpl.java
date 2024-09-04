package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IMdmBpDeptService;
import com.uwdp.module.api.vo.cmd.MdmBpDeptAddCmd;
import com.uwdp.module.api.vo.cmd.MdmBpDeptUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmBpDeptDto;
import com.uwdp.module.biz.infrastructure.assembler.MdmBpDeptAssembler;
import com.uwdp.module.biz.infrastructure.entity.MdmBpDeptDo;
import com.uwdp.module.biz.infrastructure.repository.MdmBpDeptRepository;
import com.uwdp.module.api.vo.excel.MdmBpDeptExcelExport;
import com.uwdp.module.api.vo.excel.MdmBpDeptExcelImport;
import com.uwdp.module.biz.infrastructure.excel.MdmBpDeptExcelImportListener;
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
 * 主数据-业务编码所对应部门 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MdmBpDeptServiceImpl implements IMdmBpDeptService {

    private final MdmBpDeptRepository mdmBpDeptRepository;

    private final MdmBpDeptAssembler mdmBpDeptAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<MdmBpDeptDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(MdmBpDeptDto.class, paraMap);
    }

    @Override
    public List<MdmBpDeptDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(MdmBpDeptDto.class, paraMap);
    }

    @Override
    public List<MdmBpDeptDto> listByIds(List<Long> idList) {
        List<MdmBpDeptDo> list = mdmBpDeptRepository.list(new LambdaQueryWrapper<MdmBpDeptDo>()
                .in(MdmBpDeptDo::getId, idList));
        return mdmBpDeptAssembler.toValueObjectList(list);
    }

    @Override
    public MdmBpDeptDto get(Long id) {
        MdmBpDeptDo mdmBpDeptDo = mdmBpDeptRepository.getOne(new LambdaQueryWrapper<MdmBpDeptDo>()
                .eq(MdmBpDeptDo::getId, id));
        MdmBpDeptDto mdmBpDeptDTO = mdmBpDeptAssembler.toValueObject(mdmBpDeptDo);
        return mdmBpDeptDTO;
    }

    @Override
    public void add(MdmBpDeptAddCmd addCmd) {
        MdmBpDeptDo mdmBpDeptDo = mdmBpDeptAssembler.toDO(addCmd);
        mdmBpDeptRepository.save(mdmBpDeptDo);
    }

    @Override
    public void update(MdmBpDeptUpdateCmd updateCmd) {
        System.out.println(updateCmd.getArea()+" dddddd");
        MdmBpDeptDto mdmBpDeptDTO = this.get(updateCmd.getId());
        if (mdmBpDeptDTO != null) {
            MdmBpDeptDo mdmBpDeptDo = mdmBpDeptAssembler.toDO(updateCmd);
            mdmBpDeptRepository.updateById(mdmBpDeptDo);
        }
    }

    @Override
    public void delete(Long id) {
        mdmBpDeptRepository.remove(new LambdaQueryWrapper<MdmBpDeptDo>()
                .eq(MdmBpDeptDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            mdmBpDeptRepository.remove(new LambdaQueryWrapper<MdmBpDeptDo>()
                    .in(MdmBpDeptDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), MdmBpDeptExcelExport.class, "主数据-业务编码所对应部门导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<MdmBpDeptExcelExport> searchResult = beanSearcher.searchAll(MdmBpDeptExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, MdmBpDeptExcelExport.class, "主数据-业务编码所对应部门数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<MdmBpDeptExcelImport> excelParse(MultipartFile file) {
        MdmBpDeptExcelImportListener listener = new MdmBpDeptExcelImportListener();
        ExcelUtil.readFile(file, MdmBpDeptExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<MdmBpDeptExcelImport> list) {
        List<MdmBpDeptDo> mdmBpDeptDoList = mdmBpDeptAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(mdmBpDeptDoList)) {
            mdmBpDeptRepository.saveBatch(mdmBpDeptDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<MdmBpDeptExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, MdmBpDeptExcelExport.class, "主数据-业务编码所对应部门错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
