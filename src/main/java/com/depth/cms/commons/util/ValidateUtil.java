package com.depth.cms.commons.util;

import java.util.Map;

public class ValidateUtil {
	/**
	 * 将Map<String,String>参数的值验证不为空后的值添加到目标Map<String,Object>中
	 * @param params 应用层传到服务层的map
	 * @param key 要验证的属性key
	 * @param target 转换后传到DAO层的map
	 */
	public static void addValueToTargetMap(Map<String,String> params,String key,Map<String,Object> target,Class<?> t){
//		if(StringUtils.isEmpty(params.get(key))){
//			throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//		} else {
//			if(t != null){
//				try {
//					target.put(key, CommonConvertType.help.setType(t).getValue(params.get(key)));
//				} catch (Exception e) {
//					throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//				}
//			} else {
//				target.put(key,params.get(key));
//			}
//		}
	}

	public static void addStringToTargetMap(String param,String key,Map<String,Object> target,Class<?> t){
//		if(StringUtils.isEmpty(param)){
//			throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//		} else {
//			if(t != null){
//				try {
//					target.put(key, CommonConvertType.help.setType(t).getValue(param));
//				} catch (Exception e) {
//					throw ContentBizException.PARAM_IS_NULL.newInstance(param);
//				}
//			} else {
//				target.put(key,param);
//			}
//		}
	}

	public static void addObjMapToTargetMap(Map<String,Object> params,String key,Map<String,Object> target,Class<?> t){
//		if(StringUtils.isEmpty(params.get(key))){
//			throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//		} else {
//			if(t != null){
//				try {
//					target.put(key, CommonConvertType.help.setType(t).getValue(params.get(key).toString()));
//				} catch (Exception e) {
//					throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//				}
//			} else {
//				target.put(key,params.get(key));
//			}
//		}
	}

	public static void addObjMapToMap(Map<String,Object> params,String key,Map<String,Object> target,Class<?> t){
//		if(!StringUtils.isEmpty(params.get(key))){
//			if(t != null){
//				try {
//					target.put(key, CommonConvertType.help.setType(t).getValue(params.get(key).toString()));
//				} catch (Exception e) {
//					throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//				}
//			} else {
//				target.put(key,params.get(key));
//			}
//		}
	}

	public static void validateLongIsNotEmpty(Long param,String key){
//		if(param == null || param.longValue() <= 0){
//			throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//		}
	}

	public static void validateLongNotEmptys(Long param,String key){
//		if(param == null || param.longValue() < 0){
//			throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//		}
	}

	public static void validateStringIsNotEmpty(String param,String key){
//		if(StringUtils.isEmpty(param)){
//			throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//		}
	}

	/**
	 * 将Map<String,String>参数的值验证不为空后的值添加到目标Map<String,Object>中
	 * @param params 应用层传到服务层的map
	 * @param key 	 要验证的属性key
	 * @param target 转换后传到DAO层的map
	 */
	public static void addValueToTPTargetMap(Map params, String key, Map target){
//		if(StringUtils.isEmpty(params.get(key))){
//			throw ContentBizException.PARAM_IS_NULL.newInstance(key);
//		} else {
//			target.put(key,params.get(key));
//		}
	}

	/**
	 *
	 * @param params
	 * @param key
	 * @param target
	 */
	/**
	 * 将Map<String,String>参数的值验证不为空后的值添加到目标Map<String,Object>中
	 * <p>@Description
	 * <p>author hanfeng
	 * <p>@Date 2017/3/29 17:01
	 * <p>@param params 应用层传到服务层的map
	 * <p>@param key  	要验证的属性key
	 * <p>@param targetKey  key转换的key名
	 * <p>@param target		转换后传到DAO层的map
	 * <p>@return  void
	 */
	public static void addValueToTPTargetMap(Map params, String key, String targetKey, Map target){
//		if(StringUtils.isEmpty(params.get(key))){
//			throw ContentBizException.PARAM_IS_NULL.newInstance(targetKey);
//		} else {
//			target.put(targetKey,params.get(key));
//		}
	}

}
