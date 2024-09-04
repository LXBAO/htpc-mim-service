package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.EnterpriseCheckRecordDo;

import com.uwdp.module.api.vo.cmd.EnterpriseCheckRecordAddCmd;
import com.uwdp.module.api.vo.cmd.EnterpriseCheckRecordUpdateCmd;
import com.uwdp.module.api.vo.dto.EnterpriseCheckRecordDto;
import com.uwdp.module.api.vo.excel.EnterpriseCheckRecordExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 企查查记录 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EnterpriseCheckRecordAssembler {

    EnterpriseCheckRecordAssembler MAPPER = Mappers.getMapper(EnterpriseCheckRecordAssembler.class);

    EnterpriseCheckRecordDo toDO(EnterpriseCheckRecordAddCmd addCmd);

    EnterpriseCheckRecordDo toDO(EnterpriseCheckRecordUpdateCmd updateCmd);
    
    EnterpriseCheckRecordDto toValueObject(EnterpriseCheckRecordDo entity);

    List<EnterpriseCheckRecordDto> toValueObjectList(List<EnterpriseCheckRecordDo> entityList);

    EnterpriseCheckRecordDo toDoFromImport(EnterpriseCheckRecordExcelImport importDTO);

    List<EnterpriseCheckRecordDo> toDoFromImportList(List<EnterpriseCheckRecordExcelImport> importDTOList);
}
