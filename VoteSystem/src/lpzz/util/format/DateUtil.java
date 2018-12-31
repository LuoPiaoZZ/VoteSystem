package lpzz.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڹ����ࣺ�ṩ���ڴ����ͨ�ù���
 */
public class DateUtil {
	// ��������ת�������ڸ�ʽ���ַ���
	public static String toShortDate(Long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return df.format(new Date(time));
	}	
	//���ַ�����ʽ������ת���ɳ�����
	public static Long toLong(String time) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return df.parse(time).getTime();
	}
	
}
