package com.uwdp.module.biz.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.biz.infrastructure.entity.DBConfigDo;
import com.uwdp.module.biz.infrastructure.mapper.DBConfigMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据库连接配置 服务实现类
 * </p>
 *
 */
@Service
public class DBConfigRepository extends ServiceImpl<DBConfigMapper, DBConfigDo> {

}
