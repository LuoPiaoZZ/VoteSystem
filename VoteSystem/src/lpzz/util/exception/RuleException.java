package lpzz.util.exception;
/**
 * 自定义异常类：
 * 记录因为用户操作不当，而违反业务规则的异常
 */
public class RuleException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	//自定义异常类记录好异常的原因
	public RuleException(String message){
		super(message);
	}
}
