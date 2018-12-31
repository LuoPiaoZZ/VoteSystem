package lpzz.pojo;

public class Vote {
	private long optionId;
	private User user;
	private Subject subject;
	private OptionSubject option;
	
	public Vote(){
		super();
		user=new User();
		subject=new Subject();
		option=new OptionSubject();
	}
	public long getOptionId() {
		return optionId;
	}
	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public OptionSubject getOption() {
		return option;
	}
	public void setOption(OptionSubject option) {
		this.option = option;
	}
	
}
