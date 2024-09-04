package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ForeignAddressAddCmd;
import com.uwdp.module.api.vo.cmd.ForeignAddressUpdateCmd;
import com.uwdp.module.api.vo.dto.ForeignAddressDto;
import com.uwdp.module.api.vo.excel.ForeignAddressExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ForeignAddressDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 国外地址 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ForeignAddressAssembler {

    ForeignAddressAssembler MAPPER = Mappers.getMapper(ForeignAddressAssembler.class);

    ForeignAddressDo toDO(ForeignAddressAddCmd addCmd);

    ForeignAddressDo toDO(ForeignAddressUpdateCmd updateCmd);
    
    ForeignAddressDto toValueObject(ForeignAddressDo entity);

    List<ForeignAddressDto> toValueObjectList(List<ForeignAddressDo> entityList);

    ForeignAddressDo toDoFromImport(ForeignAddressExcelImport importDTO);

    List<ForeignAddressDo> toDoFromImportList(List<ForeignAddressExcelImport> importDTOList);
}
