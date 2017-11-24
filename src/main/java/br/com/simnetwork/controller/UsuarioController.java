package br.com.simnetwork.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.simnetwork.model.entity.Usuario;
import br.com.simnetwork.model.service.UsuarioService;
import br.com.simnetwork.view.View;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@RequestMapping(value = "/usuario/save", method = RequestMethod.POST)
	@JsonView(View.All.class)
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario, HttpServletRequest request, HttpServletResponse response) {
		Usuario novoUsuario =  usuarioService.findByLogin(usuario.getLogin());
		if(novoUsuario==null) {
			usuarioService.salvar(usuario.getLogin(), usuario.getSenha());
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.ALREADY_REPORTED);
		}
	}
	
	@RequestMapping(value = "/login")
	@JsonView(View.noPrivateInformations.class)
	public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
		Usuario usuarioTarget = usuarioService.findByLogin(usuario.getLogin());
		if(usuarioTarget != null && usuarioTarget.getSenha().equals(usuario.getSenha())) {
			
			return new ResponseEntity<Usuario>(usuarioTarget, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
}
