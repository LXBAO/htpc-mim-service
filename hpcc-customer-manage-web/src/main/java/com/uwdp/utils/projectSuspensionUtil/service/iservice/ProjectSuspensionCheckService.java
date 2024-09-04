package com.uwdp.utils.projectSuspensionUtil.service.iservice;

import java.util.Map;

public interface ProjectSuspensionCheckService {
    /**
     * 中止时校验项目关联流程是否全部审批通过
     * @param id 项目id
     * @return true/false
     */
    Map checkWorkflow(String id);

    /**
     * 选择中止时校验该项目是否已经发起过中止
     * @param id 项目id
     * @return true/false
     */
    boolean checkIsNull(String id);
}
