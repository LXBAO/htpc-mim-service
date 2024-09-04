package com.uwdp.module.biz.infrastructure.repository;

import com.uwdp.module.biz.infrastructure.entity.ProjectRecordsDo;
import com.uwdp.module.biz.infrastructure.mapper.DataMapper;
import com.uwdp.module.biz.infrastructure.mapper.ProjectRecordsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目登记 服务实现类
 * </p>
 *
 */
@Service
public class ProjectRecordsRepository extends ServiceImpl<ProjectRecordsMapper, ProjectRecordsDo> {

    @Autowired
    private ProjectRecordsMapper projectRecordsMapper;

    public Integer getProjectCount(String projectName, String location,String ownerUnit, String industryCategor) {
        return projectRecordsMapper.getProjectCount(projectName, location, ownerUnit, industryCategor);
    }
}
