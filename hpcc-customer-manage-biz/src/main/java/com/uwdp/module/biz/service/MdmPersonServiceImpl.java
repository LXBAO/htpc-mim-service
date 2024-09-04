package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.operator.Contain;
import com.ejlchina.searcher.operator.Equal;
import com.ejlchina.searcher.operator.InList;
import com.ejlchina.searcher.operator.OrLike;
import com.ejlchina.searcher.util.MapBuilder;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.vo.cmd.MdmPersonAddCmd;
import com.uwdp.module.api.vo.cmd.MdmPersonUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.api.vo.query.MdmPersonQuery;
import com.uwdp.module.biz.infrastructure.assembler.MdmPersonAssembler;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import com.uwdp.module.api.vo.excel.MdmPersonExcelExport;
import com.uwdp.module.api.vo.excel.MdmPersonExcelImport;
import com.uwdp.module.biz.infrastructure.excel.MdmPersonExcelImportListener;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;
import static org.aspectj.weaver.MemberImpl.field;

/**
 * <p>
 * 主数据-人员 服务实现类
 * </p>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MdmPersonServiceImpl implements IMdmPersonService {

    private final MdmPersonRepository mdmPersonRepository;

    private final MdmPersonAssembler mdmPersonAssembler;

    private final BeanSearcher beanSearcher;

    private final IMdmOrganizationService mdmOrganizationService;

    @Override
    public SearchResult<MdmPersonDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(MdmPersonDto.class, paraMap);
    }


    @Override
    public SearchResult<MdmPersonDto> personInfoByGroupBelongUnitCode(MdmPersonQuery paramQuery, Map<String, String[]> paramsMap) {
        List<String> codes = mdmOrganizationService.getGroupListByGroupBelongUnitCode(paramQuery.getGroupBelongUnitCode());
        Map<String, Object> map = MapUtils.flatBuilder(paramsMap)
                .field(MdmPersonDto::getGroupBelongDepartmentCode, codes).op(InList.class)
                .field(MdmPersonDto::getPersonCode).op(Contain.class)
                .field(MdmPersonDto::getPersonName).op(Contain.class)
                .build();
        // 这个beanSearcher的参数所需的map , 好像是根据map里面的key与BeanMeta对比 , 最后拼接成sql的 ,
        // 如果前端传参A是Bean中存在的字段 , 但是对应过滤参数的字段是B , 此时A、B都会拼接到最终的sql中 ,这里就是这种情况 , 所以去除不需要的字段参数
        map.remove("groupBelongUnitCode");
        return beanSearcher.search(MdmPersonDto.class, map);
    }

    @Override
    public List<MdmPersonDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(MdmPersonDto.class, paraMap);
    }

    @Override
    public List<MdmPersonDto> listByIds(List<Long> idList) {
        List<MdmPersonDo> list = mdmPersonRepository.list(new LambdaQueryWrapper<MdmPersonDo>()
                .in(MdmPersonDo::getId, idList));
        return mdmPersonAssembler.toValueObjectList(list);
    }

    @Override
    public MdmPersonDto get(Long id) {
        MdmPersonDo mdmPersonDo = mdmPersonRepository.getOne(new LambdaQueryWrapper<MdmPersonDo>()
                .eq(MdmPersonDo::getId, id));
        MdmPersonDto mdmPersonDTO = mdmPersonAssembler.toValueObject(mdmPersonDo);
        return mdmPersonDTO;
    }

    @Override
    public MdmPersonDto getPersonCodeDetail(String personCode) {
        MdmPersonDo mdmPersonDo = mdmPersonRepository.getOne(new LambdaQueryWrapper<MdmPersonDo>()
                .eq(MdmPersonDo::getPersonCode, personCode).last("limit 1"));
        MdmPersonDto mdmPersonDTO = mdmPersonAssembler.toValueObject(mdmPersonDo);
        return mdmPersonDTO;
    }

    @Override
    public MdmPersonDto get(String personCode) {
        MdmPersonDo mdmPersonDo = mdmPersonRepository.getOne(new LambdaQueryWrapper<MdmPersonDo>()
                .eq(MdmPersonDo::getPersonCode, personCode));
        MdmPersonDto mdmPersonDTO = mdmPersonAssembler.toValueObject(mdmPersonDo);
        return mdmPersonDTO;
    }

    @Override
    public void add(MdmPersonAddCmd addCmd) {
        MdmPersonDo mdmPersonDo = mdmPersonAssembler.toDO(addCmd);
        mdmPersonRepository.save(mdmPersonDo);
    }

    @Override
    public void update(MdmPersonUpdateCmd updateCmd) {
        MdmPersonDto mdmPersonDTO = this.get(updateCmd.getId());
        if (mdmPersonDTO != null) {
            MdmPersonDo mdmPersonDo = mdmPersonAssembler.toDO(updateCmd);
            mdmPersonRepository.updateById(mdmPersonDo);
        }
    }

    @Override
    public void delete(Long id) {
        mdmPersonRepository.remove(new LambdaQueryWrapper<MdmPersonDo>()
                .eq(MdmPersonDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            mdmPersonRepository.remove(new LambdaQueryWrapper<MdmPersonDo>()
                    .in(MdmPersonDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), MdmPersonExcelExport.class, "主数据-人员导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<MdmPersonExcelExport> searchResult = beanSearcher.searchAll(MdmPersonExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, MdmPersonExcelExport.class, "主数据-人员数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<MdmPersonExcelImport> excelParse(MultipartFile file) {
        MdmPersonExcelImportListener listener = new MdmPersonExcelImportListener();
        ExcelUtil.readFile(file, MdmPersonExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<MdmPersonExcelImport> list) {
        List<MdmPersonDo> mdmPersonDoList = mdmPersonAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(mdmPersonDoList)) {
            mdmPersonRepository.saveBatch(mdmPersonDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<MdmPersonExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, MdmPersonExcelExport.class, "主数据-人员错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
