package lpzz.ui.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lpzz.pojo.User;
import lpzz.pojo.Vote;
import lpzz.service.VoteService;
import lpzz.service.impl.VoteServiceImpl;
import lpzz.util.exception.RuleException;

/**
 * Servlet implementation class DoVote
 */
@WebServlet("/doVote")
public class DoVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoVote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				int subjectId=Integer.parseInt(request.getParameter("subjectId"));
				String[] ids = request.getParameterValues("options");
				
				User user= (User)request.getSession().getAttribute(User.SESSION_NAME);
				List<Vote> records = new ArrayList<Vote>();
				for(int i=0;i<ids.length;i++){
					Vote record = new Vote();
					record.getUser().setUid(user.getUid());
					record.getSubject().setSubjectId(subjectId);
					record.getOption().setOptionId(Integer.parseInt(ids[i]));
					records.add(record);
				}
				
				try {			
					VoteService recordService=new VoteServiceImpl();
					recordService.vote(records);	
					response.sendRedirect(request.getContextPath()+"/doList");
				} catch (RuleException e) {
					request.getSession().setAttribute("message", e.getMessage());
					response.sendRedirect(request.getContextPath()+"/voteServlet");
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
