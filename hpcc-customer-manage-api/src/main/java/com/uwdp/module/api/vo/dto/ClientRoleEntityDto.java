package com.uwdp.module.api.vo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * 
 * @author qis
 * @email qis@gmail.com
 * @date 2023-06-28 15:34:38
 */
/**
 * <p>
 * 权限表
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "ClientRoleEntityDTO对象", description = "权限表", discriminator = "ClientRoleEntity")
@SearchBean(tables = "t_client_role")
//@TableName("t_client_role")
public class ClientRoleEntityDto implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(value = "id",type = IdType.AUTO)
    private Integer id;
	/**
	 * 
	 */
	@DbField(value = "dept_id")
	private String deptId;

	@DbField(value = "dept_Name")
	private String deptName;
	/**
	 * 
	 */
	@DbField(value = "user_id")
	private String userId;

	@DbField(value = "user_name")
	private String userName;
	/**
	 * 角色：0.管理员 1.负责人
	 */
	@DbField(value = "role")
	private Integer role;
	/**
	 * 状态 0 有效 1 失效
	 */
	@DbField(value = "status")
	private Integer status;
	/**
	 * 
	 */
	@DbField(value = "ctime")
	private Date ctime;

	@ApiModelProperty("创建者")
	@DbField("CREATED_BY")
	private String createdBy;

	@ApiModelProperty(value = "创建人名称")
	@DbField("CREATEDNAME")
	// @Length(max = 255, message = "createdName长度不在有效范围内")
	private String createdName;

	@ApiModelProperty("创建时间")
	@DbField("CREATED_TIME")
	private LocalDateTime createdTime;

	@ApiModelProperty("更新者")
	@DbField("UPDATED_BY")
	private String updatedBy;

	@ApiModelProperty("更新时间")
	@DbField("UPDATED_TIME")
	private LocalDateTime updatedTime;

	@ApiModelProperty(value = "角色名称")
	@DbField("ROLE_NAME")
	private String roleName;
}
