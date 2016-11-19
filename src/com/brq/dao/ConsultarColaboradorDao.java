package com.brq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.brq.model.Cargo;
import com.brq.model.Colaborador;

public class ConsultarColaboradorDao {

	public List<Colaborador> consultar() {
		List<Colaborador> lista = new ArrayList<Colaborador>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Colaborador col = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/ColaboradorDB",
					"postgres", "123456");

			pstmt = conn
					.prepareStatement("select * from colaborador co, cargo ca  where co.idCargo = ca.id");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				col = new Colaborador();
				col.setNome(rs.getString("nome"));
				col.setCpf(rs.getLong("cpf"));
				col.setCpfString(setMascaraCPF(col.getCpf()));
				col.setDataDeNascimento(new Date(rs.getDate("dtNascimento")
						.getTime()));
				col.setIdCargo(rs.getInt("idcargo"));
				col.setDescricao(rs.getString("descricao"));
				lista.add(col);
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return lista;
	}

	public List<Cargo> listarCargos() {
		List<Cargo> lista = new ArrayList<Cargo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Cargo car = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/ColaboradorDB",
					"postgres", "123456");

			pstmt = conn.prepareStatement("select * from cargo");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				car = new Cargo();
				car.setId(rs.getInt("id"));
				car.setDescricao(rs.getString("descricao"));

				lista.add(car);
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return lista;
	}

	private String setMascaraCPF(Long cpf) {
		String cpfText = cpf.toString();
		if (cpfText.length() < 11) {
			cpfText = "0" + cpfText;

		}
		if (cpfText.length() == 11) {

			cpfText = cpfText.substring(0, 3) + "." + cpfText.substring(3, 6)
					+ "." + cpfText.substring(6, 9) + "-"
					+ cpfText.substring(9, 11);
		}

		System.out.println(cpfText);

		return cpfText;
	}
}
