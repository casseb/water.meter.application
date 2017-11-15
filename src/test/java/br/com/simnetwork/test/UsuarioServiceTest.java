package br.com.simnetwork.test;


import static org.junit.Assert.assertNotNull;

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

public class UsuarioServiceTest {

	private static final String USUARIO = "RONALDO";
	private static final String SENHA = "123";
	
	@TestConfiguration
	static class UsuarioTestContextConfiguration {
		
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
	private UsuarioService usuarioService;
	
	
	@Test
	@Transactional
	public void testeSalvar() {
		usuarioService.salvar(USUARIO, SENHA);
		assertNotNull(usuarioService.findByLogin(USUARIO));
	}
	
}
