package lpzz.util.exception;
/**
 * �Զ����쳣�ࣺ
 * ��¼��Ϊ�û�������������Υ��ҵ�������쳣
 */
public class RuleException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	//�Զ����쳣���¼���쳣��ԭ��
	public RuleException(String message){
		super(message);
	}
}
