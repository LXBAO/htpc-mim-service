package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.PerformanceLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.PerformanceLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.PerformanceLedgerDto;
import com.uwdp.module.api.vo.excel.PerformanceLedgerExcelImport;
import com.uwdp.module.biz.infrastructure.entity.PerformanceLedgerDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 业绩台账 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PerformanceLedgerAssembler {

    PerformanceLedgerAssembler MAPPER = Mappers.getMapper(PerformanceLedgerAssembler.class);

    PerformanceLedgerDo toDO(PerformanceLedgerAddCmd addCmd);

    PerformanceLedgerDo toDO(PerformanceLedgerUpdateCmd updateCmd);
    
    PerformanceLedgerDto toValueObject(PerformanceLedgerDo entity);

    List<PerformanceLedgerDto> toValueObjectList(List<PerformanceLedgerDo> entityList);

    PerformanceLedgerDo toDoFromImport(PerformanceLedgerExcelImport importDTO);

    List<PerformanceLedgerDo> toDoFromImportList(List<PerformanceLedgerExcelImport> importDTOList);
}
