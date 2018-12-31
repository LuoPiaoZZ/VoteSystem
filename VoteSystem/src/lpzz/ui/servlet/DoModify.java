package lpzz.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lpzz.pojo.Subject;
import lpzz.service.SubjectService;
import lpzz.service.impl.SubjectServiceImpl;

/**
 * Servlet implementation class DoModify
 */
@WebServlet("/doModify")
public class DoModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoModify() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long subjectId=Long.parseLong(request.getParameter("subjectId"));
		SubjectService ss=new SubjectServiceImpl();
		Subject subject=new Subject();
		subject.setSubjectId(subjectId);
		try {
			ss.deleteModify(subject);
			response.sendRedirect(request.getContextPath()+"/modifyServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
