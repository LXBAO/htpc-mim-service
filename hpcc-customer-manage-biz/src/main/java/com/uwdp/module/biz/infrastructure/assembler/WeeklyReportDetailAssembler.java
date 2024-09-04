package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.WeeklyReportDetailAddCmd;
import com.uwdp.module.api.vo.cmd.WeeklyReportDetailUpdateCmd;
import com.uwdp.module.api.vo.dto.WeeklyReportDetailDto;
import com.uwdp.module.api.vo.excel.WeeklyReportDetailExcelImport;
import com.uwdp.module.biz.infrastructure.entity.WeeklyReportDetailDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 周报明细表 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WeeklyReportDetailAssembler {

    WeeklyReportDetailAssembler MAPPER = Mappers.getMapper(WeeklyReportDetailAssembler.class);

    WeeklyReportDetailDo toDO(WeeklyReportDetailAddCmd addCmd);

    WeeklyReportDetailDo toDO(WeeklyReportDetailUpdateCmd updateCmd);
    
    WeeklyReportDetailDto toValueObject(WeeklyReportDetailDo entity);

    List<WeeklyReportDetailDto> toValueObjectList(List<WeeklyReportDetailDo> entityList);

    WeeklyReportDetailDo toDoFromImport(WeeklyReportDetailExcelImport importDTO);

    List<WeeklyReportDetailDo> toDoFromImportList(List<WeeklyReportDetailExcelImport> importDTOList);
}
