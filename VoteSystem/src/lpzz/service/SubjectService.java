package lpzz.service;

import java.util.List;

import lpzz.pojo.Subject;
import lpzz.pojo.User;

public interface SubjectService {
	/**
	 * ���
	 * @param subject
	 * @param user
	 * @throws Exception
	 */
	public void add(Subject subject,User user) throws Exception;
	
	/**
	 * ��ý����
	 * @return
	 * @throws Exception
	 */
	public List<Object> getSubjects() throws Exception;
	
	/**
	 * ����id���һ������ʵ����
	 * @param subjectId
	 * @return
	 * @throws Exception
	 */
	public Subject getSubject(int subjectId) throws Exception;
	
	/**
	 * �޸�һ�������¼
	 * @param subject
	 * @param attribute
	 * @throws Exception
	 */
	public void modify(Subject subject, User attribute) throws Exception;
	/**
	 * ͨ��ɾ����ά��
	 * @param subject
	 * @throws Exception
	 */
	public void deleteModify(Subject subject) throws Exception;
}
