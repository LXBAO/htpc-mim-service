package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowConfigDo;

import com.uwdp.module.api.vo.cmd.LmcWorkflowConfigAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowConfigUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowConfigDto;
import com.uwdp.module.api.vo.excel.LmcWorkflowConfigExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 流程配置表 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LmcWorkflowConfigAssembler {

    LmcWorkflowConfigAssembler MAPPER = Mappers.getMapper(LmcWorkflowConfigAssembler.class);

    LmcWorkflowConfigDo toDO(LmcWorkflowConfigAddCmd addCmd);

    LmcWorkflowConfigDo toDO(LmcWorkflowConfigUpdateCmd updateCmd);
    
    LmcWorkflowConfigDto toValueObject(LmcWorkflowConfigDo entity);

    List<LmcWorkflowConfigDto> toValueObjectList(List<LmcWorkflowConfigDo> entityList);

    LmcWorkflowConfigDo toDoFromImport(LmcWorkflowConfigExcelImport importDTO);

    List<LmcWorkflowConfigDo> toDoFromImportList(List<LmcWorkflowConfigExcelImport> importDTOList);
}
