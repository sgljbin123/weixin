package com.gongzhonghao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidDBPool {
	private static DruidDataSource dataSource;
	private static Logger logger = Logger.getLogger(DruidDBPool.class);
	static {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		dataSource = new DruidDataSource();    
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");     
		dataSource.setUsername(rb.getString("mysql.user"));      
		dataSource.setPassword(rb.getString("mysql.password"));      
		dataSource.setUrl(rb.getString("mysql.url"));     
		// 连接数配置      
		dataSource.setInitialSize(Integer.parseInt(rb.getString("mysql.initialSize")));      
		dataSource.setMinIdle(Integer.parseInt(rb.getString("mysql.minIdle")));      
		dataSource.setMaxActive(Integer.parseInt(rb.getString("mysql.maxActive")));      
		// 启用监控统计功能       
		try {
			dataSource.setFilters("stat");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		// for mysql       
		dataSource.setPoolPreparedStatements(false);     
		// 使用心跳语句检测空闲连接     
//		dataSource.setValidationQuery("show status like '%Service_Status%';");    
		dataSource.setValidationQuery("select 1;"); 
		dataSource.setTestWhileIdle(true);
		try {
			dataSource.init();
			logger.info(dataSource);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs) throws SQLException{
		if(rs!=null){
			rs.close();
		}
		if(ps!=null){
			ps.close();
		}
		if(conn!=null){
			conn.close();
		}
	}
	public static void close(Connection conn,PreparedStatement ps) throws SQLException{
		close(conn, ps,null);
	}
	
	public static void main(String[] args) throws Exception {
		DruidDBPool.class.newInstance();
		Thread.sleep(100000);
		
	}
}
