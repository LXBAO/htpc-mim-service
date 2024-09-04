package com.uwdp.workflow.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.gientech.lcds.workflow.sdk.core.runtime.CandidateInfoDto;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <p>
 * 函数式工具类，让你体会丝滑的感觉
 * </p>
 *
 * @author liqingdian
 * @since 2023/3/22
 */
@Slf4j
@UtilityClass
public class FunctionalUtil {

	/**
	 * <p>
	 * if(predicate){runnable}
	 * </p>
	 *
	 * @author liqingdian
	 * @since 2023-03-22
	 */
	public static <T> void predicateAndRun(T t, Predicate<T> predicate, Runnable runnable) {
		if (predicate.test(t)) {
			runnable.run();
		}
	}

	/**
	 * <p>
	 * if(predicate){ifRun}else{elseRun}
	 * </p>
	 *
	 * @author liqingdian
	 * @since 2023-03-22
	 */
	public static <T> void predicateAndRun(T t, Predicate<T> predicate, Runnable ifRun, Runnable elseRun) {
		if (predicate.test(t)) {
			ifRun.run();
		} else {
			elseRun.run();
		}
	}

	/**
	 * <p>
	 * if(predicate){runnable}
	 * </p>
	 *
	 * @author liqingdian
	 * @since 2023-03-22
	 */
	public static <T> void predicateAndAccept(T t, Predicate<T> predicate, Consumer<T> consumer) {
		if (predicate.test(t)) {
			consumer.accept(t);
		}
	}

	/**
	 * <p>
	 * if(predicate){ifAccept}else{elseAccept}
	 * </p>
	 *
	 * @author liqingdian
	 * @since 2023-03-22
	 */
	public static <T> void predicateAndAccept(T t, Predicate<T> predicate, Consumer<T> ifAccept, Consumer<T> elseAccept) {
		if (predicate.test(t)) {
			ifAccept.accept(t);
		} else {
			elseAccept.accept(t);
		}
	}

	/**
	 * <p>
	 * if(predicate){supplier}
	 * </p>
	 *
	 * @author liqingdian
	 * @since 2023-03-22
	 */
	public static <T, R> R predicateAndGet(T t, Predicate<T> predicate, Supplier<R> supplier) {
		if (predicate.test(t)) {
			return supplier.get();
		}
		return null;
	}

	/**
	 * <p>
	 * if(predicate){supplier}else{elseValue}
	 * </p>
	 *
	 * @author liqingdian
	 * @since 2023-03-22
	 */
	public static <T, R> R predicateAndGet(T t, Predicate<T> predicate, Supplier<R> supplier, Supplier<R> elseValue) {
		if (predicate.test(t)) {
			return supplier.get();
		}
		return elseValue.get();
	}

	/**
	 * <p>
	 * if(predicate){function}
	 * </p>
	 *
	 * @author liqingdian
	 * @since 2023-03-22
	 */
	public static <T, R> R predicateAndFun(T t, Predicate<T> predicate, Function<T, R> function) {
		if (predicate.test(t)) {
			return function.apply(t);
		}
		return null;
	}

	/**
	 * <p>
	 * if(predicate){ifFun}else{elseFun}
	 * </p>
	 *
	 * @param t:
	 * @param predicate:
	 * @param ifFun:
	 * @param elseFun:
	 * @return R
	 * @author liqingdian
	 * @since 2023-04-26
	 */
	public static <T, R> R predicateAndFun(T t, Predicate<T> predicate, Function<T, R> ifFun, Function<T, R> elseFun) {
		if (predicate.test(t)) {
			return ifFun.apply(t);
		}
		return elseFun.apply(t);
	}

	/**
	 * <p>
	 * try...catch...
	 * </p>
	 *
	 * @param runnable:
	 * @author liqingdian
	 * @since 2023-05-29
	 */
	public static void tryCatch(Runnable runnable) {
		tryCatchFinally(runnable, null, false);
	}

	/**
	 * <p>
	 * try...catch...
	 * </p>
	 *
	 * @param runnable:
	 * @author liqingdian
	 * @since 2023-05-29
	 */
	public static void tryCatch(Runnable runnable, boolean throwUp) {
		tryCatchFinally(runnable, null, throwUp);
	}

	/**
	 * <p>
	 * try...catch...finally...
	 * </p>
	 *
	 * @param runnable:
	 * @author liqingdian
	 * @since 2023-05-29
	 */
	public static void tryCatchFinally(Runnable runnable, Runnable finallyRun, boolean throwUp) {
		try {
			runnable.run();
		} catch (Exception e) {
			if (throwUp) {
				throw e;
			} else {
				log.error(e.getMessage(), e);
			}
		} finally {
			boolean nonNull = Objects.nonNull(finallyRun);
			if (nonNull) {
				finallyRun.run();
			}
		}
	}

	/**
	 * <p>
	 * try...catch...finally...
	 * </p>
	 *
	 * @param runnable:
	 * @author liqingdian
	 * @since 2023-05-29
	 */
	public static void tryCatchFinally(Runnable runnable, Runnable finallyRun) {
		tryCatchFinally(runnable, finallyRun, false);
	}

	/**
	 * <p>
	 * switch
	 * </p>
	 *
	 * @param param:
	 * @param predicates:
	 * @param reliables:
	 * @param defaultRun:
	 * @author liqingdian
	 * @since 2023-05-29
	 */
	public static <T> void switchRun(T param, Predicate<T>[] predicates, Runnable[] reliables, Runnable defaultRun) {
		boolean isRun = false;
		for (int i = 0; i < predicates.length; i++) {
			if (predicates[i].test(param)) {
				isRun = true;
				reliables[i].run();
				break;
			}
		}
		if (!isRun && Objects.nonNull(defaultRun)) {
			defaultRun.run();
		}
	}

	/**
	 * <p>
	 * switch
	 * </p>
	 *
	 * @param param:
	 * @param predicates:
	 * @param reliables:
	 * @author liqingdian
	 * @since 2023-05-29
	 */
	public static <T> void switchRun(T param, Predicate<T>[] predicates, Runnable[] reliables) {
		switchRun(param, predicates, reliables, null);
	}

	/**
	 * 效验流程节点是否为空 跑运行时异常
	 * @param approveUserMap
	 * @return
	 */
//	public static Map<String, List<CandidateInfoDto>> verifyAndParseApproveUser(Map<String, Object> approveUserMap){
//		if(ObjectUtil.isEmpty(approveUserMap)){
//			// todo 抛异常
//			throw  new RuntimeException("审批人数据格式异常,请联系运维人员");
//		}
//		JSONObject jsonBean = BeanUtil.mapToBean(approveUserMap, JSONObject.class, false, CopyOptions.create());
//		JSONObject value = jsonBean.getJSONObject("value");
//		JSONArray nodes = jsonBean.getJSONArray("nodes");
//		if(ObjectUtil.isEmpty(value)){
//			//todo 审批人数据格式异常
//			throw  new RuntimeException("审批人数据格式异常,请联系运维人员");
//		}
//		if(ObjectUtil.isEmpty(nodes)){
//			//todo 审批人数据格式异常
//			throw  new RuntimeException("审批人数据格式异常,请联系运维人员");
//		}
//		StringBuilder errorMsg = new StringBuilder();
//		List<String> allowNodeType = Lists.newArrayList("userTask", "multiuserTask", "userCopyTask");
//		for (int i = 0, len = nodes.size(); i < len; i++) {
//			JSONObject node = nodes.getJSONObject(i);
//			String name = node.getStr("name");
//			String nodeType = node.getStr("nodeType");
//			String candidateType = node.getStr("candidateType");
//			if (!allowNodeType.contains(nodeType) && StrUtil.equals("2", candidateType)) {
//				continue;
//			}
//			JSONArray selecteUsers = node.getJSONArray("selecteUsers");
//			if (CollUtil.isEmpty(selecteUsers)) {
//				errorMsg.append("[");
//				errorMsg.append(name);
//				errorMsg.append("]");
//			}
//		}
//		String msg = StrUtil.EMPTY;
//		if (errorMsg.length() > 0){
//			msg = errorMsg.substring(0,errorMsg.length() - 1) +"请先选择审批人后重试!";
//			//todo 抛出异常
//			throw  new RuntimeException(msg);
//		}
//		Map<String, List<CandidateInfoDto>> approveUser = Maps.newLinkedHashMapWithExpectedSize(value.size());
//		for (String key  : value.keySet()){
//			approveUser.put(key, value.getBeanList(key, CandidateInfoDto.class));
//		}
//		return approveUser;
//	}
	public static boolean verifyAndParseApproveUser(Map<String, List<CandidateInfoDto>> approveUserMap){
		if(ObjectUtil.isEmpty(approveUserMap)){
			// todo 抛异常
			return false;
		}
		int n = 0;
		for (List<CandidateInfoDto> v : approveUserMap.values()) {
			if(CollUtil.isEmpty(v)){
				n++;
			}
			if(n > 3){
				return false;
			}
		}
		return true;
	}

	public static boolean verifyAndParseApproveUserTwo(Map<String, List<CandidateInfoDto>> approveUserMap){
		if(ObjectUtil.isEmpty(approveUserMap)){
			// todo 抛异常
			return false;
		}
		int n = 0;
		for (List<CandidateInfoDto> v : approveUserMap.values()) {
			if(CollUtil.isEmpty(v)){
				n++;
			}
			if(n > 4){
				return false;
			}
		}
		return true;
	}
}
