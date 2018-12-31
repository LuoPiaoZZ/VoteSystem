package lpzz.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lpzz.pojo.View;
import lpzz.service.VoteService;
import lpzz.service.impl.VoteServiceImpl;

/**
 * Servlet implementation class DoView
 */
@WebServlet("/doView")
public class DoView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoView() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int subjectId=Integer.parseInt(request.getParameter("subjectId"));
		VoteService vs=new VoteServiceImpl();
		try {
			View view=vs.viewVote(subjectId);
			request.setAttribute("title", view.getSubject().getTitle());
			request.setAttribute("options", view.getSubject().getOptions());
			request.setAttribute("optionsCount", view.getCountOption());
			request.setAttribute("size", view.getCountOption().size());
			request.getRequestDispatcher("/view").forward(request, response);
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
