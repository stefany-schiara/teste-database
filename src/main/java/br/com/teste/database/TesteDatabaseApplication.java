package br.com.teste.database;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.teste.database.entities.Aluno;
import br.com.teste.database.entities.Curso;
import br.com.teste.database.entities.GradeCurricular;
import br.com.teste.database.repositories.AlunoRepository;
import br.com.teste.database.repositories.CursoRepository;
import br.com.teste.database.repositories.GradeCurricularRepository;

@SpringBootApplication
public class TesteDatabaseApplication implements CommandLineRunner{

	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	AlunoRepository alunoRepository;
	
	@Autowired
	GradeCurricularRepository gradeCurricularRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteDatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Curso curso1 = new Curso("Graduação em TI", "Exatas");
		Curso curso2 = new Curso("Graduação em Economia", "Humanas");
		Curso curso3 = new Curso("Graduacao em Administração", "Humanas");
		Curso curso4 = new Curso("Graduação em Educação Física", "Humanas");		
		Curso curso5 = new Curso("Graduação em Educação Continuada", "Humanas");
		Curso curso6 = new Curso("Graduação em educação Física", "Exatas");
		
		salvarCurso(curso1);
		salvarCurso(curso2);
		salvarCurso(curso3);
		salvarCurso(curso4);
		salvarCurso(curso5);
		salvarCurso(curso6);
		
		Aluno aluno1 = new Aluno("Jose", curso1);
		Aluno aluno2 = new Aluno("Aline", curso1);
		Aluno aluno3 = new Aluno("Humberto", curso1);
		
		alunoRepository.save(aluno1);
		alunoRepository.save(aluno2);
		alunoRepository.save(aluno3);
		
		GradeCurricular grade1 = new GradeCurricular("Graduação em Games", aluno1);
		GradeCurricular grade2 = new GradeCurricular("Graduação em Academia de Rua", aluno3);
		
		gradeCurricularRepository.save(grade1);
		gradeCurricularRepository.save(grade2);
		
//		System.out.println("Aguardando 3 segundos...");
//		Thread.sleep(3000);
		
//		curso6.setNome("Graduação em educação Física - Alterada");
//		salvarCurso(curso6);
		
//		deletarCurso(curso2);
//		deletarCursoPorId(3);
		
//		findAllCursos();
		
//		findCursoById(3);
		
//		countCurso();
		
//		List<Curso> listcursoPorNome = findCursoByNome(curso2.getNome());		
//		imprimirValoresLista(listcursoPorNome);
		
//		List<Curso> listCursoContaining = findbyContaining("TI");
//		imprimirValoresLista(listCursoContaining);
		
//		List<Curso> listCursoPorNomeLike = findCursoPorNomeLike("%Grad");
//		imprimirValoresLista(listCursoPorNomeLike);
		
//		List<Curso> listCursoPorNomeLikeIgnoreCase = findCursoPorNomeLikeIgnoreCaseSensitive("%Educação%");
//		imprimirValoresLista(listCursoPorNomeLikeIgnoreCase);
		
//		List<Curso> listCursoPorQuery = findCursoPorQuery();
//		imprimirValoresLista(listCursoPorQuery);
		
//		List<Curso> listCursoAreaPorQuery = findCursoPorAreaQuery();
//		imprimirValoresLista(listCursoAreaPorQuery);
		
//		List<String> listCursoAreaInformada = findCursoPorAreaInformada("Exatas");
//		listCursoAreaInformada.forEach(curso -> System.out.println(curso));
		
//		List<String> listCursoAreaInformada = findCursoByQueryParametros(curso3.getArea(), curso3.getNome());
//		listCursoAreaInformada.forEach(curso -> System.out.println(curso));
		
		List<String> listCursoAreaInformadaPorIndice = findCursoByQueryParametrosPorIndice(curso3.getArea(), curso3.getNome());
		listCursoAreaInformadaPorIndice.forEach(curso -> System.out.println(curso));
		
		
		
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
	
	public void imprimirValoresLista(List<Curso> lista) {
		
		lista.forEach(curso -> System.out.println(curso));		
		
	}
	
	public void findCursoById(Integer id) {
		Optional<Curso> cursoProcurado = cursoRepository.findById(3);		
		Curso cursoFinal = cursoProcurado.orElse(null);
				
		System.out.println("O nome do curso procurado é:" + cursoFinal.getNome());
	}
	
	public void countCurso() {
		System.out.println("Total de cursos: (" + cursoRepository.count() + ")");
	}

	public List<Curso> findCursoPorNome(String nome){
		return cursoRepository.findCursoByNome(nome);
	}
	
	public List<Curso> findCursoPorNomeContaining(String valor){
		return cursoRepository.findCursoByNomeContaining(valor);		
	}

	public List<Curso> findCursoPorNomeLike(String valor){
		return cursoRepository.findCursoByNomeLike(valor);
	}

	public List<Curso> findCursoPorNomeLikeIgnoreCaseSensitive(String valor){
		return cursoRepository.findCursoByNomeLikeIgnoreCase(valor);
	}

	public List<Curso> findCursoPorQuery(){
		return cursoRepository.findCursoByQuery();
	}
	
	public List<Curso> findCursoPorAreaQuery(){
		return cursoRepository.findCursoByQueryArea();
	}

	public List<String> findCursoPorAreaInformada(String area){
		return cursoRepository.findCursoByQueryAreaInformada(area);
	}

	public List<String> findCursoByQueryParametros(String area, String nome){
		return cursoRepository.findCursoByQueryParametros(area, nome);
	}
	
	public List<String> findCursoByQueryParametrosPorIndice(String area, String nome){
		return cursoRepository.findCursoByQueryParametrosPorIndice(area, nome);
	}
	
	
}

