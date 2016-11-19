package com.brq.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brq.dao.AlterarColaboradorDao;
import com.brq.dao.DeletarColaboradorDao;
import com.brq.model.Cargo;
import com.brq.model.Colaborador;

@WebServlet(value = "/Deletar")
public class DeletarColaboradorControler extends HttpServlet {

	private static final long serialVersionUID = 4058559909137976359L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("ConsultarDeletar.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String action = req.getParameter("cmd");

			if (action.equalsIgnoreCase("CON")) {
				Long cpf = Long.parseLong(req.getParameter("Cpf"));
				DeletarColaboradorDao deletarColaboradorDao = new DeletarColaboradorDao();
				Colaborador col = deletarColaboradorDao.buscar(cpf);

				if (col == null) {
					req.setAttribute("msg", "Colaborador não encontrado");
					req.getRequestDispatcher("ConsultarDeletar.jsp").forward(
							req, resp);
				} else {
					req.setAttribute("col", col);
					List<Cargo> listaCargos = new ArrayList<Cargo>();
					AlterarColaboradorDao alterarColaboradorDao2 = new AlterarColaboradorDao();
					listaCargos = alterarColaboradorDao2.listarCargos();
					req.setAttribute("listaCargos", listaCargos);
					req.getRequestDispatcher("DeletarColaborador.jsp").forward(req, resp);
				}
			} else if (action.equalsIgnoreCase("DEL")) {

				Colaborador col = new Colaborador();
				col.setNome(req.getParameter("nome"));
				System.out.println(req.getParameter("nome"));
				System.out.println(req.getParameter("cpf"));
				col.setCpf(Long.parseLong(req.getParameter("cpf")));
				col.setDataDeNascimento(new SimpleDateFormat("yyyy-MM-dd")
						.parse(req.getParameter("dataNascimento")));
				col.setIdCargo(Integer.parseInt(req.getParameter("cargo")));

				DeletarColaboradorDao deletarColaboradorDao = new DeletarColaboradorDao();
				boolean result = deletarColaboradorDao.Deletar(col);
				
				if(result){
					req.setAttribute("msg", "Colaborador Deletado com sucesso");
					req.getRequestDispatcher("DeletarColaborador.jsp").forward(req,
							resp);
					
				}else{
					req.setAttribute("msg", "Não foi possível excluír, favor tente mais tarde");
					req.getRequestDispatcher("DeletarColaborador.jsp").forward(req,
							resp);
					
				}

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
