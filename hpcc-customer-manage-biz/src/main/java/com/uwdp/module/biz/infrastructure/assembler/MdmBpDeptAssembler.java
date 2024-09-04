package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.MdmBpDeptDo;

import com.uwdp.module.api.vo.cmd.MdmBpDeptAddCmd;
import com.uwdp.module.api.vo.cmd.MdmBpDeptUpdateCmd;
import com.uwdp.module.api.vo.dto.MdmBpDeptDto;
import com.uwdp.module.api.vo.excel.MdmBpDeptExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 主数据-业务编码所对应部门 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MdmBpDeptAssembler {

    MdmBpDeptAssembler MAPPER = Mappers.getMapper(MdmBpDeptAssembler.class);

    MdmBpDeptDo toDO(MdmBpDeptAddCmd addCmd);

    MdmBpDeptDo toDO(MdmBpDeptUpdateCmd updateCmd);
    
    MdmBpDeptDto toValueObject(MdmBpDeptDo entity);

    List<MdmBpDeptDto> toValueObjectList(List<MdmBpDeptDo> entityList);

    MdmBpDeptDo toDoFromImport(MdmBpDeptExcelImport importDTO);

    List<MdmBpDeptDo> toDoFromImportList(List<MdmBpDeptExcelImport> importDTOList);
}
