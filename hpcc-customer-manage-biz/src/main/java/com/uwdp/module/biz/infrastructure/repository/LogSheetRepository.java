package com.uwdp.module.biz.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.biz.infrastructure.entity.LogSheetDo;
import com.uwdp.module.biz.infrastructure.mapper.LogSheetMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 历史记录表 服务实现类
 * </p>
 *
 */
@Service
public class LogSheetRepository extends ServiceImpl<LogSheetMapper, LogSheetDo> {

}
