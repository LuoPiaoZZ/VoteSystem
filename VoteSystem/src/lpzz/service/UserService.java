package lpzz.service;

import java.util.List;

import lpzz.pojo.User;
/**
 * �����û����ܽӿ�
 */
public interface UserService {
	//�û�ע��
	public void register(User user) throws Exception;
	//�û���¼�������û�ʵ����
	public User login(User user) throws Exception;
	//����UId�����û�����
	public User getUser(int uid) throws Exception;
	//�����û��漰��Ŀ
	public List<Object> getSubjects(long UId) throws Exception;
}
