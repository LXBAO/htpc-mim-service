package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.gientech.lcds.workflow.sdk.core.call.dto.TaskCall;
import com.google.common.collect.Maps;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.service.IMarketDmpTagDetailService;
import com.uwdp.module.api.service.IProjectEnableDetailService;
import com.uwdp.module.api.service.IProjectEnableService;
import com.uwdp.module.api.vo.cmd.ProjectEnableAddCmd;
import com.uwdp.module.api.vo.cmd.ProjectEnableUpdateCmd;
import com.uwdp.module.api.vo.cmd.VisitPlanUpdateCmd;
import com.uwdp.module.api.vo.dto.ProjectEnableDto;
import com.uwdp.module.api.vo.dto.VisitPlanDto;
import com.uwdp.module.api.vo.enums.ScoreTableFields;
import com.uwdp.module.api.vo.excel.ProjectEnableExcelExport;
import com.uwdp.module.api.vo.excel.ProjectEnableExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.ProjectEnableAssembler;
import com.uwdp.module.biz.infrastructure.entity.MarketDmpTagDetailDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectEnableDetailDo;
import com.uwdp.module.biz.infrastructure.entity.ProjectEnableDo;
import com.uwdp.module.biz.infrastructure.entity.VisitPlanDo;
import com.uwdp.module.biz.infrastructure.excel.ProjectEnableExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ProjectEnableDetailRepository;
import com.uwdp.module.biz.infrastructure.repository.ProjectEnableRepository;
import com.uwdp.module.biz.utils.MdmOrganizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 项目赋能 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectEnableServiceImpl implements IProjectEnableService {

    private final ProjectEnableRepository projectEnableRepository;

    private final ProjectEnableAssembler projectEnableAssembler;

    private final BeanSearcher beanSearcher;

    private final IProjectEnableDetailService projectEnableDetailService;

    private final MdmOrganizationUtil mdmOrganizationUtil;

    private final ProjectEnableDetailRepository projectEnableDetailRepository;

    private final IClientRoleService clientRoleService;

    @Override
    public SearchResult<ProjectEnableDto> pageByParam(Map<String, Object> paraMap) {
        clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("createdTime")){
            String date = paraMap.get("createdTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("createdTime-0",date1);
            paraMap.put("createdTime-1",date2);
            paraMap.put("createdTime-op", "bt");
            paraMap.remove("createdTime");
        }
        return beanSearcher.search(ProjectEnableDto.class, paraMap);
    }

    @Override
    public List<ProjectEnableDto> searchByParam(Map<String, Object> paraMap) {
        clientRoleService.queryUserRole(paraMap);
        return beanSearcher.searchAll(ProjectEnableDto.class, paraMap);
    }

    @Override
    public List<ProjectEnableDto> listByIds(List<Long> idList) {
        List<ProjectEnableDo> list = projectEnableRepository.list(new LambdaQueryWrapper<ProjectEnableDo>()
                .in(ProjectEnableDo::getId, idList));
        return projectEnableAssembler.toValueObjectList(list);
    }

    @Override
    public ProjectEnableDto get(Long id) {
        ProjectEnableDo projectEnableDo = projectEnableRepository.getOne(new LambdaQueryWrapper<ProjectEnableDo>()
                .eq(ProjectEnableDo::getId, id));
        ProjectEnableDto projectEnableDTO = projectEnableAssembler.toValueObject(projectEnableDo);
        Map<String,Object> map = Maps.newHashMap();
        map.put("parentId",id);
        projectEnableDTO.setProjectEnableDetailDto(projectEnableDetailService.searchByParam(map));
        return projectEnableDTO;
    }

    @Override
    public void add(ProjectEnableAddCmd addCmd) {
        if(addCmd.getCreatedBy()!=null){
            addCmd.setGroupFullCode(mdmOrganizationUtil.getGroupFullCode(addCmd.getCreatedBy()));
        }
        ProjectEnableDo projectEnableDo = projectEnableAssembler.toDO(addCmd);
        boolean isSave = projectEnableRepository.save(projectEnableDo);
        if(isSave && !CollectionUtils.isEmpty(addCmd.getProjectEnableDetailList())){
            List<ProjectEnableDetailDo> projectEnableDetailDos = new ArrayList<>(addCmd.getProjectEnableDetailList().size());
            addCmd.getProjectEnableDetailList().stream().forEach
                    (obj->{
                        obj.setParentId(projectEnableDo.getId());
                        ProjectEnableDetailDo tag = new ProjectEnableDetailDo();
                        BeanUtils.copyProperties(obj,tag);
                        projectEnableDetailDos.add(tag);
                    });

            projectEnableDetailRepository.saveBatch(projectEnableDetailDos);
        }
    }

    @Override
    public Long addThenReturnId(ProjectEnableAddCmd addCmd) {
        if(addCmd.getCreatedBy()!=null){
            addCmd.setGroupFullCode(mdmOrganizationUtil.getGroupFullCode(addCmd.getCreatedBy()));
        }
        ProjectEnableDo projectEnableDo = projectEnableAssembler.toDO(addCmd);
        boolean isSave = projectEnableRepository.save(projectEnableDo);
        if(isSave && !CollectionUtils.isEmpty(addCmd.getProjectEnableDetailList())){
            List<ProjectEnableDetailDo> projectEnableDetailDos = new ArrayList<>(addCmd.getProjectEnableDetailList().size());
            addCmd.getProjectEnableDetailList().stream().forEach
                    (obj->{
                        obj.setParentId(projectEnableDo.getId());
                        ProjectEnableDetailDo tag = new ProjectEnableDetailDo();
                        BeanUtils.copyProperties(obj,tag);
                        projectEnableDetailDos.add(tag);
                    });

            projectEnableDetailRepository.saveBatch(projectEnableDetailDos);
        }
        if (isSave){
            return projectEnableDo.getId();
        }
        return 0L;
    }

    @Override
    public void update(ProjectEnableUpdateCmd updateCmd) {
        ProjectEnableDto projectEnableDTO = this.get(updateCmd.getId());
        if (projectEnableDTO != null) {
            ProjectEnableDo projectEnableDo = projectEnableAssembler.toDO(updateCmd);
            projectEnableRepository.updateById(projectEnableDo);

            ////根据主表删除明细表
            projectEnableDetailRepository.remove(new LambdaQueryWrapper<ProjectEnableDetailDo>()
                    .eq(ProjectEnableDetailDo::getParentId, updateCmd.getId()));

            if(!CollectionUtils.isEmpty(updateCmd.getProjectEnableDetailList())){
                List<ProjectEnableDetailDo> projectEnableDetailDos = new ArrayList<>(updateCmd.getProjectEnableDetailList().size());
                updateCmd.getProjectEnableDetailList().stream().forEach
                        (obj->{
                            obj.setParentId(projectEnableDo.getId());
                            ProjectEnableDetailDo tag = new ProjectEnableDetailDo();
                            BeanUtils.copyProperties(obj,tag);
                            projectEnableDetailDos.add(tag);
                        });

                projectEnableDetailRepository.saveBatch(projectEnableDetailDos);
            }
        }
    }

    /**
     * 在流程中更新领导评分建议
     * 本方法不考虑领导同名的情况
     * @param updateCmd 只要有id ,score ,suggest三个参数即可更新
     */
    @Override
    public void updateScoreList(ProjectEnableUpdateCmd updateCmd) {
        // 这里要用dto -> do ,因为dto中的scoreList为list类型方便处理 , 而do类中保存其json格式的字符串
        ProjectEnableDto enableDto = this.get(updateCmd.getId());
        String currentLeaderName = updateCmd.getCreatedByName(); /*当前用户的名称 controller传来的*/
        if (enableDto == null) {
            return;
        }
        List<Map<ScoreTableFields, String>> scoreList = enableDto.getScoreList();/*已保存的 领导评价建议*/
        log.info(">>>>>>>>查询到已保存的scoreList:{}",scoreList);
        if (null == scoreList || scoreList.size() == 0) {
            Map<ScoreTableFields, String> newMap = new HashMap<>();
            scoreList = new ArrayList<>();
            newMap.put(ScoreTableFields.SCORE_LEADER, currentLeaderName);
            newMap.put(ScoreTableFields.SCORE, String.valueOf(updateCmd.getScore()));
            newMap.put(ScoreTableFields.SUGGEST, updateCmd.getSuggest());
            scoreList.add(newMap);
            enableDto.setScoreList(scoreList);
            ProjectEnableDo projectEnableDo = projectEnableAssembler.toDO(enableDto);
            projectEnableRepository.updateById(projectEnableDo);
            log.info(">>>>>>>>scoreList已更新:{}",projectEnableDo);
            return;
        }
        // 先删再更新
        if (scoreList.stream().anyMatch(
                e-> {
                    // 判断是否存在重复的领导姓名
                    String existedLeader = e.get(ScoreTableFields.SCORE_LEADER);
                    return Objects.equals(existedLeader,currentLeaderName);
                }
        )) {
            // 取出领导姓名重复的第一个元素
            Optional<Map<ScoreTableFields, String>> first = scoreList.stream()
                    .filter(
                            e-> {
                                // 判断是否存在重复的领导姓名
                                String existedLeader = e.get(ScoreTableFields.SCORE_LEADER);
                                return Objects.equals(existedLeader,currentLeaderName);
                            })
                    .findFirst();
            Map<ScoreTableFields, String> firstSuggest = first.get();
            scoreList.remove(firstSuggest);
        }

        Map<ScoreTableFields, String> newMap = new HashMap<>();
        newMap.put(ScoreTableFields.SCORE_LEADER, currentLeaderName);
        newMap.put(ScoreTableFields.SCORE, String.valueOf(updateCmd.getScore()));
        newMap.put(ScoreTableFields.SUGGEST, updateCmd.getSuggest());
        scoreList.add(newMap);
        log.info(">>>>>>>>scoreList即将更新:{}",scoreList);

        enableDto.setScoreList(scoreList);
        ProjectEnableDo projectEnableDo = projectEnableAssembler.toDO(enableDto);
        projectEnableRepository.updateById(projectEnableDo);
        log.info(">>>>>>>>scoreList已更新:{}",projectEnableDo);
    }

    /**
     * 任务回调后更新领导评分建议
     * @param taskCall 回调参数-需要在流程中配置回调接口
     */
    @Override
    public void updateScoreList(TaskCall taskCall) {

        Map<String, Object> formData = taskCall.getFormData();
        String id = (String) formData.get("id");
        Map<String, Object> taskFormData = taskCall.getTaskFormData();
        String score = String.valueOf(taskFormData.get("score"));
        String suggest = (String) taskFormData.get("suggest");
        log.info("任务回调修改ScoreList字段id:{},score:{},suggest:{}",id,score,suggest);
        String userFullName = taskCall.getAssignee().getUserFullName();
        if (!StringUtils.hasLength(id) || !StringUtils.hasLength(userFullName) || !StringUtils.hasLength(score) || !StringUtils.hasLength(suggest)){
            return;
        }
        ProjectEnableDo enableDo = projectEnableRepository.getById(id);
        List<Map<ScoreTableFields, String>> list = new ArrayList<>();
        HashMap<ScoreTableFields, String> map = new HashMap<>();
        map.put(ScoreTableFields.SCORE_LEADER, userFullName);
        map.put(ScoreTableFields.SCORE, score);
        map.put(ScoreTableFields.SUGGEST, suggest);
        list.add(map);
        log.info(">>>>>>>>addingList:{}", list);
        List<Map<ScoreTableFields, String>> oldList = JSONObject.parseObject(enableDo.getScoreList(), new TypeReference<List<Map<ScoreTableFields, String>>>() {
        });
        log.info(">>>>>>>>oldList:{}", oldList);

        // 流程可能出问题,管理员重推后这里可能重复保存,因此要校验并删除与之重复的再更新
        if (oldList != null) {
            boolean flag = oldList.stream().anyMatch(e -> Objects.equals(e.get(ScoreTableFields.SCORE_LEADER), userFullName));
            if (flag) {
                oldList.removeIf(e -> Objects.equals(e.get(ScoreTableFields.SCORE_LEADER), userFullName));
            }
            list.addAll(oldList);
        }
        log.info(">>>>>>>>newList:{}", list);
        enableDo.setScoreList(JSON.toJSONString(list));
        projectEnableRepository.updateById(enableDo);
    }

    @Override
    public void delete(Long id) {
        projectEnableRepository.remove(new LambdaQueryWrapper<ProjectEnableDo>()
                .eq(ProjectEnableDo::getId, id));
        //根据主表删除明细表
        projectEnableDetailRepository.remove(new LambdaQueryWrapper<ProjectEnableDetailDo>()
                .eq(ProjectEnableDetailDo::getParentId, id));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            projectEnableRepository.remove(new LambdaQueryWrapper<ProjectEnableDo>()
                    .in(ProjectEnableDo::getId, primaryKeys));
            //根据主表删除明细表
            projectEnableDetailRepository.remove(new LambdaQueryWrapper<ProjectEnableDetailDo>()
                    .in(ProjectEnableDetailDo::getParentId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), ProjectEnableExcelExport.class, "项目赋能导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<ProjectEnableExcelExport> searchResult = beanSearcher.searchAll(ProjectEnableExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, ProjectEnableExcelExport.class, "项目赋能数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<ProjectEnableExcelImport> excelParse(MultipartFile file) {
        ProjectEnableExcelImportListener listener = new ProjectEnableExcelImportListener();
        ExcelUtil.readFile(file, ProjectEnableExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<ProjectEnableExcelImport> list) {
        List<ProjectEnableDo> projectEnableDoList = projectEnableAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(projectEnableDoList)) {
            projectEnableRepository.saveBatch(projectEnableDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<ProjectEnableExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, ProjectEnableExcelExport.class, "项目赋能错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
