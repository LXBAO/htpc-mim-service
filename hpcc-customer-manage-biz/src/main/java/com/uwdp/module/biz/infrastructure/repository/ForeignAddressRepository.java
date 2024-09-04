package com.uwdp.module.biz.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.biz.infrastructure.entity.ForeignAddressDo;
import com.uwdp.module.biz.infrastructure.mapper.ForeignAddressMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 国外地址 服务实现类
 * </p>
 *
 */
@Service
public class ForeignAddressRepository extends ServiceImpl<ForeignAddressMapper, ForeignAddressDo> {

}
