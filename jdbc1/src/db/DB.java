package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnection() { //Metodo para conectar ao banco de dados
		if (conn == null) {	//Se conexão nula pega o metodo loadproperties para conectar com o banco
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl"); //pega a string dburl dentro do properties(link do banco)
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static Connection closeConnection() {
		if (conn != null) {
			try {
			conn.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	private static Properties loadProperties( ) {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
}
