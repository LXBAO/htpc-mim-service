package com.uwdp.module.biz.aspect;

import cn.hutool.core.util.IdUtil;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.exception.SysException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.gientech.lcds.generator.commons.api.annotation.OperationLog;
import com.gientech.lcds.generator.commons.api.annotation.OperationModule;
import com.gientech.lcds.generator.commons.api.entity.SysOperateLogDto;
import com.gientech.lcds.generator.commons.api.enums.ModuleTypeEnum;
import com.gientech.lcds.generator.commons.api.enums.OperateTypeEnum;
import com.uwdp.module.api.service.SysOperateLogService;
import com.uwdp.module.biz.infrastructure.entity.SysOperateLogDo;
import com.uwdp.module.biz.infrastructure.mapper.SysOperateLogMapper;
import com.uwdp.module.biz.infrastructure.repository.SysOperateLogRepository;
import eu.bitwalker.useragentutils.UserAgent;
import com.gientech.lcds.data.auth.utils.JsonUtils;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 操作日志添加
 *
 */
@EnableAspectJAutoProxy
@Aspect
@Slf4j
@Component
public class LogAspect {


    @Autowired
    SysOperateLogRepository sysOperateLogRepository;

    /**
     * 操作日志
     */
    @Pointcut("execution(* com.uwdp.module.biz.service.*.*(..))")
    public void operatePointcut() {
    }

    @Around(value = "operatePointcut()")
    public Object operateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object response = null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.info("日志存储异常！");
            response = joinPoint.proceed();
            return response;
        }

        // 获取类上的模块注解
        Class<?> operateClass = joinPoint.getTarget().getClass();
        OperationModule annotation = operateClass.getAnnotation(OperationModule.class);
        String moduleName;
        String business;
        if (annotation != null) {
            moduleName = annotation.type().name();
            business = annotation.business();
        } else {
            moduleName = ModuleTypeEnum.OTHER.name();
            business = "未定义业务";
        }
        //获取方法，此处可将signature强转为MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
//        OperationLog operationLog = method.getAnnotation(OperationLog.class);
//        OperateTypeEnum operateTypeEnum = operationLog.type();
        String operateMethod = operateClass.getName() + "." + method.getName();

        HttpServletRequest servletRequest = requestAttributes.getRequest();
        HttpServletResponse servletResponse = requestAttributes.getResponse();
        String appId = servletRequest.getHeader("x-appId");
        String userNo = servletRequest.getHeader("uid");

        RequestLogDTO requestLogDTO = this.getRequestBaseInfo(servletRequest);

        String servletPath = servletRequest.getServletPath();
        String requestMethod = servletRequest.getMethod();
        String requestParamsJson;
        try {
            requestParamsJson = this.getRequestParams(joinPoint, method, servletRequest);
        } catch (Exception e) {
            //swallow it
            log.error("logRequest error: " + e);
            requestParamsJson = "";
        }

        SysOperateLogDo sysOperateLogDto = new SysOperateLogDo();
        sysOperateLogDto.setOperateLogCode(getSequenceNo());
        sysOperateLogDto.setModuleName(moduleName);
//        sysOperateLogDto.setOperateType(operateTypeEnum.name());
        sysOperateLogDto.setUserNo("");
        sysOperateLogDto.setClientIp(requestLogDTO.getIpAddress());
        sysOperateLogDto.setClientInfo("");
        sysOperateLogDto.setRequestUrl(servletPath);
        sysOperateLogDto.setRequestMethod(requestMethod);
        sysOperateLogDto.setOperateMethod(operateMethod);
        sysOperateLogDto.setRequestParam(requestParamsJson);
        if (null != appId) {
            sysOperateLogDto.setAppId(appId);
        }
        sysOperateLogDto.setBusiness(business);
        //方法上配置了业务详情描述，取方法上的业务操作，否则取类上的业务类型及方法的操作类型拼接出具体操作
//        if (StringUtils.isNotBlank(operationLog.businessDetail())) {
//            sysOperateLogDto.setBusinessDetail(operationLog.businessDetail());
//        } else {
//            sysOperateLogDto.setBusinessDetail(business + operateTypeEnum.getDesc());
//        }


        try {
            response = joinPoint.proceed();
            sysOperateLogDto.setOperateStatus("success");
        } catch (Throwable e) {
            sysOperateLogDto.setOperateStatus("fail");
            response = handleException(joinPoint, e);
        } finally {
            try {
                log.info("Biz Log info: " + JacksonUtils.toJSONString(sysOperateLogDto) + ", userNo " + userNo);
                sysOperateLogRepository.save(sysOperateLogDto);
            } catch (Exception e) {
                log.error("保存操作日志错误", e);
            }
        }
        return response;
    }

    private String getRequestParams(ProceedingJoinPoint joinPoint, Method method, HttpServletRequest servletRequest) {
        // 只记录post方法 传json格式的数据
        if (HttpMethod.POST.name().equalsIgnoreCase(servletRequest.getMethod())
                || HttpMethod.PUT.name().equalsIgnoreCase(servletRequest.getMethod())) {
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            //方法 1 请求的方法参数值 JSON 格式 null不显示
            if (joinPoint.getArgs().length > 0) {
                Object[] args = joinPoint.getArgs();
                for (int i = 0; i < args.length; i++) {
                    //请求参数类型判断过滤，防止JSON转换报错
                    if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse || args[i] instanceof MultipartFile) {
                        continue;
                    }
                    PathVariable[] pathVariables = args[i].getClass().getAnnotationsByType(PathVariable.class);
                    if (pathVariables.length != 0) {
                        continue;
                    }
                    RequestBody[] requestBodies = args[i].getClass().getAnnotationsByType(RequestBody.class);
                    if (requestBodies.length != 0) {
                        return JacksonUtils.toJSONString(args[i]);
                    }
                }
            }
        } else {
            //请求的方法参数值 兼容fromDate格式和JSON格式
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称 显示所有字段 值为null也显示该字段
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < args.length; i++) {
                    //请求参数类型判断过滤，
                    if (args[i] instanceof HttpServletResponse || args[i] instanceof MultipartFile) {
                        continue;
                    }
                    if (args[i] instanceof HttpServletRequest) {
                        HttpServletRequest request = (HttpServletRequest) args[i];
                        map.putAll(request.getParameterMap());
                        continue;
                    }
                    map.put(paramNames[i], args[i]);
                }
                return JacksonUtils.toJSONString(map);
            }
        }
        return "";
    }

    public static String getSequenceNo() {
        return "YQ_" + IdUtil.getSnowflake().nextId();
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }
        return ip;
    }

    private RequestLogDTO getRequestBaseInfo(HttpServletRequest request) {
        RequestLogDTO logDTO = new RequestLogDTO();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        logDTO.setOs(userAgent.getOperatingSystem().getName());
        logDTO.setIpAddress(getIpAddr(request));
        logDTO.setBrowser(userAgent.getBrowser().toString());
        return logDTO;
    }

    private Object handleException(ProceedingJoinPoint joinPoint, Throwable e) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Class returnType = ms.getReturnType();

        if (e instanceof BizException) {
            log.warn("BIZ EXCEPTION: " + e.getMessage());
            //在Debug的时候，对于BizException也打印堆栈
            if (log.isDebugEnabled()) {
                log.error(e.getMessage(), e);
            }
        } else if (e instanceof SysException) {
            log.error("SYS EXCEPTION:");
            log.error(e.getMessage(), e);
        } else {
            log.error("UNKNOWN EXCEPTION:");
            log.error(e.getMessage(), e);
        }
        throw e;
    }


    /**
     * 不允许删除该工具类, 特定的序列化工具类
     *
     * @author admin
     * @date 2022/08/12
     */
    private static class JacksonUtils {

        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        private static final ObjectMapper om = new ObjectMapper();

        static {
            // 序列化出所有字段, 包括为null的字段
            om.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            // 确定遇到未知属性（不映射到属性的属性，并且没有“任何设置器”或可以处理它的处理程序）是否应导致失败（通过抛出 JsonMappingException）的功能
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // 确定日期（和日期/时间）值（以及基于日期的东西，如 java.util.Calendars）是序列化为数字时间戳（true；默认值）还是其他东西（通常是文本表示）的功能。 如果使用文本表示，则实际格式取决于配置设置，包括可能对每个属性使用 @JsonFormat 注释、全局配置的 java.text.DateFormat。
            // 对于“经典”JDK 日期类型（java.util.Date、java.util.Calendar），默认格式由 com.fasterxml.jackson.databind.util.StdDateFormat 提供，对应于格式字符串“yyyy-MM-dd 'T'HH:mm:ss.SSSX"（有关格式字符串的详细信息，请参见 java.text.DateFormat）。 此功能是否影响其他日期相关类型的处理取决于这些类型的处理程序，尽管理想情况下它们应该使用此功能
            om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            // 确定表示时间段（持续时间、期间、范围）的时间值是否默认使用数字 (true) 或文本 (false) 表示进行序列化的功能。 请注意，数字表示可能表示简单数字或数字数组，具体取决于类型。
            om.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
            javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
            javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
            om.registerModule(javaTimeModule);
            om.setTimeZone(TimeZone.getDefault());
        }

        public static JavaType makeJavaType(Class<?> parametrized, Class<?>... parameterClasses) {
            return om.getTypeFactory().constructParametricType(parametrized, parameterClasses);
        }

        public static JavaType makeJavaType(Class<?> rawType, JavaType... parameterTypes) {
            return om.getTypeFactory().constructParametricType(rawType, parameterTypes);
        }

        public static String toString(Object value) {
            if (Objects.isNull(value)) {
                return null;
            }
            if (value instanceof String) {
                return (String) value;
            }
            return toJSONString(value);
        }

        @SneakyThrows
        public static String toJSONString(Object value) {
            return om.writeValueAsString(value);
        }

        @SneakyThrows
        public static String toPrettyString(Object value) {
            return om.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        }

        @SneakyThrows
        public static JsonNode fromJavaObject(Object value) {
            JsonNode result = null;
            if (Objects.nonNull(value) && (value instanceof String)) {
                result = parseObject((String) value);
            } else {
                result = om.valueToTree(value);
            }
            return result;
        }

        @SneakyThrows
        public static JsonNode parseObject(String content) {
            return om.readTree(content);
        }

        public static JsonNode getJsonElement(JsonNode node, String name) {
            return node.get(name);
        }

        public static JsonNode getJsonElement(JsonNode node, int index) {
            return node.get(index);
        }

        @SneakyThrows
        public static <T> T toJavaObject(TreeNode node, Class<T> clazz) {
            return om.treeToValue(node, clazz);
        }

        @SneakyThrows
        public static <T> T toJavaObject(TreeNode node, JavaType javaType) {
            return om.convertValue(node, javaType);
        }

        @SneakyThrows
        public static <T> T toJavaObject(TreeNode node, TypeReference<T> typeReference) {
            return om.convertValue(node, typeReference);
        }

        public static <T> T toJavaObject(TreeNode node, Type type) {
            return toJavaObject(node, om.constructType(type));
        }

        public static <E> List<E> toJavaList(TreeNode node, Class<E> clazz) {
            return toJavaObject(node, makeJavaType(List.class, clazz));
        }

        public static List<Object> toJavaList(TreeNode node) {
            return toJavaObject(node, new TypeReference<List<Object>>() {
            });
        }

        public static <V> Map<String, V> toJavaMap(TreeNode node, Class<V> clazz) {
            return toJavaObject(node, makeJavaType(Map.class, String.class, clazz));
        }

        public static Map<String, Object> toJavaMap(TreeNode node) {
            return toJavaObject(node, new TypeReference<Map<String, Object>>() {
            });
        }

        @SneakyThrows
        public static <T> T toJavaObject(String content, Class<T> clazz) {
            return om.readValue(content, clazz);
        }

        @SneakyThrows
        public static <T> T toJavaObject(String content, JavaType javaType) {
            return om.readValue(content, javaType);
        }

        @SneakyThrows
        public static <T> T toJavaObject(String content, TypeReference<T> typeReference) {
            return om.readValue(content, typeReference);
        }

        public static <T> T toJavaObject(String content, Type type) {
            return toJavaObject(content, om.constructType(type));
        }

        public static <E> List<E> toJavaList(String content, Class<E> clazz) {
            return toJavaObject(content, makeJavaType(List.class, clazz));
        }

        public static List<Object> toJavaList(String content) {
            return toJavaObject(content, new TypeReference<List<Object>>() {
            });
        }

        public static <V> Map<String, V> toJavaMap(String content, Class<V> clazz) {
            return toJavaObject(content, makeJavaType(Map.class, String.class, clazz));
        }

        public static Map<String, Object> toJavaMap(String content) {
            return toJavaObject(content, new TypeReference<Map<String, Object>>() {
            });
        }
    }

    @Data
    private static class RequestLogDTO {

        private String username;
        private String ipAddress;
        private String operateAddress;
        private String status;
        private String os;
        private String browser;
    }
}