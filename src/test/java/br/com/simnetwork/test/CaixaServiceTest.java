package br.com.simnetwork.test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Usuario;
import br.com.simnetwork.model.service.CaixaService;
import br.com.simnetwork.model.service.CaixaServiceImpl;
import br.com.simnetwork.model.service.MedicaoService;
import br.com.simnetwork.model.service.MedicaoServiceImpl;
import br.com.simnetwork.model.service.UsuarioService;
import br.com.simnetwork.model.service.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class CaixaServiceTest {

	private static final String USUARIO = "RONALDO";
	private static final String SENHA = "123";
	
	
	@TestConfiguration
	static class CaixaTestContextConfiguration {
		@Bean
		public CaixaService caixaService() {
			return new CaixaServiceImpl();
		}
		
		@Bean
		public MedicaoService medicaoService() {
			return new MedicaoServiceImpl();
		}
		
		@Bean
		public UsuarioService usuarioService() {
			return new UsuarioServiceImpl();
		}
	}
	
	
	@Autowired
	private CaixaService caixaService;
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	@Test
	@Transactional
	public void testeSalvar() {
		usuarioService.salvar(USUARIO, SENHA);
		caixaService.salvar(usuarioService.findByLogin(USUARIO));
		assertNotNull(caixaService.findByUsuario(usuarioService.findByLogin(USUARIO)));
	}
	
	@Test
	@Transactional
	public void testeEditarMedidas() {
		usuarioService.salvar(USUARIO, SENHA);
		caixaService.salvar(usuarioService.findByLogin(USUARIO));
		Caixa caixa = caixaService.findByUsuario(usuarioService.findByLogin(USUARIO)).get(0);
		boolean primeira = caixaService.editarMedidas(100, 20, caixa);
		assertEquals("Conferindo Capacidade da primeira medição",100,caixa.getCapacidade(),0);
		assertTrue("Conferindo retorno da primeira medição",primeira);
		primeira = caixaService.editarMedidas(150, 20, caixa);
		assertEquals("Conferindo Capacidade da segunda medição",150,caixa.getCapacidade(),0);
		assertFalse("Conferindo retorno da segunda medição",primeira);
	}
	
}
