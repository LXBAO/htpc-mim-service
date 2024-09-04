package com.uwdp.module.biz.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uwdp.module.biz.infrastructure.entity.PromptDo;
import com.uwdp.module.biz.infrastructure.mapper.PromptMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 信息提示 服务实现类
 * </p>
 *
 */
@Service
public class PromptRepository extends ServiceImpl<PromptMapper, PromptDo> {

}
