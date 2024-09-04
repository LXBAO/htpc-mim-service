package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ContractPartyAddCmd;
import com.uwdp.module.api.vo.cmd.ContractPartyUpdateCmd;
import com.uwdp.module.api.vo.dto.ContractPartyDto;
import com.uwdp.module.api.vo.excel.ContractPartyExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ContractPartyDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 合同甲方 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContractPartyAssembler {

    ContractPartyAssembler MAPPER = Mappers.getMapper(ContractPartyAssembler.class);

    ContractPartyDo toDO(ContractPartyAddCmd addCmd);

    ContractPartyDo toDO(ContractPartyUpdateCmd updateCmd);
    
    ContractPartyDto toValueObject(ContractPartyDo entity);

    List<ContractPartyDto> toValueObjectList(List<ContractPartyDo> entityList);

    ContractPartyDo toDoFromImport(ContractPartyExcelImport importDTO);

    List<ContractPartyDo> toDoFromImportList(List<ContractPartyExcelImport> importDTOList);
}
