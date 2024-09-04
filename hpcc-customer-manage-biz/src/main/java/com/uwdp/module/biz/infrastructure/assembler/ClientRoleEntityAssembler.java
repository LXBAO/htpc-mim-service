package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ClientRoleEntityAddCmd;
import com.uwdp.module.api.vo.cmd.ClientRoleEntityUpdateCmd;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.api.vo.excel.ClientRoleEntityExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientRoleEntityAssembler {

    ClientRoleEntityAssembler MAPPER = Mappers.getMapper(ClientRoleEntityAssembler.class);

    ClientRoleEntityDo toDO(ClientRoleEntityAddCmd addCmd);

    ClientRoleEntityDo toDO(ClientRoleEntityUpdateCmd updateCmd);
    
    ClientRoleEntityDto toValueObject(ClientRoleEntityDo entity);

    List<ClientRoleEntityDto> toValueObjectList(List<ClientRoleEntityDo> entityList);

    ClientRoleEntityDo toDoFromImport(ClientRoleEntityExcelImport importDTO);

    List<ClientRoleEntityDo> toDoFromImportList(List<ClientRoleEntityExcelImport> importDTOList);
}
