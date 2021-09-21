package br.com.teste.database.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.teste.database.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
	
	List<Curso> findCursoByNome(String nome);
	
	List<Curso> findCursoByNomeContaining(String valor);
	
	List<Curso> findCursoByNomeLike(String valor);
	
	List<Curso> findCursoByNomeLikeIgnoreCase(String valor);
	
	@Query(value = "select c from Curso c")
	List<Curso> findCursoByQuery();
	
	@Query(value = "select * from curso_faculdade where area = 'Exatas'", nativeQuery = true)	
	List<Curso> findCursoByQueryArea();
	
	@Query(value = "select nome_curso from curso_faculdade where area = :area ", nativeQuery = true)
	List<String> findCursoByQueryAreaInformada(@Param("area") String area);
	
	@Query(value = "select nome_curso from curso_faculdade where area = :area and nome_curso = :nome", nativeQuery = true)
	List<String> findCursoByQueryParametros(@Param("area") String area, @Param("nome") String nome);
	
	@Query(value = "select nome_curso from curso_faculdade where area = ?1 and nome_curso = ?2", nativeQuery = true)
	List<String> findCursoByQueryParametrosPorIndice(@Param("area") String area, @Param("nome") String nome);

}
