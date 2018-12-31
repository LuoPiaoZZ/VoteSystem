package lpzz.service;

import java.util.List;

import lpzz.pojo.Subject;
import lpzz.pojo.User;

public interface SubjectService {
	/**
	 * 添加
	 * @param subject
	 * @param user
	 * @throws Exception
	 */
	public void add(Subject subject,User user) throws Exception;
	
	/**
	 * 获得结果集
	 * @return
	 * @throws Exception
	 */
	public List<Object> getSubjects() throws Exception;
	
	/**
	 * 根据id获得一个主题实体类
	 * @param subjectId
	 * @return
	 * @throws Exception
	 */
	public Subject getSubject(int subjectId) throws Exception;
	
	/**
	 * 修改一条主题记录
	 * @param subject
	 * @param attribute
	 * @throws Exception
	 */
	public void modify(Subject subject, User attribute) throws Exception;
	/**
	 * 通过删除来维护
	 * @param subject
	 * @throws Exception
	 */
	public void deleteModify(Subject subject) throws Exception;
}
