package com.uwdp.module.biz.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uwdp.module.api.vo.dto.searcher.ProjectTrackingWorkflowDto;
import com.uwdp.module.biz.infrastructure.entity.ProjectTrackingDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目跟踪 Mapper 接口
 * </p>
 *
 */
public interface ProjectTrackingMapper extends BaseMapper<ProjectTrackingDo> {

    List<ProjectTrackingWorkflowDto> pageAll(Map<String, Object> params);

}
