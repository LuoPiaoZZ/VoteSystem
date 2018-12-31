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
 * Servlet implementation class VoteServlet
 */
@WebServlet("/voteServlet")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int subjectId = Integer.parseInt(request.getParameter("subjectId"));
			SubjectService subjectService = new SubjectServiceImpl();
			Subject subject= subjectService.getSubject(subjectId);

			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/vote")
			       .forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
