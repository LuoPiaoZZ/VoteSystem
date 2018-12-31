package lpzz.service;

import java.util.List;

import lpzz.pojo.View;
import lpzz.pojo.Vote;

public interface VoteService {
	//投票
	public void vote(List<Vote> records) throws Exception;
	//投票情况
	public View viewVote(int subjectId) throws Exception;
}
