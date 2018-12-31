package lpzz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lpzz.dao.VoteDao;
import lpzz.pojo.Vote;
import lpzz.pojo.VoteQueryModel;
import lpzz.util.base.BaseDaoImpl;
import lpzz.util.base.BaseQueryModel;
import lpzz.util.dao.DbHelper;

public class VoteDaoImpl extends BaseDaoImpl implements VoteDao {

	@Override
	public String getInsertSql(Object data) {
		Vote vote=(Vote) data;
		return "insert into vote values("+vote.getSubject().getSubjectId()+","+vote.getOption().getOptionId()+","+vote.getUser().getUid()+")";
	}

	//有些方法暂时不需要就不做处理
	@Override
	public String getUpdateSql(Object data) {
		return null;
	}

	@Override
	public String getDeleteSql(int id) {
		return null;
	}

	@Override
	public String getFindAllSql() {
		return "select * from vote";
	}

	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel) {
	    VoteQueryModel qm = (VoteQueryModel)queryModel;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from vote where 1=1 ");
		if(qm.getSubject().getSubjectId()!=0){
			sb.append(" and SubjectId="+qm.getSubject().getSubjectId());
		}
		if(qm.getUser().getUid()!=0){
			sb.append(" and UId="+qm.getUser().getUid());
		}
		
		return sb.toString();
	}

	
	
	@Override
	public String getFindOneSql(int id) {
		return null;
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		Vote vote= new Vote();
		vote.setOptionId(rs.getInt("OptionId"));
		vote.getUser().setUid(rs.getInt("UId"));
		vote.getSubject().setSubjectId(rs.getInt("SubjectId"));
		vote.getOption().setOptionId(rs.getInt("OptionId"));
		return vote;
	}

	@Override
	public long getOptionCount(long subjectId,long optionId) throws Exception {
		//拿到连接池连接，编写SQL语句，通过连接创建预处理对象，拿到查询结果集关闭连接返回结果
		long result = 0;
		Connection con = DbHelper.getConnection();
		String sql = "select count(*) as cnt from vote where SubjectId="+subjectId+" and OptionId="+optionId;
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			result = rs.getInt("cnt");
		}
		DbHelper.closeAll(null, pst, rs);
		return result;
	}

	@Override
	public void deleteQueryModel(Vote vote) throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("delete from vote where 1=1 ");
		if(vote.getOption().getOptionId()!=0){
			sb.append(" and OptionId="+vote.getOptionId());
		}
		if(vote.getSubject().getSubjectId()!=0){
			sb.append(" and SubjectId="+vote.getSubject().getSubjectId());
		}
		if(vote.getUser().getUid()!=0){
			sb.append(" and UId="+vote.getUser().getUid());
		}
		Connection con = DbHelper.getConnection();
		PreparedStatement pst = con.prepareStatement(sb.toString());
		pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);	
	}

}
