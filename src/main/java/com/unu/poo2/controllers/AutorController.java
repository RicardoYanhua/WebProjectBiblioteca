package com.unu.poo2.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.unu.poo2.models.AutoresModel;
import com.unu.poo2.beans.Autor;

@WebServlet("/AutorController")
public class AutorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AutoresModel modelAutor = new AutoresModel();
       
    public AutorController() {super();}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String operacion = request.getParameter("op") == null ? "listar" : request.getParameter("op");
			
			switch (operacion) {

			case "listar":
				listarAutor(request, response);
				break;
			case "nuevo":
				nuevoAutor(request,response);
				break;
			case "insertar":
				resgistrarAutor(request, response);
				break;
			case "obtener":
				obtenerAutor(request, response);
				break;
			case "modificar":
				obtenerAutor(request, response);
				break;
			case "eliminar":
				eliminarAutor(request, response);
				break;
			}
		} catch (Exception e) {
			System.out.println("Error en " + this.getClass().getName() + " .processRequest() \n" + e.getMessage().toString());
			e.printStackTrace();
		}
	}
    
    private void obtenerAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			Autor miAutor = modelAutor.obtenerAutor(Integer.parseInt(id));
			if (miAutor != null) {
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("/Autor/editarAutor.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/error404.jsp");
			}
		} catch (Exception e) {
			System.out.println("Error en " + this.getClass().getName() + " .obtenerAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
		}
	}
    private void modificarAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			Autor miAutor = new Autor();
			miAutor.setIdAutor(Integer.parseInt(request.getParameter("id")));
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setNacionalidad(request.getParameter("nacionalidad"));
			if (modelAutor.modificarAutor(miAutor) > 0) {
				request.getSession().setAttribute("exito", "autor modificado exitosamente");
			} else {
				request.getSession().setAttribute("fracaso", "autor no modificado");
			}

			response.sendRedirect(request.getContextPath() + "/AutorController?op=listar");
		} catch (Exception e) {
			System.out.println("Error en " + this.getClass().getName() + " .modificarAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
		}
	}
    
    private void nuevoAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
				request.getRequestDispatcher("/Autor/nuevoAutor.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Error en " + this.getClass().getName() + " .nuevoAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
		}
	}
    
    private void listarAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listaAutores", modelAutor.listaAutor());
			request.getRequestDispatcher("/Autor/listaAutores.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("Error en " + this.getClass().getName() + " .listarAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
		}
	}
    
    private void resgistrarAutor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Autor miAutor = new Autor();
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setNacionalidad(request.getParameter("nacionalidad"));
			if (!validar(request, response)) {

				if (modelAutor.insertarAutor(miAutor) > 0) {
					request.getSession().setAttribute("exito", "autor registrado exitosamente");
				} else {
					request.getSession().setAttribute("fracaso", "autor no registrado");
				}
				request.setAttribute("miAutor", miAutor);
				response.sendRedirect(request.getContextPath() + "/AutorController?op=listar");

			} else {
				request.getRequestDispatcher("/Autor/nuevoAutor.jsp").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("Error en " + this.getClass().getName() + " .resgistrarAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
		}
	}
    
    private boolean validar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean res = false;
		List<String> listError = new ArrayList<>();
		try {
			if (request.getParameter("nombre").equals("")) {
				res = true;
				listError.add("Ingrese el nombre del autor");
			}
			if (request.getParameter("nacionalidad").equals("")) {
				res = true;
				listError.add("Ingrese la nacionalidad del autor");
			}

			request.setAttribute("respuesta", res);
			request.setAttribute("listaError", listError);

		} catch (Exception e) {
			e.getStackTrace();
		}

		return res;

	}
    
    private void eliminarAutor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			if (modelAutor.eliminarAutor(id) > 0) {
				request.getSession().setAttribute("exito", "autor eliminado exitosamente");
			} else {
				request.getSession().setAttribute("fracaso", "autor no eliminad");
			}
			response.sendRedirect(request.getContextPath() + "/AutorController?op=listar");
		} catch (Exception e) {
			System.out.println("Error en " + this.getClass().getName() + " .eliminarAutor() \n" + e.getMessage().toString());
			e.printStackTrace();
		}

	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
