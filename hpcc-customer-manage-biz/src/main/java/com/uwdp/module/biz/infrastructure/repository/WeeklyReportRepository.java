package com.uwdp.module.biz.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.biz.infrastructure.entity.WeeklyReportDo;
import com.uwdp.module.biz.infrastructure.mapper.WeeklyReportMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 周报 服务实现类
 * </p>
 *
 */
@Service
public class WeeklyReportRepository extends ServiceImpl<WeeklyReportMapper, WeeklyReportDo> {

}
