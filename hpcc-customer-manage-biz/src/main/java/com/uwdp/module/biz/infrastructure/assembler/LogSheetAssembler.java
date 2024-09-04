package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.LogSheetAddCmd;
import com.uwdp.module.api.vo.cmd.LogSheetUpdateCmd;
import com.uwdp.module.api.vo.dto.LogSheetDto;
import com.uwdp.module.api.vo.excel.LogSheetExcelImport;
import com.uwdp.module.biz.infrastructure.entity.LogSheetDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 历史记录表 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LogSheetAssembler {

    LogSheetAssembler MAPPER = Mappers.getMapper(LogSheetAssembler.class);

    LogSheetDo toDO(LogSheetAddCmd addCmd);

    LogSheetDo toDO(LogSheetUpdateCmd updateCmd);
    
    LogSheetDto toValueObject(LogSheetDo entity);

    List<LogSheetDto> toValueObjectList(List<LogSheetDo> entityList);

    LogSheetDo toDoFromImport(LogSheetExcelImport importDTO);

    List<LogSheetDo> toDoFromImportList(List<LogSheetExcelImport> importDTOList);
}
