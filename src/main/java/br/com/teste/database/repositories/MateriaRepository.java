package br.com.teste.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.database.entities.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long>{

}
