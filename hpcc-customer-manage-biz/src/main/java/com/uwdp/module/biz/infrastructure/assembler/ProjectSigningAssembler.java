package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ProjectSigningAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectSigningUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectSigningDto;
import com.uwdp.module.api.vo.dto.searcher.ProjectSigningRecordsWorkflowDto;
import com.uwdp.module.api.vo.excel.ProjectSigningExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectSigningDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目签约 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectSigningAssembler {

    ProjectSigningAssembler MAPPER = Mappers.getMapper(ProjectSigningAssembler.class);

    ProjectSigningDo toDO(ProjectSigningAddCmd addCmd);

    ProjectSigningDo toDO(ProjectSigningUpdateCmd updateCmd);
    
    ProjectSigningDto toValueObject(ProjectSigningDo entity);

    List<ProjectSigningDto> toValueObjectList(List<ProjectSigningDo> entityList);

    ProjectSigningDo toDoFromImport(ProjectSigningExcelImport importDTO);

    List<ProjectSigningDo> toDoFromImportList(List<ProjectSigningExcelImport> importDTOList);
}
