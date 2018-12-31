package lpzz.util.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据访问的辅助类
 * 提供数据库连接对象；释放资源
 */
public class DbHelper {
	//提供数据连接对象
	//创建连接池对象
	private static DataSource dataSource = new ComboPooledDataSource();
	// ThreadLocal:线程槽
	private static ThreadLocal<Connection> cons  = new ThreadLocal<Connection>();
	/**
	 * 获取连接池连接对象
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		//从线程槽中获取连接对象
		Connection con = cons.get();
		//线程槽中的连接对象是null
		if(con==null){
			//就从数据库连接池中获取一个连接对象
			con = dataSource.getConnection();
			//将连接对象保存到线程槽中
			cons.set(con);
		}
		return con;
	}
	
	/**
	 * 关闭资源，关闭连接对象的同时，必须在线程槽中清除连接对象
	 * @throws Exception
	 */
	public static void close() throws Exception{
		//从线程槽中获取出连接对象
		Connection con = cons.get();
		//有连接对象
		if(con!=null){
			//关闭连接
			con.close();
			//同时，从线程槽中移除连接对象
			cons.remove();
		}
	}
	
	/**
	 * 释放所有资源
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
	 * 开启事务
	 * @throws Exception
	 */
	public static void beginTransaction() throws Exception{
		getConnection().setAutoCommit(false);
	}
	/**
	 * 提交事务
	 * @throws Exception
	 */
	public static void commitTransaction() throws Exception{
		getConnection().commit();
	}
	/**
	 * 回滚事务
	 * @throws Exception
	 */
	public static void rollbackTransaction() throws Exception{
		getConnection().rollback();
	}
}





