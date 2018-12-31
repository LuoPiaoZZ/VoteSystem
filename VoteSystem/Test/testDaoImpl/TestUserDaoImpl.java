package testDaoImpl;

import org.junit.Test;

import lpzz.dao.impl.UserDaoImpl;
import lpzz.pojo.User;
import lpzz.util.base.BaseDaoImpl;

public class TestUserDaoImpl {

	@Test
	public void test() {
		//使用父类可以实现子类的任何方法,多态
		BaseDaoImpl userDao=new UserDaoImpl();
	
		User user=new User();
		user.setName("罗飘");
		user.setPsw("lp");
		user.setGender(1);
		user.setAge(20);
		
		try {
			userDao.insert(user);
			System.out.println("成功添加");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		try {
			userDao.delete(1);
			System.out.println("已经删除");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
