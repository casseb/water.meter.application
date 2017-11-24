package br.com.simnetwork.model.service;

import java.util.List;

import br.com.simnetwork.model.entity.Usuario;

public interface UsuarioService {
	
	public void salvar(String usuario, String senha);
	public Usuario findByLogin(String login);
	public List<Usuario> getAllUsers();

}
