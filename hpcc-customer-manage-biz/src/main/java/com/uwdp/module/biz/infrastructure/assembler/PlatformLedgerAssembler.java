package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.PlatformLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.PlatformLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.PlatformLedgerDto;
import com.uwdp.module.api.vo.excel.PlatformLedgerExcelImport;
import com.uwdp.module.biz.infrastructure.entity.PlatformLedgerDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 平台台账 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PlatformLedgerAssembler {

    PlatformLedgerAssembler MAPPER = Mappers.getMapper(PlatformLedgerAssembler.class);

    PlatformLedgerDo toDO(PlatformLedgerAddCmd addCmd);

    PlatformLedgerDo toDO(PlatformLedgerUpdateCmd updateCmd);
    
    PlatformLedgerDto toValueObject(PlatformLedgerDo entity);

    List<PlatformLedgerDto> toValueObjectList(List<PlatformLedgerDo> entityList);

    PlatformLedgerDo toDoFromImport(PlatformLedgerExcelImport importDTO);

    List<PlatformLedgerDo> toDoFromImportList(List<PlatformLedgerExcelImport> importDTOList);
}
