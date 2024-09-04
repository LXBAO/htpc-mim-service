package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowDo;

import com.uwdp.module.api.vo.cmd.LmcWorkflowAddCmd;
import com.uwdp.module.api.vo.cmd.LmcWorkflowUpdateCmd;
import com.uwdp.module.api.vo.dto.LmcWorkflowDto;
import com.uwdp.module.api.vo.excel.LmcWorkflowExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 流程表 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LmcWorkflowAssembler {

    LmcWorkflowAssembler MAPPER = Mappers.getMapper(LmcWorkflowAssembler.class);

    LmcWorkflowDo toDO(LmcWorkflowAddCmd addCmd);

    LmcWorkflowDo toDO(LmcWorkflowUpdateCmd updateCmd);
    
    LmcWorkflowDto toValueObject(LmcWorkflowDo entity);

    List<LmcWorkflowDto> toValueObjectList(List<LmcWorkflowDo> entityList);

    LmcWorkflowDo toDoFromImport(LmcWorkflowExcelImport importDTO);

    List<LmcWorkflowDo> toDoFromImportList(List<LmcWorkflowExcelImport> importDTOList);
}
