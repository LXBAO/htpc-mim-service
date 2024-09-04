package com.uwdp.module.biz.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerDo;
import com.uwdp.module.biz.infrastructure.mapper.ProjectManagerMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目经理 服务实现类
 * </p>
 *
 */
@Service
public class ProjectManagerRepository extends ServiceImpl<ProjectManagerMapper, ProjectManagerDo> {

}
