package br.com.simnetwork.model.service;

import br.com.simnetwork.model.entity.Usuario;

public interface UsuarioService {
	
	public void salvar(String usuario, String senha);
	public Usuario findByLogin(String login);

}
