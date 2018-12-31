package lpzz.pojo;

import java.util.ArrayList;
import java.util.List;
/**
 * 为了方便统计投票项目各个选项数据创建
 */
public class View {
	
	private Subject subject;
	private List<Object> countsOption=new ArrayList<Object>();//应该配合键值对使用应该会更好
	
	
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
