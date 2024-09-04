package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IProjectManagerService;
import com.uwdp.module.api.vo.cmd.ProjectManagerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectManagerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectManagerDto;
import com.uwdp.module.api.vo.excel.ProjectManagerExcelExport;
import com.uwdp.module.api.vo.excel.ProjectManagerExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ProjectManagerAssembler;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectManagerExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ProjectManagerRepository;
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
 * 项目经理 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectManagerServiceImpl implements IProjectManagerService {

    private final ProjectManagerRepository projectManagerRepository;

    private final ProjectManagerAssembler projectManagerAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<ProjectManagerDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ProjectManagerDto.class, paraMap);
    }

    @Override
    public List<ProjectManagerDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectManagerDto.class, paraMap);
    }

    @Override
    public List<ProjectManagerDto> listByIds(List<Long> idList) {
        List<ProjectManagerDo> list = projectManagerRepository.list(new LambdaQueryWrapper<ProjectManagerDo>()
                .in(ProjectManagerDo::getId, idList));
        return projectManagerAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectManagerDto get(Long id) {
        ProjectManagerDo projectManagerDo = projectManagerRepository.getOne(new LambdaQueryWrapper<ProjectManagerDo>()
                .eq(ProjectManagerDo::getId, id));
        ProjectManagerDto projectManagerDTO = projectManagerAssembler.toValueObject(projectManagerDo);
        return projectManagerDTO;
    }

    @Override
    public void add(ProjectManagerAddCmd addCmd) {
        ProjectManagerDo projectManagerDo = projectManagerAssembler.toDO(addCmd);
        projectManagerRepository.save(projectManagerDo);
    }

    @Override
    public void update(ProjectManagerUpdateCmd updateCmd) {
        ProjectManagerDto projectManagerDTO = this.get(updateCmd.getId());
        if (projectManagerDTO != null) {
            ProjectManagerDo projectManagerDo = projectManagerAssembler.toDO(updateCmd);
            projectManagerRepository.updateById(projectManagerDo);
        }
    }

    @Override
    public void delete(Long id) {
        projectManagerRepository.remove(new LambdaQueryWrapper<ProjectManagerDo>()
                .eq(ProjectManagerDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectManagerRepository.remove(new LambdaQueryWrapper<ProjectManagerDo>()
                    .in(ProjectManagerDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectManagerExcelExport.class, "项目经理导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ProjectManagerExcelExport> searchResult = beanSearcher.searchAll(ProjectManagerExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ProjectManagerExcelExport.class, "项目经理数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectManagerExcelImport> excelParse(MultipartFile file) {
        ProjectManagerExcelImportListener listener = new ProjectManagerExcelImportListener();
        ExcelUtil.readFile(file, ProjectManagerExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectManagerExcelImport> list) {
        List<ProjectManagerDo> projectManagerDoList = projectManagerAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectManagerDoList)) {
            projectManagerRepository.saveBatch(projectManagerDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectManagerExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectManagerExcelExport.class, "项目经理错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
