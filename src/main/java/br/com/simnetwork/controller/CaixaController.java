package br.com.simnetwork.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Usuario;
import br.com.simnetwork.model.service.CaixaService;
import br.com.simnetwork.model.service.UsuarioService;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.impl.JwtMap;

@RestController
@RequestMapping(value = "/caixa")
public class CaixaController {

	@Autowired
	private CaixaService caixaService;
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<Caixa> save(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		caixaService.salvar(usuarioService.findByLogin(authentication.getName()));
		return new ResponseEntity<Caixa>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ResponseEntity<Caixa> editar(@RequestBody Caixa caixa, HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		caixa.setUsuario(usuarioService.findByLogin(authentication.getName()));
		caixaService.editarMedidas(caixa.getCapacidade(), caixa.getMedida_max(), caixa);
		return new ResponseEntity<Caixa>(caixa, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/findByUsuario")
	public ResponseEntity<Collection<Caixa>> findByUsuario(ServletRequest request){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<Caixa> caixas = caixaService.findByUsuario(usuarioService.findByLogin(authentication.getName()));
		if(caixas.isEmpty()) {
			return new ResponseEntity<Collection<Caixa>>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Collection<Caixa>>(caixas,HttpStatus.OK);
		}
	}
	
}
