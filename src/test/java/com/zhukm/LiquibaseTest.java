package com.zhukm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

public class LiquibaseTest {
	String url = "jdbc:mysql://10.1.53.58:3306/liquibaseTest2?autoReconnect=true";
	String driverClassName = "com.mysql.jdbc.Driver";
	String username = "root";
	String password = "123456";
	
	String changeLogPath = "E:\\changeLogs\\person.yaml";

	@Test
	public void testCreateTable() {
		Connection conn = getConnection(url, driverClassName, username, password);
		try {
			Liquibase liquibase = new Liquibase(changeLogPath, 
					new FileSystemResourceAccessor(), 
					new JdbcConnection(conn));
			liquibase.update("");
		} catch (LiquibaseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取JDBC连接
	 * @param url 连接地址
	 * @param driverClassName 驱动
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public Connection getConnection(String url, String driverClassName, 
			String username, String password) {
		Connection connection = null;
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
}
