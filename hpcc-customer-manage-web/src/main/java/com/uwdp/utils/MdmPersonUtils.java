package com.uwdp.utils;

import com.alibaba.cola.exception.BizException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uwdp.module.biz.infrastructure.entity.MdmOrganizationDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.repository.MdmOrganizationRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MdmPersonUtils {


    private final MdmPersonRepository mdmPersonRepository;

    private final MdmOrganizationRepository mdmOrganizationRepository;

    private final JdbcTemplate jdbcTemplate;

    /**
     * ifExist(personCode){
     *      return MdmPerson
     * }else{
     *      throw Exception;
     * }
     *
     * @param personCode 人员编号(公司)
     * @return
     */
    public MdmPersonDo checkIfPersonCodeExist(String personCode) {
        return Optional
                .ofNullable(
                        mdmPersonRepository.getOne(new LambdaQueryWrapper<MdmPersonDo>()
                                .eq(!StringUtils.isEmpty(personCode), MdmPersonDo::getPersonCode, personCode))
                ).orElseThrow(
                        () -> new BizException("根据personCode:{"+personCode+"}找不到对应的人员关系")
                );
    }

    /**
     * ifExist(groupCode){
     *      return MdmOrganizationDo
     * }else{
     *      throw Exception;
     * }
     * @param groupCode 组织编码(公司)
     * @return
     */
    public MdmOrganizationDo checkIfOrganizationExist(String groupCode) {
        return Optional
                .ofNullable(
                        mdmOrganizationRepository.getOne(new LambdaQueryWrapper<MdmOrganizationDo>()
                                .eq(!StringUtils.isEmpty(groupCode), MdmOrganizationDo::getGroupCode, groupCode))
                ).orElseThrow(
                        () -> new BizException("根据GROUPCODE:{"+groupCode+"}找不到对应的机构信息")
                );
    }

    /**
     * 根据 主数据人员ids返回对应的userCodes
     *  t_mdmperson.mdmId or t_mdmperson.id
     * @param ids
     * @return userCodes : t_mdmperson.personCode
     */
    public String getUserCodes(String ids){
        try{
            if (StringUtils.isEmpty(ids)){
                return "";
            }
            String[] split = ids.split(",");
            List<String> list = Arrays.asList(split);
            List<MdmPersonDo> mdmPersonDos =
                    mdmPersonRepository.list(new QueryWrapper<MdmPersonDo>()
                            .in("mdmId", list)
                            .or()
                            .in("id", list));
            return Optional.ofNullable(mdmPersonDos)
                    .map(persons -> persons.stream()
                            .map(MdmPersonDo::getPersonCode)
                            .filter(personCode -> personCode != null && !personCode.isEmpty()
                            ).collect(Collectors.joining(","))
                    ).orElse("");
        }catch (Exception e){
            throw new BizException("根据保存的人员ids:{} 获取工号出错!",ids);
        }
    }

    //                String groupPersonCode = personDo.getGroupPersonCode();
    //                return (groupPersonCode != null && !groupPersonCode.isEmpty()) ? groupPersonCode : personDo.getPersonCode();

    /**
     * 删除 t_mdmorganization 表中 groupCode重复的数据,仅保留version最大的
     */
    public void deleteDuplicatedGroupCode(){
        String sql =
                "DELETE FROM t_mdmorganization where ID not in( SELECT id from( SELECT MAX(id) AS id FROM  t_mdmorganization GROUP BY  GROUPCODE ) as m )";

        jdbcTemplate.update(sql);
    }

    /**
     * 删除 t_mdmperson 表中 PERSONCODE 重复的数据,仅保留version最大的
     */
    public void deleteDuplicatedPersonCode(){
        String sql ="DELETE FROM t_mdmperson where ID not in( SELECT id from( SELECT MAX(id) AS id FROM t_mdmperson GROUP BY PERSONCODE ) as m )";

        jdbcTemplate.update(sql);
    }
}
