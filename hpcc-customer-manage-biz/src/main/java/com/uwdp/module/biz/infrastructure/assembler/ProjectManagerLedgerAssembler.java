package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ProjectManagerLedgerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectManagerLedgerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectManagerLedgerDto;
import com.uwdp.module.api.vo.excel.ProjectManagerLedgerExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerLedgerDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目经理台账 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectManagerLedgerAssembler {

    ProjectManagerLedgerAssembler MAPPER = Mappers.getMapper(ProjectManagerLedgerAssembler.class);

    ProjectManagerLedgerDo toDO(ProjectManagerLedgerAddCmd addCmd);

    ProjectManagerLedgerDo toDO(ProjectManagerLedgerUpdateCmd updateCmd);
    
    ProjectManagerLedgerDto toValueObject(ProjectManagerLedgerDo entity);

    List<ProjectManagerLedgerDto> toValueObjectList(List<ProjectManagerLedgerDo> entityList);

    ProjectManagerLedgerDo toDoFromImport(ProjectManagerLedgerExcelImport importDTO);

    List<ProjectManagerLedgerDo> toDoFromImportList(List<ProjectManagerLedgerExcelImport> importDTOList);
}
