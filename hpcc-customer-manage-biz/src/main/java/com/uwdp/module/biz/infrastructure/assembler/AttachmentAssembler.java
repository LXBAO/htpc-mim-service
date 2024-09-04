package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.AttachmentDo;

import com.uwdp.module.api.vo.cmd.AttachmentAddCmd;
import com.uwdp.module.api.vo.cmd.AttachmentUpdateCmd;
import com.uwdp.module.api.vo.dto.AttachmentDto;
import com.uwdp.module.api.vo.excel.AttachmentExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 附件表 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AttachmentAssembler {

    AttachmentAssembler MAPPER = Mappers.getMapper(AttachmentAssembler.class);

    AttachmentDo toDO(AttachmentAddCmd addCmd);

    AttachmentDo toDO(AttachmentUpdateCmd updateCmd);
    
    AttachmentDto toValueObject(AttachmentDo entity);

    List<AttachmentDto> toValueObjectList(List<AttachmentDo> entityList);

    AttachmentDo toDoFromImport(AttachmentExcelImport importDTO);

    List<AttachmentDo> toDoFromImportList(List<AttachmentExcelImport> importDTOList);
}
