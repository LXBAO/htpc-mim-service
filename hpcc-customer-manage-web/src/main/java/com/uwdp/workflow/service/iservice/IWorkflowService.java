package com.uwdp.workflow.service.iservice;

import com.gientech.lcds.workflow.sdk.core.call.event.ProcessCallEvent;
import com.uwdp.module.api.service.ILmcWorkflowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Service;

@Service
public interface IWorkflowService {

    void passed(ProcessCallEvent processCallEvent);

    void backed(ProcessCallEvent processCallEvent);

    void vetoed(ProcessCallEvent processCallEvent);

}
