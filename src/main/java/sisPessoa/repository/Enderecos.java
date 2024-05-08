package sisPessoa.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import sisPessoa.model.Endereco;
import sisPessoa.model.Pessoa;

public class Enderecos implements Serializable {	
	
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager manager ;
    
	public Enderecos(EntityManager em) {
		this.manager = em;
	}
	
	List<Endereco> todosPorPessoa (Pessoa pessoa) {
		return manager.createQuery("from Endereco where pessoa = :pessoa", Endereco.class).setParameter("pessoa", pessoa).getResultList();
	}
	
	Endereco salvar(Endereco endereco, Pessoa pessoa) {
		endereco.setPessoa(pessoa);
		return manager.merge(endereco);
	}
	
	void remover(Endereco endereco) {
		endereco = porId(endereco.getId());
		manager.remove(endereco);
	}
	
	Endereco porId(int id) {
		return manager.find(Endereco.class, id);
	}
    
    

}
