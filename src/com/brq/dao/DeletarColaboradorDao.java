package com.brq.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.brq.model.Cargo;
import com.brq.model.Colaborador;

public class DeletarColaboradorDao {

	public Colaborador buscar(Long cpf) {

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
					.prepareStatement("select * from colaborador where cpf =?");
			pstmt.setLong(1, cpf);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				col = new Colaborador();
				col.setNome(rs.getString("nome"));
				col.setCpf(rs.getLong("cpf"));
				col.setDataDeNascimento(new Date(rs.getDate("dtNascimento")
						.getTime()));
				col.setIdCargo(rs.getInt("idcargo"));
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return col;
	}

	/**
	 * M�todo que processa a exclus�o do colaborador
	 * 
	 * @param colaborador
	 * @return boolean
	 */
	public boolean Deletar(Colaborador colaborador) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/ColaboradorDB",
					"postgres", "123456");

			pstmt = conn
					.prepareStatement("Delete from colaborador where cpf =?");

			pstmt.setLong(1, colaborador.getCpf());
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			flag = false;
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
