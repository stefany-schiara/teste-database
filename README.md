# teste-database

JPA - O que é? 

Java Persistence API - É uma coleção de classes e métodos para  interação com banco de dados.


Para que serve?

Serve para integração com banco de dados, evitando que o desenvolvedor gaste tempo codificando queries, script de criação de tabelas e etc. Através de Annotations 
e métodos da própria biblioteca é possível realizar diversas operações no banco de dados, desde criação de tabelas até consultas(select) e etc.


Como usar?

Basta adicionar as dependências no seu arquivo pom.xml do seu arquivo, que será possível utilizar os recursos
através dos imports.

-----------------------------------------------------------------------------------------

---------------------- Annotations ----------------------


@Entity - Anotação utilizada para mapear uma entidade, uma tabela no banco de dados, ao utilizar essa anotação acima do nome de uma classe, caso não exista, ela será criada no banco de dados. A tabela receberá o mesmo nome da classe e as colunas serão os atributos declarados na classe.

@Table - Anotação utilizada para definir propriedades de uma tabela, nesse caso, o nome da classe poderá ser diferente 
do nome da tabela utilizando:

        @Table(name = "nome_da_tabela")

Caso  necessário, você pode até mapear o schema:

 	@Table(name = "nome_da_tabela", schema = "nome_do_schema")

Obs: Essa anotação também é utilizada na linha acima do nome da classe.

@GeneratedValue - Anotação utilizada para mapear a propriedade da sua tabela que é um id,
você vai definir a estratégia que essa coluna adotará: 

GenerationType.AUTO: Valor padrão, deixa com o provedor de persistência a escolha da estratégia mais adequada de acordo com o banco de dados.

GenerationType.IDENTITY: Informamos ao provedor de persistência que os valores a serem atribuídos ao identificador único serão gerados pela coluna de 
auto incremento do banco de dados. Assim, um valor para o identificador é gerado para cada registro inserido no banco. Alguns bancos de dados podem não suportar essa opção.

GenerationType.SEQUENCE: Informamos ao provedor de persistência que os valores serão gerados a partir de uma sequence. Caso não seja especificado um 
nome para a sequence, será utilizada uma sequence padrão, a qual será global, para todas as entidades. Caso uma sequence seja especificada, o provedor passará a adotar 
essa sequence para criação das chaves primárias. Alguns bancos de dados podem não suportar essa opção.

GenerationType.TABLE: Com a opção TABLE é necessário criar uma tabela para gerenciar as chaves primárias. Por causa da sobrecarga de consultas necessárias 
para manter a tabela atualizada, essa opção é pouco recomendada.

É fortemente recomendado utilizar os tipos: 
GenerationType.SEQUENCE e GenerationType.IDENTITY, dado que os demais tem um problema de escalabilidade, o TABLE, por exemplo, não é uma pratica muito interessante, 
já que é criada uma tabela somente para manter ids.

---------------------- Exemplo de utilização: ---------------------- 

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private integer id;

----------------------

        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_nome_da_sequence") 
        @SequenceGenerator(name = "id_nome_da_sequence", sequenceName = "nome_da_sequence")
        private integer id;

----------------------

@Column -  Anotação utilizada para definir propriedades de uma coluna,  como por exemplo: nome, tipo, se pode ou não ser nulo.
Caso não definamos o nome, a coluna da tabela receberá o mesmo nome do atributo declarado na classe

        @Column(name = "nome_cuso")
        private String nome;

------------------------------------------------------------------------------------------------------------------------------------

JpaRepository
Quando nós criamos uma interface e estendemos da classe JpaRepository, podemos utilizar todos os métodos referentes à banco de dados que o spring fornece, como: inserções, deleções, consultas e etc.
Quando nós criamos uma interface, temos que passar como parâmetro o nome da classe que representa a entidade(tabela) e o tipo do campo Id da entidade:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
```
Ao utilizar essa interface CursoRepository, quando formos utilizar os métodos do JPA, eles esperarão como parâmetro, um objeto do tipo Curso.
Exemplos dos métodos básicos:
save
```
public classCursoService {
    @Autowired 
    CursoRepository cursoRepository;

    Curso curso = new Curso();
    curso.setNome("Graduação em TI");
    
    cursoRepository.save(curso); //Salvar no banco de Dados.
   
    //É possível realizar um update do registro utlizando o metódo save.
    //Exemplo:

    curso.setNome("Alterando nome do curso existente");
    cursoRepository.save(curso);

    //Como se trata do mesmo objeto, o spring entender que deve fazer um update.
}
```
count
```
public classCursoService { 
    @Autowired  
    CursoRepository cursoRepository;
    
    //Traz a quantidade de registros na tabela
     cursoRepository.count(); 
}
```
findAll
```
public classCursoService {
    @Autowired 
    CursoRepository cursoRepository;
    
    //Traz todos os registros da tabela
     cursoRepository.findAll(); 
}
```
delete
```
public classCursoService {
    @Autowired 
    CursoRepository cursoRepository;
      
    Curso curso1 = new Curso(); 
    curso1.setNome("Graduação em TI");    

    //Delete registro da tabela, informando o objeto
     cursoRepository.delete(curso1); 
}
```

deleteById
```
public classCursoService {
    @Autowired 
    CursoRepository cursoRepository;
      
    Curso curso1 = new Curso(); 
    curso1.setNome("Graduação em TI");

    Curso curso2 = new Curso();  
    curso2.setNome("Graduação em TI");
    
     //Delete registro da tabela, informando o id
     cursoRepository.deleteById(2); 
}
```

FindBy<Field>
Para utilizar um find com um criterio diferente de busca com um campo diferente, devemos ir na nossa interface do repository
e adicionar um método novo com o nome findByNomeDoCampoDesejado.
Ex:        
```        
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
  List<Curso> findCursoByNome(String nome);
}
        
```
Utilização:
```
  @Autowired  
  CursoRepository cursoRepository;

  cursoRepository.findCursoByNome(nome);
```

FindByCampoContaining
Para utilizar um find com um criterio like na busca , devemos ir na nossa interface do repository
e adicionar um método novo com o nome findByNomeDoCampoContaining.
Ex:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
    List<Curso> findCursoByNomeContaining(String valor);
}
```
Utilização:
```
@Autowired  
CursoRepository cursoRepository;

cursoRepository.findCursoByNomeContaining("Ste");
```
Seria como se utilizasse select * from tabela where coluna_nome like '%Ste%', ou seja, com os dois sinais de %%.

Já com o FindByCampoLike, você consegue passar se quer no começo, no final, ou ambos.
Ex:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
    List<Curso> findCursoByNomeLike(String valor);
}
```
Utilização:
```
@Autowired  
CursoRepository cursoRepository;

cursoRepository.findCursoByNomeLike("%Ste");
ou
cursoRepository.findCursoByNomeLike("Ste%");
ou
cursoRepository.findCursoByNomeLike("%Ste%");
```

FindByCampoLikeIgnoreCase
Para utilizar um find com um critério "like", porém ignorando o Case Sensitive, podemos usar criar esse método,
pois caso você tenha dados iguais, porém com linhas que comecem com minusculo e outras maiúscula, a busca trará ambos.
Ex:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
    List<Curso> findCursoByNomeLikeIgnoreCase(String valor);
}
```
Utilização:
```
@Autowired  
CursoRepository cursoRepository;

cursoRepository.findCursoByNomeLikeIgnoreCase("%Ste");
ou
cursoRepository.findCursoByNomeLikeIgnoreCase("Ste%");
ou
cursoRepository.findCursoByNomeLikeIgnoreCase("%Ste%");
```

@Query -  Anotação utilizada para inserir uma query personalizada ao seu método. 
Ex:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
    @Query(value = "select c from Curso c")
    List<Curso> findCursoByQuery(); 
}
```
Você deve dar um alias para o nome da classe que representa sua tabela, no caso acima, no banco de dados por trás 
dos panos o que está sendo executado nada mais é do que: select * from nome_tabela. Caso quisermos trazer uma coluna específica, ficaria da seguinte forma:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
    @Query(value = "select c.nome from Curso c")
    List<Curso> findCursoByQuery();
}
```

É possível utilizar a query nativa com essa mesma anotação, porém passando um parametro a mais, além disso, ao invés de colocar o nome da classe(Entidade), 
devemos colocar o próprio nome da tabela e das colunas.
Ex:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
    @Query(value = "select * from curso_faculdade where area = 'Exatas'", nativeQuery = true)
    List<Curso> findCursoByQuery(); 
}
```

@Param -  Anotação utilizada para passar um parâmetro para a query.
Ex:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
    @Query(value = "select * from curso_faculdade where area = :area", nativeQuery = true)
    List<String> findCursoByQueryAreaInformada(@Param("area") String area); 
}
```
Para utilizar mais de um parâmetro, basta separar por vírgula
Ex:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> { 
    @Query(value = "select * from curso_faculdade where area = :area", nativeQuery = true)
    List<String> findCursoByQueryAreaInformada(@Param("area") String area, @Param("")); 
}
```
Ou é possível passar apenas a posição dos parâmetros, sem utilizar a anotação
Ex:
```
public interface CursoRepository extends JpaRepository<Curso, Integer> {    
    @Query(value = "select nome_curso from curso_faculdade where area = ?1 and nome_curso = ?2", nativeQuery = true)
    List<String> findCursoByQueryParametrosPorIndice(@Param("area") String area, @Param("nome") String nome);
}
```

@CreationTimestamp -  Anotação utilizada para setar a data e hora de uma coluna, no momento em que a linha for criada.
Ex:
```
@Entity
@Table(name = "curso_faculdade")
public class Curso {
    
    @Column(name = "dt_criacao")
    @CreationTimestamp 
    private LocalDateTime dataCriacao;

}

```

@UpdateTimestamp -  Anotação utilizada para setar a data e hora de uma coluna, no momento em que uma alteração for realizada numa determinada linha.
Ex:
```
@Entity
@Table(name = "curso_faculdade")
public class Curso {    
    
    @Column(name = "dt_atualizacao")
    @UpdateTimestamp 
    private LocalDateTime dataAtualizacao;

}

```

@PostPersist -  Anotação utilizada para setar algum registro em uma linha no banco de dados, após um registro ser salvo,  só é executado ao persistir a primeira vez, no update, não é executado.  Você chama um método com essa anotação,  serve para qualquer ação que você queira realizar após salvar algum registro na base.
Ex:
```
@Entity
@Table(name = "curso_faculdade")
public class Curso {    
    
    @Column(name = "dt_atualizacao")
    @UpdateTimestamp 
    private LocalDateTime dataAtualizacao;

    @PostPersist
    private void aposPersistirDados() { 
    this.nome = this.nome + "POST"; 
}

}
        
        
```

@PrePersist -  Anotação utilizada para setar algum registro em uma linha no banco de dados, antes de um registro ser salvo .  
Você chama um método com essa anotação,  serve para qualquer ação que você queira realizar antes de salvar algum registro na base.
Ex:
```
@Entity
@Table(name = "curso_faculdade")
public class Curso {    
    
    @Column(name = "dt_atualizacao")
    @UpdateTimestamp 
    private LocalDateTime dataAtualizacao;
    
    @Column(name = "usuario")
    private String usuario;
   
    @PrePersist
    private void antesDePersistirDados() { 
        this.usuario = "Admin"; 
    }

}
```
@Transient -  Anotação utilizada para definir que um campo não será persistido no banco, é como se fosse um campo que guardasse valores temporários, como por exemplo, você precisa exibir um cálculo para o usuário, mas não precisa salvar esse cálculo em uma coluna na base de dados, mas apenas o valor final, você pode criar o campo e anotar ele com @Transient, assim, não será salvo no banco, nem será criada uma coluna para este campo.
Ex:
```
@Entity
@Table(name = "curso_faculdade")
public class Curso {    
    
    @Column(name = "dt_atualizacao")
    @UpdateTimestamp 
    private LocalDateTime dataAtualizacao;
    
    @Column(name = "usuario")
    private String usuario;
   
    @Transient
    private BigDecimal calculoValorCurso;

}
```
@NotNull -  Anotação utilizada para definir que um campo não poderá ser nulo, caso seja enviado um valor nulo, uma mensagem de erro será mostrada. É possível definir  uma mensagem personalizada.

@NotBlank -  Anotação utilizada para definir que um campo não poderá ser nulo, caso seja enviado um valor nulo, uma mensagem de erro será mostrada. É possível definir  uma mensagem personalizada.
```
@Entity
@Table(name = "curso_faculdade")
public class Curso {
 
    @Column(name = "usuario") 
    @NotNull(message = "O campo usuário não pode ser nulo") 
    @NotBlank(message = "O valor do campo usuário não pode branco") 
    private String usuario;
}
```

@OneToMany -  Anotação utilizada para mapear que uma coluna terá um relacionamento com outra tabela de "Um para muitos"
no exemplo que estamos fazendo, na classe curso, estamos dizendo que 1 curso terá muitos alunos.

@ManyToOne - Essa anotação é o contrário da acima, indica que no relacionamento entre duas tabelas é de "Muitos para Um"
no exemplo: muitos alunos para 1 curso.

Segue exemplo de como é feito esse mapeamento:
Na classe aluno, vamos criar um relacionamento entre a tabela aluno e curso.
```
@Entity
@Table(name = "aluno")
public class Aluno {
    
    @ManyToOne
    @JoinColumn(name = "curso_id") 
    private Curso curso;
}
```
No exemplo acima estou mapeando que eu vou pegar da tabela curso a coluna "curso_id" que será minha chave estrangeira na tabela aluno.

Na classe curso, criamos a propriedade com @OneToMany, passando  o parâmetro "mappedBy" e passamos o valor "curso" que deve ser o mesmo nome da propriedade que criamos na classe aluno.
```
@Entity
@Table(name = "curso_faculdade")
public class Curso {

    @OneToMany(mappedBy = "curso")	
    List<Aluno> alunos = new ArrayList<>();

}
```

Se só utilizarmos puramente as anotações @ManyToOne e @OneToMany, sem passar o @JoinColumn e o parâmetro mappedBy, o sistema criará uma terceira tabela só para guardar os relacionamentos, o que não é uma boa prática.
        
@OneToOne -  Anotação utilizada para mapear que uma coluna terá um relacionamento com outra tabela de "Um para Um".
No exemplo que estamos fazendo, na classe GradeCurricular, estamos dizendo que 1 aluno terá 1 grade curricular e vice-versa.
```
Entity
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
	    name = "id_aluno",   // aqui você cria um nome a vontade, que será o nome da coluna com a chave estrangeira
	    referencedColumnName = "id_aluno" // aqui você coloca o nome da coluna da outra tabela que será referenciada que pertence à tabela de aluno.
	)
	private Aluno aluno;
}
```

@JoinColumn -  Anotação utilizada para informar quem é o dono da relação entre as tabelas, ou seja, no caso acima, a tabela "grade" vai receber uma coluna com a chave estrangeira "id_aluno"
