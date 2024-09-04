package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.MdmPostDo;

import com.uwdp.module.api.vo.cmd.MdmPostAddCmd;
import com.uwdp.module.api.vo.cmd.MdmPostUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmPostDto;
import com.uwdp.module.api.vo.excel.MdmPostExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 主数据-岗位 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MdmPostAssembler {

    MdmPostAssembler MAPPER = Mappers.getMapper(MdmPostAssembler.class);

    MdmPostDo toDO(MdmPostAddCmd addCmd);

    MdmPostDo toDO(MdmPostUpdateCmd updateCmd);
    
    MdmPostDto toValueObject(MdmPostDo entity);

    List<MdmPostDto> toValueObjectList(List<MdmPostDo> entityList);

    MdmPostDo toDoFromImport(MdmPostExcelImport importDTO);

    List<MdmPostDo> toDoFromImportList(List<MdmPostExcelImport> importDTOList);
}
