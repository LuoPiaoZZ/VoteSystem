package lpzz.util.servlet;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
//请求类的自定义子类，在子类中重写了getParameter方法
public class MyRequest extends HttpServletRequestWrapper {
    //目标编码
	private String encode;
   
    public void setEncode(String encode){
    	this.encode = encode;
    }
    
	public MyRequest(HttpServletRequest request) {
		super(request);
	}
	
	//重写getParameter方法，改进功能
	//本质上还是调用父类方法以ISO-8859-1读取数据
	//然后进行编码转换
	public String getParameter(String name){
		String result = null;
		try {
			//调用父类中的getParameter方法获取浏览器的数据(ISO-8859-1)
			//将字符串转换成一个ISO-8859-1编码字节数组，
			//以字节数组为参数重新构建字符串，指定新字符串的编码为目标编码
			byte[] bs  = super.getParameter(name).getBytes("ISO-8859-1");
			result =  new String(bs,this.encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
