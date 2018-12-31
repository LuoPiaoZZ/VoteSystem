package lpzz.service.impl;

import java.util.List;

import lpzz.dao.OptionSubjectDao;
import lpzz.dao.SubjectDao;
import lpzz.dao.UserDao;
import lpzz.dao.impl.OptionSubjectDaoImpl;
import lpzz.dao.impl.SubjectDaoImpl;
import lpzz.dao.impl.UserDaoImpl;
import lpzz.pojo.OptionSubjectQueryModel;
import lpzz.pojo.Subject;
import lpzz.pojo.SubjectQueryModel;
import lpzz.pojo.User;
import lpzz.pojo.UserQueryModel;
import lpzz.service.UserService;
import lpzz.util.exception.RuleException;
import lpzz.util.format.Md5Class;

public class UserServiceImpl implements UserService {
	//����ӿڣ���̬���������෽��
	private UserDao userDao;
	private SubjectDao subjectDao;
	private OptionSubjectDao optionSubjectDao;
	public UserServiceImpl(){
		userDao=new UserDaoImpl();
		subjectDao=new SubjectDaoImpl();
		optionSubjectDao=new OptionSubjectDaoImpl();
	}
	/**
	 * ע��
	 */
	@Override
	public void register(User user) throws Exception {
		if(user.getName()==null||user.getName().trim().length()==0){
			throw new RuleException("�û�������Ϊ�գ�");
		}
		if(user.getPsw()==null||user.getPsw().trim().length()==0){
			throw new RuleException("���벻��Ϊ�գ�");
		}
		if(!user.getConfirmPsw().equals(user.getPsw())){
			throw new RuleException("ǰ�������������벻һ�£�");
		}
		
		//gender��ageʹ�õ���int���Ǳ�Ҫ������Ϣ���ݲ�����
		
		//������ܶ�δ洢
		user.setPsw(Md5Class.ManyTimeMd5(user.getPsw()));
		
		//ִ�в������
		int row =userDao.insert(user);
		System.out.println(userDao.findMaxId());
		if(row==0){
			throw new RuleException("�û����Ѿ����ڣ�������ע��");
		}
	}
	/**
	 * ��¼
	 */
	@Override
	public User login(User user) throws Exception {
		//�����û������������������ѯ
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		qm.setPsw(Md5Class.ManyTimeMd5(user.getPsw()));
		List<Object> list=userDao.findByCondition(qm);
		//�����ѯ���˷��ز�ѯ����������׳��쳣
		if(list!=null&&list.size()>0){
			user = (User)list.get(0);
		}else{
			throw new RuleException("�û������������");
		}
		return user;
	}
	/**
	 * ͨ��id��ȡ�û�ʵ�������
	 */
	@Override
	public User getUser(int uid) throws Exception {
		return (User) userDao.findOne(uid);
	}
	/**
	 * �����û�������Ŀ�б�
	 */
	@Override
	public List<Object> getSubjects(long UId) throws Exception {
		SubjectQueryModel qm=new SubjectQueryModel();
		qm.getUser().setUid(UId);
		qm.setUid(UId);
		List<Object> list = subjectDao.findByCondition(qm);
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

}
