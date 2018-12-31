package lpzz.util.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ݷ��ʵĸ�����
 * �ṩ���ݿ����Ӷ����ͷ���Դ
 */
public class DbHelper {
	//�ṩ�������Ӷ���
	//�������ӳض���
	private static DataSource dataSource = new ComboPooledDataSource();
	// ThreadLocal:�̲߳�
	private static ThreadLocal<Connection> cons  = new ThreadLocal<Connection>();
	/**
	 * ��ȡ���ӳ����Ӷ���
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		//���̲߳��л�ȡ���Ӷ���
		Connection con = cons.get();
		//�̲߳��е����Ӷ�����null
		if(con==null){
			//�ʹ����ݿ����ӳ��л�ȡһ�����Ӷ���
			con = dataSource.getConnection();
			//�����Ӷ��󱣴浽�̲߳���
			cons.set(con);
		}
		return con;
	}
	
	/**
	 * �ر���Դ���ر����Ӷ����ͬʱ���������̲߳���������Ӷ���
	 * @throws Exception
	 */
	public static void close() throws Exception{
		//���̲߳��л�ȡ�����Ӷ���
		Connection con = cons.get();
		//�����Ӷ���
		if(con!=null){
			//�ر�����
			con.close();
			//ͬʱ�����̲߳����Ƴ����Ӷ���
			cons.remove();
		}
	}
	
	/**
	 * �ͷ�������Դ
	 * @param con
	 * @param pst
	 * @param rs
	 * @throws Exception
	 */
	public static void closeAll(Connection con,PreparedStatement pst,ResultSet rs) throws Exception{
		if(rs!=null){
			rs.close();
		}
		if(pst!=null){
			pst.close();
		}
		if(con!=null){
			con.close();
		}
	}
	
	/**
	 * ��������
	 * @throws Exception
	 */
	public static void beginTransaction() throws Exception{
		getConnection().setAutoCommit(false);
	}
	/**
	 * �ύ����
	 * @throws Exception
	 */
	public static void commitTransaction() throws Exception{
		getConnection().commit();
	}
	/**
	 * �ع�����
	 * @throws Exception
	 */
	public static void rollbackTransaction() throws Exception{
		getConnection().rollback();
	}
}





