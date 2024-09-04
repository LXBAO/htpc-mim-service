package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.SupervisionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.SupervisionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.SupervisionUnitDto;
import com.uwdp.module.api.vo.excel.SupervisionUnitExcelImport;
import com.uwdp.module.biz.infrastructure.entity.SupervisionUnitDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 监理单位 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SupervisionUnitAssembler {

    SupervisionUnitAssembler MAPPER = Mappers.getMapper(SupervisionUnitAssembler.class);

    SupervisionUnitDo toDO(SupervisionUnitAddCmd addCmd);

    SupervisionUnitDo toDO(SupervisionUnitUpdateCmd updateCmd);
    
    SupervisionUnitDto toValueObject(SupervisionUnitDo entity);

    List<SupervisionUnitDto> toValueObjectList(List<SupervisionUnitDo> entityList);

    SupervisionUnitDo toDoFromImport(SupervisionUnitExcelImport importDTO);

    List<SupervisionUnitDo> toDoFromImportList(List<SupervisionUnitExcelImport> importDTOList);
}
