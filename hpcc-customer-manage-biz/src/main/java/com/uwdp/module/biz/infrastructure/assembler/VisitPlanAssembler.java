package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.VisitPlanDo;

import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanUpdateCmd;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.excel.VisitPlanExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 公关关系计划 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VisitPlanAssembler {

    VisitPlanAssembler MAPPER = Mappers.getMapper(VisitPlanAssembler.class);

    VisitPlanDo toDO(VisitPlanAddCmd addCmd);

    VisitPlanDo toDO(VisitPlanUpdateCmd updateCmd);
    
    VisitPlanDto toValueObject(VisitPlanDo entity);

    List<VisitPlanDto> toValueObjectList(List<VisitPlanDo> entityList);

    VisitPlanDo toDoFromImport(VisitPlanExcelImport importDTO);

    List<VisitPlanDo> toDoFromImportList(List<VisitPlanExcelImport> importDTOList);
}
