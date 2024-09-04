package com.uwdp.timed.projectmanagerledger.controller;

import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.uwdp.module.api.service.ICALedgerService;
import com.uwdp.module.api.service.ILogSheetService;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.dto.LogSheetDto;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.dto.searcher.CALedgerCerInfoDto;
import com.uwdp.module.biz.infrastructure.entity.LogSheetDo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hpcc-customer-manage/timed/test/sync")
@Api(tags = "定时任务")
@RequiredArgsConstructor
@OperationModule(type = ModuleTypeEnum.APP, business = "定时任务")
@Validated
@Slf4j
public class TestTaskController {
    /**
     * 获得最新的版本方法
     * @param currentVersion 当前版本
     * @return 最新版本
     */
    private String getNextVersion(String currentVersion) {
        //如果是第一次返回v1
        if (currentVersion == null || currentVersion.isEmpty()) {
            return "v1";
        }

        //截取v后面的数字加一
        int versionNumber = Integer.parseInt(currentVersion.substring(1));
        return "v" + (versionNumber + 1);
    }

    private final ILogSheetService logSheetService;

    @GetMapping("/test")
    public void test() {
        Map map = new HashMap();
        map.put("clientId","1738018326480965634");

        List<LogSheetDto> searchByParam = logSheetService.searchByParam(map);
        LogSheetDto latestLogSheet = findLatestLogSheet(searchByParam);
        System.out.println(latestLogSheet.getVersion());
    }

    private LogSheetDto findLatestLogSheet(List<LogSheetDto> logSheets) {
        if (logSheets.isEmpty()) {
            return null; // 如果列表为空，返回 null 或者根据业务需求进行处理
        }

        LogSheetDto latestLogSheet = logSheets.get(0);
        System.out.println("aaa"+latestLogSheet);

        for (LogSheetDto logSheet : logSheets) {
            System.out.println(logSheet);
            System.out.println(latestLogSheet);
            if(latestLogSheet != logSheet){
                latestLogSheet = logSheet;
            }
            if (logSheet.getUpdatedTime().isAfter(latestLogSheet.getUpdatedTime())) {
                latestLogSheet = logSheet; // 更新最新的对象
            }
        }

        return latestLogSheet;
    }
//    private boolean isTaskRunning = false;
//
//    private Long timing = 1L;
//    private ScheduledExecutorService executorService;
//
//    private final IPromptService promptService;
//
//    //----------------------------------------------------------------
//    @GetMapping("/startTask/{timing}")
//    public void startTask(@PathVariable Long timing) {
//        if (executorService == null || executorService.isShutdown()) {
//            executorService = Executors.newSingleThreadScheduledExecutor();
//        }
//        if (!isTaskRunning) {
//            isTaskRunning = true;
//        }else{
//            executorService.shutdown();
//        }
//        if (timing == null) {
//            timing = this.timing;
//        }
//        executorService.scheduleAtFixedRate(this::executeTask, 0, timing, TimeUnit.SECONDS);
//    }
//

//
//    private final ICALedgerService cALedgerService;
//
//    public void executeTask() {
//       log.info("执行定时任务!");
//    }


}
