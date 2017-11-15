package br.com.simnetwork.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Usuario;

public interface CaixaRepository  extends CrudRepository<Caixa, Integer>{

	public List<Caixa> findByUsuario(Usuario usuario);
	
}
