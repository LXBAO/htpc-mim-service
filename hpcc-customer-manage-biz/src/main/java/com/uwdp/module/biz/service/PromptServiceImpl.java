package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.IPromptService;
import com.uwdp.module.api.service.IRequestLogService;
import com.uwdp.module.api.vo.cmd.PromptAddCmd;
import com.uwdp.module.api.vo.cmd.PromptUpdateCmd;
import com.uwdp.module.api.vo.cmd.RequestLogAddCmd;
import com.uwdp.module.api.vo.dto.PromptDto;
import com.uwdp.module.api.vo.excel.PromptExcelExport;
import com.uwdp.module.api.vo.excel.PromptExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.PromptAssembler;
import com.uwdp.module.biz.infrastructure.entity.PromptDo;
import com.uwdp.module.biz.infrastructure.excel.PromptExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.PromptRepository;
import com.uwdp.module.biz.utils.DateUtil;
import com.uwdp.module.biz.utils.HttpUtils;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 信息提示 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PromptServiceImpl implements IPromptService {

    private final PromptRepository promptRepository;

    private final PromptAssembler promptAssembler;

    private final BeanSearcher beanSearcher;

    private final IRequestLogService requestLogService;

    @Value("${hpcc-mim-server.sysCode}")
    public String sysCode;

    @Value("${hpcc-mim-server.url}")
    public String url;

    @Value("${hpcc-mim-server.oaUrl}")
    public String oaUrl;

    @Override
    public SearchResult<PromptDto> pageByParam(Map<String, Object> paraMap) {
        return beanSearcher.search(PromptDto.class, paraMap);
    }

    @Override
    public List<PromptDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(PromptDto.class, paraMap);
    }

    @Override
    public List<PromptDto> listByIds(List<Long> idList) {
        List<PromptDo> list = promptRepository.list(new LambdaQueryWrapper<PromptDo>()
                .in(PromptDo::getId, idList));
        return promptAssembler.toValueObjectList(list);
    }

    @Override
    public PromptDto get(Long id) {
        PromptDo promptDo = promptRepository.getOne(new LambdaQueryWrapper<PromptDo>()
                .eq(PromptDo::getId, id));
        PromptDto promptDTO = promptAssembler.toValueObject(promptDo);
        return promptDTO;
    }
    @Override
    public PromptDto getByQid(Long qid,String promptPath) {
        PromptDo promptDo = promptRepository.getOne(new LambdaQueryWrapper<PromptDo>()
                .eq(PromptDo::getQid, qid).eq(PromptDo::getPromptPath,promptPath));
        PromptDto promptDTO = promptAssembler.toValueObject(promptDo);
        return promptDTO;
    }

    @Override
    public PromptDto getByQid(Long qid) {
        PromptDo promptDo = promptRepository.getOne(new LambdaQueryWrapper<PromptDo>()
                .eq(PromptDo::getQid, qid));
        PromptDto promptDTO = promptAssembler.toValueObject(promptDo);
        return promptDTO;
    }

    @Override
    public PromptAddCmd add(PromptAddCmd addCmd) {
        PromptDo promptDo = promptAssembler.toDO(addCmd);
        promptRepository.save(promptDo);
        addCmd.setId(promptDo.getId());
        return addCmd;
    }

    @Override
    public void update(PromptUpdateCmd updateCmd) {
        PromptDto promptDTO = this.get(updateCmd.getId());
        if (promptDTO != null) {
            PromptDo promptDo = promptAssembler.toDO(updateCmd);
            promptRepository.updateById(promptDo);
        }
    }

    @Override
    public void delete(Long id) {
        promptRepository.remove(new LambdaQueryWrapper<PromptDo>()
                .eq(PromptDo::getId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            promptRepository.remove(new LambdaQueryWrapper<PromptDo>()
                    .in(PromptDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), PromptExcelExport.class, "信息提示导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<PromptExcelExport> searchResult = beanSearcher.searchAll(PromptExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, PromptExcelExport.class, "信息提示数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<PromptExcelImport> excelParse(MultipartFile file) {
        PromptExcelImportListener listener = new PromptExcelImportListener();
        ExcelUtil.readFile(file, PromptExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<PromptExcelImport> list) {
        List<PromptDo> promptDoList = promptAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(promptDoList)) {
            promptRepository.saveBatch(promptDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<PromptExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, PromptExcelExport.class, "信息提示错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Transactional
    @Override
    public String haveReadByOa(Long id, String promptId, Integer flag) {
        return toBeDoneToOa(id, "2", promptId, flag, 1);
    }

    @Override
    public String toBeDoneToOa(Long id, String isremark, String promptId, Integer flag, Integer isRead) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("syscode", sysCode);
        bodyMap.put("flowid", id.toString());
        String flagName = "";
        if(flag == 0){
            flagName = "CA认证平台证书";
        }else if(flag == 1){
            flagName = "招标平台";
        }else{
            flagName = "资质信息";
        }
        bodyMap.put("requestname", String.format("[市场管理] 您的%s即将到期。", flagName));
        bodyMap.put("nodename", "领导审批");
        bodyMap.put("workflowname", "到期提醒");
        bodyMap.put("pcurl", url + "/reminderDetails?id=" + id + "&promptId=" + promptId + "&flag=" + flag + "&isRead=" + isRead);
        bodyMap.put("appurl", url + "/reminderDetails?id=" + id + "&promptId=" + promptId + "&flag=" + flag + "&isRead=" + isRead);
        bodyMap.put("isremark", isremark);
        bodyMap.put("viewtype", "0");
        bodyMap.put("creator", "77265");
        bodyMap.put("createdatetime", DateUtil.formatDateYMDHMS(new Date()));
        bodyMap.put("receiver", "77265");
        bodyMap.put("receivedatetime", DateUtil.formatDateYMDHMS(new Date()));
        bodyMap.put("receivets", String.valueOf(new Date().getTime()));

        String body = HttpUtils.postJson(oaUrl, new HashMap<>(), new JSONObject(bodyMap).toString());

        // 保存日志
        RequestLogAddCmd addCmd = new RequestLogAddCmd();
        addCmd.setRequestUrl(oaUrl);
        addCmd.setRequestParam(new JSONObject(bodyMap).toString());
        addCmd.setResponseParam(body);
        addCmd.setCtime(LocalDateTime.now());

        requestLogService.add(addCmd);

        return body;
    }
}
