package com.uwdp.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;
import com.gientech.lcds.workflow.sdk.core.call.dto.ProcessCall;
import com.gientech.lcds.workflow.sdk.core.call.event.ProcessCallEvent;
import com.uwdp.module.api.service.ILmcWorkflowTimelineService;
import com.uwdp.module.api.vo.cmd.LmcWorkflowTimelineAddCmd;
import com.uwdp.module.api.vo.enums.WorkflowStatusEnums;
import com.uwdp.module.biz.infrastructure.entity.LmcWorkflowDo;
import com.uwdp.module.biz.infrastructure.repository.LmcWorkflowRepository;
import com.uwdp.workflow.enums.BizCode;
import com.uwdp.workflow.service.iservice.IWorkflowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddClientProcessCallService implements IWorkflowService {

    public static final String PROCESS_KEY = "addClient"; /*流程编码*/

//    @Resource
    private final LmcWorkflowRepository lmcWorkflowRepository;

//    @Autowired
    private final ILmcWorkflowTimelineService lmcWorkflowTimelineService;

    @Override
    public void passed(ProcessCallEvent processCallEvent) {
        ProcessCall data = processCallEvent.getData();
        if (PROCESS_KEY.equals(data.getProcessKey())) {
            addTimeLine(processCallEvent, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_COMPLETED.getValue());
        }
        log.info("====新增客户流程结束");
    }

    @Override
    public void backed(ProcessCallEvent processCallEvent) {
        ProcessCall data = processCallEvent.getData();
        if (PROCESS_KEY.equals(data.getProcessKey())) {
            addTimeLine(processCallEvent, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_BACK.getValue());
        }
        log.info("====新增客户流程驳回");
    }

    @Override
    public void vetoed(ProcessCallEvent processCallEvent) {
        ProcessCall data = processCallEvent.getData();
        if (PROCESS_KEY.equals(data.getProcessKey())) {
            addTimeLine(processCallEvent, WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_VETO.getValue());
        }
        log.info("====新增客户流程否决");
    }

    @Async
    public void addTimeLine(ProcessCallEvent processCallEvent, String status) {

        log.info("==updatingWorkflowStatus:{}", status);
        ProcessCall data = processCallEvent.getData();

        lmcWorkflowRepository.update(new LambdaUpdateWrapper<LmcWorkflowDo>()
                .eq(LmcWorkflowDo::getProcessInstanceId, data.getProcessInstanceId())
                .eq(LmcWorkflowDo::getBizId, data.getBusinessKey())
                .set(LmcWorkflowDo::getEndTime, data.getDate())
                .set(LmcWorkflowDo::getWorkflowStatus, status)
                .set(LmcWorkflowDo::getUpdatedTime, LocalDateTime.now())
        );

        log.info("==updatedWorkflowStatus:{}", status);

//
//        log.info("==addtimeline:{}", status);
//
//        LmcWorkflowTimelineAddCmd timelineAddCmd = new LmcWorkflowTimelineAddCmd();
////        timelineAddCmd.setId(0L);
//        timelineAddCmd.setCreatedBy("weixiangxian");
//        timelineAddCmd.setCreatedTime(LocalDateTime.now());
//        timelineAddCmd.setUpdatedBy("weixiangxian");
//        timelineAddCmd.setUpdatedTime(LocalDateTime.now());
////        timelineAddCmd.setWorkflowId(workflowAddCmd.getId().toString());
//        timelineAddCmd.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
//        timelineAddCmd.setTriggerTime(LocalDateTime.now());
//        timelineAddCmd.setProcessCode(null);
//        timelineAddCmd.setProcessName(null);
//        timelineAddCmd.setProcessInstanceId(data.getProcessInstanceId());
//        timelineAddCmd.setProcessCallEventId(null);
////        timelineAddCmd.setAppId();
//        timelineAddCmd.setOperatorCode("weixiangxian");
//        timelineAddCmd.setOperatorName("魏向贤");
//        timelineAddCmd.setCurrentTaskId(null);
//        timelineAddCmd.setCurrentTaskKey(null);
//        timelineAddCmd.setCurrentTaskName(null);
//        timelineAddCmd.setBizCode(BizCode.ADD_CLIENT);
////        timelineAddCmd.setBizId(String.valueOf(fdId));
//        timelineAddCmd.setReason("审批通过回调");
//        timelineAddCmd.setSucceed(1);
//        timelineAddCmd.setErrorMsg(null);
//        timelineAddCmd.setErrorText(null);
//        timelineAddCmd.setVersion(null);
//        timelineAddCmd.setDeleted(null);
//        timelineAddCmd.setCreateUserCode("weixiangxian");
//        timelineAddCmd.setCreateUserName("魏向贤");
//        timelineAddCmd.setCreateTime(LocalDateTime.now());
//        timelineAddCmd.setUpdateUserCode("weixiangxian");
//        timelineAddCmd.setUpdateUserName("魏向贤");
//        timelineAddCmd.setUpdateTime(LocalDateTime.now());
//
//        lmcWorkflowTimelineService.add(timelineAddCmd);
    }


    @Async
    public void test(String status) {

        log.info("====test:{}",status);

            ProcessCall data = new ProcessCall();
            data.setProcessInstanceId("1670262169479225345");
            data.setBusinessKey("1670262168659795969");
            data.setDate(LocalDateTime.now());


        String sql = "select * FROM T_LMCWORKFLOW WHERE (PROCESSINSTANCEID = \"1670262169479225345\" AND BIZID = \"1670262168659795969\")";
        Map<String, Object> stringObjectMap = SqlRunner.db().selectOne(sql, LmcWorkflowDo.class);

        log.info("====testSQL:{}",sql);
        log.info("====test查询结果:{}",stringObjectMap);

//        LmcWorkflowDo one = lmcWorkflowRepository.getOne(new LambdaQueryWrapper<LmcWorkflowDo>()
//                .eq(LmcWorkflowDo::getProcessInstanceId, data.getProcessInstanceId())
//                .eq(LmcWorkflowDo::getBizId, data.getBusinessKey())
//        );
//
//        log.info("====更新状态的客户信息:{}",one);

//        lmcWorkflowRepository.update(new LambdaUpdateWrapper<LmcWorkflowDo>()
//                .eq(LmcWorkflowDo::getProcessInstanceId, data.getProcessInstanceId())
//                .eq(LmcWorkflowDo::getBizId, data.getBusinessKey())
//                .set(LmcWorkflowDo::getEndTime, data.getDate())
//                .set(LmcWorkflowDo::getWorkflowStatus, status)
//                .set(LmcWorkflowDo::getUpdatedTime, LocalDateTime.now())
//        );
//
//        log.info("==addtimeline:{}", status);
//
//        LmcWorkflowTimelineAddCmd timelineAddCmd = new LmcWorkflowTimelineAddCmd();
////        timelineAddCmd.setId(0L);
//        timelineAddCmd.setCreatedBy("weixiangxian");
//        timelineAddCmd.setCreatedTime(LocalDateTime.now());
//        timelineAddCmd.setUpdatedBy("weixiangxian");
//        timelineAddCmd.setUpdatedTime(LocalDateTime.now());
////        timelineAddCmd.setWorkflowId(workflowAddCmd.getId().toString());
//        timelineAddCmd.setWorkflowStatus(WorkflowStatusEnums.WORKFLOW_STATUS_ENUMS_ACTIVE.getValue());
//        timelineAddCmd.setTriggerTime(LocalDateTime.now());
//        timelineAddCmd.setProcessCode(null);
//        timelineAddCmd.setProcessName(null);
//        timelineAddCmd.setProcessInstanceId(data.getProcessInstanceId());
//        timelineAddCmd.setProcessCallEventId(null);
////        timelineAddCmd.setAppId();
//        timelineAddCmd.setOperatorCode("weixiangxian");
//        timelineAddCmd.setOperatorName("魏向贤");
//        timelineAddCmd.setCurrentTaskId(null);
//        timelineAddCmd.setCurrentTaskKey(null);
//        timelineAddCmd.setCurrentTaskName(null);
//        timelineAddCmd.setBizCode(BizCode.ADD_CLIENT);
////        timelineAddCmd.setBizId(String.valueOf(fdId));
//        timelineAddCmd.setReason("审批通过回调");
//        timelineAddCmd.setSucceed(1);
//        timelineAddCmd.setErrorMsg(null);
//        timelineAddCmd.setErrorText(null);
//        timelineAddCmd.setVersion(null);
//        timelineAddCmd.setDeleted(null);
//        timelineAddCmd.setCreateUserCode("weixiangxian");
//        timelineAddCmd.setCreateUserName("魏向贤");
//        timelineAddCmd.setCreateTime(LocalDateTime.now());
//        timelineAddCmd.setUpdateUserCode("weixiangxian");
//        timelineAddCmd.setUpdateUserName("魏向贤");
//        timelineAddCmd.setUpdateTime(LocalDateTime.now());
//
//        lmcWorkflowTimelineService.add(timelineAddCmd);
    }

    public boolean runSql(String sql){

        Class<LmcWorkflowDo> entityClass = LmcWorkflowDo.class;
        //        // INFO: DCTANT: 2022/9/29 使用MybatisPlus自己的SqlHelper获取SqlSessionFactory
        //        SqlSessionFactory sqlSessionFactory = SqlHelper.sqlSessionFactory(LmcWorkflowDo.class);
        //        // INFO: DCTANT: 2022/9/29 通过SqlSessionFactory创建一个新的SqlSession，并获取全局配置
        //        SqlSession sqlSession = sqlSessionFactory.openSession();
        //        Configuration configuration = sqlSessionFactory.getConfiguration();
        //
        ////        RawSqlSource rawSqlSource = new RawSqlSource(configuration, sql, Object.class);
        ////        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, sqlUuid, rawSqlSource, SqlCommandType.SELECT);
        ////        ArrayList<ResultMap> resultMaps = new ArrayList<>();
        ////        // INFO: DCTANT: 2022/9/29 创建返回映射
        ////        ResultMap.Builder resultMapBuilder = new ResultMap.Builder(configuration, UUID.fastUUID().toString(true), entityClass, new ArrayList<>());
        ////        ResultMap resultMap = resultMapBuilder.build();
        ////        resultMaps.add(resultMap);
        ////        builder.resultMaps(resultMaps);
        ////
        ////        MappedStatement mappedStatement = builder.build();
        ////        // INFO: DCTANT: 2022/9/29 将创建的MappedStatement注册到配置中
        ////        configuration.addMappedStatement(mappedStatement);
        ////        // INFO: DCTANT: 2022/9/29 使用SqlSession查询原生SQL
        ////        List<ExampleEntity> list = sqlSession.selectList(sqlUuid);
        //        // INFO: DCTANT: 2022/9/29 关闭session
        sqlSession.close();
        return true;
    }
}
