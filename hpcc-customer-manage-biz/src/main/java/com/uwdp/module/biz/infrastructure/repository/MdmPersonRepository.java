package com.uwdp.module.biz.infrastructure.repository;

import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.mapper.MdmPersonMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 主数据-人员 服务实现类
 * </p>
 *
 */
@Service
public class MdmPersonRepository extends ServiceImpl<MdmPersonMapper, MdmPersonDo> {

}
