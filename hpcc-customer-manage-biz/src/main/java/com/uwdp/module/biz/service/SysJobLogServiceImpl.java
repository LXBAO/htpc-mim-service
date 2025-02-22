package com.uwdp.module.biz.service;

import cn.hutool.core.convert.Convert;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.uwdp.module.api.service.ISysJobLogService;
import com.uwdp.module.api.vo.dto.SysJobLogDto;
import com.uwdp.module.biz.infrastructure.mapper.SysJobLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 定时任务调度日志信息 服务层
 *
 * @author ruoyi
 */
@Service
public class SysJobLogServiceImpl implements ISysJobLogService {
    @Autowired
    private SysJobLogMapper jobLogMapper;
    @Autowired
    private BeanSearcher beanSearcher;

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLogDto 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public SearchResult<SysJobLogDto> selectJobLogList(Map<String, Object> paraMap) {
        return beanSearcher.search(SysJobLogDto.class, paraMap);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLogDto selectJobLogById(Long jobLogId) {
        return jobLogMapper.selectJobLogById(jobLogId);
    }

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    @Override
    public void addJobLog(SysJobLogDto jobLog) {
        jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * 批量删除调度日志信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobLogByIds(String ids) {
        return jobLogMapper.deleteJobLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     */
    @Override
    public int deleteJobLogById(Long jobId) {
        return jobLogMapper.deleteJobLogById(jobId);
    }

    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog() {
        jobLogMapper.cleanJobLog();
    }
}
