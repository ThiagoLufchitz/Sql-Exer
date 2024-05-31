package dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaConexao {
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/EMPRESA", "linder", "12345");
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
