package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.UpdateViewDo;

import com.uwdp.module.api.vo.cmd.UpdateViewAddCmd;
import com.uwdp.module.api.vo.cmd.UpdateViewUpdateCmd;
import com.uwdp.module.api.vo.dto.UpdateViewDto;
import com.uwdp.module.api.vo.excel.UpdateViewExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 更新查看 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateViewAssembler {

    UpdateViewAssembler MAPPER = Mappers.getMapper(UpdateViewAssembler.class);

    UpdateViewDo toDO(UpdateViewAddCmd addCmd);

    UpdateViewDo toDO(UpdateViewUpdateCmd updateCmd);
    
    UpdateViewDto toValueObject(UpdateViewDo entity);

    List<UpdateViewDto> toValueObjectList(List<UpdateViewDo> entityList);

    UpdateViewDo toDoFromImport(UpdateViewExcelImport importDTO);

    List<UpdateViewDo> toDoFromImportList(List<UpdateViewExcelImport> importDTOList);
}
