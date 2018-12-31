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
	//定义接口，多态，调用子类方法
	private UserDao userDao;
	private SubjectDao subjectDao;
	private OptionSubjectDao optionSubjectDao;
	public UserServiceImpl(){
		userDao=new UserDaoImpl();
		subjectDao=new SubjectDaoImpl();
		optionSubjectDao=new OptionSubjectDaoImpl();
	}
	/**
	 * 注册
	 */
	@Override
	public void register(User user) throws Exception {
		if(user.getName()==null||user.getName().trim().length()==0){
			throw new RuleException("用户名不能为空！");
		}
		if(user.getPsw()==null||user.getPsw().trim().length()==0){
			throw new RuleException("密码不能为空！");
		}
		if(!user.getConfirmPsw().equals(user.getPsw())){
			throw new RuleException("前后输入两次密码不一致！");
		}
		
		//gender和age使用的是int，非必要处理信息，暂不处理
		
		//密码加密多次存储
		user.setPsw(Md5Class.ManyTimeMd5(user.getPsw()));
		
		//执行插入操作
		int row =userDao.insert(user);
		System.out.println(userDao.findMaxId());
		if(row==0){
			throw new RuleException("用户名已经存在，请重新注册");
		}
	}
	/**
	 * 登录
	 */
	@Override
	public User login(User user) throws Exception {
		//根据用户名和密码进行条件查询
		UserQueryModel qm = new UserQueryModel();
		qm.setName(user.getName());
		qm.setPsw(Md5Class.ManyTimeMd5(user.getPsw()));
		List<Object> list=userDao.findByCondition(qm);
		//如果查询到了返回查询结果，否则抛出异常
		if(list!=null&&list.size()>0){
			user = (User)list.get(0);
		}else{
			throw new RuleException("用户名或密码错误！");
		}
		return user;
	}
	/**
	 * 通过id获取用户实体类对象
	 */
	@Override
	public User getUser(int uid) throws Exception {
		return (User) userDao.findOne(uid);
	}
	/**
	 * 查找用户发起项目列表
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
