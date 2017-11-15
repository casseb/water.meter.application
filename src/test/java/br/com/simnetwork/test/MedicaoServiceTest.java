package br.com.simnetwork.test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

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
import br.com.simnetwork.model.repository.MedicaoRepository;
import br.com.simnetwork.model.service.CaixaService;
import br.com.simnetwork.model.service.CaixaServiceImpl;
import br.com.simnetwork.model.service.MedicaoService;
import br.com.simnetwork.model.service.MedicaoServiceImpl;
import br.com.simnetwork.model.service.UsuarioService;
import br.com.simnetwork.model.service.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration

public class MedicaoServiceTest {
	
	private static final String USUARIO = "RONALDO";
	private static final String SENHA = "123";
	
	@Autowired
	private CaixaService caixaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private MedicaoService medicaoService;
	@Autowired
	private MedicaoRepository medicaoRepo;
	
	@TestConfiguration
	static class MedicaoTestContextConfiguration {
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
	
	@Test
	@Transactional
	public void litrosAtualTest() {
		usuarioService.salvar(USUARIO, SENHA);
		caixaService.salvar(usuarioService.findByLogin(USUARIO));
		Caixa caixa = caixaService.findByUsuario(usuarioService.findByLogin(USUARIO)).get(0);
		caixaService.editarMedidas(100, 20, caixa);
		
		assertEquals("Conferindo medição",50,medicaoService.litrosAtual(caixa, 10),0.1);
		
	}

	@Test
	@Transactional
	public void medicaoControladaTest() {
		usuarioService.salvar(USUARIO, SENHA);
		caixaService.salvar(usuarioService.findByLogin(USUARIO));
		Caixa caixa = caixaService.findByUsuario(usuarioService.findByLogin(USUARIO)).get(0);
		
		medicaoService.medicaoControlada(caixa, 5);
		LocalDateTime dataAtual = LocalDateTime.now();
		assertFalse(medicaoService.buscaPorDia(dataAtual.getDayOfMonth(), dataAtual.getMonthValue(), dataAtual.getYear(),caixa).isEmpty());
	}
	
}
