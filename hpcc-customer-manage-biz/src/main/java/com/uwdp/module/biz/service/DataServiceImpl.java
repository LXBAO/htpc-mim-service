package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IDataService;
import com.uwdp.module.api.vo.cmd.DataAddCmd;
import com.uwdp.module.api.vo.cmd.DataUpdateCmd;
import com.uwdp.module.api.vo.dto.DataDto;
import com.uwdp.module.api.vo.excel.DataExcelExport;
import com.uwdp.module.api.vo.excel.DataExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.DataAssembler;
import com.uwdp.module.biz.infrastructure.entity.DataDo;
import com.uwdp.module.biz.infrastructure.excel.DataExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 下拉列表维护 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DataServiceImpl implements IDataService {

    private final DataRepository dataRepository;

    private final DataAssembler dataAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<DataDto> pageByParam(Map<String, Object> paraMap) {
        SearchResult<DataDto> search = beanSearcher.search(DataDto.class, paraMap);
        List<DataDto> dataList = search.getDataList();
        Collections.sort(dataList, new Comparator<DataDto>() {
            @Override
            public int compare(DataDto o1, DataDto o2) {
                return o1.getRemark() - o2.getRemark();
            }
        });
        return search;
    }

    @Override
    public List<DataDto> searchByParam(Map<String, Object> paraMap) {
        List<DataDto> dataDtos = beanSearcher.searchAll(DataDto.class, paraMap);
         Collections.sort(dataDtos, new Comparator<DataDto>() {
             @Override
             public int compare(DataDto o1, DataDto o2) {
                 return o1.getRemark() - o2.getRemark();
             }
         });
        return dataDtos;
    }

    @Override
    public List<DataDto> listByIds(List<Long> idList) {
        List<DataDo> list = dataRepository.list(new LambdaQueryWrapper<DataDo>()
                .in(DataDo::getId, idList));
        return dataAssembler.toValueObjectList(list);
    }

    @Override
    public DataDto get(Long id) {
        DataDo dataDo = dataRepository.getOne(new LambdaQueryWrapper<DataDo>()
                .eq(DataDo::getId, id));
        DataDto dataDTO = dataAssembler.toValueObject(dataDo);
        return dataDTO;
    }

    @Override
    public DataDto getBy(int type) {
        DataDo dataDo = dataRepository.getOne(new LambdaQueryWrapper<DataDo>()
                .eq(DataDo::getType, type));
        DataDto dataDTO = dataAssembler.toValueObject(dataDo);
        return dataDTO;
    }

    @Override
    public void add(DataAddCmd addCmd) {
        Integer maxRemark = dataRepository.getMaxRemark(addCmd.getType(), addCmd.getTypeName());
        if (maxRemark != null){
            addCmd.setRemark(maxRemark+1);
        }else{
            addCmd.setRemark(1);
        }
        //根据addCmd.getType(); addCmd.getTypeName();
        // 如果他们两的值在数据库中有数据 那么意味着addCmd.getRemark()也有数据例如 == 11
        // 那我需要addCmd.setRemark() ==12 也就是说比原来的值+1

        DataDo dataDo = dataAssembler.toDO(addCmd);
        dataRepository.save(dataDo);
    }

    @Override
    public void update(DataUpdateCmd updateCmd) {
        DataDto dataDTO = this.get(updateCmd.getId());
        if (dataDTO != null) {
            DataDo dataDo = dataAssembler.toDO(updateCmd);
            dataRepository.updateById(dataDo);
        }
    }

    @Override
    public void delete(Long id) {
        dataRepository.remove(new LambdaQueryWrapper<DataDo>()
                .eq(DataDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            dataRepository.remove(new LambdaQueryWrapper<DataDo>()
                    .in(DataDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), DataExcelExport.class, "下拉列表维护导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<DataExcelExport> searchResult = beanSearcher.searchAll(DataExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, DataExcelExport.class, "下拉列表维护数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<DataExcelImport> excelParse(MultipartFile file) {
        DataExcelImportListener listener = new DataExcelImportListener();
        ExcelUtil.readFile(file, DataExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<DataExcelImport> list) {
        List<DataDo> dataDoList = dataAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(dataDoList)) {
            dataRepository.saveBatch(dataDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<DataExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, DataExcelExport.class, "下拉列表维护错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
