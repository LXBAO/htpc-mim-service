package com.uwdp.module.biz.utils;

import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uwdp.module.api.vo.cmd.ClientInfoAddCmd;
import com.uwdp.module.api.vo.cmd.PublicRelationsAddCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanAddCmd;
import com.uwdp.module.biz.infrastructure.entity.MdmOrganizationDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.repository.MdmOrganizationRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * 表单新建，添加必要字段
 */
@Configuration
public class MdmOrganizationUtil {
    @Autowired
    public MdmPersonRepository mdmPersonRepository;

    @Autowired
    public MdmOrganizationRepository mdmOrganizationRepository;

    /**
     *
     * @param addCmd
     * @param personCode
     * @return
     */
    public  Object getMdmOrganization(Object addCmd,String personCode){
        PublicRelationsAddCmd publicRelationsAddCmd=null;
        VisitPlanAddCmd visitPlanAddCmd=null;
        ClientInfoAddCmd clientInfoAddCmd=null;
        // 数据权限 添加创建人字段(保存创建人personCode)
        if(addCmd instanceof PublicRelationsAddCmd){//公关反馈
            publicRelationsAddCmd =(PublicRelationsAddCmd)addCmd;
        }else if(addCmd instanceof VisitPlanAddCmd){
            visitPlanAddCmd=(VisitPlanAddCmd)addCmd;
        }else if(addCmd instanceof ClientInfoAddCmd){
            clientInfoAddCmd=(ClientInfoAddCmd)addCmd;
        }//后面新加的Cmd可手动加判断

        MdmPersonDo personDo = Optional
                .ofNullable(
                        mdmPersonRepository.getOne(new LambdaQueryWrapper<MdmPersonDo>()
                                .select(MdmPersonDo::getGroupBelongDepartmentCode)
                                .eq(!StringUtils.isEmpty(personCode), MdmPersonDo::getPersonCode, personCode))
                ).orElseThrow(
                        () -> new BizException("根据personCode:{" + personCode + "}找不到对应的人员关系")
                );

        // 数据权限 添加部门全路径字段(保存创建人所属部门全路径)
        String groupCode = personDo.getGroupBelongDepartmentCode();
        if (StringUtils.isEmpty(groupCode)){
            throw new BizException("根据人员编码:{" + personDo.getPersonCode() + "}" +
                    ",人员名称:{"+personDo.getPersonName()+"}找不到对应的部门信息");
        }
//        String groupCode = personDo.getBelongDepartmentCode();
        MdmOrganizationDo mdmOrganizationDo = Optional
                .ofNullable(
                        mdmOrganizationRepository.getOne(new LambdaQueryWrapper<MdmOrganizationDo>()
                                .select(MdmOrganizationDo::getGroupFullCode)
                                .eq(!StringUtils.isEmpty(groupCode), MdmOrganizationDo::getGroupCode, groupCode))
                ).orElseThrow(
                        () -> new BizException("根据GROUPCODE:{" + groupCode + "}找不到对应的机构信息")
                );
        if(addCmd instanceof PublicRelationsAddCmd){//公关反馈
            publicRelationsAddCmd.setGroupFullCode(mdmOrganizationDo.getGroupFullCode());
            return publicRelationsAddCmd;
        }else if(addCmd instanceof VisitPlanAddCmd){
            visitPlanAddCmd.setGroupFullCode(mdmOrganizationDo.getGroupFullCode());
            return visitPlanAddCmd;
        }else if(addCmd instanceof ClientInfoAddCmd){
            clientInfoAddCmd.setGroupFullCode(mdmOrganizationDo.getGroupFullCode());
            return clientInfoAddCmd;
        }//后面新加的Cmd可手动加判断
        return addCmd;
    }

    /**
     * 根据人员编号获取组织全路径
     * @param createdBy
     * @return
     */
    public String getGroupFullCode(String createdBy){
        MdmPersonDo personDo = Optional
                .ofNullable(
                        mdmPersonRepository.getOne(new LambdaQueryWrapper<MdmPersonDo>()
                                .select(MdmPersonDo::getGroupBelongDepartmentCode)
                                .eq(!StringUtils.isEmpty(createdBy), MdmPersonDo::getPersonCode, createdBy))
                ).orElseThrow(
                        () -> new BizException("根据personCode:{" + createdBy + "}找不到对应的人员关系")
                );

        // 数据权限 添加部门全路径字段(保存创建人所属部门全路径)
        String groupCode = personDo.getGroupBelongDepartmentCode();
        if (StringUtils.isEmpty(groupCode)){
            throw new BizException("根据人员编码:{" + personDo.getPersonCode() + "}" +
                    ",人员名称:{"+personDo.getPersonName()+"}找不到对应的部门信息");
        }
//        String groupCode = personDo.getBelongDepartmentCode();
        MdmOrganizationDo mdmOrganizationDo = Optional
                .ofNullable(
                        mdmOrganizationRepository.getOne(new LambdaQueryWrapper<MdmOrganizationDo>()
                                .select(MdmOrganizationDo::getGroupFullCode)
                                .eq(!StringUtils.isEmpty(groupCode), MdmOrganizationDo::getGroupCode, groupCode))
                ).orElseThrow(
                        () -> new BizException("根据GROUPCODE:{" + groupCode + "}找不到对应的机构信息")
                );
        return mdmOrganizationDo.getGroupFullCode();
    }
}
