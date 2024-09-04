package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.ProjectRecordsDo;

import com.uwdp.module.api.vo.cmd.ProjectRecordsAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectRecordsUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectRecordsDto;
import com.uwdp.module.api.vo.excel.ProjectRecordsExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 项目登记 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectRecordsAssembler {

    ProjectRecordsAssembler MAPPER = Mappers.getMapper(ProjectRecordsAssembler.class);

    ProjectRecordsDo toDO(ProjectRecordsAddCmd addCmd);

    ProjectRecordsDo toDO(ProjectRecordsUpdateCmd updateCmd);
    
    ProjectRecordsDto toValueObject(ProjectRecordsDo entity);

    List<ProjectRecordsDto> toValueObjectList(List<ProjectRecordsDo> entityList);

    ProjectRecordsDo toDoFromImport(ProjectRecordsExcelImport importDTO);

    List<ProjectRecordsDo> toDoFromImportList(List<ProjectRecordsExcelImport> importDTOList);
}
