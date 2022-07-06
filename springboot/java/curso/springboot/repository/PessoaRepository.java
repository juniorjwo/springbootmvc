package curso.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.springboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
//	@Query("select p from Pessoa p where p.nome like %?1%")
//	List<Pessoa> findPessoaByName(String nome);
//}
//	@Query("select p from Pessoa p where upper(trim( p.nome)) like %?1%")
//	List<Pessoa> findPessoaByName(String nome);
//}
	/*Busca por nome maiusculo ou minusculo*/
	@Query("select p from Pessoa p where upper(p.nome) like upper(concat('%', ?1,'%')) order by p.id")
	
	List<Pessoa> findPessoaByName(String nome);
}


	/*Busca por nome maiusculo ou minusculo SEGUNDA */
//	@Query("select u from Usuario u where lower(u.nome) like lower(concat('%', :nameToFind,'%'))")
//	public List<Usuario> buscarPorNome(@Param("nameToFind") String name);
	
	
//	@Query 
//	List<Pessoa> findPessoaByName(String nome);


