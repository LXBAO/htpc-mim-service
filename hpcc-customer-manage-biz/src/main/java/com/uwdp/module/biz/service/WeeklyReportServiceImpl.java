package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.google.common.collect.Maps;
import com.uwdp.module.api.service.IClientRoleService;
import com.uwdp.module.api.service.IWeeklyReportDetailService;
import com.uwdp.module.api.service.IWeeklyReportService;
import com.uwdp.module.api.vo.cmd.WeeklyReportAddCmd;
import com.uwdp.module.api.vo.cmd.WeeklyReportUpdateCmd;
import com.uwdp.module.api.vo.dto.WeeklyReportDetailDto;
import com.uwdp.module.api.vo.dto.WeeklyReportDto;
import com.uwdp.module.api.vo.excel.WeeklyReportExcelExport;
import com.uwdp.module.api.vo.excel.WeeklyReportExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.WeeklyReportAssembler;
import com.uwdp.module.biz.infrastructure.assembler.WeeklyReportDetailAssembler;
import com.uwdp.module.biz.infrastructure.entity.ClientRoleEntityDo;
import com.uwdp.module.biz.infrastructure.entity.WeeklyReportDetailDo;
import com.uwdp.module.biz.infrastructure.entity.WeeklyReportDo;
import com.uwdp.module.biz.infrastructure.excel.WeeklyReportExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.ClientRoleEntityRepository;
import com.uwdp.module.biz.infrastructure.repository.WeeklyReportDetailRepository;
import com.uwdp.module.biz.infrastructure.repository.WeeklyReportRepository;
import com.uwdp.module.biz.utils.MdmOrganizationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.hutool.core.date.DatePattern.PURE_DATE_FORMAT;

/**
 * <p>
 * 周报 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WeeklyReportServiceImpl implements IWeeklyReportService {

    private final WeeklyReportRepository weeklyReportRepository;

    private final WeeklyReportAssembler weeklyReportAssembler;

    private final WeeklyReportDetailRepository weeklyReportDetailRepository;

    private final WeeklyReportDetailAssembler weeklyReportDetailAssembler;

    private final IWeeklyReportDetailService weeklyReportDetailService;

    private final BeanSearcher beanSearcher;

    private final ClientRoleEntityRepository clientRoleEntityRepository;

    private final MdmOrganizationUtil mdmOrganizationUtil;

    @Autowired
    private IClientRoleService clientRoleService;

    @Override
    public SearchResult<WeeklyReportDto> pageByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        if(paraMap.containsKey("createdTime")){
            String date = paraMap.get("createdTime").toString();
            String date1 = date.substring(0, 19);
            String date2 = date.substring(20, 39);
            paraMap.put("createdTime-0",date1);
            paraMap.put("createdTime-1",date2);
            paraMap.put("createdTime-op", "bt");
            paraMap.remove("createdTime");
        }
        return beanSearcher.search(WeeklyReportDto.class, paraMap);
    }

    @Override
    public List<WeeklyReportDto> searchByParam(Map<String, Object> paraMap) {
        paraMap = clientRoleService.queryUserRole(paraMap);
        return beanSearcher.searchAll(WeeklyReportDto.class, paraMap);
    }

    @Override
    public List<WeeklyReportDto> listByIds(List<Long> idList) {
        List<WeeklyReportDo> list = weeklyReportRepository.list(new LambdaQueryWrapper<WeeklyReportDo>()
                .in(WeeklyReportDo::getId, idList));
        return weeklyReportAssembler.toValueObjectList(list);
    }

    @Override
    public WeeklyReportDto get(Long id) {
        WeeklyReportDo weeklyReportDo = weeklyReportRepository.getOne(new LambdaQueryWrapper<WeeklyReportDo>()
                .eq(WeeklyReportDo::getId, id));
        WeeklyReportDto weeklyReportDTO = weeklyReportAssembler.toValueObject(weeklyReportDo);
        Map<String,Object> map = Maps.newHashMap();
        map.put("parentId",id);
        map.put("orderBy","dateStr");
        weeklyReportDTO.setWeeklyReportDetailDtos(weeklyReportDetailService.searchByParam(map));
        return weeklyReportDTO;
    }

    @Override
    public void add(WeeklyReportAddCmd addCmd) {
        // 数据权限 添加创建人字段(保存创建人personCode)
        String personCode = addCmd.getCreatedBy();
        String groupFullCode=mdmOrganizationUtil.getGroupFullCode(personCode);
        addCmd.setGroupFullCode(groupFullCode);

        WeeklyReportDo weeklyReportDo = weeklyReportAssembler.toDO(addCmd);
        weeklyReportRepository.save(weeklyReportDo);
        List<WeeklyReportDetailDo> marketDmpTagDetailDos = new ArrayList<>(addCmd.getWeeklyReportDetailAddCmdList().size());
        addCmd.getWeeklyReportDetailAddCmdList().stream().forEach
                (obj->{
                    obj.setParentId(weeklyReportDo.getId());
                    WeeklyReportDetailDo tag = new WeeklyReportDetailDo();
                    BeanUtils.copyProperties(obj,tag);
                    marketDmpTagDetailDos.add(tag);
                });
        weeklyReportDetailRepository.saveBatch(marketDmpTagDetailDos);
    }

    @Override
    public void update(WeeklyReportUpdateCmd updateCmd) {
        WeeklyReportDto weeklyReportDTO = this.get(updateCmd.getId());
        if (weeklyReportDTO != null) {
            WeeklyReportDo weeklyReportDo = weeklyReportAssembler.toDO(updateCmd);
            weeklyReportRepository.updateById(weeklyReportDo);
            List<WeeklyReportDetailDo> marketDmpTagDetailDos = new ArrayList<>(updateCmd.getWeeklyReportDetailAddCmdList().size());
            updateCmd.getWeeklyReportDetailAddCmdList().stream().forEach
                    (obj->{
                        obj.setParentId(weeklyReportDo.getId());
                        WeeklyReportDetailDo tag = new WeeklyReportDetailDo();
                        BeanUtils.copyProperties(obj,tag);
                        marketDmpTagDetailDos.add(tag);
                    });
            weeklyReportDetailRepository.saveBatch(marketDmpTagDetailDos);
        }
    }

    @Override
    public void delete(Long id) {
        weeklyReportRepository.remove(new LambdaQueryWrapper<WeeklyReportDo>()
                .eq(WeeklyReportDo::getId, id));
        weeklyReportDetailRepository.remove(new LambdaQueryWrapper<WeeklyReportDetailDo>()
                .eq(WeeklyReportDetailDo::getParentId, id));
    }

    @Override
    public List<WeeklyReportDetailDto> getWeeklyReportDetailByPersonCode(Long personCode) {

        WeeklyReportDo weeklyReportDo= weeklyReportRepository.getOne(new LambdaQueryWrapper<WeeklyReportDo>()
                .eq(WeeklyReportDo::getCreatedBy, personCode).select(WeeklyReportDo::getMaxId));
        if(weeklyReportDo == null){
            return null;
        }
        List<WeeklyReportDetailDo> list = weeklyReportDetailRepository.list(new LambdaQueryWrapper<WeeklyReportDetailDo>()
                .eq(WeeklyReportDetailDo::getParentId,weeklyReportDo.getMaxId()).eq(WeeklyReportDetailDo::getStatus,0));

        return weeklyReportDetailAssembler.toValueObjectList(list);
    }
    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            weeklyReportRepository.remove(new LambdaQueryWrapper<WeeklyReportDo>()
                    .in(WeeklyReportDo::getId, primaryKeys));
            weeklyReportDetailRepository.remove(new LambdaQueryWrapper<WeeklyReportDetailDo>()
                    .in(WeeklyReportDetailDo::getParentId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), WeeklyReportExcelExport.class, "周报导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<WeeklyReportExcelExport> searchResult = beanSearcher.searchAll(WeeklyReportExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, WeeklyReportExcelExport.class, "周报数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<WeeklyReportExcelImport> excelParse(MultipartFile file) {
        WeeklyReportExcelImportListener listener = new WeeklyReportExcelImportListener();
        ExcelUtil.readFile(file, WeeklyReportExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<WeeklyReportExcelImport> list) {
        List<WeeklyReportDo> weeklyReportDoList = weeklyReportAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(weeklyReportDoList)) {
            weeklyReportRepository.saveBatch(weeklyReportDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<WeeklyReportExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, WeeklyReportExcelExport.class, "周报错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
