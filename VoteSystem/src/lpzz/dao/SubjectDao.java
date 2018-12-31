package lpzz.dao;

import lpzz.util.base.BaseDao;
/**
 * 基础基本的CRUD
 */
public interface SubjectDao extends BaseDao {
	public long getUserCount(long subjectId) throws Exception;

	public long getGenerateId()throws Exception;
}
