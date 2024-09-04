package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ProjectSuspensionAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSuspensionUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectSuspensionDto;
import com.uwdp.module.api.vo.excel.ProjectSuspensionExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectSuspensionDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目中止 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectSuspensionAssembler {

    ProjectSuspensionAssembler MAPPER = Mappers.getMapper(ProjectSuspensionAssembler.class);

    ProjectSuspensionDo toDO(ProjectSuspensionAddCmd addCmd);

    ProjectSuspensionDo toDO(ProjectSuspensionUpdateCmd updateCmd);
    
    ProjectSuspensionDto toValueObject(ProjectSuspensionDo entity);

    List<ProjectSuspensionDto> toValueObjectList(List<ProjectSuspensionDo> entityList);

    ProjectSuspensionDo toDoFromImport(ProjectSuspensionExcelImport importDTO);

    List<ProjectSuspensionDo> toDoFromImportList(List<ProjectSuspensionExcelImport> importDTOList);
}
