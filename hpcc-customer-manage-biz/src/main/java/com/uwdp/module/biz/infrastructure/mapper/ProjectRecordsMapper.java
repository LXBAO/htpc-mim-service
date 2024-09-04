package com.uwdp.module.biz.infrastructure.mapper;

import com.uwdp.module.biz.infrastructure.entity.ProjectRecordsDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 项目登记 Mapper 接口
 * </p>
 *
 */
public interface ProjectRecordsMapper extends BaseMapper<ProjectRecordsDo> {
    Integer getProjectCount(@Param("projectName") String projectName, @Param("location") String location, @Param("ownerUnit") String ownerUnit, @Param("industryCategory") String industryCategory);
}
