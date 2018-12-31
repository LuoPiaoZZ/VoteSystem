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
import lpzz.pojo.SubjectQueryModel;
import lpzz.pojo.User;
import lpzz.pojo.Vote;
import lpzz.service.SubjectService;
import lpzz.util.exception.RuleException;

public class SubjectServiceImpl implements SubjectService {
	private SubjectDao subjectDao;
	private OptionSubjectDao optionSubjectDao;
	public SubjectServiceImpl(){
		this.subjectDao=new SubjectDaoImpl();
		this.optionSubjectDao = new OptionSubjectDaoImpl();
	}

	@Override
	public void add(Subject subject, User user) throws Exception {
		//������
		if(subject.getTitle()==null&&subject.getTitle().trim().length()==0){
			throw new RuleException("��Ŀ����ı��ⲻ��Ϊ��");
		}
		//������ѡ�������
		for(Object data:subject.getOptions()){
			OptionSubject option = (OptionSubject)data;
			if(option.getOptionContent()==null || option.getOptionContent().trim().length()==0){
				throw new RuleException("ÿ����Ŀѡ������ݲ���Ϊ��");
			}
		}
		//��Ŀѡ��������ܵ���2��
		if(subject.getOptions().size()<2){
			throw new RuleException("ѡ���������ܵ���2");
		}
		//����ѡ������ݲ�һ��
		for(int i=0;i<subject.getOptions().size();i++){
			OptionSubject first = (OptionSubject) subject.getOptions().get(i);
			for(int j=i+1;j<subject.getOptions().size();j++){
				OptionSubject next = (OptionSubject) subject.getOptions().get(j);
				if(first.getOptionContent().equals(next.getOptionContent())){
					throw new RuleException("ÿ��ѡ������ݲ���һ��");
				}
			}
		}
		//ͬһ�������˲��ܷ���ͬ�������ͶƱ��Ŀ
		SubjectQueryModel queryModel=new SubjectQueryModel();
		queryModel.setTitle(subject.getTitle());
		queryModel.getUser().setUid(user.getUid());
		List<Object> list = subjectDao.findByCondition(queryModel);
		if(list!=null && list.size()>0){
			throw new RuleException("ͬһ�������˲��ܷ���ͬ�������ͶƱ��Ŀ");
		}
		//���ÿ�ʼʱ�䣬����ʱ��ͷ�����
		long startTime=new Date().getTime();
		subject.setStartTime(startTime);
		subject.setEndTime(startTime+1*24*60*60*1000);
		subject.setUser(user);
		subject.setUid(user.getUid());
		//��������
		subjectDao.insert(subject);
		//�õ����µ�����id
		long subjectId = subjectDao.getGenerateId();
		//����ѡ��ϱ���
		for(int i=0;i<subject.getOptions().size();i++){
			OptionSubject op = (OptionSubject)subject.getOptions().get(i);
			op.setSubjectId(subjectId);
			optionSubjectDao.insert(op);
		}
		
	}

	@Override
	public List<Object> getSubjects() throws Exception {
		List<Object> list = subjectDao.findAll();
		if(list!=null && list.size()>0){
			for(Object data:list){
				Subject subject = (Subject)data;
				OptionSubjectQueryModel queryModel = new OptionSubjectQueryModel();
				queryModel.setSubjectId(subject.getSubjectId());
				subject.setOptions(optionSubjectDao.findByCondition(queryModel));
				subject.setUserCount(subjectDao.getUserCount(subject.getSubjectId()));
			}
		}
		return list;
	}

	@Override
	public Subject getSubject(int subjectId) throws Exception {
		Subject subject = (Subject)subjectDao.findOne(subjectId);
		if(subject!=null){			
			OptionSubjectQueryModel queryModel = new OptionSubjectQueryModel();
			queryModel.setSubjectId(subject.getSubjectId());
			subject.setOptions(optionSubjectDao.findByCondition(queryModel));
			subject.setUserCount(subjectDao.getUserCount(subject.getSubjectId()));
		}
		return subject;
	}

	@Override
	public void modify(Subject subject, User attribute) throws Exception {
		//�Ѿ�����ͶƱ��¼������������޸�
		if(subjectDao.getUserCount(subject.getSubjectId())>0){
			throw new Exception("�Ѿ�����ͶƱ��¼���������޸�");
		}
		//��ʼ�޸�
		subjectDao.update(subject);
		optionSubjectDao.delete((int)subject.getSubjectId());
		for(int i=0;i<subject.getOptions().size();i++){
				OptionSubject op = (OptionSubject)subject.getOptions().get(i);
				op.setSubjectId(subject.getSubjectId());
				optionSubjectDao.insert(op);
		}
	}
	/**
	 * ͨ��ɾ����ά��
	 * @param subject
	 * @throws Exception
	 */

	@Override
	public void deleteModify(Subject subject) throws Exception {
		int subjectId=(int)subject.getSubjectId();
		subjectDao.delete(subjectId);
		OptionSubject os=new OptionSubject();
		os.setSubjectId(subjectId);
		optionSubjectDao.deleteQueryModel(os);
		Vote vote=new Vote();
		vote.getSubject().setSubjectId(subjectId);
		VoteDao voteDao=new VoteDaoImpl();
		voteDao.deleteQueryModel(vote);	
	}

}
