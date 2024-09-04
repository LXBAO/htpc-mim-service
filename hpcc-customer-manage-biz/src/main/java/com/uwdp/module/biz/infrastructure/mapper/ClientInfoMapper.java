package com.uwdp.module.biz.infrastructure.mapper;

import com.uwdp.module.biz.infrastructure.entity.ClientInfoDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户信息总表 Mapper 接口
 * </p>
 *
 */
public interface ClientInfoMapper extends BaseMapper<ClientInfoDo> {
    Integer getClientInfoCount(@Param("fdName") String fdName, @Param("fdUnit") String fdUnit);

}
