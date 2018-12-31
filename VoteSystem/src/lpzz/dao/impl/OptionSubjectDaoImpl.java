package lpzz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lpzz.dao.OptionSubjectDao;
import lpzz.pojo.OptionSubject;
import lpzz.pojo.OptionSubjectQueryModel;
import lpzz.util.base.BaseDaoImpl;
import lpzz.util.base.BaseQueryModel;
import lpzz.util.dao.DbHelper;

public class OptionSubjectDaoImpl extends BaseDaoImpl implements OptionSubjectDao {

	/**
	 * ����һ����¼
	 */
	@Override
	public String getInsertSql(Object data) {
		OptionSubject os=(OptionSubject) data;
		return "insert into optionSubject (SubjectId,OptionContent) values ("+os.getSubjectId()+",'"+os.getOptionContent()+"')";
	}

	/**
	 * ����ѡ��id��������
	 */
	@Override
	public String getUpdateSql(Object data) {
		OptionSubject os=(OptionSubject) data;
		return "update optionSubject set OptionContent='"+os.getOptionContent()+"' where OptionId="+os.getOptionId();
	}

	/**
	 * ����ѡ��idɾ������
	 */
	@Override
	public String getDeleteSql(int id) {
		return "delete from optionSubject where OptionId="+id;
	}

	/**
	 * �������б���ѡ��
	 */
	@Override
	public String getFindAllSql() {
		return "select * from optionSubject";
	}

	/**
	 * ����������ѯ��¼�����磬������Ŀ����id���Ҹ���Ŀ����Щѡ��
	 */
	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel) {
		OptionSubjectQueryModel qm = (OptionSubjectQueryModel)queryModel;
		StringBuilder sb=new StringBuilder();
		sb.append("select * from optionSubject where 1=1 ");
		if(qm.getOptionId()!=0){
			sb.append(" and OptionId="+qm.getOptionId());
		}
		if(qm.getSubjectId()!=0){
			sb.append(" and SubjectId="+qm.getSubjectId());
		}
		if(qm.getOptionContent()!=null&&qm.getOptionContent().trim().length()>0){
			sb.append(" and OptionContent='"+qm.getOptionContent()+"'");
		}
		return sb.toString();
	}

	/**
	 * ������Ŀѡ��id����һ����¼
	 */
	@Override
	public String getFindOneSql(int id) {
		return "select * from optionSubject where OptionId="+id;
	}

	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		OptionSubject os=new OptionSubject();
		os.setOptionId(rs.getLong("OptionId"));
		os.setSubjectId(rs.getLong("SubjectId"));
		os.setOptionContent(rs.getString("OptionContent"));
		return os;
	}

	/**
	 * ��������ɾ��
	 */
	@Override
	public void deleteQueryModel(OptionSubject os) throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("delete from optionSubject where 1=1 ");
		if(os.getOptionId()!=0){
			sb.append(" and OptionId="+os.getOptionId());
		}
		if(os.getSubjectId()!=0){
			sb.append(" and SubjectId="+os.getSubjectId());
		}
		if(os.getOptionContent()!=null&&os.getOptionContent().trim().length()>0){
			sb.append(" and OptionContent='"+os.getOptionContent()+"'");
		}
		Connection con = DbHelper.getConnection();
		PreparedStatement pst = con.prepareStatement(sb.toString());
	    pst.executeUpdate();
		DbHelper.closeAll(null, pst, null);	
	}

}
