package sisPessoa.repository;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import sisPessoa.model.Pessoa;


public class Pessoas implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private EntityManager manager;
	
	
	public Pessoas() {
	}
	
	
	@Inject
	public Pessoas(EntityManager manager) {
        this.manager = manager;
    }
	
	
	
	public List<Pessoa> todos() {
		return manager.createQuery("from Pessoa", Pessoa.class).getResultList();
	}
	
	public Pessoa salvar(Pessoa pessoa) {
		
		return manager.merge(pessoa);
	}
	
	public void remover(Pessoa pessoa) {
		pessoa = porId(pessoa.getId());
		manager.remove(pessoa);
	}
	
	public Pessoa porId(int id) {
		return manager.find(Pessoa.class, id);
	}
	
	public Pessoa comEnderecos(int id) {
		return manager.createQuery("from Pessoa p join fetch p.enderecos where p.id = :id", Pessoa.class)
				.setParameter("id", id).getSingleResult();
	}
	
	public Pessoa pesquisarPorNome(String nome) {
		return manager.createQuery("from Pessoa where nome = :nome", Pessoa.class).setParameter("nome", nome)
				.getSingleResult();
	}
	
	
	public List<Pessoa> pesquisarPorNomeLike(String nome) {
		return manager.createQuery("from Pessoa where nome like :nome", Pessoa.class)
				.setParameter("nome", "%" + nome + "%").getResultList();
	}
	
	public List<Pessoa> pesquisarPorNomeLike(String nome, String sexo) {
		return manager.createQuery("from Pessoa where nome like :nome and sexo = :sexo", Pessoa.class)
				.setParameter("nome", "%" + nome + "%").setParameter("sexo", sexo).getResultList();
	}
	
	public List<Pessoa> pesquisarPorNomeLike(String nome, String sexo, String estado) {
		return manager
				.createQuery("from Pessoa where nome like :nome and sexo = :sexo and estado = :estado", Pessoa.class)
				.setParameter("nome", "%" + nome + "%").setParameter("sexo", sexo).setParameter("estado", estado)
				.getResultList();
	}
	
	public List<Pessoa> pesquisarPorNomeLike(String nome, String sexo, String estado, String cidade) {
		return manager
				.createQuery(
						"from Pessoa where nome like :nome and sexo = :sexo and estado = :estado and cidade = :cidade",
						Pessoa.class)
				.setParameter("nome", "%" + nome + "%").setParameter("sexo", sexo).setParameter("estado", estado)
				.setParameter("cidade", cidade).getResultList();
	}
	
	public List<Pessoa> pesquisarPorNomeLike(String nome, String sexo, String estado, String cidade,
			String logradouro) {
		return manager.createQuery(
				"from Pessoa where nome like :nome and sexo = :sexo and estado = :estado and cidade = :cidade and logradouro = :logradouro",
				Pessoa.class).setParameter("nome", "%" + nome + "%").setParameter("sexo", sexo)
				.setParameter("estado", estado).setParameter("cidade", cidade).setParameter("logradouro", logradouro)
				.getResultList();
	}
	
	public List<Pessoa> pesquisarPorNomeLike(String nome, String sexo, String estado, String cidade, String logradouro,
			int numero) {
		return manager.createQuery(
				"from Pessoa where nome like :nome and sexo = :sexo and estado = :estado and cidade = :cidade and logradouro = :logradouro and numero = :numero",
				Pessoa.class).setParameter("nome", "%" + nome + "%").setParameter("sexo", sexo)
				.setParameter("estado", estado).setParameter("cidade", cidade).setParameter("logradouro", logradouro)
				.setParameter("numero", numero).getResultList();
	}
	
	
	

}
