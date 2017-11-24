package br.com.simnetwork.model.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import br.com.simnetwork.model.entity.Usuario;
import br.com.simnetwork.model.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	@Transactional
	public void salvar(String usuario, String senha) {
		if(findByLogin(usuario) == null) {
			Usuario novoUsuario = new Usuario();
			novoUsuario.setLogin(usuario);
			novoUsuario.setSenha(senha);
			usuarioRepo.save(novoUsuario);
		}
	}

	@Override
	public Usuario findByLogin(String login) {
		return usuarioRepo.findByLogin(login);
	}
		
	
	public List<Usuario> getAllUsers(){
		return  Lists.newArrayList(usuarioRepo.findAll());
	}

}
