package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowVariableDo;

import com.uwdp.module.api.vo.cmd.LmcWorkflowVariableAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowVariableUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowVariableDto;
import com.uwdp.module.api.vo.excel.LmcWorkflowVariableExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 流程表表单数据 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LmcWorkflowVariableAssembler {

    LmcWorkflowVariableAssembler MAPPER = Mappers.getMapper(LmcWorkflowVariableAssembler.class);

    LmcWorkflowVariableDo toDO(LmcWorkflowVariableAddCmd addCmd);

    LmcWorkflowVariableDo toDO(LmcWorkflowVariableUpdateCmd updateCmd);
    
    LmcWorkflowVariableDto toValueObject(LmcWorkflowVariableDo entity);

    List<LmcWorkflowVariableDto> toValueObjectList(List<LmcWorkflowVariableDo> entityList);

    LmcWorkflowVariableDo toDoFromImport(LmcWorkflowVariableExcelImport importDTO);

    List<LmcWorkflowVariableDo> toDoFromImportList(List<LmcWorkflowVariableExcelImport> importDTOList);
}
