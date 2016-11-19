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

import com.brq.dao.CadastrarColaboradorDao;
import com.brq.model.Cargo;
import com.brq.model.Colaborador;

@WebServlet(value = "/cadastrar")
public class CadastraColaboradorController extends HttpServlet {

	private static final long serialVersionUID = 2590653595114634373L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<Cargo> listaCargos = new ArrayList<Cargo>();
		CadastrarColaboradorDao cadastrarColaboradorDao = new CadastrarColaboradorDao();
		listaCargos = cadastrarColaboradorDao.listarCargos();

		req.setAttribute("listaCargos", listaCargos);

		req.getRequestDispatcher("CadastrarColaborador.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
			Colaborador colaborador = new Colaborador();
			colaborador.setNome(req.getParameter("nome"));
			colaborador.setCpf(Long.parseLong(req.getParameter("cpf").replace(".", "").replace(".", "").replace("-", "")));
			colaborador.setDataDeNascimento(formatar.parse(req
					.getParameter("dataNascimento")));

			colaborador.setIdCargo(Integer.parseInt(req.getParameter("cargo")));

			CadastrarColaboradorDao cadastrarColaboradorDao = new CadastrarColaboradorDao();
			boolean isSave = cadastrarColaboradorDao.salvar(colaborador);
			if (isSave) {
				req.setAttribute("msgType", "info");
				req.setAttribute("msg", "Colaborador cadastrado com sucesso");
				req.getRequestDispatcher("CadastrarColaborador.jsp").forward(
						req, resp);
			} else {
				req.setAttribute("msgType", "error");
				req.setAttribute("msg", "Falha no sistema tente mais tarde");
				req.getRequestDispatcher("CadastrarColaborador.jsp").forward(
						req, resp);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
