package com.uwdp.module.biz.infrastructure.repository;

import com.uwdp.module.biz.infrastructure.entity.DataDo;
import com.uwdp.module.biz.infrastructure.mapper.DataMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 下拉列表维护 服务实现类
 * </p>
 *
 */
@Service
public class DataRepository extends ServiceImpl<DataMapper, DataDo> {

        @Autowired
        private  DataMapper dataMapper;

        public Integer getMaxRemark(int type,String typeName) {
                return dataMapper.getMaxRemark(type,typeName);
        }



}
