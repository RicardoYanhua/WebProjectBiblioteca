package com.unu.poo2.models;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.unu.poo2.utils.Conexion;
import com.unu.poo2.beans.*;

public class AutoresModel {

	Connection conexion = Conexion.getInstance().getConnection();
	CallableStatement cs;
	ResultSet rs;
	
	public List<Autor> listaAutor() {
		try {
			List<Autor> lista = new ArrayList<>();
			String sql = "CALL sp_listarAutor()";
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				lista.add(new Autor(
						rs.getInt("idautor"),
						rs.getString("nombre"),
						rs.getString("nacionalidad")
						)
				);
			}
			return lista;

		} catch (SQLException e) {
			System.out.println("Error en " + this.getClass().getName() + " .listaAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
			return null;
		}finally {
			Conexion.getInstance().closeResources(cs,rs);;
		}

	}

	public int insertarAutor(Autor autor) throws SQLException {
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_insertarAutor(?,?)";
			cs = conexion.prepareCall(sql);
			cs.setString(1, autor.getNombre());
			cs.setString(2, autor.getNacionalidad());
			filasAfectadas = cs.executeUpdate();
			return filasAfectadas;
		} catch (SQLException e) {
			System.out.println("Error en " + this.getClass().getName() + " .insertarAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
			return 0;
		}finally {
			Conexion.getInstance().closeResources(cs,rs);;
		}

	}

	public Autor obtenerAutor(int idautor) {
		Autor autor = new Autor();
		try {
			String sql = "CALL sp_obtenerAutor(?)";
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idautor);
			rs= cs.executeQuery();
			if (rs.next()) {
				autor.setIdAutor(rs.getInt("idautor"));
				autor.setNombre(rs.getString("nombre"));
				autor.setNacionalidad(rs.getString("nacionalidad"));
			}
			return autor;
		} catch (SQLException e) {
			System.out.println("Error en " + this.getClass().getName() + " .obtenerAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
			return autor;
		}finally {
			Conexion.getInstance().closeResources(cs,rs);;
		}
		
	}
	
	public int modificarAutor(Autor autor){
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_modificarAutor(?,?,?)";
			cs = conexion.prepareCall(sql);
			cs.setInt(1, autor.getIdAutor());
			cs.setString(2, autor.getNombre());
			cs.setString(3, autor.getNacionalidad());
			filasAfectadas = cs.executeUpdate();
			return filasAfectadas;
		} catch (SQLException e) {
			System.out.println("Error en " + this.getClass().getName() + " .modificarAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
			return 0;
		}finally {
			Conexion.getInstance().closeResources(cs,rs);;
		}

	}
	
	public int eliminarAutor(int idautor){
		try {
			int filasAfectadas = 0;
			String sql = "CALL sp_eliminarAutor(?)";
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idautor);			
			filasAfectadas = cs.executeUpdate();
			return filasAfectadas;
		} catch (SQLException e) {
			System.out.println("Error en " + this.getClass().getName() + " .eliminarAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
			return 0;
		}finally {
			Conexion.getInstance().closeResources(cs,rs);;
		}

	}
	

}


/*
 String TABLA_SQL = "";
String TABLA_SQL_PRIMARY_KEY = "";
String TABLA_DATA_SEARCH = "";

String SQL_SELECT_ALL = "SELECT * FROM " + TABLA_SQL;

String SQL_COUNT_ALL_RECORDS = "SELECT COUNT(*) FROM " + TABLA_SQL;

String SQL_SELECT_CONDICION = "SELECT * FROM " + TABLA_SQL + " WHERE " + TABLA_SQL_PRIMARY_KEY + " = ?";
String SQL_SELECT_SEARCH = "SELECT * FROM " + TABLA_SQL + " WHERE " + TABLA_DATA_SEARCH + " LIKE CONCAT('%',?,'%')";

String SQL_UPDATE = "UPDATE " + TABLA_SQL + " SET "
		+"COLUM_UPDATE = ?, "
		+"COLUM_UPDATE = ?, "
		+"COLUM_UPDATE = ?, "
		+"COLUM_UPDATE = ? "
		+"WHERE " + TABLA_SQL_PRIMARY_KEY + " = ?";

String SQL_DELETE = "DELETE FROM " + TABLA_SQL + " WHERE " + TABLA_SQL_PRIMARY_KEY + " = ?";
 */

/*
public List<Object[]> getListaTableGenerate(){
	try {
		ArrayList<Object[]> lista = new ArrayList<>(); 
		
		lista.add(new Object[] {
					"Variable 1",
					"Variable 2",
					"Variable 3"
		});
	} catch (Exception e) {
		System.out.println("Error en " + this.getClass().getName() + " .getListaTableGenerate() \n" + e.getMessage().toString());
	}
}
*/
