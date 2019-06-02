package com.lyh.util;

import java.util.ArrayList;
import java.util.List;

public class MyUtil {

	/**
	 * 把传来的string ids 变成 List<Integer> ids = new ArrayList<Integer>() 返回
	 * 因为mybatis查范围,要用的迭代
	 */
	public static List<Integer> Str_ids_To_ListInteger_ids(String ids) {
		List<Integer> ListInteger_ids = new ArrayList<Integer>();
		String[] arr = ids.split(",");
		for (String i : arr) {
			// 验证是不是数字
			if (i.matches("\\d+")) {
				ListInteger_ids.add(Integer.parseInt(i));
			}
		}
		return ListInteger_ids;
	}


}
