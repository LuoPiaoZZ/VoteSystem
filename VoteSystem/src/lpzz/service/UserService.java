package lpzz.service;

import java.util.List;

import lpzz.pojo.User;
/**
 * 定义用户功能接口
 */
public interface UserService {
	//用户注册
	public void register(User user) throws Exception;
	//用户登录，返回用户实体类
	public User login(User user) throws Exception;
	//根据UId查找用户对象
	public User getUser(int uid) throws Exception;
	//查找用户涉及项目
	public List<Object> getSubjects(long UId) throws Exception;
}
