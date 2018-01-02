package com.depth.cms.commons.util;
import java.util.Random;

public class RandomUtil {

	/**
	 * 生成随机密码【英文字母+数字】
	 * @param length
	 * @return
	 */
	public static String pwdRandom(int length){
		String password = "";
		Random random = new Random();

		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			//输出字母还是数字
			if( "char".equalsIgnoreCase(charOrNum) ) {
				//输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				password += (char)(random.nextInt(26) + temp);
			} else if( "num".equalsIgnoreCase(charOrNum) ) {
				password += String.valueOf(random.nextInt(10));
			}
		}
		return password;
	}

	public static void  main(String[] args) {
		//测试
		System.out.println(RandomUtil.pwdRandom(6));
	}
}
