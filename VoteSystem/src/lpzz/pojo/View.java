package lpzz.pojo;

import java.util.ArrayList;
import java.util.List;
/**
 * Ϊ�˷���ͳ��ͶƱ��Ŀ����ѡ�����ݴ���
 */
public class View {
	
	private Subject subject;
	private List<Object> countsOption=new ArrayList<Object>();//Ӧ����ϼ�ֵ��ʹ��Ӧ�û����
	
	
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public List<Object> getCountOption() {
		return countsOption;
	}
	public void setCountOption(List<Object> countsOption) {
		this.countsOption = countsOption;
	}
	
}
