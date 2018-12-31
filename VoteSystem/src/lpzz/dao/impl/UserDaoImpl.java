package lpzz.dao.impl;

import java.sql.ResultSet;
import lpzz.dao.UserDao;
import lpzz.pojo.User;
import lpzz.pojo.UserQueryModel;
import lpzz.util.base.BaseDaoImpl;
import lpzz.util.base.BaseQueryModel;
/**
 * 继承和实现了基础的BaseDao接口的实现类BaseDaoImpl以及其子接口UserDao
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public String getInsertSql(Object data) {
		User user=(User) data;
		return "insert into user(Name,Psw, Gender,Age) " +" values('"+user.getName()+"','"+user.getPsw()+"',"+user.getGender()+","+user.getAge()+")";
	}

	@Override
	public String getUpdateSql(Object data) {
		User user=(User) data;
		return "update user set "+" Name='"+user.getName()+"',"+"Psw='"+user.getPsw()+"'," + "Gender="+user.getGender()+"Age="+user.getAge()+" "+"where UId="+user.getUid();
	}

	@Override
	public String getDeleteSql(int id) {
		return "delete from user where UId="+id;
	}

	@Override
	public String getFindAllSql() {
		return "select * from user";
	}
	
	/**
	 * 实现特殊条件查找，有值的就拼接到sql语句的条件里面
	 */
	@Override
	public String getFindByConditionSql(BaseQueryModel queryModel) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from user where 1=1 ");
		UserQueryModel qm = (UserQueryModel)queryModel;
		if(qm.getName()!=null &&qm.getName().trim().length()>0){
			sb.append(" and Name ='"+qm.getName()+"'");
		}	
		if(qm.getPsw()!=null &&qm.getPsw().trim().length()>0){
				sb.append(" and Psw ='"+qm.getPsw()+"'");
		}
		if(qm.getGender()>0){
			sb.append(" and Gender="+qm.getGender());
		}
		if(qm.getAge()>0){
			sb.append(" and UId="+qm.getUid());
		}
		if(qm.getAge()>0){
			sb.append(" and Age="+qm.getAge());
		}
		
		return sb.toString();
	}

	/**
	 * 将记录转化成用户对象
	 */
	@Override
	public Object rsToObject(ResultSet rs) throws Exception {
		User  user = new User();
		user.setUid(rs.getLong("UId"));
		user.setName(rs.getString("Name"));
		user.setPsw(rs.getString("Psw"));
		user.setGender(rs.getInt("Gender"));
		user.setAge(rs.getInt("Age"));
		
		return user;
	}

	@Override
	public String getFindOneSql(int id) {
			return getFindAllSql() + " where UId=" + id;
	}


}
