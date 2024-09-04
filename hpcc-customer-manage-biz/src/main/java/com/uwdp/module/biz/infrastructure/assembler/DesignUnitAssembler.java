package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.DesignUnitAddCmd;
import com.uwdp.module.api.vo.cmd.DesignUnitUpdateCmd;
import com.uwdp.module.api.vo.dto.DesignUnitDto;
import com.uwdp.module.api.vo.excel.DesignUnitExcelImport;
import com.uwdp.module.biz.infrastructure.entity.DesignUnitDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 设计单位 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DesignUnitAssembler {

    DesignUnitAssembler MAPPER = Mappers.getMapper(DesignUnitAssembler.class);

    DesignUnitDo toDO(DesignUnitAddCmd addCmd);

    DesignUnitDo toDO(DesignUnitUpdateCmd updateCmd);
    
    DesignUnitDto toValueObject(DesignUnitDo entity);

    List<DesignUnitDto> toValueObjectList(List<DesignUnitDo> entityList);

    DesignUnitDo toDoFromImport(DesignUnitExcelImport importDTO);

    List<DesignUnitDo> toDoFromImportList(List<DesignUnitExcelImport> importDTOList);
}
