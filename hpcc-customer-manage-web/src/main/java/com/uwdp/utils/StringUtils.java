package com.uwdp.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 字符串处理类
 */
@Configuration
@Slf4j
public class StringUtils {

    /**
     * 将Object转化成String
     * @param obj
     * @return
     */
    public String getStr(Object obj){
        if(obj==null||obj.toString().isEmpty()){
            return "";
        }
        return obj.toString();
    }

    /**
     * 获取Date类型数据
     * @param dateString
     * @param format
     * @return
     * @throws Exception
     */
    public Date convertToDate(String dateString, String format) throws Exception {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
        if(dateString==null||dateString.isEmpty()){
            return null;
        }
        return dateFormatter.parse(dateString);
    }

    /**
     * 将Date转换成LocalDateTime
     * @param date
     * @return
     */
    public LocalDateTime convertToLocalDateTime(Date date) {
        if(date==null){
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
