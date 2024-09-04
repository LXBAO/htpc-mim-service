package com.uwdp.module.biz.utils;


import com.uwdp.module.api.vo.dto.SysJobDto;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 * 
 * @author ruoyi
 *
 */
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJobDto sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
