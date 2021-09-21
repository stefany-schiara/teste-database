package br.com.teste.database.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "curso_faculdade")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome_curso", nullable = false)
	private String nome;
	
	@Column(name = "area", nullable = false)
	private String area;
	
	@Column(name = "dt_criacao")
	@CreationTimestamp
	private LocalDateTime dataCriacao;
		
	@Column(name = "dt_atualizacao")
	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;

	public Curso() {

	}

	public Curso(String nome, String area) {
		super();
		this.nome = nome;
		this.area = area;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", area=" + area + "]";
	}	

}
