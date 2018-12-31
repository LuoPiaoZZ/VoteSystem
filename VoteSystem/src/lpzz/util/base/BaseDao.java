package lpzz.util.base;

import java.util.List;

/**
 * ���ݷ��ʵĸ��ӿڣ�������ͨ�õ���ɾ�Ĳ�ķ���
 */
public interface BaseDao {
	
	/**
	 * ����һ����¼������ʱ�������е�id==null���޸�ʱ��idһ��Ҫ��ֵ
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insert(Object data) throws Exception;
	/**
	 * ����
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int update(Object data) throws Exception;
	/**
	 * ����idɾ��
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(int id) throws Exception;
	/**
	 * ��ѯ���м�¼����list���Ͷ���
	 * @return
	 * @throws Exception
	 */
	public List<Object> findAll() throws Exception;
	/**
	 * ����id����һ����¼
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object findOne(int id) throws Exception;
	/**
	 * ָ��������������ѯ
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	public List<Object> findByCondition(BaseQueryModel queryModel) throws Exception;
	/**
	 * ������ӵļ�¼
	 * @return
	 * @throws Exception
	 */
	public Long findMaxId() throws Exception;
	
	
	
}








