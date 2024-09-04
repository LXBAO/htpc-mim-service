package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.TimedTaskAddCmd;
import com.uwdp.module.api.vo.cmd.TimedTaskUpdateCmd;
import com.uwdp.module.api.vo.dto.TimedTaskDto;
import com.uwdp.module.api.vo.excel.TimedTaskExcelImport;
import com.uwdp.module.biz.infrastructure.entity.TimedTaskDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TimedTaskAssembler {

    TimedTaskAssembler MAPPER = Mappers.getMapper(TimedTaskAssembler.class);

    TimedTaskDo toDO(TimedTaskAddCmd addCmd);

    TimedTaskDo toDO(TimedTaskUpdateCmd updateCmd);
    
    TimedTaskDto toValueObject(TimedTaskDo entity);

    List<TimedTaskDto> toValueObjectList(List<TimedTaskDo> entityList);

    TimedTaskDo toDoFromImport(TimedTaskExcelImport importDTO);

    List<TimedTaskDo> toDoFromImportList(List<TimedTaskExcelImport> importDTOList);
}
