package sisPessoa.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sisPessoa.model.Pessoa;
import sisPessoa.repository.Pessoas;
import sisPessoa.util.Transacional;


@Stateless
public class PessoaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
	private Pessoas pessoas;
	

	
	public List<Pessoa> getAllPessoas() {
        List<Pessoa> todos = pessoas.todos();
        return todos;   
    }
	
	@Transacional
	public Pessoa salvar(Pessoa pessoa) {
		Pessoa.preencherEnderecos(pessoa);
		return pessoas.salvar(pessoa);
	}
	
	@Transacional
	public void remover(Pessoa pessoa) {
		pessoas.remover(pessoa);
	}
	
	public Pessoa porId(int id) {
		return pessoas.porId(id);
	}
	
	public Pessoa comEnderecos(int id) {
		return pessoas.comEnderecos(id);
	}
	
	public Pessoa pesquisarPorNome(String nome) {
		return pessoas.pesquisarPorNome(nome);
	}
	
	public List<Pessoa> pesquisarPorNomeLike(String nome) {
		return pessoas.pesquisarPorNomeLike(nome);
	}
	
	
	public List<Pessoa> pesquisarPorNomeLike(String nome, String sexo) {
		return pessoas.pesquisarPorNomeLike(nome, sexo);
	}
	
	
	public List<Pessoa> pesquisaPorNomeSexo(String nome, String sexo) {
		return pessoas.pesquisarPorNomeLike(nome, sexo);
	}
	
	public List<Pessoa> pesquisaPorNomeSexoCidade(String nome, String sexo, String cidade) {
		return pessoas.pesquisarPorNomeLike(nome, sexo);
	}
	
	public List<Pessoa> pesquisaPorNomeSexoEstado(String nome, String sexo, String estado) {
		return pessoas.pesquisarPorNomeLike(nome, sexo, estado);
	}
	
	public List<Pessoa> pesquisaPorNomeSexoEstadoCidade(String nome, String sexo, String estado, String cidade) {
		return pessoas.pesquisarPorNomeLike(nome, sexo, estado, cidade);
	}
	
	
	
}
	
	
	

