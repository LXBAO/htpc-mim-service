package com.uwdp.module.biz.service;


import cn.hutool.core.convert.Convert;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.uwdp.module.api.service.ISysJobService;
import com.uwdp.module.api.vo.dto.SysJobDto;
import com.uwdp.module.biz.infrastructure.job.TaskException;
import com.uwdp.module.biz.infrastructure.mapper.SysJobMapper;
import com.uwdp.module.biz.utils.CronUtils;
import com.uwdp.module.biz.utils.ScheduleConstants;
import com.uwdp.module.biz.utils.ScheduleUtils;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度信息 服务层
 *
 * @author ruoyi
 */
@Service
public class SysJobServiceImpl implements ISysJobService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private SysJobMapper jobMapper;

    @Autowired
    private BeanSearcher beanSearcher;

    /**
     * 项目启动时，初始化定时器
     * 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException {
        scheduler.clear();
        List<SysJobDto> jobList = jobMapper.selectJobAll();
        for (SysJobDto job : jobList) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }

    /**
     * 获取quartz调度器的计划任务列表
     *
     * @param paraMap@return
     */
    @Override
    public SearchResult<SysJobDto> selectJobList(Map<String, Object> paraMap) {
        return beanSearcher.search(SysJobDto.class, paraMap);
    }

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public SysJobDto selectJobById(Long jobId) {
        return jobMapper.selectJobById(jobId);
    }

    /**
     * 暂停任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int pauseJob(SysJobDto job) {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            try {
                scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        }

        return rows;
    }

    /**
     * 恢复任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resumeJob(SysJobDto job) {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            try {
                scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        }
        return rows;
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteJob(SysJobDto job) {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        int rows = jobMapper.deleteJobById(jobId);
        if (rows > 0) {
            try {
                scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        }
        return rows;
    }

    /**
     * 批量删除调度信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJobByIds(String ids) {
        Long[] jobIds = Convert.toLongArray(ids);
        for (Long jobId : jobIds) {
            SysJobDto job = jobMapper.selectJobById(jobId);
            deleteJob(job);
        }
    }

    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeStatus(SysJobDto job) {
        int rows = 0;
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
            rows = resumeJob(job);
        } else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
            rows = pauseJob(job);
        }
        return rows;
    }

    /**
     * 立即运行任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean run(SysJobDto job) {
        boolean result = false;
        Long jobId = job.getJobId();
        SysJobDto tmpObj = selectJobById(job.getJobId());
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, tmpObj);
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, tmpObj.getJobGroup());
        try {
            if (scheduler.checkExists(jobKey)) {
                result = true;
                scheduler.triggerJob(jobKey, dataMap);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 新增任务
     *
     * @param job 调度信息 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertJob(SysJobDto job){
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = jobMapper.insertJob(job);
        if (rows > 0) {
            try {
                ScheduleUtils.createScheduleJob(scheduler, job);
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            } catch (TaskException e) {
                throw new RuntimeException(e);
            }
        }
        return rows;
    }

    /**
     * 更新任务的时间表达式
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateJob(SysJobDto job){
        SysJobDto properties = selectJobById(job.getJobId());
        int rows = jobMapper.updateJob(job);
        if (rows > 0) {
            try {
                updateSchedulerJob(job, properties.getJobGroup());
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            } catch (TaskException e) {
                throw new RuntimeException(e);
            }
        }
        return rows;
    }

    /**
     * 更新任务
     *
     * @param job      任务对象
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(SysJobDto job, String jobGroup) throws SchedulerException, TaskException {
        Long jobId = job.getJobId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }
}