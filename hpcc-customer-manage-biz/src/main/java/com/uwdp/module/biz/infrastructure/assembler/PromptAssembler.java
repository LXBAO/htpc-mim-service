package com.uwdp.module.biz.infrastructure.assembler;

import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.excel.PromptExcelImport;
import com.uwdp.module.biz.infrastructure.entity.PromptDo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 信息提示 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromptAssembler {

    PromptAssembler MAPPER = Mappers.getMapper(PromptAssembler.class);

    PromptDo toDO(PromptAddCmd addCmd);

    PromptDo toDO(PromptUpdateCmd updateCmd);
    
    PromptDto toValueObject(PromptDo entity);

    List<PromptDto> toValueObjectList(List<PromptDo> entityList);

    PromptDo toDoFromImport(PromptExcelImport importDTO);

    List<PromptDo> toDoFromImportList(List<PromptExcelImport> importDTOList);
}
