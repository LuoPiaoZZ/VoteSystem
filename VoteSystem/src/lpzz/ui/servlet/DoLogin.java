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
 * Servlet implementation class DoLogin
 */
@WebServlet("/doLogin")
public class DoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoLogin() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取页面数据
		User user=new User();
		user.setName(request.getParameter("name"));
		user.setPsw(request.getParameter("psw"));
		//拿到session对象
		HttpSession session = request.getSession();
		try {
			//拿到user的业务对象
			UserService userServce=new UserServiceImpl();
			//验证信息，保存对话信息
			user=userServce.login(user);
			session.setAttribute(User.SESSION_NAME, user);
			// 重定向到投票项目列表页面
			response.sendRedirect(request.getContextPath()+"/doList");
		} catch (RuleException e) {
			//数据二次回显,返回登录界面
			session.setAttribute("user", user);
			session.setAttribute("message", e.getMessage());
			response.sendRedirect(request.getContextPath()+"/index.jsp");		
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
