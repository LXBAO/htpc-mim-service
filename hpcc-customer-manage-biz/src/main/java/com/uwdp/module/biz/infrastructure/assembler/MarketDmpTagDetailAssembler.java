package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.MarketDmpTagDetailAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDetailDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagDetailExcelImport;
import com.uwdp.module.biz.infrastructure.entity.MarketDmpTagDetailDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 指标明细 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MarketDmpTagDetailAssembler {

    MarketDmpTagDetailAssembler MAPPER = Mappers.getMapper(MarketDmpTagDetailAssembler.class);

    MarketDmpTagDetailDo toDO(MarketDmpTagDetailAddCmd addCmd);

    MarketDmpTagDetailDo toDO(MarketDmpTagDetailUpdateCmd updateCmd);
    
    MarketDmpTagDetailDto toValueObject(MarketDmpTagDetailDo entity);

    List<MarketDmpTagDetailDto> toValueObjectList(List<MarketDmpTagDetailDo> entityList);

    MarketDmpTagDetailDo toDoFromImport(MarketDmpTagDetailExcelImport importDTO);

    List<MarketDmpTagDetailDo> toDoFromImportList(List<MarketDmpTagDetailExcelImport> importDTOList);
}
