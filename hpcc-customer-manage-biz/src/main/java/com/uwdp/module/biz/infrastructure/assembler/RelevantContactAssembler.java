package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.RelevantContactDo;

import com.uwdp.module.api.vo.cmd.RelevantContactAddCmd;
import com.uwdp.module.api.vo.cmd.RelevantContactUpdateCmd;
import com.uwdp.module.api.vo.dto.RelevantContactDto;
import com.uwdp.module.api.vo.excel.RelevantContactExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 客户相关联系人 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RelevantContactAssembler {

    RelevantContactAssembler MAPPER = Mappers.getMapper(RelevantContactAssembler.class);

    RelevantContactDo toDO(RelevantContactAddCmd addCmd);

    RelevantContactDo toDO(RelevantContactUpdateCmd updateCmd);
    
    RelevantContactDto toValueObject(RelevantContactDo entity);

    List<RelevantContactDto> toValueObjectList(List<RelevantContactDo> entityList);

    RelevantContactDo toDoFromImport(RelevantContactExcelImport importDTO);

    List<RelevantContactDo> toDoFromImportList(List<RelevantContactExcelImport> importDTOList);
}
