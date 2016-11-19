package com.brq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.brq.model.Cargo;
import com.brq.model.Colaborador;

public class CadastrarColaboradorDao {

	public boolean salvar(Colaborador colaborador) {

		boolean flag = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/ColaboradorDB",
					"postgres", "123456");

			pstmt = conn
					.prepareStatement("INSERT INTO colaborador (nome, cpf, dtnascimento, idcargo) values "
							+ "(?, ?, ?, ?)");
			pstmt.setString(1, colaborador.getNome());
			pstmt.setLong(2, colaborador.getCpf());
			pstmt.setDate(3, new java.sql.Date(colaborador
					.getDataDeNascimento().getTime()));
			pstmt.setInt(4, colaborador.getIdCargo());
			pstmt.execute();
			flag = true;
		} catch (ClassNotFoundException e) {
			flag = false;
			e.printStackTrace();
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}

		return flag;
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
}
