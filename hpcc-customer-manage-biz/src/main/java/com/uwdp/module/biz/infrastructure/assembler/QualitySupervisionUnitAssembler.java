package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.QualitySupervisionUnitAddCmd;
import com.uwdp.module.api.vo.cmd.QualitySupervisionUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.QualitySupervisionUnitDto;
import com.uwdp.module.api.vo.excel.QualitySupervisionUnitExcelImport;
import com.uwdp.module.biz.infrastructure.entity.QualitySupervisionUnitDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 质量监督单位 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QualitySupervisionUnitAssembler {

    QualitySupervisionUnitAssembler MAPPER = Mappers.getMapper(QualitySupervisionUnitAssembler.class);

    QualitySupervisionUnitDo toDO(QualitySupervisionUnitAddCmd addCmd);

    QualitySupervisionUnitDo toDO(QualitySupervisionUnitUpdateCmd updateCmd);
    
    QualitySupervisionUnitDto toValueObject(QualitySupervisionUnitDo entity);

    List<QualitySupervisionUnitDto> toValueObjectList(List<QualitySupervisionUnitDo> entityList);

    QualitySupervisionUnitDo toDoFromImport(QualitySupervisionUnitExcelImport importDTO);

    List<QualitySupervisionUnitDo> toDoFromImportList(List<QualitySupervisionUnitExcelImport> importDTOList);
}
