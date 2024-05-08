package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.faces.context.FacesContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sisPessoa.model.Pessoa;
import sisPessoa.service.PessoaService;

public class PessoaBeanTest {

    @InjectMocks
    private PessoaBean pessoaBean;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private FacesContext facesContext; 

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvar() {
       Pessoa pessoa = new Pessoa();
       pessoa.setNome("Teste");
       pessoa.setIdade(30);
       pessoa.setSexo("M");
		pessoa.setEnderecos(null);

		when(pessoaService.salvar(any(Pessoa.class))).thenReturn(pessoa);
		    pessoaBean.salvar();
    
            assertNotNull(pessoaBean.getPessoa());
            assertEquals("Teste", pessoaBean.getPessoa().getNome());
            assertEquals(30, pessoaBean.getPessoa().getIdade());
            assertEquals("M", pessoaBean.getPessoa().getSexo());
            
     }
    
    
}