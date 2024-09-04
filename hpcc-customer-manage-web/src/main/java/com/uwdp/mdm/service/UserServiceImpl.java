package com.uwdp.mdm.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.uwdp.config.CommonConfiguration;
import com.uwdp.module.api.service.IUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lx
 * @data 2023/6/29 15:19
 */
@Component
@Data
public class UserServiceImpl implements IUserService {

    @Autowired private CommonConfiguration commonConfiguration;

    @Override
    public String getUserInfo(String token) {
        return HttpUtil.createGet(commonConfiguration.getUserInfoUrl())
                .header(" X-TenantId","1")
                .header("x-token",token).execute().body();
    }
}
