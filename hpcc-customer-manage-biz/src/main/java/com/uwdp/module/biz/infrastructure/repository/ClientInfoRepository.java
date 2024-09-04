package com.uwdp.module.biz.infrastructure.repository;

import com.uwdp.module.biz.infrastructure.entity.ClientInfoDo;
import com.uwdp.module.biz.infrastructure.mapper.ClientInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.biz.infrastructure.mapper.ProjectRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户信息总表 服务实现类
 * </p>
 *
 */
@Service
public class ClientInfoRepository extends ServiceImpl<ClientInfoMapper, ClientInfoDo> {
    @Autowired
    private ClientInfoMapper clientInfoMapper;

    public Integer getClientInfoCount(String fdName,String fdUnit) {
        return clientInfoMapper.getClientInfoCount(fdName, fdUnit);
    }
}
