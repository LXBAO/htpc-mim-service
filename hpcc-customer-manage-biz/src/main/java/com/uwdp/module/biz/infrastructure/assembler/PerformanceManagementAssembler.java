package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.PerformanceManagementAddCmd;
import com.uwdp.module.api.vo.cmd.PerformanceManagementUpdateCmd;
import com.uwdp.module.api.vo.dto.PerformanceManagementDto;
import com.uwdp.module.api.vo.excel.PerformanceManagementExcelImport;
import com.uwdp.module.biz.infrastructure.entity.PerformanceManagementDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 业绩管理 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PerformanceManagementAssembler {

    PerformanceManagementAssembler MAPPER = Mappers.getMapper(PerformanceManagementAssembler.class);

    PerformanceManagementDo toDO(PerformanceManagementAddCmd addCmd);

    PerformanceManagementDo toDO(PerformanceManagementUpdateCmd updateCmd);
    
    PerformanceManagementDto toValueObject(PerformanceManagementDo entity);

    List<PerformanceManagementDto> toValueObjectList(List<PerformanceManagementDo> entityList);

    PerformanceManagementDo toDoFromImport(PerformanceManagementExcelImport importDTO);

    List<PerformanceManagementDo> toDoFromImportList(List<PerformanceManagementExcelImport> importDTOList);
}
