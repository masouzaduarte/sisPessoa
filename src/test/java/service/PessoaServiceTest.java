package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import sisPessoa.model.Pessoa;
import sisPessoa.repository.Pessoas;
import sisPessoa.service.PessoaService;

public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private Pessoas pessoas;

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
    	pessoa.setEnderecos(new ArrayList<>());
    	
        when(pessoas.salvar(any(Pessoa.class))).thenReturn(pessoa);
        
        Pessoa pessoaSalva = pessoaService.salvar(pessoa);

        assertNotNull(pessoaSalva);
        assertEquals("Teste", pessoaSalva.getNome());
        assertEquals(30, pessoaSalva.getIdade());
        assertEquals("M", pessoaSalva.getSexo());
    }
    
}
