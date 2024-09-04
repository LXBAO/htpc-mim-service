package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.DBConfigAddCmd;
import com.uwdp.module.api.vo.cmd.DBConfigUpdateCmd;
import com.uwdp.module.api.vo.dto.DBConfigDto;
import com.uwdp.module.api.vo.excel.DBConfigExcelImport;
import com.uwdp.module.biz.infrastructure.entity.DBConfigDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 数据库连接配置 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DBConfigAssembler {

    DBConfigAssembler MAPPER = Mappers.getMapper(DBConfigAssembler.class);

    DBConfigDo toDO(DBConfigAddCmd addCmd);

    DBConfigDo toDO(DBConfigUpdateCmd updateCmd);
    
    DBConfigDto toValueObject(DBConfigDo entity);

    List<DBConfigDto> toValueObjectList(List<DBConfigDo> entityList);

    DBConfigDo toDoFromImport(DBConfigExcelImport importDTO);

    List<DBConfigDo> toDoFromImportList(List<DBConfigExcelImport> importDTOList);
}
