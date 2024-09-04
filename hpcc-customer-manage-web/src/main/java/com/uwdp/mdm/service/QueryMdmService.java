package com.uwdp.mdm.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.ejlchina.searcher.util.MapUtils;
import com.uwdp.mdm.util.MenuTree;
import com.uwdp.module.api.service.IMdmOrganizationService;
import com.uwdp.module.api.service.IMdmPersonService;
import com.uwdp.module.api.service.IMdmPostService;
import com.uwdp.module.api.vo.dto.MdmOrganizationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueryMdmService {

    private final IMdmPersonService mdmPersonService;
    private final IMdmOrganizationService mdmOrganizationService;
    private final IMdmPostService mdmPostService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 调用 MenuTree 查询返回部门单位树状结构 json
     * @return
     */
    public JSONArray get() {
        if(redisTemplate.hasKey("shjh:mim:menuJsonString")){
            return JSONArray.parseArray((String)redisTemplate.opsForValue().get("shjh:mim:menuJsonString"));
        }else{
            Map<String, Object> paraMap = MapUtils.builder()
//                .field(MdmOrganizationDto::getTypeCode, "1").op(Equal.class)
                    .build();
            List<MdmOrganizationDto> list = mdmOrganizationService.searchByParam(paraMap);
            List<Object> objects = new MenuTree().menuList(list);
            redisTemplate.opsForValue().set("shjh:mim:menuJsonString", JSON.toJSONString(objects), 24*60*60*1000, TimeUnit.MILLISECONDS);
            return JSONArray.parseArray(JSON.toJSONString(objects));
        }
    }

    /**
     * 更新 redis 中的 menuJson
     * @return
     */
    public JSONArray refreshMenuTree() {
        redisTemplate.delete("shjh:mim:menuJson");
        Map<String, Object> paraMap = MapUtils.builder()
                .build();
        List<MdmOrganizationDto> list = mdmOrganizationService.searchByParam(paraMap);
        List<Object> objects = new MenuTree().menuList(list);
        redisTemplate.opsForList().rightPush("shjh:mim:menuJson", objects, 24*60*60*1000);
        return JSONArray.parseArray(JSON.toJSONString(objects));
    }

}
