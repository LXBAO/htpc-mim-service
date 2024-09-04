package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.RequestLogDo;

import com.uwdp.module.api.vo.cmd.RequestLogAddCmd;
import com.uwdp.module.api.vo.cmd.RequestLogUpdateCmd;
import com.uwdp.module.api.vo.dto.RequestLogDto;
import com.uwdp.module.api.vo.excel.RequestLogExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 流程日志 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RequestLogAssembler {

    RequestLogAssembler MAPPER = Mappers.getMapper(RequestLogAssembler.class);

    RequestLogDo toDO(RequestLogAddCmd addCmd);

    RequestLogDo toDO(RequestLogUpdateCmd updateCmd);
    
    RequestLogDto toValueObject(RequestLogDo entity);

    List<RequestLogDto> toValueObjectList(List<RequestLogDo> entityList);

    RequestLogDo toDoFromImport(RequestLogExcelImport importDTO);

    List<RequestLogDo> toDoFromImportList(List<RequestLogExcelImport> importDTOList);
}
