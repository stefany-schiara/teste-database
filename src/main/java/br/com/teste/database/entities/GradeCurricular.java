package br.com.teste.database.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class GradeCurricular {
	
	@Id
	@Column(name = "id_grade")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGrade;
	
	@Column(name = "objetivo")
	private String objetivo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			name = "id_aluno",
			referencedColumnName = "id_aluno"
	)
	private Aluno aluno;

	public GradeCurricular() {
		super();		
	}

	public GradeCurricular(String objetivo, Aluno aluno) {
		super();
		this.objetivo = objetivo;
		this.aluno = aluno;
	}

	public Integer getIdGrade() {
		return idGrade;
	}

	public void setIdGrade(Integer idGrade) {
		this.idGrade = idGrade;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}	

}
