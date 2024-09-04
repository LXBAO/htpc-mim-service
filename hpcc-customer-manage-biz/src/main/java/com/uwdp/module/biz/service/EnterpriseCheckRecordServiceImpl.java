package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IEnterpriseCheckRecordService;
import com.uwdp.module.api.vo.cmd.EnterpriseCheckRecordAddCmd;
import com.uwdp.module.api.vo.cmd.EnterpriseCheckRecordUpdateCmd;
import com.uwdp.module.api.vo.dto.EnterpriseCheckRecordDto;
import com.uwdp.module.biz.infrastructure.assembler.EnterpriseCheckRecordAssembler;
import com.uwdp.module.biz.infrastructure.entity.EnterpriseCheckRecordDo;
import com.uwdp.module.biz.infrastructure.repository.EnterpriseCheckRecordRepository;
import com.uwdp.module.api.vo.excel.EnterpriseCheckRecordExcelExport;
import com.uwdp.module.api.vo.excel.EnterpriseCheckRecordExcelImport;
import com.uwdp.module.biz.infrastructure.excel.EnterpriseCheckRecordExcelImportListener;
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
 * 企查查记录 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EnterpriseCheckRecordServiceImpl implements IEnterpriseCheckRecordService {

    private final EnterpriseCheckRecordRepository enterpriseCheckRecordRepository;

    private final EnterpriseCheckRecordAssembler enterpriseCheckRecordAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<EnterpriseCheckRecordDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(EnterpriseCheckRecordDto.class, paraMap);
    }

    @Override
    public List<EnterpriseCheckRecordDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(EnterpriseCheckRecordDto.class, paraMap);
    }

    @Override
    public List<EnterpriseCheckRecordDto> listByIds(List<Long> idList) {
        List<EnterpriseCheckRecordDo> list = enterpriseCheckRecordRepository.list(new LambdaQueryWrapper<EnterpriseCheckRecordDo>()
                .in(EnterpriseCheckRecordDo::getId, idList));
        return enterpriseCheckRecordAssembler.toValueObjectList(list);
    }

    @Override
    public EnterpriseCheckRecordDto get(Long id) {
        EnterpriseCheckRecordDo enterpriseCheckRecordDo = enterpriseCheckRecordRepository.getOne(new LambdaQueryWrapper<EnterpriseCheckRecordDo>()
                .eq(EnterpriseCheckRecordDo::getId, id));
        EnterpriseCheckRecordDto enterpriseCheckRecordDTO = enterpriseCheckRecordAssembler.toValueObject(enterpriseCheckRecordDo);
        return enterpriseCheckRecordDTO;
    }

    @Override
    public EnterpriseCheckRecordDto getName(String name) {
        EnterpriseCheckRecordDo enterpriseCheckRecordDo = enterpriseCheckRecordRepository.getOne(new LambdaQueryWrapper<EnterpriseCheckRecordDo>()
                .eq(EnterpriseCheckRecordDo::getName, name));
        EnterpriseCheckRecordDto enterpriseCheckRecordDTO = enterpriseCheckRecordAssembler.toValueObject(enterpriseCheckRecordDo);
        return enterpriseCheckRecordDTO;
    }

    @Override
    public void add(EnterpriseCheckRecordAddCmd addCmd) {
        EnterpriseCheckRecordDo enterpriseCheckRecordDo = enterpriseCheckRecordAssembler.toDO(addCmd);
        enterpriseCheckRecordRepository.save(enterpriseCheckRecordDo);
    }

    @Override
    public void update(EnterpriseCheckRecordUpdateCmd updateCmd) {
        EnterpriseCheckRecordDto enterpriseCheckRecordDTO = this.get(updateCmd.getId());
        if (enterpriseCheckRecordDTO != null) {
            EnterpriseCheckRecordDo enterpriseCheckRecordDo = enterpriseCheckRecordAssembler.toDO(updateCmd);
            enterpriseCheckRecordRepository.updateById(enterpriseCheckRecordDo);
        }
    }

    @Override
    public void delete(Long id) {
        enterpriseCheckRecordRepository.remove(new LambdaQueryWrapper<EnterpriseCheckRecordDo>()
                .eq(EnterpriseCheckRecordDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            enterpriseCheckRecordRepository.remove(new LambdaQueryWrapper<EnterpriseCheckRecordDo>()
                    .in(EnterpriseCheckRecordDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), EnterpriseCheckRecordExcelExport.class, "企查查记录导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<EnterpriseCheckRecordExcelExport> searchResult = beanSearcher.searchAll(EnterpriseCheckRecordExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, EnterpriseCheckRecordExcelExport.class, "企查查记录数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<EnterpriseCheckRecordExcelImport> excelParse(MultipartFile file) {
        EnterpriseCheckRecordExcelImportListener listener = new EnterpriseCheckRecordExcelImportListener();
        ExcelUtil.readFile(file, EnterpriseCheckRecordExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<EnterpriseCheckRecordExcelImport> list) {
        List<EnterpriseCheckRecordDo> enterpriseCheckRecordDoList = enterpriseCheckRecordAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(enterpriseCheckRecordDoList)) {
            enterpriseCheckRecordRepository.saveBatch(enterpriseCheckRecordDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<EnterpriseCheckRecordExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, EnterpriseCheckRecordExcelExport.class, "企查查记录错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
