package lpzz.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lpzz.pojo.User;
import lpzz.service.UserService;
import lpzz.service.impl.UserServiceImpl;
import lpzz.util.exception.RuleException;

/**
 * Servlet implementation class DoReg
 */
@WebServlet("/doReg")
public class DoReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoReg() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//拿到注册表单数据
		User user=new User();
		user.setName(request.getParameter("name"));
		user.setPsw(request.getParameter("psw"));
		user.setConfirmPsw(request.getParameter("confirmPsw"));
		String gender=request.getParameter("gender");
		if(gender.trim().length()==0||gender==null){
			user.setGender(0);
		}else{
			user.setGender(Integer.parseInt(gender));
		}
		String age=request.getParameter("age");
		if(age.trim().length()==0||age==null||Integer.parseInt(age)==0){
			user.setAge(1);
		}else{
			user.setAge(Integer.parseInt(age));
		}
		//注册成功跳到首页登录，否则数据回显
		try{
			UserService userDao=new UserServiceImpl();
			userDao.register(user);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}catch(RuleException e){
			HttpSession hs=request.getSession();
			hs.setAttribute("user", user);
			hs.setAttribute("message", e.getMessage());
			response.sendRedirect(request.getContextPath()+"/reg");
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
