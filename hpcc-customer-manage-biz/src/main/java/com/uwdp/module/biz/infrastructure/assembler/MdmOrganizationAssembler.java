package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.MdmOrganizationDo;

import com.uwdp.module.api.vo.cmd.MdmOrganizationAddCmd;
import com.uwdp.module.api.vo.cmd.MdmOrganizationUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import com.uwdp.module.api.vo.excel.MdmOrganizationExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 主数据-组织 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MdmOrganizationAssembler {

    MdmOrganizationAssembler MAPPER = Mappers.getMapper(MdmOrganizationAssembler.class);

    MdmOrganizationDo toDO(MdmOrganizationAddCmd addCmd);

    MdmOrganizationDo toDO(MdmOrganizationUpdateCmd updateCmd);
    
    MdmOrganizationDto toValueObject(MdmOrganizationDo entity);

    List<MdmOrganizationDto> toValueObjectList(List<MdmOrganizationDo> entityList);

    MdmOrganizationDo toDoFromImport(MdmOrganizationExcelImport importDTO);

    List<MdmOrganizationDo> toDoFromImportList(List<MdmOrganizationExcelImport> importDTOList);
}
