package com.uwdp.module.biz.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uwdp.module.api.vo.dto.ClientRoleEntityDto;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author qis
 * @email qis@gmail.com
 * @date 2023-06-28 15:34:38
 */
@Mapper
public interface ClientRoleMapper extends BaseMapper<ClientRoleEntityDo> {
	
}
