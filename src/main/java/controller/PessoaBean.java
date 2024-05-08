package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import sisPessoa.dto.Cidade;
import sisPessoa.dto.Estado;
import sisPessoa.model.Endereco;
import sisPessoa.model.Pessoa;
import sisPessoa.service.PessoaService;

@Named
@ViewScoped  
public class PessoaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Inject
    private PessoaService pessoaService; 
	
	private Pessoa pessoa; 
	
	private String pesquisaNome;
	
	private List<Pessoa> pessoas;
	
	private List<Estado> estados;
	
    private List<Cidade> cidades;

    private Estado selectedEstadoSigla;
    private Cidade selectedCidade;

    @PostConstruct
    public void init() {
        if (pessoa == null) {
        	prepararNovoRegistro();
        }
        pessoas = pessoaService.getAllPessoas(); 
        carregarEstados();
    }

	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void pesquisar() {
	    pessoas = pessoaService.pesquisarPorNomeLike(pesquisaNome);
	}
	
	
	public void prepararNovoRegistro() {
	    this.pessoa = new Pessoa();
	    this.pessoa.setId(0); 
	    Endereco endereco = new Endereco();  
	    endereco.setLogradouro("");          
	    endereco.setCep("");                 
	    this.pessoa.setEnderecos(new ArrayList<>());
	    this.pessoa.getEnderecos().add(endereco); 
	}


	
	public void prepararEdicao(Pessoa pessoa) {
	    this.pessoa = pessoaService.porId(pessoa.getId());
	}

	
	public void salvar() {
	    try {
	    	
	    	pessoa.getEnderecos().get(0).setCidade(selectedCidade.getNome());
	    	pessoa.getEnderecos().get(0).setEstado(selectedEstadoSigla.getSigla());
	    	
	        Pessoa pessoaSalva = pessoaService.salvar(pessoa); 

	        if (pessoa.getId() == 0) {
	            pessoas.add(pessoaSalva);
	        } else {
	            int index = pessoas.indexOf(pessoa);  
	            if (index != -1) {
	                pessoas.set(index, pessoaSalva);  
	            }
	        }
	        
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro salvo com sucesso!"));
	        prepararNovoRegistro(); 
	    } catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o registro: " + e.getMessage(), null));
	    }
	}


	 public void carregarEstados() {
	        try {
	            HttpClient httpClient = HttpClients.createDefault();
	            HttpGet httpGet = new HttpGet("https://servicodados.ibge.gov.br/api/v1/localidades/estados");
	            HttpResponse response = httpClient.execute(httpGet);

	            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            StringBuilder result = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                result.append(line);
	            }

	            Jsonb jsonb = JsonbBuilder.create();
	            estados = jsonb.fromJson(result.toString(), new ArrayList<Estado>(){}.getClass().getGenericSuperclass());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	 public void carregarCidades() {
		    
		    String sigla = selectedEstadoSigla.getSigla();

		    
		    if (sigla == null || sigla.isEmpty()) {
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sigla do estado não encontrada", null));
		        return;
		    }

		    
		    try {
		        HttpClient httpClient = HttpClients.createDefault();
		        HttpGet httpGet = new HttpGet("https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + sigla + "/municipios");
		        HttpResponse response = httpClient.execute(httpGet);

		        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		        StringBuilder result = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            result.append(line);
		        }

		        Jsonb jsonb = JsonbBuilder.create();
		        cidades = jsonb.fromJson(result.toString(), new ArrayList<Cidade>(){}.getClass().getGenericSuperclass());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

	
	
	public void remover(Pessoa pessoa) {
		pessoaService.remover(pessoa);
		pessoas = pessoaService.getAllPessoas();
	}
	
	
	public String getPesquisaNome() {
		return pesquisaNome;
	}
	
	public void setPesquisaNome(String pesquisaNome) {
		this.pesquisaNome = pesquisaNome;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}


	public List<Estado> getEstados() {
		return estados;
	}


	public List<Cidade> getCidades() {
		return cidades;
	}
	
	
	 public Estado getSelectedEstadoSigla() {
	        return selectedEstadoSigla;
     }

    public void setSelectedEstadoSigla(Estado selectedEstadoSigla) {
        this.selectedEstadoSigla = selectedEstadoSigla;
    }
    
	public Cidade getSelectedCidade() {
		return selectedCidade;
	}
	
	public void setSelectedCidade(Cidade selectedCidade) {
		this.selectedCidade = selectedCidade;
	}

}
