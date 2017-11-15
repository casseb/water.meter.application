package br.com.simnetwork.model.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.simnetwork.model.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	public Usuario findByLogin(String login);
	
}
