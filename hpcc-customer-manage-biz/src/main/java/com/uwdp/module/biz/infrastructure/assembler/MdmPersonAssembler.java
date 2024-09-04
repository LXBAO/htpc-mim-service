package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;

import com.uwdp.module.api.vo.cmd.MdmPersonAddCmd;
import com.uwdp.module.api.vo.cmd.MdmPersonUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmPersonDto;
import com.uwdp.module.api.vo.excel.MdmPersonExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 主数据-人员 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MdmPersonAssembler {

    MdmPersonAssembler MAPPER = Mappers.getMapper(MdmPersonAssembler.class);

    MdmPersonDo toDO(MdmPersonAddCmd addCmd);

    MdmPersonDo toDO(MdmPersonUpdateCmd updateCmd);
    
    MdmPersonDto toValueObject(MdmPersonDo entity);

    List<MdmPersonDto> toValueObjectList(List<MdmPersonDo> entityList);

    MdmPersonDo toDoFromImport(MdmPersonExcelImport importDTO);

    List<MdmPersonDo> toDoFromImportList(List<MdmPersonExcelImport> importDTOList);
}
