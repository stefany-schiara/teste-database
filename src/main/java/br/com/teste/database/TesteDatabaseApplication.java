package br.com.teste.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.teste.database.entities.Curso;
import br.com.teste.database.repositories.CursoRepository;

@SpringBootApplication
public class TesteDatabaseApplication implements CommandLineRunner{

	@Autowired
	CursoRepository cursoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteDatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Curso curso1 = new Curso("Graduação TI");
		Curso curso2 = new Curso("Graduação Engenharia");
		Curso curso3 = new Curso("Graduação em Administração");
		Curso curso4 = new Curso("Graduação Educação Física");		
		
		salvarCurso(curso1);
		salvarCurso(curso2);
		salvarCurso(curso3);
		salvarCurso(curso4);
		
//		deletarCurso(curso2);
//		deletarCursoPorId(3);
		
//		findAllCursos();
		
//		findCursoById(3);
		
//		countCurso();
		
		List<Curso> ListcursoPorNome = findCursoByNome(curso2.getNome());
		imprimirValoresLista(ListcursoPorNome);
		
	}
	
	public void salvarCurso(Curso curso) {
		cursoRepository.save(curso);		
	}
	
	public void deletarCurso(Curso curso) {
		cursoRepository.delete(curso);
	}
	
	public void deletarCursoPorId(Integer id) {
		cursoRepository.deleteById(id);
	}
	
	public List<Curso> findAllCursos(){
		return  cursoRepository.findAll();		
	}
	
	public void imprimirValoresLista(List<Curso> listaCursos) {
		
		listaCursos.forEach(curso -> System.out.println(curso));		
		
	}
	
	public void findCursoById(Integer id) {
		Optional<Curso> cursoProcurado = cursoRepository.findById(3);		
		Curso cursoFinal = cursoProcurado.orElse(null);
				
		System.out.println("O nome do curso procurado é:" + cursoFinal.getNome());
	}
	
	public void countCurso() {
		System.out.println("Total de cursos: (" + cursoRepository.count() + ")");
	}

	public List<Curso> findCursoByNome(String nome){
		return cursoRepository.findCursoByNome(nome);
	}
}
