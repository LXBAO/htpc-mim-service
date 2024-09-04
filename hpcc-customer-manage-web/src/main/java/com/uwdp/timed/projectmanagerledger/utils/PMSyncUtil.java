package com.uwdp.timed.projectmanagerledger.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerLedgerDo;
import com.uwdp.utils.StringUtils;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Map;

/**
 * 同步人资系统项目经理数据
 */
@Configuration
@Slf4j
public class PMSyncUtil{

    public ProjectManagerLedgerDo mapToObject(Map<String, Object> map) {
        // 创建相应的 Java 对象实例
        ProjectManagerLedgerDo obj = new ProjectManagerLedgerDo();

        // 通过遍历 Map 中的键值对，将数据设置到对象的属性上
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            // 根据键值对的键和值，设置对象的属性值
            // 这里假设对象中存在对应的 setter 方法
            try {
                setObjectProperty(obj, key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    @Autowired
    public StringUtils stringUtils;

    private void setObjectProperty(ProjectManagerLedgerDo cmd, String key, Object value) throws Exception{
        // 根据 key 和 value 设置对象属性值的逻辑实现
        // 这里需要根据实际情况自行编写相应的逻辑
        if(key==null||key.isEmpty()){
            return;
        }
        if("data".equals(key)){
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> objMap = objectMapper.readValue(stringUtils.getStr(value), new TypeReference<Map<String, Object>>() {});
            String memo=stringUtils.getStr(objMap.get("xxbz"));//备注
            String dqDate=stringUtils.getStr(objMap.get("xxdqsj"));//到期时间
            LocalDateTime dqDate0=stringUtils.convertToLocalDateTime(
                    stringUtils.convertToDate(dqDate,"yyyy-MM-dd")
            );
            String hdUnit=stringUtils.getStr(objMap.get("xxhddw"));//获得单位
            String syUnit=stringUtils.getStr(objMap.get("xxsydw"));//授予单位
            String syDate=stringUtils.getStr(objMap.get("xxsysj"));//授予时间
            LocalDateTime syDate0=stringUtils.convertToLocalDateTime(
                    stringUtils.convertToDate(syDate,"yyyy-MM-dd")
            );
            String zsNum=stringUtils.getStr(objMap.get("xxzsbh"));//证书编号
            String zyQualification=stringUtils.getStr(objMap.get("xxzyzg"));//执业资格
//            String userInfo=stringUtils.getStr(objMap.get("zzgl_id"));//人员信息
            String zxDate=stringUtils.getStr(objMap.get("xxzxzcsj"));//最新注册时间
            LocalDateTime zxDate0=stringUtils.convertToLocalDateTime(
                    stringUtils.convertToDate(zxDate,"yyyy-MM-dd")
            );
            String crt= StringUtil.isEmpty(stringUtils.getStr(objMap.get("xxzjmc")))? stringUtils.getStr(objMap.get("xxzyzg")) : stringUtils.getStr(objMap.get("xxzjmc"));//安全证书
            cmd.setFdMemo(memo);
            cmd.setFdExpireDate(dqDate0);
            cmd.setFdGainUnits(hdUnit);
            cmd.setFdGrantingUnit(syUnit);
            cmd.setFdGrantTime(syDate0);
            cmd.setFdCertificateNo(zsNum);
            cmd.setFdNVQ(zyQualification);
            cmd.setFdNewRegisterTime(zxDate0);
            cmd.setFdSafetyCertificate(crt);

            String fdLicenceIssued=stringUtils.getStr(objMap.get("xxfzrq"));//发证日期
            LocalDateTime fdLicenceIssued0=stringUtils.convertToLocalDateTime(
                    stringUtils.convertToDate(fdLicenceIssued,"yyyy-MM-dd")
            );
            cmd.setFdLicenceIssued(fdLicenceIssued0);
            String certificateNum=StringUtil.isEmpty(stringUtils.getStr(objMap.get("xxzjbh")))? stringUtils.getStr(objMap.get("xxzsbh")):stringUtils.getStr(objMap.get("xxzjbh"));//证件编号
            cmd.setFdCertificateNum(certificateNum);
            String certificateCategory=stringUtils.getStr(objMap.get("xxzjlb"));//证件类别
            cmd.setFdCertificateCategory(certificateCategory);
            String certificateName=stringUtils.getStr(objMap.get("xxzjmc"));//证件名称
            cmd.setFdCertificateName(certificateName);
            String certificateContent=stringUtils.getStr(objMap.get("xxzjnr"));//证件内容
            cmd.setFdCertificateContent(certificateContent);
            String fdExpiryDate=stringUtils.getStr(objMap.get("xxyxqjzrq"));//有效期截止日期
            LocalDateTime fdExpiryDate0=stringUtils.convertToLocalDateTime(
                    stringUtils.convertToDate(fdExpiryDate,"yyyy-MM-dd")
            );
            cmd.setFdExpiryDate(fdExpiryDate0);
            String fdExpirationDate=stringUtils.getStr(objMap.get("xxyxqksrq"));//有效期开始日期
            LocalDateTime fdExpirationDate0=stringUtils.convertToLocalDateTime(
                    stringUtils.convertToDate(fdExpirationDate,"yyyy-MM-dd")
            );
            cmd.setFdExpirationDate(fdExpirationDate0);

        }
        switch (key){
            case "name":cmd.setFdPMName(stringUtils.getStr(value));break;//姓名
            case "number":cmd.setFdJobNumber(stringUtils.getStr(value));
                            cmd.setCreatedBy(stringUtils.getStr(value));break;//工号
            case "mobile":cmd.setFdPhoneNumber(stringUtils.getStr(value));break;//手机号
            case "identity_card":cmd.setFdIDCardNo(stringUtils.getStr(value));break;//身份证号码
            case "id":cmd.setHrId(stringUtils.getStr(value));break;//关联人资id
        }
    }
}
