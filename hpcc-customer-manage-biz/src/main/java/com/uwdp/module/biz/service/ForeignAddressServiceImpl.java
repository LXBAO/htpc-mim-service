package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IForeignAddressService;
import com.uwdp.module.api.vo.cmd.ForeignAddressAddCmd;
import com.uwdp.module.api.vo.cmd.ForeignAddressUpdateCmd;
import com.uwdp.module.api.vo.dto.ForeignAddressDto;
import com.uwdp.module.api.vo.excel.ForeignAddressExcelExport;
import com.uwdp.module.api.vo.excel.ForeignAddressExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ForeignAddressAssembler;
import com.uwdp.module.biz.infrastructure.entity.ForeignAddressDo;
import com.uwdp.module.biz.infrastructure.excel.ForeignAddressExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ForeignAddressRepository;
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
 * 国外地址 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ForeignAddressServiceImpl implements IForeignAddressService {

    private final ForeignAddressRepository foreignAddressRepository;

    private final ForeignAddressAssembler foreignAddressAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<ForeignAddressDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ForeignAddressDto.class, paraMap);
    }

    @Override
    public List<ForeignAddressDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ForeignAddressDto.class, paraMap);
    }

    @Override
    public List<ForeignAddressDto> listByIds(List<Long> idList) {
        List<ForeignAddressDo> list = foreignAddressRepository.list(new LambdaQueryWrapper<ForeignAddressDo>()
                .in(ForeignAddressDo::getId, idList));
        return foreignAddressAssembler.toValueObjectList(list);
    }

    @Override
    public ForeignAddressDto get(Long id) {
        ForeignAddressDo foreignAddressDo = foreignAddressRepository.getOne(new LambdaQueryWrapper<ForeignAddressDo>()
                .eq(ForeignAddressDo::getId, id));
        ForeignAddressDto foreignAddressDTO = foreignAddressAssembler.toValueObject(foreignAddressDo);
        return foreignAddressDTO;
    }

    @Override
    public void add(ForeignAddressAddCmd addCmd) {
        ForeignAddressDo foreignAddressDo = foreignAddressAssembler.toDO(addCmd);
        foreignAddressRepository.save(foreignAddressDo);
    }

    @Override
    public void update(ForeignAddressUpdateCmd updateCmd) {
        ForeignAddressDto foreignAddressDTO = this.get(updateCmd.getId());
        if (foreignAddressDTO != null) {
            ForeignAddressDo foreignAddressDo = foreignAddressAssembler.toDO(updateCmd);
            foreignAddressRepository.updateById(foreignAddressDo);
        }
    }

    @Override
    public void delete(Long id) {
        foreignAddressRepository.remove(new LambdaQueryWrapper<ForeignAddressDo>()
                .eq(ForeignAddressDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            foreignAddressRepository.remove(new LambdaQueryWrapper<ForeignAddressDo>()
                    .in(ForeignAddressDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ForeignAddressExcelExport.class, "国外地址导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ForeignAddressExcelExport> searchResult = beanSearcher.searchAll(ForeignAddressExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ForeignAddressExcelExport.class, "国外地址数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ForeignAddressExcelImport> excelParse(MultipartFile file) {
        ForeignAddressExcelImportListener listener = new ForeignAddressExcelImportListener();
        ExcelUtil.readFile(file, ForeignAddressExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ForeignAddressExcelImport> list) {
        List<ForeignAddressDo> foreignAddressDoList = foreignAddressAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(foreignAddressDoList)) {
            foreignAddressRepository.saveBatch(foreignAddressDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ForeignAddressExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ForeignAddressExcelExport.class, "国外地址错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
