package br.com.restcontato.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.restcontato.config.*;
import br.com.restcontato.entidade.Contato;


public class ContatoDAO {
	
	Connection conexao;
	
	public List<Contato> getAll(Integer page, Integer size) throws Exception {
		List<Contato> lista = new ArrayList<>();

		conexao = DBConfig.getConnection();

		//String sql = "select * from contato";
		StringBuilder sql = new StringBuilder("select * from contato ");
		
		sql.append(String.format("limit %d,%d", page == null ? 0 : page,
				size == null ? 10 : size.compareTo(10) <= 0 ? size : size));

		PreparedStatement statement = conexao.prepareStatement(sql.toString());
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Contato contato = new Contato();
			contato.setId(rs.getInt("id"));
			contato.setNome(rs.getString("nome"));
			contato.setCanal(rs.getString("canal"));
			contato.setValor(rs.getString("valor"));
			contato.setObs(rs.getString("obs"));

			lista.add(contato);
		}

		return lista;
	}

	public Contato getById(Integer id) throws Exception {
		Contato contato = null;

		conexao = DBConfig.getConnection();

		String sql = "select * from contato where id = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			contato = new Contato();
			contato.setId(rs.getInt("id"));
			contato.setNome(rs.getString("nome"));
			contato.setCanal(rs.getString("canal"));
			contato.setValor(rs.getString("valor"));
			contato.setObs(rs.getString("obs"));
		}

		return contato;
	}

	public Contato create(Contato contato) throws Exception {
		int idGerado = 0;
		Contato novoContato;
		conexao = DBConfig.getConnection();

		String sql = "insert into contato(nome, canal, valor, obs) VALUES(?, ?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getCanal());
		statement.setString(3, contato.getValor());
		statement.setString(4, contato.getObs());
		statement.execute();

		ResultSet rs = statement.getGeneratedKeys();
		if (rs.next()) {
			idGerado = rs.getInt(1);
		}
		
		novoContato = getById(idGerado);

		return novoContato;
	}

	public Contato update(Contato contato, Integer id) throws Exception {
		conexao = DBConfig.getConnection();

		String sql = "update contato set nome = ?, canal = ?, valor = ?, obs = ? where id = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, contato.getNome());
		statement.setString(2, contato.getCanal());
		statement.setString(3, contato.getValor());
		statement.setString(4, contato.getObs());
		statement.setInt(5,    id);
		statement.execute();
		
		return contato;
	}

	public void remove(Integer id) throws Exception {
		conexao = DBConfig.getConnection();

		String sql = "delete from contato where id = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, id);
		statement.execute();
	}
}
