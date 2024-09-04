package com.uwdp.module.biz.infrastructure.assembler;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.uwdp.module.api.vo.cmd.ProjectEnableAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectEnableDto;
import com.uwdp.module.api.vo.excel.ProjectEnableExcelImport;
import com.uwdp.module.biz.infrastructure.entity.ProjectEnableDo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import com.alibaba.fastjson2.JSON;
import com.uwdp.module.api.vo.enums.ScoreTableFields;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目赋能 服务实现类
 * </p>
 *
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {JSON.class,ScoreTableFields.class, JSONObject.class, Map.class, TypeReference.class})
public interface ProjectEnableAssembler {

    ProjectEnableAssembler MAPPER = Mappers.getMapper(ProjectEnableAssembler.class);

    @Mapping(target = "scoreList",expression = "java(JSON.toJSONString(addCmd.getScoreList()))")
    ProjectEnableDo toDO(ProjectEnableAddCmd addCmd);

    @Mapping(target = "scoreList",expression = "java(JSON.toJSONString(updateCmd.getScoreList()))")
    ProjectEnableDo toDO(ProjectEnableUpdateCmd updateCmd);

    @Mapping(target = "scoreList",expression = "java(JSON.toJSONString(projectEnableDto.getScoreList()))")
    ProjectEnableDo toDO(ProjectEnableDto projectEnableDto);

    @Mapping(target = "scoreList",expression = "java(JSONObject.parseObject(entity.getScoreList(), new TypeReference<List<Map<ScoreTableFields, String>>>() {}))")
    ProjectEnableDto toValueObject(ProjectEnableDo entity);

    @Mapping(target = "scoreList",expression = "java(JSONObject.parseObject(entity.getScoreList(), new TypeReference<List<Map<ScoreTableFields, String>>>() {}))")
    List<ProjectEnableDto> toValueObjectList(List<ProjectEnableDo> entityList);

    ProjectEnableDo toDoFromImport(ProjectEnableExcelImport importDTO);

    List<ProjectEnableDo> toDoFromImportList(List<ProjectEnableExcelImport> importDTOList);
}
