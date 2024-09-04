package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ProjectManagerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectManagerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectManagerDto;
import com.uwdp.module.api.vo.excel.ProjectManagerExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目经理 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectManagerAssembler {

    ProjectManagerAssembler MAPPER = Mappers.getMapper(ProjectManagerAssembler.class);

    ProjectManagerDo toDO(ProjectManagerAddCmd addCmd);

    ProjectManagerDo toDO(ProjectManagerUpdateCmd updateCmd);
    
    ProjectManagerDto toValueObject(ProjectManagerDo entity);

    List<ProjectManagerDto> toValueObjectList(List<ProjectManagerDo> entityList);

    ProjectManagerDo toDoFromImport(ProjectManagerExcelImport importDTO);

    List<ProjectManagerDo> toDoFromImportList(List<ProjectManagerExcelImport> importDTOList);
}
