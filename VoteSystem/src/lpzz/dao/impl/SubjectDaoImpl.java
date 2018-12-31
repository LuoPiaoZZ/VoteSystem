package lpzz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lpzz.dao.SubjectDao;
import lpzz.pojo.Subject;
import lpzz.pojo.SubjectQueryModel;
import lpzz.util.base.BaseDaoImpl;
import lpzz.util.base.BaseQueryModel;
import lpzz.util.dao.DbHelper;

public class SubjectDaoImpl extends BaseDaoImpl implements SubjectDao {

	/**
	 * 插入一条记录
	 */
	@Override
	public String getInsertSql(Object data) {
		Subject subject=(Subject) data;
		return "insert into subject (UId,Title,OptionWay,StartTime,EndTime) values("+subject.getUid()+",'"+subject.getTitle()+"',"+subject.getOptionWay()+","+subject.getStartTime()+","+subject.getEndTime()+")";
	}

	/**
	 * 数据更新
	 */
	@Override
	public String getUpdateSql(Object data) {
		Subject subject=(Subject) data;
		return "update subject set Title='"+subject.getTitle()+"',OptionWay="+subject.getOptionWay()+",StartTime="+subject.getStartTime()+",EndTime="+subject.getEndTime()+" where SubjectId="+subject.getSubjectId();
	}

	/**
	 * 删除指定id记录
	 */
	@Override
	public String getDeleteSql(int id) {
		return "delete from subject where SubjectId="+id;
	}

	/**
	 * 查找全部记录
	 */
	@Override
	public String getFindAllSql() {
		return "select * from subject";
	}

	/**
	 * 特殊条件查找，这里只有主题和发起人id元组条件查找
	 */
	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel) {
		SubjectQueryModel qm = (SubjectQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from subject where 1=1 ");
		if(qm.getTitle()!=null && qm.getTitle().trim().length()>0){
			sb.append(" and Title='"+qm.getTitle()+"' ");
		}
		if(qm.getUser()!=null && qm.getUser().getUid()!=0){
			sb.append(" and UId="+qm.getUser().getUid());
		}
		if(qm.getSubjectId()!=0){
			sb.append(" and SubjectId="+qm.getSubjectId());
		}
		return sb.toString();
	}

	/**
	 * 查找指定id的记录
	 */
	@Override
	public String getFindOneSql(int id) {
		return "select * from subject where SubjectId="+id;
	}

	/**
	 * 将记录转化为实体对象
	 */
	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		Subject subject= new Subject();
		subject.setSubjectId(rs.getInt("SubjectId"));
		subject.setTitle(rs.getString("Title"));
		subject.setOptionWay(rs.getInt("OptionWay"));
		subject.setStartTime(rs.getLong("StartTime"));
		subject.setEndTime(rs.getLong("EndTime"));
		long uid= rs.getInt("UId");
		subject.setUid(uid);
		subject.getUser().setUid(uid);
		return subject;
	}

	/**
	 * 计算参与项目的人数，去重
	 */
	@Override
	public long getUserCount(long subjectId) throws Exception {
		//拿到连接池连接，编写SQL语句，通过连接创建预处理对象，拿到查询结果集关闭连接返回结果
		long result = 0;
		Connection con = DbHelper.getConnection();
		String sql = "select count(distinct UId) as cnt from vote where SubjectId="+subjectId;
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			result = rs.getInt("cnt");
		}
		DbHelper.closeAll(null, pst, rs);
		return result;
	}

	@Override
	public long getGenerateId() throws Exception {
		long result = 0;
		Connection con = DbHelper.getConnection();
		String sql = "SELECT max(SubjectId) as id from subject";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			result = rs.getInt("id");
		}
		DbHelper.closeAll(null, pst, rs);
		return result;
	}

}
