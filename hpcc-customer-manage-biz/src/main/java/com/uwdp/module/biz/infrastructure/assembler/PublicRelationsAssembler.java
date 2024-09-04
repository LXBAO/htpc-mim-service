package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.PublicRelationsDo;

import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsUpdateCmd;
import com.uwdp.module.api.vo.dto.PublicRelationsDto;
import com.uwdp.module.api.vo.excel.PublicRelationsExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 公关实施 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PublicRelationsAssembler {

    PublicRelationsAssembler MAPPER = Mappers.getMapper(PublicRelationsAssembler.class);

    PublicRelationsDo toDO(PublicRelationsAddCmd addCmd);

    PublicRelationsDo toDO(PublicRelationsUpdateCmd updateCmd);
    
    PublicRelationsDto toValueObject(PublicRelationsDo entity);

    List<PublicRelationsDto> toValueObjectList(List<PublicRelationsDo> entityList);

    PublicRelationsDo toDoFromImport(PublicRelationsExcelImport importDTO);

    List<PublicRelationsDo> toDoFromImportList(List<PublicRelationsExcelImport> importDTOList);
}
