package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.UpdateViewDetailAddCmd;
import com.uwdp.module.api.vo.cmd.UpdateViewDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.UpdateViewDetailDto;
import com.uwdp.module.api.vo.excel.UpdateViewDetailExcelImport;
import com.uwdp.module.biz.infrastructure.entity.UpdateViewDetailDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 更新查看详情 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateViewDetailAssembler {

    UpdateViewDetailAssembler MAPPER = Mappers.getMapper(UpdateViewDetailAssembler.class);

    UpdateViewDetailDo toDO(UpdateViewDetailAddCmd addCmd);

    UpdateViewDetailDo toDO(UpdateViewDetailUpdateCmd updateCmd);
    
    UpdateViewDetailDto toValueObject(UpdateViewDetailDo entity);

    List<UpdateViewDetailDto> toValueObjectList(List<UpdateViewDetailDo> entityList);

    UpdateViewDetailDo toDoFromImport(UpdateViewDetailExcelImport importDTO);

    List<UpdateViewDetailDo> toDoFromImportList(List<UpdateViewDetailExcelImport> importDTOList);
}
