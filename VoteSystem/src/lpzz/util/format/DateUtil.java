package lpzz.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类：提供日期处理的通用功能
 */
public class DateUtil {
	// 将长整形转换成日期格式的字符串
	public static String toShortDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return df.format(new Date(time));
	}	
	//将字符串格式的日期转换成长整形
	public static Long toLong(String time) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return df.parse(time).getTime();
	}
	
}
