package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.biz.infrastructure.entity.CertificatesDo;

import com.uwdp.module.api.vo.cmd.CertificatesAddCmd;
import com.uwdp.module.api.vo.cmd.CertificatesUpdateCmd;
import com.uwdp.module.api.vo.dto.CertificatesDto;
import com.uwdp.module.api.vo.excel.CertificatesExcelImport;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <p>
 * 荣誉证书 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CertificatesAssembler {

    CertificatesAssembler MAPPER = Mappers.getMapper(CertificatesAssembler.class);

    CertificatesDo toDO(CertificatesAddCmd addCmd);

    CertificatesDo toDO(CertificatesUpdateCmd updateCmd);
    
    CertificatesDto toValueObject(CertificatesDo entity);

    List<CertificatesDto> toValueObjectList(List<CertificatesDo> entityList);

    CertificatesDo toDoFromImport(CertificatesExcelImport importDTO);

    List<CertificatesDo> toDoFromImportList(List<CertificatesExcelImport> importDTOList);
}
