package lpzz.service.impl;

import java.util.Date;
import java.util.List;

import lpzz.dao.OptionSubjectDao;
import lpzz.dao.SubjectDao;
import lpzz.dao.VoteDao;
import lpzz.dao.impl.OptionSubjectDaoImpl;
import lpzz.dao.impl.SubjectDaoImpl;
import lpzz.dao.impl.VoteDaoImpl;
import lpzz.pojo.OptionSubject;
import lpzz.pojo.OptionSubjectQueryModel;
import lpzz.pojo.Subject;
import lpzz.pojo.View;
import lpzz.pojo.Vote;
import lpzz.pojo.VoteQueryModel;
import lpzz.service.VoteService;
import lpzz.util.exception.RuleException;

public class VoteServiceImpl implements VoteService {

	private SubjectDao subjectDao;
    private VoteDao voteDao;
    public VoteServiceImpl(){
    	this.subjectDao=new SubjectDaoImpl();
    	this.voteDao=new VoteDaoImpl();
    }
    /**进行投票
	 * 
	 */
	@Override
	public void vote(List<Vote> records) throws Exception {
		Subject subject= (Subject)subjectDao.findOne((int) records.get(0).getSubject().getSubjectId());
		if(subject.getOptionWay()==1 && records.size()!=1 ||subject.getOptionWay()==2 && records.size()<2){
			throw new RuleException("没有按照单选和多选类别进行选择");
		}
		//验证是否投过该项
		for(Vote v:records){
			VoteQueryModel qm=new VoteQueryModel();
			qm.setUser(v.getUser());
			qm.setSubject(v.getSubject());
			
			if(voteDao.findByCondition(qm).size()>0){
				throw new RuleException("已经投过票");
			}
		}
		//验证时间：
		long curr = new Date().getTime();
		if(curr<subject.getStartTime() || curr>subject.getEndTime()){
			throw new RuleException("没有在项目规定的时间内投票");
		}
		
		//开始投票
		for(Vote record:records){
			voteDao.insert(record);
		}
	}

	/**
	 * 获取统计图制作数据
	 * @return 
	 */
	@Override
	public View viewVote(int subjectId) throws Exception {
		View view=new View();
		//返回项目名称，选项内容，选项投票数量
		Subject subject=(Subject) subjectDao.findOne(subjectId);
		view.setSubject(subject);
		OptionSubjectDao optionDao=new OptionSubjectDaoImpl();
		OptionSubjectQueryModel qm=new OptionSubjectQueryModel();
		qm.setSubjectId(subjectId);
		List<Object> options=optionDao.findByCondition(qm);
		subject.setOptions(options);
		for(Object os:options){
			OptionSubject option=(OptionSubject) os;
			view.getCountOption().add(voteDao.getOptionCount(subjectId, option.getOptionId()));
		}	
		return view;
	}

}
