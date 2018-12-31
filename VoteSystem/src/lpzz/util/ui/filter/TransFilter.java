package lpzz.util.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lpzz.util.dao.DbHelper;

/**
 * ������͹ر����ӵĹ�����
 */
public class TransFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try{
		// 1 ��������
			DbHelper.beginTransaction();
		// 2 ����
			chain.doFilter(req, res);
		// 3 �ύ����
			DbHelper.commitTransaction();
		}
		catch(Exception e){
			//�ع�����
			try {
				DbHelper.rollbackTransaction();
			} catch (Exception e1) {
				//����ȫ�ֵ��쳣������
				throw new RuntimeException(e1);
			}
			
			//����ȫ�ֵ��쳣������
			throw new RuntimeException(e);			
		}
		finally{
			//�ر����ݿ�����
			try {
				DbHelper.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	
	}

}
