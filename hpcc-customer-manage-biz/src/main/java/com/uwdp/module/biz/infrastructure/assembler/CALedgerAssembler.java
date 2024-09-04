package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.CALedgerAddCmd;
import com.uwdp.module.api.vo.cmd.CALedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.CALedgerDto;
import com.uwdp.module.api.vo.excel.CALedgerExcelImport;
import com.uwdp.module.biz.infrastructure.entity.CALedgerDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * CA台账 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CALedgerAssembler {

    CALedgerAssembler MAPPER = Mappers.getMapper(CALedgerAssembler.class);

    CALedgerDo toDO(CALedgerAddCmd addCmd);

    CALedgerDo toDO(CALedgerUpdateCmd updateCmd);
    
    CALedgerDto toValueObject(CALedgerDo entity);

    List<CALedgerDto> toValueObjectList(List<CALedgerDo> entityList);

    CALedgerDo toDoFromImport(CALedgerExcelImport importDTO);

    List<CALedgerDo> toDoFromImportList(List<CALedgerExcelImport> importDTOList);
}
