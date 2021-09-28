package br.com.teste.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.database.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
