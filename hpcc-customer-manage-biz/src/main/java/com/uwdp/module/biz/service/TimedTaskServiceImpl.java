package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ITimedTaskService;
import com.uwdp.module.api.vo.cmd.TimedTaskAddCmd;
import com.uwdp.module.api.vo.cmd.TimedTaskUpdateCmd;
import com.uwdp.module.api.vo.dto.TimedTaskDto;
import com.uwdp.module.api.vo.excel.TimedTaskExcelExport;
import com.uwdp.module.api.vo.excel.TimedTaskExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.TimedTaskAssembler;
import com.uwdp.module.biz.infrastructure.entity.TimedTaskDo;
import com.uwdp.module.biz.infrastructure.excel.TimedTaskExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.TimedTaskRepository;
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
 * 定时任务 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TimedTaskServiceImpl implements ITimedTaskService {

    private final TimedTaskRepository timedTaskRepository;

    private final TimedTaskAssembler timedTaskAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<TimedTaskDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(TimedTaskDto.class, paraMap);
    }

    @Override
    public List<TimedTaskDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(TimedTaskDto.class, paraMap);
    }

    @Override
    public List<TimedTaskDto> listByIds(List<Long> idList) {
        List<TimedTaskDo> list = timedTaskRepository.list(new LambdaQueryWrapper<TimedTaskDo>()
                .in(TimedTaskDo::getId, idList));
        return timedTaskAssembler.toValueObjectList(list);
    }

    @Override
    public TimedTaskDto get(Long id) {
        TimedTaskDo timedTaskDo = timedTaskRepository.getOne(new LambdaQueryWrapper<TimedTaskDo>()
                .eq(TimedTaskDo::getId, id));
        TimedTaskDto timedTaskDTO = timedTaskAssembler.toValueObject(timedTaskDo);
        return timedTaskDTO;
    }

    @Override
    public void add(TimedTaskAddCmd addCmd) {
        TimedTaskDo timedTaskDo = timedTaskAssembler.toDO(addCmd);
        timedTaskRepository.save(timedTaskDo);
    }

    @Override
    public void update(TimedTaskUpdateCmd updateCmd) {
        TimedTaskDto timedTaskDTO = this.get(updateCmd.getId());
        if (timedTaskDTO != null) {
            TimedTaskDo timedTaskDo = timedTaskAssembler.toDO(updateCmd);
            timedTaskRepository.updateById(timedTaskDo);
        }
    }

    @Override
    public void delete(Long id) {
        timedTaskRepository.remove(new LambdaQueryWrapper<TimedTaskDo>()
                .eq(TimedTaskDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            timedTaskRepository.remove(new LambdaQueryWrapper<TimedTaskDo>()
                    .in(TimedTaskDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), TimedTaskExcelExport.class, "定时任务导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<TimedTaskExcelExport> searchResult = beanSearcher.searchAll(TimedTaskExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, TimedTaskExcelExport.class, "定时任务数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<TimedTaskExcelImport> excelParse(MultipartFile file) {
        TimedTaskExcelImportListener listener = new TimedTaskExcelImportListener();
        ExcelUtil.readFile(file, TimedTaskExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<TimedTaskExcelImport> list) {
        List<TimedTaskDo> timedTaskDoList = timedTaskAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(timedTaskDoList)) {
            timedTaskRepository.saveBatch(timedTaskDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<TimedTaskExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, TimedTaskExcelExport.class, "定时任务错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
