package com.brq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brq.dao.ConsultarColaboradorDao;
import com.brq.model.Colaborador;

@WebServlet(value = "/Consultar")
public class ConsultarColaboradorController extends HttpServlet {

	private static final long serialVersionUID = 2750654989350416943L;
	private ConsultarColaboradorDao consultarColaboradorDao = new ConsultarColaboradorDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<Colaborador> lista = consultarColaboradorDao.consultar();
		
		 
		req.setAttribute("lista", lista);
		
		req.getRequestDispatcher("ConsultarColaborador.jsp").forward(req, resp);
	}

     
}
