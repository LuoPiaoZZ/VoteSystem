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
		//��ȡҳ������
		User user=new User();
		user.setName(request.getParameter("name"));
		user.setPsw(request.getParameter("psw"));
		//�õ�session����
		HttpSession session = request.getSession();
		try {
			//�õ�user��ҵ�����
			UserService userServce=new UserServiceImpl();
			//��֤��Ϣ������Ի���Ϣ
			user=userServce.login(user);
			session.setAttribute(User.SESSION_NAME, user);
			// �ض���ͶƱ��Ŀ�б�ҳ��
			response.sendRedirect(request.getContextPath()+"/doList");
		} catch (RuleException e) {
			//���ݶ��λ���,���ص�¼����
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
