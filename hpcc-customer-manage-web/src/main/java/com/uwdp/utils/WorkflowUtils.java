package com.uwdp.utils;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.gientech.lcds.workflow.sdk.core.runtime.BaseDto;
import com.gientech.lcds.workflow.sdk.core.runtime.CandidateInfoDto;
import com.gientech.lcds.workflow.sdk.runtime.procinst.service.impl.RuntimeProcInstServiceImpl;
import com.uwdp.uac.AuthorizationProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkflowUtils {

    private final RuntimeProcInstServiceImpl rpi;

    private final AuthorizationProperty authorizationProperty;
    /**
     *  根据流程指定审批人页面指定的审批人字符串 , 拼接 approveUser 属性
     *  StartProcessByCodeRequestDto.approveUser
     * @param jsonStr 可转化为 JSONObject 的字符串
     * @return
     */
    public static Map<String, List<CandidateInfoDto>> buildApproveUser(@NotBlank String jsonStr){
        Map<String, List<CandidateInfoDto>> resultMap =  new HashMap<>();
        try{
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            resultMap = jsonObject.entrySet().stream()
                    .filter(entry -> {
                        Object value = entry.getValue();
                        // 传入的json字符串解析后的jsonObject对应的属性值为[] 或[{}] 时, 在返回的map中去除这个键值对 下面是ai写的 , 反正能用
                        return !(value instanceof JSONArray && ((JSONArray) value).isEmpty()) && !((value instanceof JSONArray) && (((JSONArray) value).size() == 1 && ((JSONArray) value).get(0) instanceof JSONObject && ((JSONObject) ((JSONArray) value).get(0)).isEmpty()));
                    })
                    .collect(Collectors.toMap(
                            entry -> entry.getKey(),
                            entry -> {
                                JSONArray jsonArray = (JSONArray) entry.getValue();
                                return jsonArray.toJavaList(CandidateInfoDto.class);
                            }
                    ));

        } catch (Exception e) {
            throw new BizRuntimeException(String.format("转换审批人时发生错误:{jsonStr:%s}", jsonStr), e);
        }
        return resultMap.isEmpty() ? null : resultMap;
    }

    public static void main(String[] args) {
//        String a = " {\"node-IQtUEOxm\":[],\"node-OhRccRcg\":[{}],\"node-4mFkgLhB\":[],\"node-t7PJC4gM\":[{}],\"node-QT0Z2iLg\":[{}]} ";
        String b = " {\"node-IQtUEOxm\":[],\"node-OhRccRcg\":[{}],\"node-4mFkgLhB\":[],\"node-t7PJC4gM\":[{\"candidate\":\"77265\",\"candidateName\":\"任卓宇\",\"type\":\"USER\"}],\"node-QT0Z2iLg\":[{\"candidate\":\"77265\",\"candidateName\":\"任卓宇\",\"type\":\"USER\"}]} ";
        Map<String, List<CandidateInfoDto>> stringListMap = buildApproveUser(b);
        System.out.println(stringListMap);
    }

    public void processLog(String processInstanceId) {
        BaseDto baseDto = new BaseDto();
//        baseDto.setAppId(String.valueOf(authorizationProperty.getAppId()));
        baseDto.setTenantId("1");
        baseDto.setUserNo("77265");
        baseDto.setUserFullName("任卓宇");
//        baseDto.setRoleNos();
//        baseDto.setOrgNos();
//        baseDto.setStandardPositions();
//        baseDto.setBusinessPositions();


        MultiResponse multiResponse = rpi.processLog(processInstanceId, baseDto);
    }

}
