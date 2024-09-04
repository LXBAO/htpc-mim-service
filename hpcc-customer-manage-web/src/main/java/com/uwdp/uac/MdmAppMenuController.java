package com.uwdp.uac;

import com.alibaba.cola.dto.SingleResponse;
import com.ejlchina.searcher.operator.InList;
import com.ejlchina.searcher.util.MapUtils;
import com.gientech.lcds.user.controller.AppMenuInfoController;
import com.gientech.lcds.user.service.IAppMenuInfoService;
import com.google.common.collect.Lists;
import com.uwdp.module.api.service.IMdmBpDeptService;
import com.uwdp.module.biz.infrastructure.entity.MdmBpDeptDo;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * <p>
 * MDM_LOGIN 服务控制类
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/hpcc-customer-manage/v1/app/menu")
@Validated
//@ConditionalOnProperty(prefix = "hpcc-mdm-server.authorization", name = "type", havingValue = MdmAuthorizationProperty.TYPE_SSO)
public class MdmAppMenuController extends AppMenuInfoController {
	private final AuthorizationProperty authorizationProperty;

	public MdmAppMenuController(IAppMenuInfoService appMenuInfoService, AuthorizationProperty mdmAuthorizationProperty) {
		super(appMenuInfoService);
		this.authorizationProperty = mdmAuthorizationProperty;
	}
	@GetMapping({"/currentUserPermission"})
//	public SingleResponse queryCurrentUserPermission(@ApiParam("应用id") @RequestParam(value = "appId", required = false) @NotNull(message = "应用ID") Long appId) {
	public SingleResponse queryCurrentUserPermission(@ApiParam("appUserToken") @RequestParam(value = "appUserToken") @NotNull(message = "appUserToken") String appUserToken) {
		Map<String, Object> userPermissionMap = new HashMap<>();
		//获取用户所有权限类型集合
		Map<String, List<String>> permissionMap = IdassAuthProcessUtil.mapPermission(appUserToken, authorizationProperty.getPermissonUrl());
		//获取菜单权限
		List<String> menulList = permissionMap.get(IdassAuthProcessUtil.PERMISSION_TYPE_MENU);
		menulList =  Objects.nonNull(menulList) ? menulList : Lists.newArrayList();
		log.info("菜单权限集合:{}", menulList);
		//封装返回值
//		userPermissionMap.put("menu", super.appMenuInfoService.queryCurrentUserMenuList(authorizationProperty.getAppId(), menulList));
		/* 本来应该用对应环境的appId的 , 但是这里数据库还没切换所以读菜单就用这个吧 */
		userPermissionMap.put("menu", super.appMenuInfoService.queryCurrentUserMenuList(1658290286455627778L, menulList));
		userPermissionMap.put("permission",permissionMap);

		List<String> pointResourcesList = permissionMap.get(IdassAuthProcessUtil.PERMISSION_TYPE_PERMISSION_POINT);
		pointResourcesList =  Objects.nonNull(pointResourcesList) ? pointResourcesList : Lists.newArrayList();
		log.info("通用点权限集合:{}", pointResourcesList);
		userPermissionMap.put("pointResourcesList", pointResourcesList);

		return SingleResponse.of(userPermissionMap);
	}
}
