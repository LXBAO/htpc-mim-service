package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.ClientInfoDo;

import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.ClientInfoUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientInfoDto;
import com.uwdp.module.api.vo.excel.ClientInfoExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 客户信息总表 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientInfoAssembler {

    ClientInfoAssembler MAPPER = Mappers.getMapper(ClientInfoAssembler.class);

    ClientInfoDo toDO(ClientInfoAddCmd addCmd);

    ClientInfoDo toDO(ClientInfoUpdateCmd updateCmd);
    
    ClientInfoDto toValueObject(ClientInfoDo entity);

    List<ClientInfoDto> toValueObjectList(List<ClientInfoDo> entityList);

    ClientInfoDo toDoFromImport(ClientInfoExcelImport importDTO);

    List<ClientInfoDo> toDoFromImportList(List<ClientInfoExcelImport> importDTOList);
}
