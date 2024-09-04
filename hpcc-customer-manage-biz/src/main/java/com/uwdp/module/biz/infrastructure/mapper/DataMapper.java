package com.uwdp.module.biz.infrastructure.mapper;

import com.uwdp.module.biz.infrastructure.entity.DataDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 下拉列表维护 Mapper 接口
 * </p>
 *
 */
public interface DataMapper extends BaseMapper<DataDo> {

    Integer getMaxRemark(@Param("type") int type, @Param("typeName") String typeName);

}
