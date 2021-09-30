package br.com.teste.database.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "materia")
public class Materia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materia")
	private Integer idMateria;
	
	@Column(name = "nome_materia", nullable = false)
	private String nome;
	
	@ManyToMany()
	@JoinTable(
			name = "grade_materia",
			joinColumns = {
					@JoinColumn(name = "materia_id", referencedColumnName = "id_materia")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "grade_id", referencedColumnName = "id_grade")
			}
			
	)
	private Set<GradeCurricular> grades = new HashSet<>();

}
