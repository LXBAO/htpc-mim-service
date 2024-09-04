package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.WinTheBidAddCmd;
import com.uwdp.module.api.vo.cmd.WinTheBidUpdateCmd;
import com.uwdp.module.api.vo.dto.WinTheBidDto;
import com.uwdp.module.api.vo.excel.WinTheBidExcelImport;
import com.uwdp.module.biz.infrastructure.entity.WinTheBidDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目中标 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WinTheBidAssembler {

    WinTheBidAssembler MAPPER = Mappers.getMapper(WinTheBidAssembler.class);

    WinTheBidDo toDO(WinTheBidAddCmd addCmd);

    WinTheBidDo toDO(WinTheBidUpdateCmd updateCmd);
    
    WinTheBidDto toValueObject(WinTheBidDo entity);

    List<WinTheBidDto> toValueObjectList(List<WinTheBidDo> entityList);

    WinTheBidDo toDoFromImport(WinTheBidExcelImport importDTO);

    List<WinTheBidDo> toDoFromImportList(List<WinTheBidExcelImport> importDTOList);
}
