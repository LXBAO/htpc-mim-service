package com.uwdp.module.biz.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 系统操作日志表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-09-07 11:55:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("sys_operate_log")
@ApiModel(value = "SysOperateLogDo entity对象", description = "系统操作日志表")
public class SysOperateLogDo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@ApiModelProperty("主键")
	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * 操作日志编码
	 */
	@TableField("operate_log_code")
	private String operateLogCode;
	/**
	 * 模块名称
	 */
	@TableField("module_name")
	private String moduleName;
	/**
	 * 操作类型
	 */
	@TableField("operate_type")
	private String operateType;
	/**
	 * 操作用户_iD
	 */
	@TableField("user_no")
	private String userNo;
	/**
	 * 客户端_i_p信息
	 */
	@TableField("client_ip")
	private String clientIp;
	/**
	 * 客户端信息
	 */
	@TableField("client_info")
	private String clientInfo;
	/**
	 * 请求地址
	 */
	@TableField("request_url")
	private String requestUrl;
	/**
	 * 请求方式
	 */
	@TableField("request_method")
	private String requestMethod;
	/**
	 * 操作方法
	 */
	@TableField("operate_method")
	private String operateMethod;
	/**
	 * 请求参数
	 */
	@TableField("request_param")
	private String requestParam;
	/**
	 * 操作状态
	 */
	@TableField("operate_status")
	private String operateStatus;
	/**
	 * 额外配置
	 */
	@TableField("log_extra")
	private String logExtra;
	/**
	 * 创建者_iD
	 */
	@TableField("log_extra")
	private String createdBy;
	/**
	 * 创建时间
	 */
	@TableField("created_time")
	private String createdTime;
	/**
	 * 
	 */
	@TableField("updated_by")
	private String updatedBy;
	/**
	 * 
	 */
	@TableField("updated_time")
	private String updatedTime;
	/**
	 * 应用id
	 */
	@TableField("app_id")
	private String appId;
	/**
	 * 所属业务
	 */
	@TableField("business")
	private String business;
	/**
	 * 业务操作详情
	 */
	@TableField("business_detail")
	private String businessDetail;

}
