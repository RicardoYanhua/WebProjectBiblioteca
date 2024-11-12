package com.unu.poo2.utils;

import java.sql.*;

public class Conexion {

	public static Conexion Instance;

	public static Conexion getInstance() {
		if (Instance == null) {
			Instance = new Conexion();
		}
		return Instance;
	}
	
	private String DATABASE_NAME = "bdbibliotecapoo2";
	private String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
	private String DRIVER = "com.mysql.cj.jdbc.Driver";
	private String USER ="root";
	private String PASSWORD = "123456";
	private Connection conexion;

	public Conexion() {
		
		try {
			Class.forName(DRIVER);
			conexion = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
			System.err.println(conexion != null ? "Conexion Exitosa" : "Conexion Fallida");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("ERROR en Conexion.contructor() \n " + e.getMessage().toString());
		}
	}
	
	public Connection getConnection() {
		return conexion;
	}
	public void cerrarConexion() {
		closeResources(conexion);
	}
	
	public void closeResources(AutoCloseable...recursos) {
		try {
			for(AutoCloseable recurso : recursos) {
				if (recurso != null) {
                    recurso.close();
                }
			}
		} catch (Exception e) {
			System.out.println("ERROR en Conexion.closeResources() \n " + e.getMessage().toString());
		}
	}

}
