package lpzz.pojo;

import java.util.ArrayList;
import java.util.List;
/**
 * 投票项目实体类
 */
public class Subject {
	//以下是数据类型已经在内部进行了处理
	private long subjectId;
	private long uid;
	private String title;
	private int optionWay;//选择方式：单选、多选
	private long startTime;
	private long endTime;
	
	//1单选2多选
	public static final int SIMPLY=1;
	public static final int MULTI=2;
	
	//view属性
	private String startTimeView;
	private String endTimeView;
	private long userCount;
	
	//对象一定要进行初始化，否则进行操作的时候回报空指针异常
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
	//导航属性
	private User user;
	private List<Object> options;//选项列表
	private int optionCount;//选项数量
	
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
