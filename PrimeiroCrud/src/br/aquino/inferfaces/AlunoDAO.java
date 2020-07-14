package br.aquino.inferfaces;

import java.util.List;

import br.aquino.model.Aluno;



public interface AlunoDAO {
	public List<Aluno> getAll();

	public Aluno getById(Integer id);
	
	public boolean create(Aluno aluno);
	
	public boolean update(Aluno aluno);
	
	public boolean delete(Integer id);
	
	public List<Aluno> getByName(String name);

}
