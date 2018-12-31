package lpzz.util.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lpzz.util.dao.DbHelper;

/**
 * ���ݷ��ʵĸ��࣬ ʵ��BaseDao�ӿ��е�ͨ�õ���ɾ�Ĳ�ķ���
 */
public abstract class BaseDaoImpl implements BaseDao {

	/**
	 * ����һ����¼������Ҫid
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
	 * ���¼�¼����Ҫid
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
	 * ����idɾ����¼
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
	 * �������м�¼������list���Ͷ���
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
	 * ����id����һ����¼������һ��ʵ����
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
	 * ���������������м�¼������list���Ͷ���
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
	 * ͬһ�������»�ȡ������ӵ�����idֵ��������max(id)Ҳ����ʵ��ͬһЧ�������Ǹ߲�������¾Ͳ������ˡ�
	 * ���ң���Ҫע��������sql���ִ�г��������last_insert_id()��ֵδ���塣���磬��������ִ�г���ع�����last_insert_id()��ֵ����ָ�������ִ��ǰ���Ǹ�ֵ��
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

	// ��̬�������������SQL���Ĺ������������������ʵ�֣���Ϊֻ�о�������ݷ��ʵ�����Ŷ�Ӧ����ı����ӿںͳ�������ʹ������Ŀ�л�϶�ʹ��
	public abstract String getInsertSql(Object data);

	public abstract String getUpdateSql(Object data);

	public abstract String getDeleteSql(int id);

	public abstract String getFindAllSql();

	public abstract String getFindByConditionSql(BaseQueryModel queryModel);
	
	public abstract String getFindOneSql(int id);
	// ����¼ת����һ������
	public abstract Object rsToObject(ResultSet rs) throws Exception;
}
