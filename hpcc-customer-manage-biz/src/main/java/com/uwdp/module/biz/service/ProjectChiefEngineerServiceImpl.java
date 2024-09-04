package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IProjectChiefEngineerService;
import com.uwdp.module.api.vo.cmd.ProjectChiefEngineerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectChiefEngineerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectChiefEngineerDto;
import com.uwdp.module.api.vo.excel.ProjectChiefEngineerExcelExport;
import com.uwdp.module.api.vo.excel.ProjectChiefEngineerExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ProjectChiefEngineerAssembler;
import com.uwdp.module.biz.infrastructure.entity.ProjectChiefEngineerDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectChiefEngineerExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ProjectChiefEngineerRepository;
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
 * 项目总工 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectChiefEngineerServiceImpl implements IProjectChiefEngineerService {

    private final ProjectChiefEngineerRepository projectChiefEngineerRepository;

    private final ProjectChiefEngineerAssembler projectChiefEngineerAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<ProjectChiefEngineerDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ProjectChiefEngineerDto.class, paraMap);
    }

    @Override
    public List<ProjectChiefEngineerDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectChiefEngineerDto.class, paraMap);
    }

    @Override
    public List<ProjectChiefEngineerDto> listByIds(List<Long> idList) {
        List<ProjectChiefEngineerDo> list = projectChiefEngineerRepository.list(new LambdaQueryWrapper<ProjectChiefEngineerDo>()
                .in(ProjectChiefEngineerDo::getId, idList));
        return projectChiefEngineerAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectChiefEngineerDto get(Long id) {
        ProjectChiefEngineerDo projectChiefEngineerDo = projectChiefEngineerRepository.getOne(new LambdaQueryWrapper<ProjectChiefEngineerDo>()
                .eq(ProjectChiefEngineerDo::getId, id));
        ProjectChiefEngineerDto projectChiefEngineerDTO = projectChiefEngineerAssembler.toValueObject(projectChiefEngineerDo);
        return projectChiefEngineerDTO;
    }

    @Override
    public void add(ProjectChiefEngineerAddCmd addCmd) {
        ProjectChiefEngineerDo projectChiefEngineerDo = projectChiefEngineerAssembler.toDO(addCmd);
        projectChiefEngineerRepository.save(projectChiefEngineerDo);
    }

    @Override
    public void update(ProjectChiefEngineerUpdateCmd updateCmd) {
        ProjectChiefEngineerDto projectChiefEngineerDTO = this.get(updateCmd.getId());
        if (projectChiefEngineerDTO != null) {
            ProjectChiefEngineerDo projectChiefEngineerDo = projectChiefEngineerAssembler.toDO(updateCmd);
            projectChiefEngineerRepository.updateById(projectChiefEngineerDo);
        }
    }

    @Override
    public void delete(Long id) {
        projectChiefEngineerRepository.remove(new LambdaQueryWrapper<ProjectChiefEngineerDo>()
                .eq(ProjectChiefEngineerDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectChiefEngineerRepository.remove(new LambdaQueryWrapper<ProjectChiefEngineerDo>()
                    .in(ProjectChiefEngineerDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectChiefEngineerExcelExport.class, "项目总工导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ProjectChiefEngineerExcelExport> searchResult = beanSearcher.searchAll(ProjectChiefEngineerExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ProjectChiefEngineerExcelExport.class, "项目总工数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectChiefEngineerExcelImport> excelParse(MultipartFile file) {
        ProjectChiefEngineerExcelImportListener listener = new ProjectChiefEngineerExcelImportListener();
        ExcelUtil.readFile(file, ProjectChiefEngineerExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectChiefEngineerExcelImport> list) {
        List<ProjectChiefEngineerDo> projectChiefEngineerDoList = projectChiefEngineerAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectChiefEngineerDoList)) {
            projectChiefEngineerRepository.saveBatch(projectChiefEngineerDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectChiefEngineerExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectChiefEngineerExcelExport.class, "项目总工错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
