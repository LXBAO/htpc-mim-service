package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IProjectManagerLedgerService;
import com.uwdp.module.api.vo.cmd.ProjectManagerLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectManagerLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectManagerLedgerDto;
import com.uwdp.module.api.vo.excel.ProjectManagerLedgerExcelExport;
import com.uwdp.module.api.vo.excel.ProjectManagerLedgerExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ProjectManagerLedgerAssembler;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerLedgerDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectManagerLedgerExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ProjectManagerLedgerRepository;
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
 * 项目经理台账 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectManagerLedgerServiceImpl implements IProjectManagerLedgerService {

    private final ProjectManagerLedgerRepository projectManagerLedgerRepository;

    private final ProjectManagerLedgerAssembler projectManagerLedgerAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<ProjectManagerLedgerDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ProjectManagerLedgerDto.class, paraMap);
    }

    @Override
    public List<ProjectManagerLedgerDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectManagerLedgerDto.class, paraMap);
    }

    @Override
    public List<ProjectManagerLedgerDto> listByIds(List<Long> fdIdList) {
        List<ProjectManagerLedgerDo> list = projectManagerLedgerRepository.list(new LambdaQueryWrapper<ProjectManagerLedgerDo>()
                .in(ProjectManagerLedgerDo::getFdId, fdIdList));
        return projectManagerLedgerAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectManagerLedgerDto get(Long fdId) {
        ProjectManagerLedgerDo projectManagerLedgerDo = projectManagerLedgerRepository.getOne(new LambdaQueryWrapper<ProjectManagerLedgerDo>()
                .eq(ProjectManagerLedgerDo::getFdId, fdId));
        ProjectManagerLedgerDto projectManagerLedgerDTO = projectManagerLedgerAssembler.toValueObject(projectManagerLedgerDo);
        return projectManagerLedgerDTO;
    }

    @Override
    public ProjectManagerLedgerDto detailByid(String hrId) {
        ProjectManagerLedgerDo projectManagerLedgerDo = projectManagerLedgerRepository.getOne(new LambdaQueryWrapper<ProjectManagerLedgerDo>()
                .eq(ProjectManagerLedgerDo::getHrId, hrId));
        ProjectManagerLedgerDto projectManagerLedgerDTO = projectManagerLedgerAssembler.toValueObject(projectManagerLedgerDo);
        return projectManagerLedgerDTO;
    }

    @Override
    public void add(ProjectManagerLedgerAddCmd addCmd) {
        ProjectManagerLedgerDo projectManagerLedgerDo = projectManagerLedgerAssembler.toDO(addCmd);
        projectManagerLedgerRepository.save(projectManagerLedgerDo);
    }

    @Override
    public void update(ProjectManagerLedgerUpdateCmd updateCmd) {
        ProjectManagerLedgerDto projectManagerLedgerDTO = this.get(updateCmd.getFdId());
        if (projectManagerLedgerDTO != null) {
            ProjectManagerLedgerDo projectManagerLedgerDo = projectManagerLedgerAssembler.toDO(updateCmd);
            projectManagerLedgerRepository.updateById(projectManagerLedgerDo);
        }
    }

    @Override
    public void delete(Long fdId) {
        projectManagerLedgerRepository.remove(new LambdaQueryWrapper<ProjectManagerLedgerDo>()
                .eq(ProjectManagerLedgerDo::getFdId, fdId));
    }

    @Override
    public void batchDelete(String fdIds) {
        if (StringUtils.hasText(fdIds)) {
            List<Long> primaryKeys = StrUtil.split(fdIds, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectManagerLedgerRepository.remove(new LambdaQueryWrapper<ProjectManagerLedgerDo>()
                    .in(ProjectManagerLedgerDo::getFdId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectManagerLedgerExcelExport.class, "项目经理台账导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ProjectManagerLedgerExcelExport> searchResult = beanSearcher.searchAll(ProjectManagerLedgerExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ProjectManagerLedgerExcelExport.class, "项目经理台账数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectManagerLedgerExcelImport> excelParse(MultipartFile file) {
        ProjectManagerLedgerExcelImportListener listener = new ProjectManagerLedgerExcelImportListener();
        ExcelUtil.readFile(file, ProjectManagerLedgerExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectManagerLedgerExcelImport> list) {
        List<ProjectManagerLedgerDo> projectManagerLedgerDoList = projectManagerLedgerAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectManagerLedgerDoList)) {
            projectManagerLedgerRepository.saveBatch(projectManagerLedgerDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectManagerLedgerExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectManagerLedgerExcelExport.class, "项目经理台账错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
