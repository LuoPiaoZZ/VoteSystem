package lpzz.util.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lpzz.util.dao.DbHelper;

/**
 * 数据访问的父类， 实现BaseDao接口中的通用的增删改查的方法
 */
public abstract class BaseDaoImpl implements BaseDao {

	/**
	 * 新增一条记录，不需要id
	 */
	@Override
	public int insert(Object data) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = getInsertSql(data);
		PreparedStatement pst = con.prepareStatement(sql);
		int rows = pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);
		return rows;
	}
	/**
	 * 更新记录，需要id
	 */
	@Override
	public int update(Object data) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = getUpdateSql(data);
		PreparedStatement pst = con.prepareStatement(sql);
		int rows = pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);
		return rows;
	}
	/**
	 * 根据id删除记录
	 */
	@Override
	public int delete(int id) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = getDeleteSql(id);
		PreparedStatement pst = con.prepareStatement(sql);
		int rows = pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);
		return rows;
	}
	/**
	 * 查找所有记录，返回list泛型对象
	 */
	public List<Object> findAll() throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = getFindAllSql();
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List<Object> list = new ArrayList<Object>();
		while (rs.next()) {
			Object data = rsToObject(rs);
			list.add(data);
		}
		DbHelper.closeAll(null, pst, null);

		return list;
	}
	/**
	 * 根据id查找一条记录并返回一个实体类
	 */
	public Object findOne(int id) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = getFindOneSql(id);
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		Object result = null;
		if (rs.next()) {
			result = rsToObject(rs);
		}
		DbHelper.closeAll(null, pst, null);

		return result;
	}
	
	/**
	 * 特殊条件查找所有记录，返回list泛型对象
	 */
	public List<Object> findByCondition(BaseQueryModel queryModel) throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = getFindByConditionSql(queryModel);
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List<Object> list = new ArrayList<Object>();
		while (rs.next()) {
			list.add(rsToObject(rs));
		}
		DbHelper.closeAll(null, pst, null);

		return list;

	}

	/**
	 * 同一个连接下获取最新添加的自增id值，理论上max(id)也可以实现同一效果，但是高并发情况下就不适用了。
	 * 而且，需要注意的是如果sql语句执行出错，则调用last_insert_id()的值未定义。例如，若事务因执行出错回滚，则last_insert_id()的值不会恢复到事务执行前的那个值。
	 */
	public Long findMaxId() throws Exception {
		Connection con = DbHelper.getConnection();
		String sql = "SELECT LAST_INSERT_ID() AS maxid";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		Long result = null;
		if (rs.next()) {
			result = rs.getLong("maxid");
		}
		DbHelper.closeAll(null, pst, null);

		return result;
	}

	// 多态，将产生具体的SQL语句的功能留给具体的子类来实现，因为只有具体的数据访问的子类才对应具体的表，将接口和抽象类结合使用在项目中会较多使用
	public abstract String getInsertSql(Object data);

	public abstract String getUpdateSql(Object data);

	public abstract String getDeleteSql(int id);

	public abstract String getFindAllSql();

	public abstract String getFindByConditionSql(BaseQueryModel queryModel);
	
	public abstract String getFindOneSql(int id);
	// 将记录转换成一个对象
	public abstract Object rsToObject(ResultSet rs) throws Exception;
}
