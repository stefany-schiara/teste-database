package br.com.teste.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.teste.database.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
	
	List<Curso> findCursoByNome(String nome);
	
	List<Curso> findCursoByNomeContaining(String valor);
	
	List<Curso> findCursoByNomeLike(String valor);
	
	List<Curso> findCursoByNomeLikeIgnoreCase(String valor);
	
	@Query(value = "select * from Curso")
	List<Curso> findCursoByQuery();

}
