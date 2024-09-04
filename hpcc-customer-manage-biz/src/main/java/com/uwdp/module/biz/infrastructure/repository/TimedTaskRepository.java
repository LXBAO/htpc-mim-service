package com.uwdp.module.biz.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.biz.infrastructure.entity.TimedTaskDo;
import com.uwdp.module.biz.infrastructure.mapper.TimedTaskMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 */
@Service
public class TimedTaskRepository extends ServiceImpl<TimedTaskMapper, TimedTaskDo> {

}
