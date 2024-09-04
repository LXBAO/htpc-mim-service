package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.WeeklyReportAddCmd;
import com.uwdp.module.api.vo.cmd.WeeklyReportUpdateCmd;
import com.uwdp.module.api.vo.dto.WeeklyReportDto;
import com.uwdp.module.api.vo.excel.WeeklyReportExcelImport;
import com.uwdp.module.biz.infrastructure.entity.WeeklyReportDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 周报主表信息 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WeeklyReportAssembler {

    WeeklyReportAssembler MAPPER = Mappers.getMapper(WeeklyReportAssembler.class);

    WeeklyReportDo toDO(WeeklyReportAddCmd addCmd);

    WeeklyReportDo toDO(WeeklyReportUpdateCmd updateCmd);
    
    WeeklyReportDto toValueObject(WeeklyReportDo entity);

    List<WeeklyReportDto> toValueObjectList(List<WeeklyReportDo> entityList);

    WeeklyReportDo toDoFromImport(WeeklyReportExcelImport importDTO);

    List<WeeklyReportDo> toDoFromImportList(List<WeeklyReportExcelImport> importDTOList);
}
