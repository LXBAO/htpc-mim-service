package com.uwdp.timed.projectmanagerledger.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.jdbcutils.DatabaseReaderUtil;
import com.uwdp.module.biz.infrastructure.entity.ProjectManagerLedgerDo;
import com.uwdp.module.biz.infrastructure.repository.ProjectManagerLedgerRepository;
import com.uwdp.timed.projectmanagerledger.utils.PMSyncUtil;
import com.uwdp.utils.StringUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hpcc-customer-manage/timed/pm/sync")
@Api(tags = "定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class ScheduledTaskController {
//    private boolean isTaskRunning = false;
//
//    private Long timing = 30L;
//
//    private ScheduledExecutorService executorService;
//
////    @PostConstruct
////    public void initExecutorService() {
////        // 初始化定时任务执行器
////        executorService = Executors.newSingleThreadScheduledExecutor();
////        // 开始执行定时任务
////        if (!executorService.isShutdown()) {
////            startTask();
////        }
////    }
//
//    @GetMapping("/startTask/{timing}")
//    public void startTask(@PathVariable Long timing) {
//        if (executorService == null || executorService.isShutdown()) {
//            executorService = Executors.newSingleThreadScheduledExecutor();
//        }
//        if (!isTaskRunning) {
//            isTaskRunning = true;
//        }
//        if (timing == null) {
//            timing = this.timing;
//        }
//        executorService.scheduleAtFixedRate(this::executeTask, 0, timing, TimeUnit.MINUTES);
//    }
//
//    @GetMapping("/stopTask")
//    public void stopTask() {
//        if (isTaskRunning) {
//            executorService.shutdown();
//            isTaskRunning = false;
//        }
//        log.info("{}-项目经理，九大员人资同步停止！！！", new Date());
//    }
//
//    @Autowired
//    public DatabaseReaderUtil databaseReaderUtil;
//
//    @Autowired
//    public PMSyncUtil pmSyncUtil;
//
//    @Autowired
//    public ProjectManagerLedgerRepository projectManagerLedgerRepository;
//
//    @Autowired
//    public StringUtils stringUtils;
//
//    /**
//     * 定时同步人资中项目经理数据
//     */
//    private void executeTask() {
//        // 定时任务执行的逻辑
//        log.info("{}-同步人资系统项目经理数据,定时任务执行中...", new Date());
//        String sql = "select flex.data,flex.id,emp.name,emp.number,emp.mobile,emp.identity_card from " +
//                "employee_subset_flex_data flex left join employee emp on flex.employee_id=emp.id where flex.model_name='employee_dynamic_subset.practice_qualification'";
//        List<Map> list = databaseReaderUtil.findData("火电", sql);
//        try {
//
//
//            for (Map map : list) {
//                ProjectManagerLedgerDo cmd = pmSyncUtil.mapToObject(map);
//                cmd.setFdPersonnelType("项目经理");
//                String hrId = cmd.getHrId();//关联人资id
//                String fdJobNumber = cmd.getFdJobNumber();//工号
//                String mdmSql = "select per.groupBelongDepartmentName,per.groupBelongPostName," +
//                        "per.groupBelongUnitName,post.postProperty,post.groupPostCategory" +
//                        " from T_MDMPERSON per left join T_MDMPOST post on post.groupPostCode=" +
//                        "per.groupBelongPostCode where per.personCode='" + fdJobNumber + "'";
//                List<Map> mdmList = databaseReaderUtil.findData("local", mdmSql);
//                if (mdmList != null && !mdmList.isEmpty()) {
//                    Map mdmMap = mdmList.get(0);
//                    String dept = stringUtils.getStr(mdmMap.get("groupbelongdepartmentname"));//部门
//                    String post = stringUtils.getStr(mdmMap.get("groupbelongpostname"));//岗位
//                    String unit = stringUtils.getStr(mdmMap.get("groupbelongunitname"));//单位
//                    String postProperty = stringUtils.getStr(mdmMap.get("postproperty"));//岗位性质
//                    String category = stringUtils.getStr(mdmMap.get("grouppostcategory"));//岗位类别
//                    cmd.setFdUnit(unit);//单位
//                    cmd.setFdDepartment(dept);//部门
//                    cmd.setFdPosition(post);//岗位
//                    cmd.setFdNatureOfPost(postProperty);//岗位性质
//                    cmd.setFdJobLevel(category);//岗位层级
//                }
//                String findSql = "select * from t_projectmanagerledger where hrid='" + hrId + "'";
//                List<Map> findList = databaseReaderUtil.findData("local", findSql);
//                if (findList != null && findList.size() > 0) {
//                    Map dataMap = findList.get(0);
//                    cmd.setFdInputType(stringUtils.getStr(dataMap.get("fdinputtype")));
//                    Optional.of(
//                            projectManagerLedgerRepository.update(cmd, new LambdaQueryWrapper<ProjectManagerLedgerDo>()
//                                    .eq(ProjectManagerLedgerDo::getHrId, hrId))
//                    ).orElseThrow(
//                            () -> new BizRuntimeException(
//                                    "根据关联人资id:{" + hrId + "}找不到对应的数据", new Exception()));
//                } else {
//                    cmd.setFdInputType("未使用");
//                    Optional.of(
//                            projectManagerLedgerRepository.save(cmd)
//                    ).orElseThrow(
//                            () -> new BizRuntimeException(
//                                    "根据关联人资id:{" + hrId + "}找不到对应的数据", new Exception()));
//                }
//            }
//            log.info("{}-同步人资系统项目经理数据,定时任务执行完成！！！", new Date());
//            executeTask0();
//        } catch (Exception e) {
//            log.error("同步人资系统项目经理数据,定时任务执行报错！{}", e);
//            e.fillInStackTrace();
//        }
//    }
//
//    /**
//     * 定时同步人资中九大员数据
//     */
//    private void executeTask0() {
//        // 定时任务执行的逻辑
//        log.info("{}-同步人资系统九大员数据,定时任务执行中...", new Date());
//        String sql = "select flex.data,flex.id,emp.name,emp.number,emp.mobile,emp.identity_card from " +
//                "employee_subset_flex_data flex left join employee emp on flex.employee_id=emp.id where flex.model_name='employee_dynamic_subset.qualifications_information'";
//        List<Map> list = databaseReaderUtil.findData("火电", sql);
//        try {
//            for (Map map : list) {
//                ProjectManagerLedgerDo cmd = pmSyncUtil.mapToObject(map);
//                cmd.setFdPersonnelType("九大员");
//                String hrId = cmd.getHrId();//关联人资id
//                String fdJobNumber = cmd.getFdJobNumber();//工号
//                String mdmSql = "select per.groupBelongDepartmentName,per.groupBelongPostName," +
//                        "per.groupBelongUnitName,post.postProperty,post.groupPostCategory" +
//                        " from T_MDMPERSON per left join T_MDMPOST post on post.groupPostCode=" +
//                        "per.groupBelongPostCode where per.personCode='" + fdJobNumber + "'";
//                List<Map> mdmList = databaseReaderUtil.findData("local", mdmSql);
//                if (mdmList != null && !mdmList.isEmpty()) {
//                    Map mdmMap = mdmList.get(0);
//                    String dept = stringUtils.getStr(mdmMap.get("groupbelongdepartmentname"));//部门
//                    String post = stringUtils.getStr(mdmMap.get("groupbelongpostname"));//岗位
//                    String unit = stringUtils.getStr(mdmMap.get("groupbelongunitname"));//单位
//                    String postProperty = stringUtils.getStr(mdmMap.get("postproperty"));//岗位性质
//                    String category = stringUtils.getStr(mdmMap.get("grouppostcategory"));//岗位类别
//                    cmd.setFdUnit(unit);//单位
//                    cmd.setFdDepartment(dept);//部门
//                    cmd.setFdPosition(post);//岗位
//                    cmd.setFdNatureOfPost(postProperty);//岗位性质
//                    cmd.setFdJobLevel(category);//岗位层级
//                }
//                String findSql = "select * from t_projectmanagerledger where hrid='" + hrId + "'";
//                List<Map> findList = databaseReaderUtil.findData("local", findSql);
//                if (findList != null && findList.size() > 0) {
//                    Map dataMap = findList.get(0);
//                    cmd.setFdInputType(stringUtils.getStr(dataMap.get("fdinputtype")));
//                    Optional.of(
//                            projectManagerLedgerRepository.update(cmd, new LambdaQueryWrapper<ProjectManagerLedgerDo>()
//                                    .eq(ProjectManagerLedgerDo::getHrId, hrId))
//                    ).orElseThrow(
//                            () -> new BizRuntimeException(
//                                    "根据关联人资id:{" + hrId + "}找不到对应的数据", new Exception()));
//                } else {
//                    cmd.setFdInputType("未使用");
//                    Optional.of(
//                            projectManagerLedgerRepository.save(cmd)
//                    ).orElseThrow(
//                            () -> new BizRuntimeException(
//                                    "根据关联人资id:{" + hrId + "}找不到对应的数据", new Exception()));
//                }
//            }
//            log.info("{}-同步人资系统九大员数据,定时任务执行完成！！！", new Date());
//        } catch (Exception e) {
//            log.error("同步人资系统项目经理数据,定时任务执行报错！{}", e);
//            e.fillInStackTrace();
//        }
//    }
}
