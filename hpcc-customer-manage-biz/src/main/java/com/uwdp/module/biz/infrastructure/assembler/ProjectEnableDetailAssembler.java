package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ProjectEnableDetailAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectEnableDetailDto;
import com.uwdp.module.api.vo.excel.ProjectEnableDetailExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectEnableDetailDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目赋能明细 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectEnableDetailAssembler {

    ProjectEnableDetailAssembler MAPPER = Mappers.getMapper(ProjectEnableDetailAssembler.class);

    ProjectEnableDetailDo toDO(ProjectEnableDetailAddCmd addCmd);

    ProjectEnableDetailDo toDO(ProjectEnableDetailUpdateCmd updateCmd);
    
    ProjectEnableDetailDto toValueObject(ProjectEnableDetailDo entity);

    List<ProjectEnableDetailDto> toValueObjectList(List<ProjectEnableDetailDo> entityList);

    ProjectEnableDetailDo toDoFromImport(ProjectEnableDetailExcelImport importDTO);

    List<ProjectEnableDetailDo> toDoFromImportList(List<ProjectEnableDetailExcelImport> importDTOList);
}
