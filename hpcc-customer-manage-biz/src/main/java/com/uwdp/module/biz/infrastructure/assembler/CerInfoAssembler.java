package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.CerInfoAddCmd;
import com.uwdp.module.api.vo.cmd.CerInfoUpdateCmd;
import com.uwdp.module.api.vo.dto.CerInfoDto;
import com.uwdp.module.api.vo.excel.CerInfoExcelImport;
import com.uwdp.module.biz.infrastructure.entity.CerInfoDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 证书信息 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CerInfoAssembler {

    CerInfoAssembler MAPPER = Mappers.getMapper(CerInfoAssembler.class);

    CerInfoDo toDO(CerInfoAddCmd addCmd);

    CerInfoDo toDO(CerInfoUpdateCmd updateCmd);
    
    CerInfoDto toValueObject(CerInfoDo entity);

    List<CerInfoDto> toValueObjectList(List<CerInfoDo> entityList);

    CerInfoDo toDoFromImport(CerInfoExcelImport importDTO);

    List<CerInfoDo> toDoFromImportList(List<CerInfoExcelImport> importDTOList);
}
