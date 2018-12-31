package lpzz.pojo;

import java.util.ArrayList;
import java.util.List;
/**
 * ͶƱ��Ŀʵ����
 */
public class Subject {
	//���������������Ѿ����ڲ������˴���
	private long subjectId;
	private long uid;
	private String title;
	private int optionWay;//ѡ��ʽ����ѡ����ѡ
	private long startTime;
	private long endTime;
	
	//1��ѡ2��ѡ
	public static final int SIMPLY=1;
	public static final int MULTI=2;
	
	//view����
	private String startTimeView;
	private String endTimeView;
	private long userCount;
	
	//����һ��Ҫ���г�ʼ����������в�����ʱ��ر���ָ���쳣
	public Subject() {
		super();
		options=new ArrayList<Object>();
		user=new User();
	}
	public long getUserCount() {
		return userCount;
	}
	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}
	//��������
	private User user;
	private List<Object> options;//ѡ���б�
	private int optionCount;//ѡ������
	
	public String getStartTimeView() {
		return startTimeView;
	}
	public void setStartTimeView(String startTimeView) {
		this.startTimeView = startTimeView;
	}
	public String getEndTimeView() {
		return endTimeView;
	}
	public void setEndTimeView(String endTimeView) {
		this.endTimeView = endTimeView;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Object> getOptions() {
		return options;
	}
	public void setOptions(List<Object> list) {
		this.options = list;
	}
	public int getOptionCount() {
		return optionCount;
	}
	public void setOptionCount(int optionCount) {
		this.optionCount = optionCount;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getOptionWay() {
		return optionWay;
	}
	public void setOptionWay(int optionWay) {
		this.optionWay = optionWay;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	
}
