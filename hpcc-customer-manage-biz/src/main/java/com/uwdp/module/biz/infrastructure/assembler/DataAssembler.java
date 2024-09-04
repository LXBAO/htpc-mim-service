package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.DataDo;

import com.uwdp.module.api.vo.cmd.DataAddCmd;
import com.uwdp.module.api.vo.cmd.DataUpdateCmd;
import com.uwdp.module.api.vo.dto.DataDto;
import com.uwdp.module.api.vo.excel.DataExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 下拉列表维护 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DataAssembler {

    DataAssembler MAPPER = Mappers.getMapper(DataAssembler.class);

    DataDo toDO(DataAddCmd addCmd);

    DataDo toDO(DataUpdateCmd updateCmd);
    
    DataDto toValueObject(DataDo entity);

    List<DataDto> toValueObjectList(List<DataDo> entityList);

    DataDo toDoFromImport(DataExcelImport importDTO);

    List<DataDo> toDoFromImportList(List<DataExcelImport> importDTOList);
}
