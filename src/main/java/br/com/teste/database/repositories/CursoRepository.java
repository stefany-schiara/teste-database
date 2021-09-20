package br.com.teste.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.database.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
	
	List<Curso> findCursoByNome(String nome);

}
