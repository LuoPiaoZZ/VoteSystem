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
		//检查标题
		if(subject.getTitle()==null&&subject.getTitle().trim().length()==0){
			throw new RuleException("项目主题的标题不能为空");
		}
		//检查各个选项的内容
		for(Object data:subject.getOptions()){
			OptionSubject option = (OptionSubject)data;
			if(option.getOptionContent()==null || option.getOptionContent().trim().length()==0){
				throw new RuleException("每个项目选项的内容不能为空");
			}
		}
		//项目选项个数不能低于2个
		if(subject.getOptions().size()<2){
			throw new RuleException("选项数量不能低于2");
		}
		//各个选项的内容不一致
		for(int i=0;i<subject.getOptions().size();i++){
			OptionSubject first = (OptionSubject) subject.getOptions().get(i);
			for(int j=i+1;j<subject.getOptions().size();j++){
				OptionSubject next = (OptionSubject) subject.getOptions().get(j);
				if(first.getOptionContent().equals(next.getOptionContent())){
					throw new RuleException("每个选项的内容不能一致");
				}
			}
		}
		//同一个发起人不能发起同样主题的投票项目
		SubjectQueryModel queryModel=new SubjectQueryModel();
		queryModel.setTitle(subject.getTitle());
		queryModel.getUser().setUid(user.getUid());
		List<Object> list = subjectDao.findByCondition(queryModel);
		if(list!=null && list.size()>0){
			throw new RuleException("同一个发起人不能发起同样主题的投票项目");
		}
		//设置开始时间，结束时间和发起人
		long startTime=new Date().getTime();
		subject.setStartTime(startTime);
		subject.setEndTime(startTime+1*24*60*60*1000);
		subject.setUser(user);
		subject.setUid(user.getUid());
		//保存数据
		subjectDao.insert(subject);
		//拿到最新的主题id
		long subjectId = subjectDao.getGenerateId();
		//遍历选项集合保存
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
		//已经存在投票记录，不允许进行修改
		if(subjectDao.getUserCount(subject.getSubjectId())>0){
			throw new Exception("已经存在投票记录，不可以修改");
		}
		//开始修改
		subjectDao.update(subject);
		optionSubjectDao.delete((int)subject.getSubjectId());
		for(int i=0;i<subject.getOptions().size();i++){
				OptionSubject op = (OptionSubject)subject.getOptions().get(i);
				op.setSubjectId(subject.getSubjectId());
				optionSubjectDao.insert(op);
		}
	}
	/**
	 * 通过删除来维护
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
