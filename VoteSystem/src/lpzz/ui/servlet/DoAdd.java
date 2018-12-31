package lpzz.ui.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lpzz.pojo.OptionSubject;
import lpzz.pojo.Subject;
import lpzz.pojo.User;
import lpzz.service.SubjectService;
import lpzz.service.impl.SubjectServiceImpl;
import lpzz.util.exception.RuleException;
import lpzz.util.format.DateUtil;

/**
 * Servlet implementation class DoAdd
 */
@WebServlet("/doAdd")
public class DoAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoAdd() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		       //获取浏览器提交的数据：主题标题，选择类型，各个选项的内容
				String subjectId = request.getParameter("subjectId");
				String title = request.getParameter("title");
				int optionWay = Integer.parseInt(request.getParameter("optionWay"));
				//选项值数组接收
				String[] os = request.getParameterValues("options");
		        String startTime = request.getParameter("startTime");
		        String endTime = request.getParameter("endTime");
		        
				Subject subject = new Subject();		
				subject.setTitle(title);
				subject.setOptionWay(optionWay);
			
				for(String ocontent:os){
					OptionSubject op = new OptionSubject();
					op.setOptionContent(ocontent);	
					subject.getOptions().add(op);
					
				}
				
				try {			
					//创建业务逻辑类的对象，调用业务方法处理
					SubjectService subjectService=new SubjectServiceImpl();
					if(subjectId==null || subjectId.trim().length()==0){
						subjectService.add(subject,(User)request.getSession().getAttribute(User.SESSION_NAME));
					}
					else{
						subject.setSubjectId(Integer.parseInt(subjectId));
						subject.setStartTime(DateUtil.toLong(startTime));
						subject.setEndTime(DateUtil.toLong(endTime));
						subjectService.modify(subject,(User)request.getSession().getAttribute(User.SESSION_NAME));
					}
					//重定向到投票项目列表页面			
					response.sendRedirect(request.getContextPath() + "/doList");
				} catch (RuleException e) {
					HttpSession session = request.getSession();
					session.setAttribute("subject", subject);
					session.setAttribute("message", e.getMessage());
					response.sendRedirect(request.getContextPath() + "/add");
				} catch (Exception ex) {
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
