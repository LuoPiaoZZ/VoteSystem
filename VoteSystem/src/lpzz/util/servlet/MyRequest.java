package lpzz.util.servlet;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
//��������Զ������࣬����������д��getParameter����
public class MyRequest extends HttpServletRequestWrapper {
    //Ŀ�����
	private String encode;
   
    public void setEncode(String encode){
    	this.encode = encode;
    }
    
	public MyRequest(HttpServletRequest request) {
		super(request);
	}
	
	//��дgetParameter�������Ľ�����
	//�����ϻ��ǵ��ø��෽����ISO-8859-1��ȡ����
	//Ȼ����б���ת��
	public String getParameter(String name){
		String result = null;
		try {
			//���ø����е�getParameter������ȡ�����������(ISO-8859-1)
			//���ַ���ת����һ��ISO-8859-1�����ֽ����飬
			//���ֽ�����Ϊ�������¹����ַ�����ָ�����ַ����ı���ΪĿ�����
			byte[] bs  = super.getParameter(name).getBytes("ISO-8859-1");
			result =  new String(bs,this.encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
