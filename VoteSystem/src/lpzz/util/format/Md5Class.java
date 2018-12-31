package lpzz.util.format;

import java.security.MessageDigest;

public class Md5Class {
	// 传入一个明文字符串，返回加密后的md5字符串,16位
	public static String stringToMd5(String info) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = info.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	//可多次加密，增加破解不确定性，这里加密了6次
	public static String ManyTimeMd5(String info){
		int count=5;
		String newInfo=stringToMd5(info);
		while(count>0){
			newInfo=stringToMd5(newInfo);
			count--;
		}
		return newInfo;
	}
}
