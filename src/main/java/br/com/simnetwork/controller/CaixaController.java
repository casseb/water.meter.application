package br.com.simnetwork.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Usuario;
import br.com.simnetwork.model.service.CaixaService;
import br.com.simnetwork.model.service.UsuarioService;

@RestController
@RequestMapping(value = "/caixa")
public class CaixaController {

	@Autowired
	private CaixaService caixaService;
	@Autowired
	private UsuarioService usuarioService;

	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Caixa> save(@RequestBody Caixa caixa, HttpServletRequest request, HttpServletResponse response) {
		caixaService.salvar(usuarioService.findByLogin(caixa.getUsuario().getLogin()));
		return new ResponseEntity<Caixa>(caixa, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ResponseEntity<Caixa> editar(@RequestBody Caixa caixa, HttpServletRequest request, HttpServletResponse response) {
		caixa.setUsuario(usuarioService.findByLogin(caixa.getUsuario().getLogin()));
		caixaService.editarMedidas(caixa.getCapacidade(), caixa.getMedida_max(), caixa);
		return new ResponseEntity<Caixa>(caixa, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/findByUsuario")
	public ResponseEntity<Collection<Caixa>> findByUsuario(@RequestBody Usuario usuario){
		List<Caixa> caixas = caixaService.findByUsuario(usuarioService.findByLogin(usuario.getLogin()));
		if(caixas.isEmpty()) {
			return new ResponseEntity<Collection<Caixa>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Caixa>>(caixas,HttpStatus.OK);
		}
	}
	
}
