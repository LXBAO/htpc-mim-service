package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IDBConfigService;
import com.uwdp.module.api.vo.cmd.DBConfigAddCmd;
import com.uwdp.module.api.vo.cmd.DBConfigUpdateCmd;
import com.uwdp.module.api.vo.dto.DBConfigDto;
import com.uwdp.module.api.vo.excel.DBConfigExcelExport;
import com.uwdp.module.api.vo.excel.DBConfigExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.DBConfigAssembler;
import com.uwdp.module.biz.infrastructure.entity.DBConfigDo;
import com.uwdp.module.biz.infrastructure.excel.DBConfigExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.DBConfigRepository;
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
 * 数据库连接配置 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DBConfigServiceImpl implements IDBConfigService {

    private final DBConfigRepository dBConfigRepository;

    private final DBConfigAssembler dBConfigAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<DBConfigDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(DBConfigDto.class, paraMap);
    }

    @Override
    public List<DBConfigDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(DBConfigDto.class, paraMap);
    }

    @Override
    public List<DBConfigDto> listByIds(List<Long> fdIdList) {
        List<DBConfigDo> list = dBConfigRepository.list(new LambdaQueryWrapper<DBConfigDo>()
                .in(DBConfigDo::getFdId, fdIdList));
        return dBConfigAssembler.toValueObjectList(list);
    }

    @Override
    public DBConfigDto get(Long fdId) {
        DBConfigDo dBConfigDo = dBConfigRepository.getOne(new LambdaQueryWrapper<DBConfigDo>()
                .eq(DBConfigDo::getFdId, fdId));
        DBConfigDto dBConfigDTO = dBConfigAssembler.toValueObject(dBConfigDo);
        return dBConfigDTO;
    }

    @Override
    public void add(DBConfigAddCmd addCmd) {
        DBConfigDo dBConfigDo = dBConfigAssembler.toDO(addCmd);
        dBConfigRepository.save(dBConfigDo);
    }

    @Override
    public void update(DBConfigUpdateCmd updateCmd) {
        DBConfigDto dBConfigDTO = this.get(updateCmd.getFdId());
        if (dBConfigDTO != null) {
            DBConfigDo dBConfigDo = dBConfigAssembler.toDO(updateCmd);
            dBConfigRepository.updateById(dBConfigDo);
        }
    }

    @Override
    public void delete(Long fdId) {
        dBConfigRepository.remove(new LambdaQueryWrapper<DBConfigDo>()
                .eq(DBConfigDo::getFdId, fdId));
    }

    @Override
    public void batchDelete(String fdIds) {
        if (StringUtils.hasText(fdIds)) {
            List<Long> primaryKeys = StrUtil.split(fdIds, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            dBConfigRepository.remove(new LambdaQueryWrapper<DBConfigDo>()
                    .in(DBConfigDo::getFdId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), DBConfigExcelExport.class, "数据库连接配置导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<DBConfigExcelExport> searchResult = beanSearcher.searchAll(DBConfigExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, DBConfigExcelExport.class, "数据库连接配置数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<DBConfigExcelImport> excelParse(MultipartFile file) {
        DBConfigExcelImportListener listener = new DBConfigExcelImportListener();
        ExcelUtil.readFile(file, DBConfigExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<DBConfigExcelImport> list) {
        List<DBConfigDo> dBConfigDoList = dBConfigAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(dBConfigDoList)) {
            dBConfigRepository.saveBatch(dBConfigDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<DBConfigExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, DBConfigExcelExport.class, "数据库连接配置错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
