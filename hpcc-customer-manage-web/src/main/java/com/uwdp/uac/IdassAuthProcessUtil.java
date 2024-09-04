package com.uwdp.uac;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.*;

/**
 * 从业权系统获取权限矩阵 json解析处理工具类
 */
@Slf4j
@UtilityClass
public class IdassAuthProcessUtil {

	private static final String RESPONSE_CODE_KEY = "code";
	private static final String RESPONSE_DATA_KEY = "data";

	private static final String PERMISSION_RESOURCE_CODE = "resourceCode";
	private static final String PERMISSION_RESOURCE_OPERATION_TYPE="operationType";

	//IDASS权限类型
	private static final String IDASS_PERMISSION_TYPE_APP_ROLES="appRoles";
	private static final String IDASS_PERMISSION_TYPE_PERMISSION_POINT="appResources";
	private static final String IDASS_PERMISSION_TYPE_MENU="appMenuResources";
	private static final String	IDASS_PERMISSION_TYPE_PAGE_ELEMENT="appPageElementResources";

	//权限类型,返回数据使用
	public static final String PERMISSION_TYPE_PERMISSION_POINT="permissionPointResources";//权限码
	public static final String PERMISSION_TYPE_MENU="menuResources";//菜单
	public static final String	PERMISSION_TYPE_PAGE_ELEMENT="pageElementResources";//页面元素(按钮等)
	public static final String	PERMISSION_TYPE_ALL="allResources";//所有权限
	public static final String	PERMISSION_TYPE_APP_ROLES="roles";//所有权限

	//权限操作类型
	private static final String OPERATION_TYPE_USABLE="USABLE";
	private static final String OPERATION_TYPE_VISIBLE="VISIBLE";


    /**
     * 获取全量权限CODE list
	 * @param token
     * @param permissionUrl
     * @return
     */
	public List<String> listPermission(String token,String permissionUrl) {
		// 请求响应处理
		Map<String, String> headers = Maps.newHashMapWithExpectedSize(16);
		headers.put("x-token", token);
		HttpResponse response = HttpRequest.get(permissionUrl).timeout(120 * 1000).headerMap(headers, true).execute();
		String body = response.body();
		log.info("查询当前用户指定应用的权限集合>>>url: {} | token: {} | response: {}", permissionUrl, token, body);
		if (!response.isOk()) {
			return Lists.newArrayList();
		}
		JSONObject permissionResponse = JSONUtil.parseObj(body);
		if (!Objects.equals(HttpStatus.OK.value(), permissionResponse.getInt(RESPONSE_CODE_KEY))) {
			return Lists.newArrayList();
		}
		List<String> list = ((Map<String,List<String>>)listPermission4IdassJson(permissionResponse)).get(PERMISSION_TYPE_ALL);
		return Objects.nonNull(list) ? list : Lists.newArrayList();
	}

	/**
     * 获取全量权限CODE Map 分类返回
	 * @param token
     * @param permissionUrl 获取权限的请求地址
     * @return
     */
	public Map<String,List<String>>  mapPermission(String token,String permissionUrl) {
		// 请求响应处理
		Map<String, String> headers = Maps.newHashMapWithExpectedSize(16);
		headers.put("x-token", token);
		HttpResponse response = HttpRequest.get(permissionUrl).timeout(120 * 1000).headerMap(headers, true).execute();
		String body = response.body();
		log.info("查询当前用户指定应用的权限集合>>>url: {} | token: {} | response: {}", permissionUrl, token, body);
		if (!response.isOk()) {
			return new HashMap<>();
		}
		JSONObject permissionResponse = JSONUtil.parseObj(body);
		if (!Objects.equals(HttpStatus.OK.value(), permissionResponse.getInt(RESPONSE_CODE_KEY))) {
			return new HashMap<>();
		}
		return listPermission4IdassJson(permissionResponse);
	}

	/**
     *
	 * @param permissionResponse
     * @return
     */
	public  Map<String,List<String>> listPermission4IdassJson(JSONObject permissionResponse){
		JSONObject  permissionDataJson = permissionResponse.getJSONObject(RESPONSE_DATA_KEY);
		//权限点
		List<String> permissionPointResources=getPermissionList4SingleType(permissionDataJson,IDASS_PERMISSION_TYPE_PERMISSION_POINT,false);
		//菜单
		List<String> menuResources = getPermissionList4SingleType(permissionDataJson,IDASS_PERMISSION_TYPE_MENU,true);
		//按钮
		List<String> pageElementResources = getPermissionList4SingleType(permissionDataJson,IDASS_PERMISSION_TYPE_PAGE_ELEMENT,true);
		//角色
		List<String> roleResources = getPermissionList4SingleType(permissionDataJson, IDASS_PERMISSION_TYPE_APP_ROLES, true);
		//全量
		List<String> allResources = new ArrayList<>();
		allResources.addAll(permissionPointResources);
		allResources.addAll(menuResources);
		allResources.addAll(pageElementResources);
		//组装返回数据集合
		Map<String,List<String>> returnMap = new HashMap<>();
		returnMap.put(PERMISSION_TYPE_APP_ROLES, roleResources);
		returnMap.put(PERMISSION_TYPE_PERMISSION_POINT,permissionPointResources);
		returnMap.put(PERMISSION_TYPE_MENU,menuResources);
		returnMap.put(PERMISSION_TYPE_PAGE_ELEMENT,pageElementResources);
		returnMap.put(PERMISSION_TYPE_ALL,allResources);
		return returnMap;
	}


	private List<String> getPermissionList4SingleType(JSONObject permissionDataJson, String permissionType, Boolean operationTypeCheckFlag) {
		List<String> returnList = new ArrayList<>();
		if (ObjectUtil.isNull(permissionDataJson.getJSONObject(permissionType))) {
			return returnList;
		}
		if (permissionDataJson.getJSONObject(permissionType).keySet().isEmpty()) {
			return returnList;
		}
		String idassClientBizId = (String) permissionDataJson.getJSONObject(permissionType).keySet().toArray()[0];
		JSONArray permissionArry = permissionDataJson.getJSONObject(permissionType).getJSONArray(idassClientBizId);
		for (Object temp : permissionArry) {
			if(permissionType.equals(IDASS_PERMISSION_TYPE_APP_ROLES)){
				returnList.add(temp.toString());
			}else {
				JSONObject jsonData = (JSONObject) temp;
				String resourceCode = jsonData.getStr(PERMISSION_RESOURCE_CODE);
				if (operationTypeCheckFlag) {
					List<String> operationTypeList = jsonData.getBeanList(PERMISSION_RESOURCE_OPERATION_TYPE, String.class);
					if (operationTypeList.contains(OPERATION_TYPE_USABLE) && operationTypeList.contains(OPERATION_TYPE_VISIBLE)) {
						returnList.add(resourceCode);
					}

				} else {
					returnList.add(resourceCode);
				}
			}
		}
		return returnList;
	}

}
