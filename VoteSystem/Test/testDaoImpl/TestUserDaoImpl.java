package testDaoImpl;

import org.junit.Test;

import lpzz.dao.impl.UserDaoImpl;
import lpzz.pojo.User;
import lpzz.util.base.BaseDaoImpl;

public class TestUserDaoImpl {

	@Test
	public void test() {
		//ʹ�ø������ʵ��������κη���,��̬
		BaseDaoImpl userDao=new UserDaoImpl();
	
		User user=new User();
		user.setName("��Ʈ");
		user.setPsw("lp");
		user.setGender(1);
		user.setAge(20);
		
		try {
			userDao.insert(user);
			System.out.println("�ɹ����");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		try {
			userDao.delete(1);
			System.out.println("�Ѿ�ɾ��");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
