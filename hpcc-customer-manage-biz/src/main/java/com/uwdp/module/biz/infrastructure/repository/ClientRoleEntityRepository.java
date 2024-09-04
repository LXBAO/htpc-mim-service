package com.uwdp.module.biz.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.mapper.ClientRoleMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 */
@Service
public class ClientRoleEntityRepository extends ServiceImpl<ClientRoleMapper, ClientRoleEntityDo> {
}
