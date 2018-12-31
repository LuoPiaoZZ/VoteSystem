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
    /**����ͶƱ
	 * 
	 */
	@Override
	public void vote(List<Vote> records) throws Exception {
		Subject subject= (Subject)subjectDao.findOne((int) records.get(0).getSubject().getSubjectId());
		if(subject.getOptionWay()==1 && records.size()!=1 ||subject.getOptionWay()==2 && records.size()<2){
			throw new RuleException("û�а��յ�ѡ�Ͷ�ѡ������ѡ��");
		}
		//��֤�Ƿ�Ͷ������
		for(Vote v:records){
			VoteQueryModel qm=new VoteQueryModel();
			qm.setUser(v.getUser());
			qm.setSubject(v.getSubject());
			
			if(voteDao.findByCondition(qm).size()>0){
				throw new RuleException("�Ѿ�Ͷ��Ʊ");
			}
		}
		//��֤ʱ�䣺
		long curr = new Date().getTime();
		if(curr<subject.getStartTime() || curr>subject.getEndTime()){
			throw new RuleException("û������Ŀ�涨��ʱ����ͶƱ");
		}
		
		//��ʼͶƱ
		for(Vote record:records){
			voteDao.insert(record);
		}
	}

	/**
	 * ��ȡͳ��ͼ��������
	 * @return 
	 */
	@Override
	public View viewVote(int subjectId) throws Exception {
		View view=new View();
		//������Ŀ���ƣ�ѡ�����ݣ�ѡ��ͶƱ����
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
