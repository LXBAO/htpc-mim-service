package com.uwdp.module.api.service;

import com.ejlchina.searcher.SearchResult;
import com.uwdp.module.api.vo.dto.SysJobDto;

import java.util.List;
import java.util.Map;

/**
 * 定时任务调度信息信息 服务层
 *
 * @author ruoyi
 */
public interface ISysJobService {
    /**
     * 获取quartz调度器的计划任务
     *
     * @param paraMap@return 调度任务集合
     */
    public SearchResult<SysJobDto> selectJobList(Map<String, Object> paraMap);

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    public SysJobDto selectJobById(Long jobId);

    /**
     * 暂停任务
     *
     * @param job 调度信息
     * @return 结果
     */
    public int pauseJob(SysJobDto job);

    /**
     * 恢复任务
     *
     * @param job 调度信息
     * @return 结果
     */
    public int resumeJob(SysJobDto job);

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     * @return 结果
     */
    public int deleteJob(SysJobDto job);

    /**
     * 批量删除调度信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public void deleteJobByIds(String ids);

    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     * @return 结果
     */
    public int changeStatus(SysJobDto job);

    /**
     * 立即运行任务
     *
     * @param job 调度信息
     * @return 结果
     */
    public boolean run(SysJobDto job);

    /**
     * 新增任务
     *
     * @param job 调度信息
     * @return 结果
     */
    public int insertJob(SysJobDto job);

    /**
     * 更新任务
     *
     * @param job 调度信息
     * @return 结果
     */
    public int updateJob(SysJobDto job);

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    public boolean checkCronExpressionIsValid(String cronExpression);
}