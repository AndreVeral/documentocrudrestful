package br.com.digiro.documentocrud.factory;

import java.sql.*;


import org.postgresql.Driver;



public class ConnectionFactory {

	private static final String URL = "jdbc:postgresql://localhost:5432/basedocumentos";
	private static final String USER = "digitro";
	private static final String PASSWORD = "digitro";
	
	/**
	 * Cria conexão com a base de dados
	 */
	public static Connection getConnection() {
		try {
			DriverManager.registerDriver(new Driver());
			return DriverManager.getConnection(URL, USER, PASSWORD);
		}catch(SQLException e) {
			throw new RuntimeException("Erro ao conectar a base de dados ", e);
		}
		
	}
	
	
}
