package com.uwdp.module.biz.infrastructure.excel;

import com.alibaba.cola.exception.BizException;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.gientech.lcds.generator.commons.biz.listener.BaseExcelImportListener;
import com.uwdp.module.api.vo.excel.PerformanceManagementExcelImport;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * 业绩管理 数据导入监听器
 * </p>
 *
 */
@Slf4j
public class PerformanceManagementExcelImportListener extends BaseExcelImportListener<PerformanceManagementExcelImport> implements ReadListener<PerformanceManagementExcelImport> {

    private static final Map<String, String> METHOD_MAP = new HashMap<>();
    private static final AtomicBoolean IS_INIT = new AtomicBoolean(Boolean.FALSE);

    public PerformanceManagementExcelImportListener() {
        if (!IS_INIT.get()) {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(PerformanceManagementExcelImport.class);
                for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
                    METHOD_MAP.put(propertyDescriptor.getReadMethod().getName(), propertyDescriptor.getName());
                }
                IS_INIT.set(Boolean.TRUE);
            } catch (Exception e) {
                throw new BizException("导入类: " + PerformanceManagementExcelImport.class.getName() + ", 解析错误");
            }
        }
        super.initAnnotationInfoList(PerformanceManagementExcelImport.class);
    }

    @Override
    public void invoke(PerformanceManagementExcelImport data, AnalysisContext context) {
        if (DATA_LIST.size() > IMPORT_MAX) {
            return;
        }
        // 错误超过50行则不进行进一步处理
        String rowIndex = "第" + (context.readRowHolder().getRowIndex() + 1) + "行";
        data.setRowIndex(rowIndex);

        // 跳过错误的数据
        if (super.parseObjectField(data, PerformanceManagementExcelImport.class, METHOD_MAP)) {
            ERROR_DATA_LIST.add(data);
        } else {
            DATA_LIST.add(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("导入实体元数据, 新增: {}", DATA_LIST.size());
    }

}