package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IProjectEnableDetailService;
import com.uwdp.module.api.vo.cmd.ProjectEnableDetailAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectEnableDetailDto;
import com.uwdp.module.api.vo.excel.ProjectEnableDetailExcelExport;
import com.uwdp.module.api.vo.excel.ProjectEnableDetailExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ProjectEnableDetailAssembler;
import com.uwdp.module.biz.infrastructure.entity.ProjectEnableDetailDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectEnableDetailExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ProjectEnableDetailRepository;
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
 * 项目赋能明细 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectEnableDetailServiceImpl implements IProjectEnableDetailService {

    private final ProjectEnableDetailRepository projectEnableDetailRepository;

    private final ProjectEnableDetailAssembler projectEnableDetailAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<ProjectEnableDetailDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(ProjectEnableDetailDto.class, paraMap);
    }

    @Override
    public List<ProjectEnableDetailDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(ProjectEnableDetailDto.class, paraMap);
    }

    @Override
    public List<ProjectEnableDetailDto> listByIds(List<Long> idList) {
        List<ProjectEnableDetailDo> list = projectEnableDetailRepository.list(new LambdaQueryWrapper<ProjectEnableDetailDo>()
                .in(ProjectEnableDetailDo::getId, idList));
        return projectEnableDetailAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectEnableDetailDto get(Long id) {
        ProjectEnableDetailDo projectEnableDetailDo = projectEnableDetailRepository.getOne(new LambdaQueryWrapper<ProjectEnableDetailDo>()
                .eq(ProjectEnableDetailDo::getId, id));
        ProjectEnableDetailDto projectEnableDetailDTO = projectEnableDetailAssembler.toValueObject(projectEnableDetailDo);
        return projectEnableDetailDTO;
    }

    @Override
    public void add(ProjectEnableDetailAddCmd addCmd) {
        ProjectEnableDetailDo projectEnableDetailDo = projectEnableDetailAssembler.toDO(addCmd);
        projectEnableDetailRepository.save(projectEnableDetailDo);
    }

    @Override
    public void update(ProjectEnableDetailUpdateCmd updateCmd) {
        ProjectEnableDetailDto projectEnableDetailDTO = this.get(updateCmd.getId());
        if (projectEnableDetailDTO != null) {
            ProjectEnableDetailDo projectEnableDetailDo = projectEnableDetailAssembler.toDO(updateCmd);
            projectEnableDetailRepository.updateById(projectEnableDetailDo);
        }
    }

    @Override
    public void delete(Long id) {
        projectEnableDetailRepository.remove(new LambdaQueryWrapper<ProjectEnableDetailDo>()
                .eq(ProjectEnableDetailDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectEnableDetailRepository.remove(new LambdaQueryWrapper<ProjectEnableDetailDo>()
                    .in(ProjectEnableDetailDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectEnableDetailExcelExport.class, "项目赋能明细导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ProjectEnableDetailExcelExport> searchResult = beanSearcher.searchAll(ProjectEnableDetailExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ProjectEnableDetailExcelExport.class, "项目赋能明细数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectEnableDetailExcelImport> excelParse(MultipartFile file) {
        ProjectEnableDetailExcelImportListener listener = new ProjectEnableDetailExcelImportListener();
        ExcelUtil.readFile(file, ProjectEnableDetailExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectEnableDetailExcelImport> list) {
        List<ProjectEnableDetailDo> projectEnableDetailDoList = projectEnableDetailAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectEnableDetailDoList)) {
            projectEnableDetailRepository.saveBatch(projectEnableDetailDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectEnableDetailExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectEnableDetailExcelExport.class, "项目赋能明细错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
