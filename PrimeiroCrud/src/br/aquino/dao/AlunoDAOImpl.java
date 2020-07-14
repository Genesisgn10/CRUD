package br.aquino.dao;

import br.aquino.conexao.FabricaConexao;
import br.aquino.inferfaces.AlunoDAO;
import br.aquino.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AlunoDAOImpl implements AlunoDAO {

    
        /**
         * Método utilizado para buscar todos os alunos.
         * @return 
         */
	@Override
	public List<Aluno> getAll() {
		String sql = "SELECT * FROM aluno";
		ResultSet rs = null;
		List<Aluno> allAluno = new ArrayList<>();
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			rs = ps.executeQuery();
			while (rs.next()) {
			    Aluno a = new Aluno(rs.getInt("id_aluno"), rs.getString("nome"), rs.getString("email"),rs.getString("cpf"));	
                            allAluno.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FabricaConexao.fecharConexao(rs);
		}
		return allAluno;
	}

	@Override
	public Aluno getById(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException();
		}
		String sql = "SELECT * FROM `pessoa` WHERE id=?";
		ResultSet rs = null;
		Aluno aluno = null;
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			aluno = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),rs.getString("cpf"));	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FabricaConexao.fecharConexao(rs);
		}
		return aluno;
	}

	@Override
	public boolean create(Aluno aluno) {
		if (aluno == null) {
			throw new IllegalArgumentException();
		}
		String sql = "INSERT INTO aluno (nome, email) VALUES (?, ?)";
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getEmail());
			int linhasAfetadas = ps.executeUpdate();
			if (linhasAfetadas > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Aluno aluno) {
		if (aluno == null || aluno.getId() <=0 || aluno.getId() <= 0) {
			throw new IllegalArgumentException();
		}
		String sql = "UPDATE aluno SET nome = ?, email = ? WHERE `pessoa`.`id` = ?";
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getEmail());
			ps.setInt(3, aluno.getId());
			int linhasAfetadas = ps.executeUpdate();
			if (linhasAfetadas > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		if (id == null || id < 0) {
			throw new IllegalArgumentException();
		}
		String sql = "DELETE FROM aluno WHERE aluno.id_aluno = ?";
		try 
                    (Connection conn = FabricaConexao.getConexao();
                        PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			int linhasAfetadas = ps.executeUpdate();
			if (linhasAfetadas > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<Aluno> getByName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException();
		}
		String sql = "SELECT * FROM `aluno` where nome like ?";
		ResultSet rs = null;
		List<Aluno> allAlunos = new ArrayList<>();
		try (Connection conn = FabricaConexao.getConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%"+name+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Aluno a = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),rs.getString("cpf"));	
                                allAlunos.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FabricaConexao.fecharConexao(rs);
		}
		return allAlunos;
	}

}
