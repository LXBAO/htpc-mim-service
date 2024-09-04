package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.MarketDmpTagAddCmd;
import com.uwdp.module.api.vo.cmd.MarketDmpTagUpdateCmd;
import com.uwdp.module.api.vo.dto.MarketDmpTagDto;
import com.uwdp.module.api.vo.excel.MarketDmpTagExcelImport;
import com.uwdp.module.biz.infrastructure.entity.MarketDmpTagDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 市场部分公司年度指标 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MarketDmpTagAssembler {

    MarketDmpTagAssembler MAPPER = Mappers.getMapper(MarketDmpTagAssembler.class);

    MarketDmpTagDo toDO(MarketDmpTagAddCmd addCmd);

    MarketDmpTagDo toDO(MarketDmpTagUpdateCmd updateCmd);
    
    MarketDmpTagDto toValueObject(MarketDmpTagDo entity);

    List<MarketDmpTagDto> toValueObjectList(List<MarketDmpTagDo> entityList);

    MarketDmpTagDo toDoFromImport(MarketDmpTagExcelImport importDTO);

    List<MarketDmpTagDo> toDoFromImportList(List<MarketDmpTagExcelImport> importDTOList);
}
