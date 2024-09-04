package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ProjectChiefEngineerAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectChiefEngineerUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectChiefEngineerDto;
import com.uwdp.module.api.vo.excel.ProjectChiefEngineerExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectChiefEngineerDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目总工 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectChiefEngineerAssembler {

    ProjectChiefEngineerAssembler MAPPER = Mappers.getMapper(ProjectChiefEngineerAssembler.class);

    ProjectChiefEngineerDo toDO(ProjectChiefEngineerAddCmd addCmd);

    ProjectChiefEngineerDo toDO(ProjectChiefEngineerUpdateCmd updateCmd);
    
    ProjectChiefEngineerDto toValueObject(ProjectChiefEngineerDo entity);

    List<ProjectChiefEngineerDto> toValueObjectList(List<ProjectChiefEngineerDo> entityList);

    ProjectChiefEngineerDo toDoFromImport(ProjectChiefEngineerExcelImport importDTO);

    List<ProjectChiefEngineerDo> toDoFromImportList(List<ProjectChiefEngineerExcelImport> importDTOList);
}
