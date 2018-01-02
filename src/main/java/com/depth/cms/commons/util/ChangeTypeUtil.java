package com.depth.cms.commons.util;


import java.util.ArrayList;
import java.util.List;

public class ChangeTypeUtil {

	//将数组转换成list
	public static List<String> convertStringToList(String ids){
		List<String> resultList = new ArrayList<String>();
		String[] idss = ids.split(",");
		for(String s:idss){
			resultList.add(s);
		}
		return resultList;
	}
}
