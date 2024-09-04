package com.uwdp.module.biz.infrastructure.repository;

import com.uwdp.module.biz.infrastructure.entity.RequestLogDo;
import com.uwdp.module.biz.infrastructure.mapper.RequestLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程日志 服务实现类
 * </p>
 *
 */
@Service
public class RequestLogRepository extends ServiceImpl<RequestLogMapper, RequestLogDo> {

}
