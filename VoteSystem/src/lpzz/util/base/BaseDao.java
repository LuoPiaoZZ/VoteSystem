package lpzz.util.base;

import java.util.List;

/**
 * 数据访问的父接口，声明了通用的增删改查的方法
 */
public interface BaseDao {
	
	/**
	 * 增加一条记录，新增时，数据中的id==null，修改时，id一定要有值
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int insert(Object data) throws Exception;
	/**
	 * 更新
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public int update(Object data) throws Exception;
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(int id) throws Exception;
	/**
	 * 查询所有记录返回list泛型对象
	 * @return
	 * @throws Exception
	 */
	public List<Object> findAll() throws Exception;
	/**
	 * 根据id查找一条记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object findOne(int id) throws Exception;
	/**
	 * 指定的特殊条件查询
	 * @param queryModel
	 * @return
	 * @throws Exception
	 */
	public List<Object> findByCondition(BaseQueryModel queryModel) throws Exception;
	/**
	 * 最新添加的记录
	 * @return
	 * @throws Exception
	 */
	public Long findMaxId() throws Exception;
	
	
	
}








