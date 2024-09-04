package com.uwdp.timed.projectmanagerledger.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.net.ceec.hpcc.mdm.sdk.enums.MdmDataTypeEnum;
import com.uwdp.mdm.service.SynMdm2MimService;
import com.uwdp.module.biz.utils.AseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyPair;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Slf4j
@Component("ryTask")
public class RyTask {
    @Autowired
    private SynMdm2MimService synMdm2MimService;

    /**
     * 同步主数据人员数据跟组织数据
     */
    public void syncMdmPersonAndOrganization() {
        synMdm2MimService.updateMdmStats2Mim(MdmDataTypeEnum.MDM_PERSON);
        synMdm2MimService.updateMdmStats2Mim(MdmDataTypeEnum.MDM_ORGANIZATION);
    }

    /**
     * 同步项目经理跟九大员
     */
    public void syncManagerAndNine(){
        synMdm2MimService.syncManager();
        synMdm2MimService.syncNine();
    }

    /**
     * ca认证平台证书/招标平台/资质信息 到期提示
     */
    public void caPlatformExpire(){
        synMdm2MimService.caPlatformExpire();
        synMdm2MimService.callPlatformExpire();
        synMdm2MimService.aptitudeExpire();
    }

    /**
     * 项目跟踪、投标、签约、实施提醒
     */
    public void projectManagementTask(){
        synMdm2MimService.projectTrackingTask();
        synMdm2MimService.projectBiddingTask();
        synMdm2MimService.projectSigningTask();
        synMdm2MimService.projectImplementTask();
    }

    public static void main(String[] args) {
        String content = "hello world";

        String a = AseUtil.encryptBase64(content);
        System.out.println(a);
        System.out.println(AseUtil.decryptStr(a));
    }


}
