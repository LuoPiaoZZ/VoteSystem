package lpzz.pojo;
/**
 * �û�ʵ����
 */
public class User {
	private long uid;
	private String name;
	private String psw;
	private int gender;
	private int age;
	
	//1����2Ů��3����
	public static final int KEEPSECRET=3;
	public static final int GIRL=2;
	public static final int BOY=1;
	
	public final static String SESSION_NAME ="CurrentUser";
	//ȷ������
	private String confirmPsw;
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getConfirmPsw() {
		return confirmPsw;
	}
	public void setConfirmPsw(String confirmPwd) {
		this.confirmPsw = confirmPwd;
	}
}
