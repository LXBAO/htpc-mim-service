package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.ProjectBiddingAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectBiddingUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectBiddingDto;
import com.uwdp.module.api.vo.excel.ProjectBiddingExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectBiddingDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 项目投标 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectBiddingAssembler {

    ProjectBiddingAssembler MAPPER = Mappers.getMapper(ProjectBiddingAssembler.class);

    ProjectBiddingDo toDO(ProjectBiddingAddCmd addCmd);

    ProjectBiddingDo toDO(ProjectBiddingUpdateCmd updateCmd);
    
    ProjectBiddingDto toValueObject(ProjectBiddingDo entity);

    List<ProjectBiddingDto> toValueObjectList(List<ProjectBiddingDo> entityList);

    ProjectBiddingDo toDoFromImport(ProjectBiddingExcelImport importDTO);

    List<ProjectBiddingDo> toDoFromImportList(List<ProjectBiddingExcelImport> importDTOList);
}
