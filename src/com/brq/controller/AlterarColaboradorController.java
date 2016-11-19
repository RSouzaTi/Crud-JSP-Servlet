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
import com.brq.model.Cargo;
import com.brq.model.Colaborador;

@WebServlet(value = "/Alterar")
public class AlterarColaboradorController extends HttpServlet {

	private static final long serialVersionUID = 7949517084426341821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("ConsultarAlterarColaborador.jsp").forward(
				req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String action = req.getParameter("cmd");
			if (action.equalsIgnoreCase("CON")) {
				Long cpf = Long.parseLong(req.getParameter("cpf"));
				AlterarColaboradorDao alterarColaboradorDao = new AlterarColaboradorDao();
				Colaborador col = alterarColaboradorDao.buscar(cpf);

				if (col == null) {
					req.setAttribute("msg", "colaborador não encontrado");
					req.getRequestDispatcher("ConsultarAlterarColaborador.jsp")
							.forward(req, resp);
				} else {
					req.setAttribute("col", col);
					req.setAttribute("cpfAntigo", col.getCpf());
					List<Cargo> listaCargos = new ArrayList<Cargo>();
					AlterarColaboradorDao alterarColaboradorDao2 = new AlterarColaboradorDao();
					listaCargos = alterarColaboradorDao2.listarCargos();
					req.setAttribute("listaCargos", listaCargos);
					req.getRequestDispatcher("AlterarColaborador.jsp").forward(
							req, resp);
				}
			} else if (action.equalsIgnoreCase("ALT")) {
				Colaborador col = new Colaborador();
				col.setNome(req.getParameter("nome"));
				col.setCpf(Long.parseLong(req.getParameter("cpf")));
				col.setDataDeNascimento(new SimpleDateFormat("dd/MM/yyyy")
						.parse(req.getParameter("dataNascimento")));
				col.setIdCargo(Integer.parseInt(req.getParameter("cargo")));

				Long cpfAntigo = Long.parseLong(req.getParameter("cpfAntigo"));

				AlterarColaboradorDao alterarColaboradorDao = new AlterarColaboradorDao();
				boolean result = alterarColaboradorDao.Alterar(col, cpfAntigo);
				if (result) {
					req.setAttribute("msg", "Colaborador alterado com sucesso");
					req.getRequestDispatcher("AlterarColaborador.jsp").forward(
							req, resp);

				} else {
					req.setAttribute("msg",
							"Não foi possivel Alterar o colaborador tente mais tarde");
					req.getRequestDispatcher("AlterarColaborador.jsp").forward(
							req, resp);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			req.setAttribute("msg", "Erro no formato da data");
			req.getRequestDispatcher("AlterarColaborador.jsp").forward(req,
					resp);
		}

	}
}
