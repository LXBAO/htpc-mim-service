package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.ProjectImplementDo;

import com.uwdp.module.api.vo.cmd.ProjectImplementAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectImplementUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectImplementDto;
import com.uwdp.module.api.vo.excel.ProjectImplementExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 项目实施 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectImplementAssembler {

    ProjectImplementAssembler MAPPER = Mappers.getMapper(ProjectImplementAssembler.class);

    ProjectImplementDo toDO(ProjectImplementAddCmd addCmd);

    ProjectImplementDo toDO(ProjectImplementUpdateCmd updateCmd);
    
    ProjectImplementDto toValueObject(ProjectImplementDo entity);

    List<ProjectImplementDto> toValueObjectList(List<ProjectImplementDo> entityList);

    ProjectImplementDo toDoFromImport(ProjectImplementExcelImport importDTO);

    List<ProjectImplementDo> toDoFromImportList(List<ProjectImplementExcelImport> importDTOList);
}
