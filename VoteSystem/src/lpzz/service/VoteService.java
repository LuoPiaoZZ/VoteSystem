package lpzz.service;

import java.util.List;

import lpzz.pojo.View;
import lpzz.pojo.Vote;

public interface VoteService {
	//ͶƱ
	public void vote(List<Vote> records) throws Exception;
	//ͶƱ���
	public View viewVote(int subjectId) throws Exception;
}
