package br.com.inicio.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BaseDAO {
	public BaseDAO() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	protected Connection getConnection() throws SQLException {
		
		String url = "jdbc:mysql://localhost/livro";
		
		Connection conn = DriverManager.getConnection(url, "livro", "livro123");
		return conn;
	}

	public static void main(String[] args) throws SQLException {
		BaseDAO db = new BaseDAO();
		
		Connection conn = db.getConnection();
		//System.out.println(conn);
		CarroDAO carrodao = new CarroDAO();
		Carro carro;
		try {
			carro = carrodao.getCarroById(30L);
			System.out.println(carro.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
