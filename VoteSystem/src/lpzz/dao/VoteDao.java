package lpzz.dao;

import lpzz.pojo.Vote;
import lpzz.util.base.BaseDao;

public interface VoteDao extends BaseDao {
	public long getOptionCount(long subjectId,long optionId) throws Exception;
	public void deleteQueryModel(Vote vote) throws Exception;
}
