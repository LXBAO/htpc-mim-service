package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IContractPartyService;
import com.uwdp.module.api.vo.cmd.ContractPartyAddCmd;
import com.uwdp.module.api.vo.cmd.ContractPartyUpdateCmd;
import com.uwdp.module.api.vo.dto.ContractPartyDto;
import com.uwdp.module.api.vo.excel.ContractPartyExcelExport;
import com.uwdp.module.api.vo.excel.ContractPartyExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ContractPartyAssembler;
import com.uwdp.module.biz.infrastructure.entity.ContractPartyDo;
import com.uwdp.module.biz.infrastructure.excel.ContractPartyExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ContractPartyRepository;
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
 * 合同甲方 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContractPartyServiceImpl implements IContractPartyService {

    private final ContractPartyRepository contractPartyRepository;

    private final ContractPartyAssembler contractPartyAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<ContractPartyDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ContractPartyDto.class, paraMap);
    }

    @Override
    public List<ContractPartyDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ContractPartyDto.class, paraMap);
    }

    @Override
    public List<ContractPartyDto> listByIds(List<Long> idList) {
        List<ContractPartyDo> list = contractPartyRepository.list(new LambdaQueryWrapper<ContractPartyDo>()
                .in(ContractPartyDo::getId, idList));
        return contractPartyAssembler.toValueObjectList(list);
    }

    @Override
    public ContractPartyDto get(Long id) {
        ContractPartyDo contractPartyDo = contractPartyRepository.getOne(new LambdaQueryWrapper<ContractPartyDo>()
                .eq(ContractPartyDo::getId, id));
        ContractPartyDto contractPartyDTO = contractPartyAssembler.toValueObject(contractPartyDo);
        return contractPartyDTO;
    }

    @Override
    public void add(ContractPartyAddCmd addCmd) {
        ContractPartyDo contractPartyDo = contractPartyAssembler.toDO(addCmd);
        contractPartyRepository.save(contractPartyDo);
    }

    @Override
    public void update(ContractPartyUpdateCmd updateCmd) {
        ContractPartyDto contractPartyDTO = this.get(updateCmd.getId());
        if (contractPartyDTO != null) {
            ContractPartyDo contractPartyDo = contractPartyAssembler.toDO(updateCmd);
            contractPartyRepository.updateById(contractPartyDo);
        }
    }

    @Override
    public void delete(Long id) {
        contractPartyRepository.remove(new LambdaQueryWrapper<ContractPartyDo>()
                .eq(ContractPartyDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            contractPartyRepository.remove(new LambdaQueryWrapper<ContractPartyDo>()
                    .in(ContractPartyDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ContractPartyExcelExport.class, "合同甲方导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ContractPartyExcelExport> searchResult = beanSearcher.searchAll(ContractPartyExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ContractPartyExcelExport.class, "合同甲方数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ContractPartyExcelImport> excelParse(MultipartFile file) {
        ContractPartyExcelImportListener listener = new ContractPartyExcelImportListener();
        ExcelUtil.readFile(file, ContractPartyExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ContractPartyExcelImport> list) {
        List<ContractPartyDo> contractPartyDoList = contractPartyAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(contractPartyDoList)) {
            contractPartyRepository.saveBatch(contractPartyDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ContractPartyExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ContractPartyExcelExport.class, "合同甲方错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
