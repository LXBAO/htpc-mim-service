package com.uwdp.module.biz.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.SearchResult;
import com.gientech.lcds.generator.commons.api.entity.ExcelParseDTO;
import com.gientech.lcds.generator.commons.biz.utils.ExcelUtil;
import com.uwdp.module.api.service.ILinkmanService;
import com.uwdp.module.api.vo.cmd.LinkmanAddCmd;
import com.uwdp.module.api.vo.cmd.LinkmanUpdateCmd;
import com.uwdp.module.api.vo.dto.LinkmanDto;
import com.uwdp.module.api.vo.excel.LinkmanExcelExport;
import com.uwdp.module.api.vo.excel.LinkmanExcelImport;
import com.uwdp.module.biz.infrastructure.assembler.LinkmanAssembler;
import com.uwdp.module.biz.infrastructure.entity.LinkmanDo;
import com.uwdp.module.biz.infrastructure.excel.LinkmanExcelImportListener;
import com.uwdp.module.biz.infrastructure.repository.LinkmanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * 联系人 服务实现类
 * </p>
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LinkmanServiceImpl implements ILinkmanService {

    private final LinkmanRepository linkmanRepository;

    private final LinkmanAssembler linkmanAssembler;

    private final BeanSearcher beanSearcher;

    @Override
    public SearchResult<LinkmanDto> pageByParam(Map<String, Object> paraMap) {
        paraMap.remove("createdBy");
        return beanSearcher.search(LinkmanDto.class, paraMap);
    }

    @Override
    public List<LinkmanDto> searchByParam(Map<String, Object> paraMap) {
        return beanSearcher.searchAll(LinkmanDto.class, paraMap);
    }

    @Override
    public List<LinkmanDto> listByIds(List<Long> idList) {
        List<LinkmanDo> list = linkmanRepository.list(new LambdaQueryWrapper<LinkmanDo>()
                .in(LinkmanDo::getId, idList));
        return linkmanAssembler.toValueObjectList(list);
    }

    @Override
    public LinkmanDto get(Long id) {
        LinkmanDo linkmanDo = linkmanRepository.getOne(new LambdaQueryWrapper<LinkmanDo>()
                .eq(LinkmanDo::getId, id));
        LinkmanDto linkmanDTO = linkmanAssembler.toValueObject(linkmanDo);
        return linkmanDTO;
    }

    @Override
    public void add(LinkmanAddCmd addCmd) {
        LinkmanDo linkmanDo = linkmanAssembler.toDO(addCmd);
        linkmanRepository.save(linkmanDo);
    }

    @Override
    public void update(LinkmanUpdateCmd updateCmd) {
        LinkmanDto linkmanDTO = this.get(updateCmd.getId());
        if (linkmanDTO != null) {
            LinkmanDo linkmanDo = linkmanAssembler.toDO(updateCmd);
            linkmanRepository.updateById(linkmanDo);
        }
    }

    @Override
    public void delete(Long id) {
        linkmanRepository.remove(new LambdaQueryWrapper<LinkmanDo>()
                .eq(LinkmanDo::getId, id));
    }

    @Override
    public void deleteById(String biddingId) {
        linkmanRepository.remove(new LambdaQueryWrapper<LinkmanDo>()
                .eq(LinkmanDo::getBiddingId, biddingId));
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.hasText(ids)) {
            List<Long> primaryKeys = StrUtil.split(ids, ",").stream().map(Long::valueOf).collect(Collectors.toList());
            linkmanRepository.remove(new LambdaQueryWrapper<LinkmanDo>()
                    .in(LinkmanDo::getId, primaryKeys));
        }
    }

    @Override
    public void excelTemplate(HttpServletResponse response) {
        ExcelUtil.downloadFile(new ArrayList<>(), LinkmanExcelExport.class, "联系人导入模板", response);
    }

    @Override
    public void excelExport(Map<String, Object> map, HttpServletResponse response) {
        List<LinkmanExcelExport> searchResult = beanSearcher.searchAll(LinkmanExcelExport.class, map);
        ExcelUtil.downloadFile(searchResult, LinkmanExcelExport.class, "联系人数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }

    @Override
    public ExcelParseDTO<LinkmanExcelImport> excelParse(MultipartFile file) {
        LinkmanExcelImportListener listener = new LinkmanExcelImportListener();
        ExcelUtil.readFile(file, LinkmanExcelImport.class, listener);
        return listener.getExcelParseDTO();
    }

    @Override
    public void excelImport(List<LinkmanExcelImport> list) {
        List<LinkmanDo> linkmanDoList = linkmanAssembler.toDoFromImportList(list);
        if (!CollectionUtils.isEmpty(linkmanDoList)) {
            linkmanRepository.saveBatch(linkmanDoList);
        }
    }

    @Override
    public void excelErrorDownload(List<LinkmanExcelExport> list, HttpServletResponse response) {
        ExcelUtil.downloadFile(list, LinkmanExcelExport.class, "联系人错误数据_" + PURE_DATE_FORMAT.format(new Date()), response);
    }
}
