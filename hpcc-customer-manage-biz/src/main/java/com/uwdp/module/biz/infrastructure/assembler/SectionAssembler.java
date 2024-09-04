package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.SectionAddCmd;
import com.uwdp.module.api.vo.cmd.SectionUpdateCmd;
import com.uwdp.module.api.vo.dto.SectionDto;
import com.uwdp.module.api.vo.excel.SectionExcelImport;
import com.uwdp.module.biz.infrastructure.entity.SectionDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 标段 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SectionAssembler {

    SectionAssembler MAPPER = Mappers.getMapper(SectionAssembler.class);

    SectionDo toDO(SectionAddCmd addCmd);

    SectionDo toDO(SectionUpdateCmd updateCmd);
    
    SectionDto toValueObject(SectionDo entity);

    List<SectionDto> toValueObjectList(List<SectionDo> entityList);

    SectionDo toDoFromImport(SectionExcelImport importDTO);

    List<SectionDo> toDoFromImportList(List<SectionExcelImport> importDTOList);
}
