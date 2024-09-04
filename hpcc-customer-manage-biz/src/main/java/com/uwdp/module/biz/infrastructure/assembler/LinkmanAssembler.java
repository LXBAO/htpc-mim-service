package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.LinkmanAddCmd;
import com.uwdp.module.api.vo.cmd.LinkmanUpdateCmd;
import com.uwdp.module.api.vo.dto.LinkmanDto;
import com.uwdp.module.api.vo.excel.LinkmanExcelImport;
import com.uwdp.module.biz.infrastructure.entity.LinkmanDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 联系人 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LinkmanAssembler {

    LinkmanAssembler MAPPER = Mappers.getMapper(LinkmanAssembler.class);

    LinkmanDo toDO(LinkmanAddCmd addCmd);

    LinkmanDo toDO(LinkmanUpdateCmd updateCmd);
    
    LinkmanDto toValueObject(LinkmanDo entity);

    List<LinkmanDto> toValueObjectList(List<LinkmanDo> entityList);

    LinkmanDo toDoFromImport(LinkmanExcelImport importDTO);

    List<LinkmanDo> toDoFromImportList(List<LinkmanExcelImport> importDTOList);
}
