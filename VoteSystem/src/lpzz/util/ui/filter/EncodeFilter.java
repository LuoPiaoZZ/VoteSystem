package lpzz.util.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lpzz.util.servlet.MyRequest;

/**
 * 编码过滤器
 */
public class EncodeFilter implements Filter {

	private String encodeName;
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 向下转型
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		//拦截在servlet代码之前执行
		request.setCharacterEncoding(this.encodeName);
		response.setCharacterEncoding(this.encodeName);
		
		if("GET".equals(request.getMethod().toUpperCase())){
			MyRequest mr = new MyRequest(request);
			mr.setEncode(this.encodeName);
			req=mr;
		}
		//放行
		chain.doFilter(req, res);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.encodeName = config.getInitParameter("ec");
	}

}
