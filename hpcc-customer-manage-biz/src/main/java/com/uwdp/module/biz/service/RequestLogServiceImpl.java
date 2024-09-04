package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IRequestLogService;
import com.uwdp.module.api.vo.cmd.RequestLogAddCmd;
import com.uwdp.module.api.vo.cmd.RequestLogUpdateCmd;
import com.uwdp.module.api.vo.dto.RequestLogDto;
import com.uwdp.module.biz.infrastructure.assembler.RequestLogAssembler;
import com.uwdp.module.biz.infrastructure.entity.RequestLogDo;
import com.uwdp.module.biz.infrastructure.repository.RequestLogRepository;
import com.uwdp.module.api.vo.excel.RequestLogExcelExport;
import com.uwdp.module.api.vo.excel.RequestLogExcelImport;
import com.uwdp.module.biz.infrastructure.excel.RequestLogExcelImportListener;
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
 * 流程日志 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RequestLogServiceImpl implements IRequestLogService {

    private final RequestLogRepository requestLogRepository;

    private final RequestLogAssembler requestLogAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<RequestLogDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(RequestLogDto.class, paraMap);
    }

    @Override
    public List<RequestLogDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(RequestLogDto.class, paraMap);
    }

    @Override
    public List<RequestLogDto> listByIds(List<Long> idList) {
        List<RequestLogDo> list = requestLogRepository.list(new LambdaQueryWrapper<RequestLogDo>()
                .in(RequestLogDo::getId, idList));
        return requestLogAssembler.toValueObjectList(list);
    }

    @Override
    public RequestLogDto get(Long id) {
        RequestLogDo requestLogDo = requestLogRepository.getOne(new LambdaQueryWrapper<RequestLogDo>()
                .eq(RequestLogDo::getId, id));
        RequestLogDto requestLogDTO = requestLogAssembler.toValueObject(requestLogDo);
        return requestLogDTO;
    }

    @Override
    public void add(RequestLogAddCmd addCmd) {
        RequestLogDo requestLogDo = requestLogAssembler.toDO(addCmd);
        requestLogRepository.save(requestLogDo);
    }

    @Override
    public void update(RequestLogUpdateCmd updateCmd) {
        RequestLogDto requestLogDTO = this.get(updateCmd.getId());
        if (requestLogDTO != null) {
            RequestLogDo requestLogDo = requestLogAssembler.toDO(updateCmd);
            requestLogRepository.updateById(requestLogDo);
        }
    }

    @Override
    public void delete(Long id) {
        requestLogRepository.remove(new LambdaQueryWrapper<RequestLogDo>()
                .eq(RequestLogDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            requestLogRepository.remove(new LambdaQueryWrapper<RequestLogDo>()
                    .in(RequestLogDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), RequestLogExcelExport.class, "流程日志导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<RequestLogExcelExport> searchResult = beanSearcher.searchAll(RequestLogExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, RequestLogExcelExport.class, "流程日志数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<RequestLogExcelImport> excelParse(MultipartFile file) {
        RequestLogExcelImportListener listener = new RequestLogExcelImportListener();
        ExcelUtil.readFile(file, RequestLogExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<RequestLogExcelImport> list) {
        List<RequestLogDo> requestLogDoList = requestLogAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(requestLogDoList)) {
            requestLogRepository.saveBatch(requestLogDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<RequestLogExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, RequestLogExcelExport.class, "流程日志错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
