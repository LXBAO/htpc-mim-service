package com.uwdp.mdm.service;

import cn.hutool.http.HttpRequest;
import cn.net.ceec.hpcc.mdm.sdk.enums.MdmDataTypeEnum;
import cn.net.ceec.hpcc.mdm.sdk.util.AuthUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.google.common.collect.Maps;
import com.uwdp.jdbcutils.DatabaseReaderUtil;
import com.uwdp.module.api.service.*;
import com.uwdp.module.api.vo.cmd.MdmPersonAddCmd;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.*;
import com.uwdp.module.api.vo.dto.searcher.CALedgerCerInfoDto;
import com.uwdp.module.biz.infrastructure.entity.MdmOrganizationDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPersonDo;
import com.uwdp.module.biz.infrastructure.entity.MdmPostDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerLedgerDo;
import com.uwdp.module.biz.infrastructure.repository.MdmOrganizationRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmPersonRepository;
import com.uwdp.module.biz.infrastructure.repository.MdmPostRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectManagerLedgerRepository;
import com.uwdp.timed.projectmanagerledger.utils.PMSyncUtil;
import com.uwdp.utils.MdmPersonUtils;
import com.uwdp.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SynMdm2MimService {

    public static final String APP_SECRET_DEV = "f44bb49cad7143808c2d4e7ae1e5709e";

    public static final String APP_SECRET_PROD = "d79df1a88d30435db21d9e0f4d90b89c";

    private boolean flag = false;   /* 判断接口返回数目为整pageSize时的特殊处理 */

    private final MdmPersonRepository mdmPersonRepository;
    private final MdmOrganizationRepository mdmOrganizationRepository;
    private final MdmPostRepository mdmPostRepository;
    private final QueryMdmService queryMdmService;
    private final MdmPersonUtils mdmPersonUtils;

    private Map<MdmDataTypeEnum, Class> mdmDataTypeMap = Maps.newHashMapWithExpectedSize(16);
    private Map<MdmDataTypeEnum, ServiceImpl> serviceMap = Maps.newHashMapWithExpectedSize(16);

    @PostConstruct
    public void init() {
        mdmDataTypeMap.put(MdmDataTypeEnum.MDM_PERSON, MdmPersonDo.class);
        mdmDataTypeMap.put(MdmDataTypeEnum.MDM_ORGANIZATION, MdmOrganizationDo.class);
        mdmDataTypeMap.put(MdmDataTypeEnum.MDM_POST, MdmPostDo.class);

        serviceMap.put(MdmDataTypeEnum.MDM_PERSON, mdmPersonRepository);
        serviceMap.put(MdmDataTypeEnum.MDM_ORGANIZATION, mdmOrganizationRepository);
        serviceMap.put(MdmDataTypeEnum.MDM_POST, mdmPostRepository);
    }


    /**
     * 根据mdm类型 初始化mdm数据
     *
     * @param m 选择要同步的表是人员、组织还是岗位
     */
    @Async
    public void initMdmStats2Mim(MdmDataTypeEnum m) {
        // 只能同步限定类型的数据
        if (null == mdmDataTypeMap.get(m)) {
            return;
        }
        String rtn = "";
        if (m == MdmDataTypeEnum.MDM_PERSON) {
            List<MdmPersonDo> list = circlePage(m, 1, new ArrayList());
            log.info(">>>>>>>>初始化{}接收参数完毕-共{}条数据", m, list.size());
            if (null != list && !list.isEmpty()) {
                list = list.stream()
                        .peek(cmd -> cmd.setMdmId(null))  // 设置 mdmId 属性为 id 属性的值
                        .peek(cmd -> cmd.setLocalVersion(1))
                        .peek(cmd -> cmd.setId(null))   // 将 id 属性置空
                        .collect(Collectors.toList());
                serviceMap.get(m).saveBatch(list);
            }
        } else if (m == MdmDataTypeEnum.MDM_ORGANIZATION) {
            List<MdmOrganizationDo> list = circlePage(m, 1, new ArrayList());
            log.info(">>>>>>>>初始化{}接收参数完毕-共{}条数据", m, list.size());
            if (null != list && !list.isEmpty()) {
                list = list.stream()
                        .peek(cmd -> cmd.setMdmId(null))  // 设置 mdmId 属性为 id 属性的值
                        .peek(cmd -> cmd.setLocalVersion(1))
                        .peek(cmd -> cmd.setId(null))   // 将 id 属性置空
                        .collect(Collectors.toList());
                serviceMap.get(m).saveBatch(list);
            }
        } else if (m == MdmDataTypeEnum.MDM_POST) {
            List<MdmPostDo> list = circlePage(m, 1, new ArrayList());
            log.info(">>>>>>>>初始化{}接收参数完毕-共{}条数据", m, list.size());
            if (null != list && !list.isEmpty()) {
                list = list.stream()
                        .peek(cmd -> cmd.setMdmId(null))  // 设置 mdmId 属性为 id 属性的值
                        .peek(cmd -> cmd.setLocalVersion(1))
                        .peek(cmd -> cmd.setId(null))   // 将 id 属性置空
                        .collect(Collectors.toList());
                serviceMap.get(m).saveBatch(list);
            }
        }
    }

//    public static void main(String[] args) {
//        page(MdmDataTypeEnum.MDM_PERSON, MdmPersonDo.class, 1);
//    }

    public static <T> List<T> page(MdmDataTypeEnum m, Class<T> c, int pageIndex) {
        JSONObject requestBody = new JSONObject();
        JSONObject requestData = new JSONObject();
        requestBody.put("pageIndex", pageIndex);
        requestBody.put("pageSize", 2000);
        requestBody.put("data", requestData);
        requestBody.put("requestId", UUID.randomUUID());
        String timestamp = String.valueOf(System.currentTimeMillis());
        String appKey = "MIM";//替换真实值
        String appSecret = APP_SECRET_PROD;//替换真实值
        String saltValue = "6da2bd017c574150bd9ee5039e19d8bf";//盐值 对接系统自己定义
        String sign = AuthUtil.createSign(appKey, timestamp, cn.hutool.json.JSONUtil.toJsonStr(requestBody), saltValue, appSecret);

        String url = "https://uwdp-api.htpc.com.cn/hpcc-mdm-openapi/v1/" + m.getDataType() + "/page";

        String body = HttpRequest.post(url)
                .header("appKey", appKey)
                .header("appSecret", appSecret)
                .header("saltValue", saltValue)
                .header("sign", sign)
                .header("timestamp", timestamp)
                .body(requestBody.toString())
                .execute().body();
        JSONObject object = JSONObject.parseObject(body);
        JSONArray respData = object.getJSONArray("data");
        List<T> list = JSON.parseArray(JSON.toJSONString(respData), c);
        return list;
    }


    /**
     * 根据mdm类型 调用时间区间接口更新mdm数据
     * <p>
     * 1.根据按时间更新接口{@link #circleUpdateTime}获取需要同步的数据
     * </p>
     * <p>
     * 2.对获取到的数据进行过滤
     * <pre>
     *          2.1 去重
     *          2.2 对重复的数据做保留id的处理
     *          2.3 合并去重及处理后的数据
     *  </pre></p>
     * 3.删除重复的version低于最新的数据{@link MdmPersonUtils#deleteDuplicatedPersonCode}
     *
     * @param m {@link MdmDataTypeEnum 选择要同步的是人员、组织还是岗位}
     */
    @Transactional
    @Async
    public void updateMdmStats2Mim(MdmDataTypeEnum m) {
        // 只能同步限定类型的数据
        if (mdmDataTypeMap.get(m) == null) {
            return;
        }

        if (m == MdmDataTypeEnum.MDM_PERSON) {
            List<MdmPersonDo> list = circleUpdateTime(m, 1, new ArrayList<>());   /*即将新增的MdmPersonDo列表*/
            log.info(">>>>>>>>udpateTime-{}接收参数完毕-共{}条数据", m, list.size());
            if (list != null && !list.isEmpty()) {
                QueryWrapper<MdmPersonDo> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("PERSONCODE", "ID", "LOCALVERSION");  /*根据工号去重,更新后要保留的id,本地版本*/
                queryWrapper.ne("PERSONCODE", "");
                Map<String, MdmPersonDo> collect = mdmPersonRepository.list(queryWrapper)   /*数据库中已存在的MdmPersonDo列表*/
                        .stream().collect(Collectors.toMap(MdmPersonDo::getPersonCode, e -> e));    /*转换成map方便校验*/
                List<MdmPersonDo> updateList = list.stream()
                        .filter(e -> collect.containsKey(e.getPersonCode())).collect(Collectors.toList());  /*新增列表中需要『更新』的*/
                // 1.先删除重复的
                list.removeIf(e -> collect.containsKey(e.getPersonCode()));
                updateList = updateList.stream()
                        .peek(cmd -> {
                            MdmPersonDo personDo = collect.get(cmd.getPersonCode());
                            cmd.setId(null);   /*保留id*/
                            cmd.setMdmId(null);
                            cmd.setLocalVersion(personDo.getLocalVersion() + 1); /*本地版本+1*/
                        }).collect(Collectors.toList());
                list = list.stream()
                        .peek(cmd -> {
                            cmd.setMdmId(null);
                            cmd.setLocalVersion(1);
                            cmd.setId(null);   // 将 id 属性置空
                        }).collect(Collectors.toList());
                // 2.合并为最终需要插入的
                list.addAll(updateList);
                // 插入数据
                serviceMap.get(m).saveBatch(list);
                // 删除重复的version低于最新的数据(version是同步来的)
                mdmPersonUtils.deleteDuplicatedPersonCode();
            }
        } else if (m == MdmDataTypeEnum.MDM_ORGANIZATION) {
            List<MdmOrganizationDo> list = circleUpdateTime(m, 1, new ArrayList<>());
            log.info(">>>>>>>>udpateTime-{}接收参数完毕-共{}条数据", m, list.size());
            if (list != null && !list.isEmpty()) {
                QueryWrapper<MdmOrganizationDo> queryWrapper = new QueryWrapper<>();
                queryWrapper.select("GROUPCODE", "ID", "LOCALVERSION");  /*根据组织编码（集团唯一）去重,更新后要保留的id,本地版本*/
                Map<String, MdmOrganizationDo> collect = mdmOrganizationRepository.list(queryWrapper)   /*数据库中已存在的MdmOrganizationDo列表*/
                        .stream().collect(Collectors.toMap(MdmOrganizationDo::getGroupCode, e -> e));    /*转换成map方便校验*/
                List<MdmOrganizationDo> updateList = list.stream()
                        .filter(e -> collect.containsKey(e.getGroupCode())).collect(Collectors.toList());  /*新增列表中需要『更新』的*/
                // 1.先删除重复的
                list.removeIf(e -> collect.containsKey(e.getGroupCode()));
                updateList = updateList.stream()
                        .peek(cmd -> {
                            MdmOrganizationDo organizationDo = collect.get(cmd.getGroupCode());
                            cmd.setId(null);   /*保留id*/
                            cmd.setMdmId(null);
                            cmd.setLocalVersion(organizationDo.getLocalVersion() + 1); /*本地版本+1*/
                        })
                        .collect(Collectors.toList());
                list = list.stream()
                        .peek(cmd -> {
                            cmd.setMdmId(null);  // 将 mdmId 属性置空
                            cmd.setLocalVersion(1);
                            cmd.setId(null);   // 将 id 属性置空
                        })
                        .collect(Collectors.toList());
                // 2.合并为最终需要插入的
                list.addAll(updateList);
                // 插入数据
                serviceMap.get(m).saveBatch(list);
                // 删除重复的version低于最新的数据
                mdmPersonUtils.deleteDuplicatedGroupCode();
                // 刷新redis中保存的部门树
                queryMdmService.refreshMenuTree();
            }
        } else if (m == MdmDataTypeEnum.MDM_POST) {
            //TODO 还没用到岗位所以先不写
//        List<MdmPostDo> list = circleUpdateTime(m, 1, new ArrayList());
//        log.info(">>>>>>>>udpateTime-{}接收参数完毕-共{}条数据", m, list.size());
//        if (null !=list && !list.isEmpty()) {
//            list = list.stream()
//                    .peek(cmd -> cmd.setMdmId(null))  // 设置 mdmId 属性为 id 属性的值
//                    .peek(cmd -> cmd.setLocalVersion(1))
//                    .peek(cmd -> cmd.setId(null))   // 将 id 属性置空
//                    .collect(Collectors.toList());
//            serviceMap.get(m).saveBatch(list);
//        }
        }
    }

    public <T> List<T> updateTime(MdmDataTypeEnum m, Class<T> c, int pageIndex) {
        String startTime = getLastUpdateTime(m);
        log.info(">>>>>>>>定时更新{}开始-第{}页-开始时间{}", m, pageIndex, startTime);
        JSONObject requestBody = new JSONObject();
        JSONObject requestData = new JSONObject();
        requestData.put("startUpdateTime", startTime);
        requestData.put("endUpdateTime",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        requestBody.put("pageIndex", pageIndex);
        requestBody.put("pageSize", 2000);
        requestBody.put("data", requestData);
        requestBody.put("requestId", UUID.randomUUID());
        String timestamp = String.valueOf(System.currentTimeMillis());
        String appKey = "MIM";
        String appSecret = APP_SECRET_PROD;
        String saltValue = "6da2bd017c574150bd9ee5039e19d8bf";//盐值 对接系统自己定义
        String sign = AuthUtil.createSign(appKey, timestamp, cn.hutool.json.JSONUtil.toJsonStr(requestBody), saltValue, appSecret);

        String url = "https://uwdp-api.htpc.com.cn/hpcc-mdm-openapi/v1/" + m.getDataType() + "/range/update-time";

        String body = HttpRequest.post(url)
                .header("appKey", appKey)
                .header("appSecret", appSecret)
                .header("saltValue", saltValue)
                .header("sign", sign)
                .header("timestamp", timestamp)
                .body(requestBody.toString())
                .execute().body();
        JSONObject object = JSONObject.parseObject(body);
        JSONArray respData = object.getJSONArray("data");
        List<T> list = JSON.parseArray(JSON.toJSONString(respData), c);
        return list;
    }

    /**
     * mdm数据接口单次分页查询最大2000条
     *
     * @param m
     * @param pageIndex
     * @param list
     * @return
     */
    public List circlePage(MdmDataTypeEnum m, int pageIndex, List list) {
        log.info(">>>>>>>>初始化{}开始-第{}页", m, pageIndex);
        List<MdmPersonAddCmd> page = page(m, mdmDataTypeMap.get(m), pageIndex);
        boolean b = list.addAll(page);
        // 分页查询为 0 , 退出同步
        if (null == page || page.size() == 0) {
            flag = true;
            log.info(">>>>>>>>初始化{}-第{}页-接口没有返回数据-结束", m, pageIndex);
            return list;
        }
        // 最多一次同步6000条数据(从接口获取) 此数值必须是pagSize的倍数 , 默认pageSize=2000
//        if (list.size() == 6000) {
//            log.info(">>>>>>>>初始化{}-第{}页-达到单次同步最大数量{}-结束",m,pageIndex,list.size());
//            return list;
//        }
        // 接口返回整2000条, 可能还有
        if (b && list.size() % 2000 == 0) {
            pageIndex++;
            list = circlePage(m, pageIndex, list);
        }
        return list;
    }

    public List circleUpdateTime(MdmDataTypeEnum m, int pageIndex, List list) {
        List<MdmPersonAddCmd> page = updateTime(m, mdmDataTypeMap.get(m), pageIndex);
        boolean b = list.addAll(page);
        // 分页查询为 0 , 退出同步
        if (null == page || page.size() == 0) {
            flag = true;
            log.info(">>>>>>>>初始化{}-第{}页-接口没有返回数据-结束", m, pageIndex);
            return list;
        }
        // 最多一次同步6000条数据(从接口获取) 此数值必须是pagSize的倍数 , 默认pageSize=2000
        if (list.size() == 6000) {
            log.info(">>>>>>>>初始化{}-第{}页-达到单次同步最大数量{}-结束", m, pageIndex, list.size());
            return list;
        }
        // 接口返回整2000条, 可能还有
        if (b && list.size() % 2000 == 0) {
            pageIndex++;
            list = circlePage(m, pageIndex, list);
        }
        return list;
    }

    /**
     * 获取表中最新的创建时间
     *
     * @param m
     * @return
     */
    public String getLastUpdateTime(MdmDataTypeEnum m) {
        ServiceImpl service = serviceMap.get(m);
        Class c = mdmDataTypeMap.get(m);
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(CREATED_TIME) as CREATED_TIME");
        Map map = service.getMap(queryWrapper);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime createdTime = (LocalDateTime) map.get("CREATED_TIME");

        return createdTime.format(fmt);
    }


    @Autowired
    public DatabaseReaderUtil databaseReaderUtil;

    @Autowired
    public PMSyncUtil pmSyncUtil;

    @Autowired
    public ProjectManagerLedgerRepository projectManagerLedgerRepository;

    @Autowired
    public StringUtils stringUtils;

    @Autowired
    public IProjectRecordsService projectRecordsService;

    @Autowired
    public IProjectTrackingService projectTrackingService;

    @Autowired
    public IProjectBiddingService projectBiddingService;

    @Autowired
    public IWinTheBidService winTheBidService;

    @Autowired
    public IProjectSigningService projectSigningService;

    @Autowired
    public IProjectImplementService projectImplementService;

    @Transactional
    public void syncManager() {
        // 定时任务执行的逻辑
        log.info("{}-同步人资系统项目经理数据,定时任务执行中...", new Date());
        String sql = "select flex.data,flex.id,emp.name,emp.number,emp.mobile,emp.identity_card from " +
                "employee_subset_flex_data flex left join employee emp on flex.employee_id=emp.id where flex.model_name='employee_dynamic_subset.practice_qualification'";
        List<Map> list = databaseReaderUtil.findData("火电", sql);
        try {


            for (Map map : list) {
                ProjectManagerLedgerDo cmd = pmSyncUtil.mapToObject(map);
                cmd.setFdPersonnelType("项目经理");
                String hrId = cmd.getHrId();//关联人资id
                String fdJobNumber = cmd.getFdJobNumber();//工号
                String mdmSql = "select per.groupBelongDepartmentName,per.groupBelongPostName," +
                        "per.groupBelongUnitName,post.postProperty,post.groupPostCategory" +
                        " from T_MDMPERSON per left join T_MDMPOST post on post.groupPostCode=" +
                        "per.groupBelongPostCode where per.personCode='" + fdJobNumber + "'";
                List<Map> mdmList = databaseReaderUtil.findData("local", mdmSql);
                if (mdmList != null && !mdmList.isEmpty()) {
                    Map mdmMap = mdmList.get(0);
                    String dept = stringUtils.getStr(mdmMap.get("groupbelongdepartmentname"));//部门
                    String post = stringUtils.getStr(mdmMap.get("groupbelongpostname"));//岗位
                    String unit = stringUtils.getStr(mdmMap.get("groupbelongunitname"));//单位
                    String postProperty = stringUtils.getStr(mdmMap.get("postproperty"));//岗位性质
                    String category = stringUtils.getStr(mdmMap.get("grouppostcategory"));//岗位类别
                    cmd.setFdUnit(unit);//单位
                    cmd.setFdDepartment(dept);//部门
                    cmd.setFdPosition(post);//岗位
                    cmd.setFdNatureOfPost(postProperty);//岗位性质
                    cmd.setFdJobLevel(category);//岗位层级
                }
                String findSql = "select * from t_projectmanagerledger where hrid='" + hrId + "'";
                List<Map> findList = databaseReaderUtil.findData("local", findSql);
                if (findList != null && findList.size() > 0) {
                    Map dataMap = findList.get(0);
                    cmd.setFdInputType(stringUtils.getStr(dataMap.get("fdinputtype")));
                    Optional.of(
                            projectManagerLedgerRepository.update(cmd, new LambdaQueryWrapper<ProjectManagerLedgerDo>()
                                    .eq(ProjectManagerLedgerDo::getHrId, hrId))
                    ).orElseThrow(
                            () -> new BizRuntimeException(
                                    "根据关联人资id:{" + hrId + "}找不到对应的数据", new Exception()));
                } else {
                    cmd.setFdInputType("未使用");
                    Optional.of(
                            projectManagerLedgerRepository.save(cmd)
                    ).orElseThrow(
                            () -> new BizRuntimeException(
                                    "根据关联人资id:{" + hrId + "}找不到对应的数据", new Exception()));
                }
            }
            log.info("{}-同步人资系统项目经理数据,定时任务执行完成！！！", new Date());
        } catch (Exception e) {
            log.error("同步人资系统项目经理数据,定时任务执行报错！{}", e);
            e.fillInStackTrace();
        }
    }

    @Transactional
    public void syncNine() {
        // 定时任务执行的逻辑
        log.info("{}-同步人资系统九大员数据,定时任务执行中...", new Date());
        String sql = "select flex.data,flex.id,emp.name,emp.number,emp.mobile,emp.identity_card from " +
                "employee_subset_flex_data flex left join employee emp on flex.employee_id=emp.id where flex.model_name='employee_dynamic_subset.qualifications_information'";
        List<Map> list = databaseReaderUtil.findData("火电", sql);
        try {
            for (Map map : list) {
                ProjectManagerLedgerDo cmd = pmSyncUtil.mapToObject(map);
                cmd.setFdPersonnelType("九大员");
                String hrId = cmd.getHrId();//关联人资id
                String fdJobNumber = cmd.getFdJobNumber();//工号
                String mdmSql = "select per.groupBelongDepartmentName,per.groupBelongPostName," +
                        "per.groupBelongUnitName,post.postProperty,post.groupPostCategory" +
                        " from T_MDMPERSON per left join T_MDMPOST post on post.groupPostCode=" +
                        "per.groupBelongPostCode where per.personCode='" + fdJobNumber + "'";
                List<Map> mdmList = databaseReaderUtil.findData("local", mdmSql);
                if (mdmList != null && !mdmList.isEmpty()) {
                    Map mdmMap = mdmList.get(0);
                    String dept = stringUtils.getStr(mdmMap.get("groupbelongdepartmentname"));//部门
                    String post = stringUtils.getStr(mdmMap.get("groupbelongpostname"));//岗位
                    String unit = stringUtils.getStr(mdmMap.get("groupbelongunitname"));//单位
                    String postProperty = stringUtils.getStr(mdmMap.get("postproperty"));//岗位性质
                    String category = stringUtils.getStr(mdmMap.get("grouppostcategory"));//岗位类别
                    cmd.setFdUnit(unit);//单位
                    cmd.setFdDepartment(dept);//部门
                    cmd.setFdPosition(post);//岗位
                    cmd.setFdNatureOfPost(postProperty);//岗位性质
                    cmd.setFdJobLevel(category);//岗位层级
                }
                String findSql = "select * from t_projectmanagerledger where hrid='" + hrId + "'";
                List<Map> findList = databaseReaderUtil.findData("local", findSql);
                if (findList != null && findList.size() > 0) {
                    Map dataMap = findList.get(0);
                    cmd.setFdInputType(stringUtils.getStr(dataMap.get("fdinputtype")));
                    Optional.of(
                            projectManagerLedgerRepository.update(cmd, new LambdaQueryWrapper<ProjectManagerLedgerDo>()
                                    .eq(ProjectManagerLedgerDo::getHrId, hrId))
                    ).orElseThrow(
                            () -> new BizRuntimeException(
                                    "根据关联人资id:{" + hrId + "}找不到对应的数据", new Exception()));
                } else {
                    cmd.setFdInputType("未使用");
                    Optional.of(
                            projectManagerLedgerRepository.save(cmd)
                    ).orElseThrow(
                            () -> new BizRuntimeException(
                                    "根据关联人资id:{" + hrId + "}找不到对应的数据", new Exception()));
                }
            }
            log.info("{}-同步人资系统九大员数据,定时任务执行完成！！！", new Date());
        } catch (Exception e) {
            log.error("同步人资系统项目经理数据,定时任务执行报错！{}", e);
            e.fillInStackTrace();
        }
    }

    @Autowired
    private ICALedgerService cALedgerService;

    @Autowired
    private IPromptService promptService;

    private final IDataService dataService;

    @Transactional
    public void caPlatformExpire() {
        log.info("{}-查询ca认证平台证书到期时间,定时任务执行中...", new Date());

        LocalDate currentDate = LocalDate.now();

        DataDto by = dataService.getBy(9);  //9是ca认证平台证书到期时间

        LocalDate remindDate = currentDate.plusDays(Long.parseLong(by.getValue())); // 提醒时间是到期前五天

        try {
            Map<String, Object> map = new HashMap<>();
            List<CALedgerCerInfoDto> search = cALedgerService.searchAll(map);

            for (CALedgerCerInfoDto caLedgerCerInfoDto : search) {
                LocalDateTime expireDateTime = caLedgerCerInfoDto.getExpireDate();

                if (expireDateTime == null) {
                    continue;
                }

                LocalDate expireDate = LocalDate.from(expireDateTime);
                PromptDto promptDto = promptService.getByQid(caLedgerCerInfoDto.getFdId());
                if (promptDto != null && promptDto.getDeleteStatus().equals("1")) {
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    if (currentDate.isBefore(expireDate) && remindDate.isAfter(expireDate)) {
                        log.info("进来了update1：" + expireDate);

                        if (promptDto.getPromptStatus().equals("")) {
                            promptUpdateCmd.setPromptDetails(String.format("您的CA认证平台证书即将于 %s 到期。", expireDate));
                            promptUpdateCmd.setPromptStatus("warning");
                            promptUpdateCmd.setExpireDate(expireDate);
                        }
                        promptUpdateCmd.setDeleteStatus("0");
                        promptUpdateCmd.setId(promptDto.getId());
                    } else if (expireDate.isBefore(currentDate)) {
                        log.info("进来了update2：" + expireDate);

                        if (promptDto.getPromptStatus().equals("warning")) {
                            promptUpdateCmd.setPromptDetails("您的CA认证平台证书已经到期。");
                            promptUpdateCmd.setPromptStatus("");
                        }
                        promptUpdateCmd.setDeleteStatus("0");
                        promptUpdateCmd.setId(promptDto.getId());
                    }
                    promptService.update(promptUpdateCmd);
                } else if (promptDto == null && expireDate.isBefore(remindDate)) {
                    System.out.println("进来了add");
                    PromptAddCmd addCmd = new PromptAddCmd();
                    addCmd.setPromptTitle("CA认证平台证书到期提醒");

                    String message;
                    String status;
                    if (expireDate.isBefore(currentDate)) {
                        log.info("证书已过期！");
                        message = "您的CA认证平台证书已经到期。";
                        status = "";
                    } else {
                        message = String.format("您的CA认证平台证书即将于 %s 到期。", expireDate);
                        status = "warning";
                    }
                    addCmd.setPromptDetails(message);
                    addCmd.setExpireDate(expireDate);
                    addCmd.setCreatedBy(caLedgerCerInfoDto.getCreatedBy());
                    addCmd.setPromptId(caLedgerCerInfoDto.getCreatedBy());
                    addCmd.setQid(caLedgerCerInfoDto.getFdId());
                    addCmd.setCreatedName(caLedgerCerInfoDto.getFdRegistrant());
                    addCmd.setPromptStatus(status);
                    addCmd.setDeleteStatus("0"); // 0未删  1已删
                    addCmd.setPromptPath("CALedger");
                    promptService.add(addCmd);

                    promptService.toBeDoneToOa(caLedgerCerInfoDto.getFdId(), "8", caLedgerCerInfoDto.getCreatedBy(), 0, 0);
                }
            }
            log.info("{}-查询ca认证平台证书到期时间,定时任务执行完成", new Date());
        } catch (Exception e) {
            log.error("查询ca认证平台证书到期时间,定时任务执行报错！{}", e);
            e.fillInStackTrace();
        }
    }

    @Autowired
    private IPlatformLedgerService platformLedgerService;

    @Transactional
    public void callPlatformExpire() {
        log.info("{}-查询招标平台证书到期时间,定时任务执行中...", new Date());

        DataDto by = dataService.getBy(10);  //10是招标平台证书到期时间

        LocalDate currentDate = LocalDate.now();
        LocalDate remindDate = currentDate.plusDays(Long.parseLong(by.getValue()));

        try {
            Map<String, Object> map = new HashMap<>();
            List<PlatformLedgerDto> search = platformLedgerService.searchAll(map);

            for (PlatformLedgerDto platformLedgerDto : search) {
                String fdPlatformValidity = platformLedgerDto.getFdPlatformValidity();
                LocalDate expireDate;

                if (fdPlatformValidity != null) {
                    expireDate = LocalDate.parse(fdPlatformValidity.substring(0, 10));
                } else {
                    continue;
                }

                PromptDto promptDto = promptService.getByQid(platformLedgerDto.getFdId());

                if (null != promptDto && "1".equals(promptDto.getDeleteStatus())) {
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    if (currentDate.isBefore(expireDate) && remindDate.isAfter(expireDate)) {
                        log.info("进来了update1：" + expireDate);

                        if (promptDto.getPromptStatus().equals("")) {
                            promptUpdateCmd.setPromptDetails(String.format("您的招标平台证书即将于 %s 到期。", expireDate));
                            promptUpdateCmd.setPromptStatus("warning");
                            promptUpdateCmd.setExpireDate(expireDate);
                        }
                        promptUpdateCmd.setDeleteStatus("0");
                        promptUpdateCmd.setId(promptDto.getId());
                    } else if (expireDate.isBefore(currentDate)) {
                        log.info("进来了update2：" + expireDate);

                        if (promptDto.getPromptStatus().equals("warning")) {
                            promptUpdateCmd.setPromptDetails("您的招标平台证书已经到期。");
                            promptUpdateCmd.setPromptStatus("");
                        }
                        promptUpdateCmd.setDeleteStatus("0");
                        promptUpdateCmd.setId(promptDto.getId());
                    }
                    promptService.update(promptUpdateCmd);
                }

                if (promptDto == null && expireDate.isBefore(remindDate)) {
                    log.info("进来了add");
                    String message = "";
                    String status = "";
                    if (expireDate.isBefore(currentDate)) {
                        log.info("证书已过期！");
                        message = "您的招标平台证书已经到期。";
                        status = "";
                    } else {
                        message = String.format("您的招标平台证书即将于 %s 到期。", expireDate);
                        status = "warning";
                    }

                    PromptAddCmd addCmd = new PromptAddCmd();
                    addCmd.setPromptTitle("招标平台证书到期提醒");
                    addCmd.setPromptDetails(message);
                    addCmd.setExpireDate(expireDate);
                    addCmd.setCreatedBy(platformLedgerDto.getCreatedBy());
                    addCmd.setPromptId(platformLedgerDto.getCreatedBy());
                    addCmd.setCreatedName(platformLedgerDto.getFdRegistrant());
                    addCmd.setQid(platformLedgerDto.getFdId());
                    addCmd.setPromptStatus(status);  // warning 快到期  error 已经到期
                    addCmd.setDeleteStatus("0");  // 0未删  1已删
                    addCmd.setPromptPath("platformLedger");

                    promptService.add(addCmd);

                    promptService.toBeDoneToOa(platformLedgerDto.getFdId(), "8", platformLedgerDto.getCreatedBy(), 1, 0);
                }
            }

            log.info("{}-查询招标平台证书到期时间,定时任务执行中...", new Date());
        } catch (Exception e) {
            log.error("查询招标平台证书到期时间,定时任务执行报错！{}", e);
            e.fillInStackTrace();
        }
    }

    @Transactional
    public void aptitudeExpire(){
        log.info("{}-查询资质信息证书到期时间,定时任务执行中...", new Date());

        DataDto by = dataService.getBy(11);  //11是资质信息到期日期

        LocalDate currentDate = LocalDate.now();
        LocalDate remindDate = currentDate.plusDays(Long.parseLong(by.getValue()));

        try {
            QueryWrapper<ProjectManagerLedgerDo> queryWrapper = new QueryWrapper<>();
            queryWrapper.between("fdExpireDate", LocalDate.from(currentDate), remindDate)
                    .or()
                    .between("fdExpiryDate", LocalDate.from(currentDate), remindDate);

            List<ProjectManagerLedgerDo> list = projectManagerLedgerRepository.list(queryWrapper);

            queryWrapper = new QueryWrapper<>();
            queryWrapper.le("fdExpireDate", LocalDate.from(currentDate))
                    .or()
                    .le("fdExpiryDate", LocalDate.from(currentDate));

            List<ProjectManagerLedgerDo> list1 = projectManagerLedgerRepository.list(queryWrapper);
            list.addAll(list1);

            for (ProjectManagerLedgerDo projectManagerLedgerDo : list) {
                LocalDateTime fdExpireDate = projectManagerLedgerDo.getFdExpireDate();
                LocalDateTime fdExpiryDate = projectManagerLedgerDo.getFdExpiryDate();
                LocalDate expireDate = null;
                LocalDate expiryDate = null;

                if (null != fdExpireDate) {
                    expireDate = LocalDate.from(fdExpireDate);
                } else if (null != fdExpiryDate) {
                    expiryDate = LocalDate.from(fdExpiryDate);
                }

                PromptDto promptDto = promptService.getByQid(projectManagerLedgerDo.getFdId());

                if (null != promptDto && "1".equals(promptDto.getDeleteStatus())) {
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    if ((expireDate != null && currentDate.isBefore(expireDate) && remindDate.isAfter(expireDate)) ||
                            (expiryDate != null && currentDate.isBefore(expiryDate) && remindDate.isAfter(expiryDate))) {

                        log.info("进来了update1：" + expireDate + " " + expiryDate);
                        if (promptDto.getPromptStatus().equals("")) {
                            if (expireDate != null) {
                                promptUpdateCmd.setPromptDetails(String.format("您的项目经理证书即将于 %s 到期。", expireDate));
                            } else {
                                promptUpdateCmd.setPromptDetails(String.format("您的九大员证件即将于 %s 到期。", expiryDate));
                            }
                            promptUpdateCmd.setPromptStatus("warning");
                            promptUpdateCmd.setExpireDate(expireDate != null ? expireDate : expiryDate);
                        }
                        promptUpdateCmd.setDeleteStatus("0");
                        promptUpdateCmd.setId(promptDto.getId());


                    } else if ((expireDate != null && expireDate.isBefore(currentDate)) ||
                            (expiryDate != null && expiryDate.isBefore(currentDate))) {

                        log.info("进来了update2：" + expireDate + " " + expiryDate);

                        if (promptDto.getPromptStatus().equals("warning")) {
                            promptUpdateCmd.setPromptDetails("您的资质信息证书(件)已经到期。");
                            promptUpdateCmd.setPromptStatus("");
                        }
                        promptUpdateCmd.setDeleteStatus("0");
                        promptUpdateCmd.setId(promptDto.getId());


                    }
                    promptService.update(promptUpdateCmd);
                }
                if (promptDto == null) {
                    String message = "";
                    String status = "";
                    if ((expireDate != null && expireDate.isBefore(currentDate)) ||
                            (expiryDate != null && expiryDate.isBefore(currentDate))) {
                        log.info("证书已过期！");
                        message = "您的资质信息证书(件)已经到期。";
                        status = "";
                    } else if (expireDate != null && expireDate.isBefore(remindDate)) {
                        message = String.format("您的项目经理证书即将于 %s 到期。", expireDate);
                        status = "warning";
                    } else if (expiryDate != null && expiryDate.isBefore(remindDate)) {
                        message = String.format("您的九大员证件即将于 %s 到期。", expiryDate);
                        status = "warning";
                    }

                    if (!message.isEmpty()) {
                        PromptAddCmd addCmd = new PromptAddCmd();
                        log.info("进来了add{}", addCmd);
                        addCmd.setPromptTitle("资质信息证书到期提醒");
                        addCmd.setPromptDetails(message);
                        addCmd.setExpireDate(expireDate != null ? expireDate : expiryDate);
                        addCmd.setCreatedBy(projectManagerLedgerDo.getFdJobNumber());
                        addCmd.setPromptId(projectManagerLedgerDo.getFdJobNumber());
                        addCmd.setCreatedName(projectManagerLedgerDo.getFdPMName());
                        addCmd.setQid(projectManagerLedgerDo.getFdId());
                        addCmd.setPromptStatus(status);  // 0未读  1已读
                        addCmd.setDeleteStatus("0");  // 0未删  1已删
                        addCmd.setPromptPath("projectManagerLedger");
                        promptService.add(addCmd);

                        promptService.toBeDoneToOa(projectManagerLedgerDo.getFdId(), "8", projectManagerLedgerDo.getCreatedBy(), 2, 0);
                    }
                }
            }

            log.info("{}-查询资质信息证书到期时间,定时任务执行中...", new Date());
        } catch (Exception e) {
            log.error("查询资质信息证书到期时间,定时任务执行报错！{}", e);
            e.fillInStackTrace();
        }
    }

    @Transactional
    public void projectTrackingTask() {
        log.info("项目跟踪定时任务执行中...");
        try {
            // 查询所有项目信息
            List<ProjectRecordsDto> projectRecordsList = projectRecordsService.searchByParam(new HashMap<>());

            LocalDate currentDate = LocalDate.now();

            for (ProjectRecordsDto projectRecord : projectRecordsList) {
                // 获取项目登记通过日期
                LocalDate registrationDate = projectRecord.getSuccessTime();

                if (registrationDate == null) {
                    continue;
                }

                // 计算15天后的日期
                LocalDate reminderDate = registrationDate.plusDays(15);
                PromptDto promptDto = promptService.getByQid(projectRecord.getId(),"ProjectTracking"); //根据登记id查出提示信息
                ProjectTrackingDto projectTrackingDto = projectTrackingService.getProjectNumber(String.valueOf(projectRecord.getId()));//查询项目跟踪有没有项目信息
                if (promptDto != null && projectTrackingDto == null && promptDto.getDeleteStatus().equals("1")) {
                    //已经删除的提醒，如果未跟踪继续提醒
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    log.info("进来了update：" + registrationDate);
                    promptUpdateCmd.setDeleteStatus("0"); // 0未删  1已删
                    promptUpdateCmd.setId(promptDto.getId());
                    promptService.update(promptUpdateCmd);
                }else if ((currentDate.equals(reminderDate) || currentDate.isAfter(reminderDate))) {
                    if (projectTrackingDto==null && promptDto==null){
                        // 需要提醒
                        // 查询提示信息
                        String promptMessage = "请尽快对"+projectRecord.getProjectName()+"项目进行跟踪！";

                        // 构建 PromptAddCmd
                        PromptAddCmd addCmd = new PromptAddCmd();
                        addCmd.setPromptTitle("项目跟踪提醒");
                        addCmd.setPromptId(projectRecord.getCreatedBy());
                        addCmd.setPromptDetails(promptMessage);
                        addCmd.setExpireDate(projectRecord.getSuccessTime());
                        addCmd.setCreatedBy(projectRecord.getCreatedBy());
                        addCmd.setQid(projectRecord.getId());
                        addCmd.setCreatedName(projectRecord.getCreatedName());
                        addCmd.setPromptStatus("warning");
                        addCmd.setDeleteStatus("0"); // 0未删  1已删
                        addCmd.setPromptPath("ProjectTracking"); //不要随意更改

                        // 添加提示信息
                        promptService.add(addCmd);
                    }
                }
            }
            log.info("项目跟踪定时任务执行完成");
        } catch (Exception e) {
            log.error("项目跟踪定时任务执行报错！{}", e);
            e.printStackTrace();
        }
    }

    @Transactional
    public void projectBiddingTask() {
        log.info("项目投标定时任务执行中...");
        try {
            // 查询所有项目信息
            List<ProjectRecordsDto> projectRecordsList = projectRecordsService.searchByParam(new HashMap<>());

            LocalDate currentDate = LocalDate.now();

            for (ProjectRecordsDto projectRecord : projectRecordsList) {
                // 获取项目登记通过日期
                LocalDateTime estimatedBiddingTime = projectRecord.getEstimatedBiddingTime();

                if (estimatedBiddingTime == null) {
                    continue;
                }

                LocalDate estimatedBiddingDate = estimatedBiddingTime.toLocalDate();


                //根据登记id查出提示信息
                PromptDto promptDto = promptService.getByQid(projectRecord.getId(),"ProjectBidding");
                //查询项目投标有没有项目信息
                ProjectBiddingDto projectBiddingDto = projectBiddingService.getProjectNumber(String.valueOf(projectRecord.getId()));
                //判断提示信息不为空且删除状态未已删除、投标信息为空
                if (promptDto != null && projectBiddingDto == null && promptDto.getDeleteStatus().equals("1")) {
                    log.info("进来了update：" + projectRecord);
                    //已经删除的提醒，如果未跟踪继续提醒
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    promptUpdateCmd.setDeleteStatus("0"); // 0未删  1已删
                    promptUpdateCmd.setExpireDate(estimatedBiddingDate);
                    promptUpdateCmd.setId(promptDto.getId());
                    promptService.update(promptUpdateCmd);
                } else if (currentDate.isAfter(estimatedBiddingDate) || currentDate.isEqual(estimatedBiddingDate)) {
                    if (projectBiddingDto == null && promptDto == null){
                        log.info("进来了add：" + projectRecord);
                        // 需要提醒
                        // 查询提示信息
                        String promptMessage = "请尽快对"+projectRecord.getProjectName()+"项目进行投标！";

                        // 构建 PromptAddCmd
                        PromptAddCmd addCmd = new PromptAddCmd();
                        addCmd.setPromptTitle("项目投标提醒");
                        addCmd.setPromptId(projectRecord.getCreatedBy());
                        addCmd.setPromptDetails(promptMessage);
                        addCmd.setExpireDate(estimatedBiddingDate);
                        addCmd.setCreatedBy(projectRecord.getCreatedBy());
                        addCmd.setQid(projectRecord.getId());
                        addCmd.setCreatedName(projectRecord.getCreatedName());
                        addCmd.setPromptStatus("warning");
                        addCmd.setDeleteStatus("0"); // 0未删  1已删
                        addCmd.setPromptPath("ProjectBidding");

                        // 添加提示信息
                        promptService.add(addCmd);
                    }
                }
            }

            log.info("项目投标定时任务执行完成");
        } catch (Exception e) {
            log.error("项目投标定时任务执行报错！{}", e);
            e.printStackTrace();
        }
    }

    @Transactional
    public void projectSigningTask() {
        log.info("项目签约定时任务执行中...");
        try {
            // 查询所有项目信息
            List<WinTheBidDto> winTheBidDtoList = winTheBidService.searchByParam(new HashMap<>());

            LocalDate currentDate = LocalDate.now();

            for (WinTheBidDto winTheBidDto : winTheBidDtoList) {
                // 获取项目登记通过日期
                LocalDate signingTime = winTheBidDto.getSigningTime();

                if (signingTime == null) {
                    continue;
                }


                //根据登记id查出提示信息
                PromptDto promptDto = promptService.getByQid(Long.valueOf(winTheBidDto.getItemNumber()),"ProjectSigning");
                //查询项目签约有没有项目信息
                ProjectSigningDto projectSigningDto = projectSigningService.getProjectNumber(String.valueOf(winTheBidDto.getItemNumber()));
                //判断提示信息不为空且删除状态未已删除、投标信息为空
                if (promptDto != null && projectSigningDto == null && promptDto.getDeleteStatus().equals("1")) {
                    log.info("进来了update：" + winTheBidDto);
                    //已经删除的提醒，如果未签约继续提醒
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    promptUpdateCmd.setDeleteStatus("0"); // 0未删  1已删
                    promptUpdateCmd.setExpireDate(signingTime);
                    promptUpdateCmd.setId(promptDto.getId());
                    promptService.update(promptUpdateCmd);
                } else if (currentDate.isAfter(signingTime) || currentDate.isEqual(signingTime)) {
                    if (projectSigningDto == null && promptDto == null){
                        log.info("进来了add：" + winTheBidDto);
                        // 需要提醒
                        // 查询提示信息
                        String promptMessage = "请尽快对"+winTheBidDto.getProjectName()+"项目进行签约！";

                        // 构建 PromptAddCmd
                        PromptAddCmd addCmd = new PromptAddCmd();
                        addCmd.setPromptTitle("项目签约提醒");
                        addCmd.setPromptId(winTheBidDto.getCreatedBy());
                        addCmd.setPromptDetails(promptMessage);
                        addCmd.setExpireDate(winTheBidDto.getSigningTime());
                        addCmd.setCreatedBy(winTheBidDto.getCreatedBy());
                        addCmd.setQid(Long.valueOf(winTheBidDto.getItemNumber()));
                        addCmd.setCreatedName(winTheBidDto.getCreatedName());
                        addCmd.setPromptStatus("warning");
                        addCmd.setDeleteStatus("0"); // 0未删  1已删
                        addCmd.setPromptPath("ProjectSigning");

                        // 添加提示信息
                        promptService.add(addCmd);
                    }
                }
            }

            log.info("项目签约定时任务执行完成");
        } catch (Exception e) {
            log.error("项目签约定时任务执行报错！{}", e);
            e.printStackTrace();
        }
    }

    @Transactional
    public void projectImplementTask() {
        log.info("项目实施定时任务执行中...");
        try {
            // 查询所有项目信息
            List<ProjectSigningDto> projectSigningDtos = projectSigningService.searchByParam(new HashMap<>());

            LocalDate currentDate = LocalDate.now();

            for (ProjectSigningDto projectSigningDto : projectSigningDtos) {
                // 获取项目登记通过日期
                String commencementOfContract = projectSigningDto.getCommencementOfContract();

                if (commencementOfContract == null) {
                    continue;
                }

                LocalDate commencementOfContractDate = LocalDate.parse(commencementOfContract);

                //根据登记id查出提示信息
                PromptDto promptDto = promptService.getByQid(Long.valueOf(projectSigningDto.getProjectNumber()),"ProjectImplement");
                //查询项目实施有没有项目信息
                ProjectImplementDto projectImplementDto = projectImplementService.getProjectNumber(projectSigningDto.getProjectNumber());
                //判断提示信息不为空且删除状态未已删除、投标信息为空
                if (promptDto != null && projectImplementDto == null && promptDto.getDeleteStatus().equals("1")) {
                    log.info("进来了update：" + projectSigningDto);
                    //已经删除的提醒，如果未签约继续提醒
                    PromptUpdateCmd promptUpdateCmd = new PromptUpdateCmd();
                    promptUpdateCmd.setDeleteStatus("0"); // 0未删  1已删
                    promptUpdateCmd.setExpireDate(commencementOfContractDate);
                    promptUpdateCmd.setId(promptDto.getId());
                    promptService.update(promptUpdateCmd);
                } else if (currentDate.isAfter(commencementOfContractDate) || currentDate.isEqual(commencementOfContractDate)) {
                    if (projectImplementDto == null && promptDto == null){
                        log.info("进来了add：" + projectSigningDto);
                        // 需要提醒
                        // 查询提示信息
                        String promptMessage = "请尽快对"+projectSigningDto.getProjectName()+"项目进行实施！";

                        // 构建 PromptAddCmd
                        PromptAddCmd addCmd = new PromptAddCmd();
                        addCmd.setPromptTitle("项目实施提醒");
                        addCmd.setPromptId(projectSigningDto.getCreatedBy());
                        addCmd.setPromptDetails(promptMessage);
                        addCmd.setExpireDate(LocalDate.parse(projectSigningDto.getCommencementOfContract()));
                        addCmd.setCreatedBy(projectSigningDto.getCreatedBy());
                        addCmd.setQid(Long.valueOf(projectSigningDto.getProjectNumber()));
                        addCmd.setCreatedName(projectSigningDto.getCreatedName());
                        addCmd.setPromptStatus("warning");
                        addCmd.setDeleteStatus("0"); // 0未删  1已删
                        addCmd.setPromptPath("ProjectImplement");

                        // 添加提示信息
                        promptService.add(addCmd);
                    }
                }
            }

            log.info("项目实施定时任务执行完成");
        } catch (Exception e) {
            log.error("项目实施定时任务执行报错！{}", e);
            e.printStackTrace();
        }
    }
}
