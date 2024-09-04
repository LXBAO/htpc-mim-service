package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ConstructionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.ConstructionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.ConstructionUnitDto;
import com.uwdp.module.api.vo.excel.ConstructionUnitExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ConstructionUnitDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 建设单位 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ConstructionUnitAssembler {

    ConstructionUnitAssembler MAPPER = Mappers.getMapper(ConstructionUnitAssembler.class);

    ConstructionUnitDo toDO(ConstructionUnitAddCmd addCmd);

    ConstructionUnitDo toDO(ConstructionUnitUpdateCmd updateCmd);
    
    ConstructionUnitDto toValueObject(ConstructionUnitDo entity);

    List<ConstructionUnitDto> toValueObjectList(List<ConstructionUnitDo> entityList);

    ConstructionUnitDo toDoFromImport(ConstructionUnitExcelImport importDTO);

    List<ConstructionUnitDo> toDoFromImportList(List<ConstructionUnitExcelImport> importDTOList);
}
