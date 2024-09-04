package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ProjectTrackingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectTrackingUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectTrackingDto;
import com.uwdp.module.api.vo.excel.ProjectTrackingExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectTrackingDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目跟踪 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectTrackingAssembler {

    ProjectTrackingAssembler MAPPER = Mappers.getMapper(ProjectTrackingAssembler.class);

    ProjectTrackingDo toDO(ProjectTrackingAddCmd addCmd);

    ProjectTrackingDo toDO(ProjectTrackingUpdateCmd updateCmd);
    
    ProjectTrackingDto toValueObject(ProjectTrackingDo entity);

    List<ProjectTrackingDto> toValueObjectList(List<ProjectTrackingDo> entityList);

    ProjectTrackingDo toDoFromImport(ProjectTrackingExcelImport importDTO);

    List<ProjectTrackingDo> toDoFromImportList(List<ProjectTrackingExcelImport> importDTOList);
}
