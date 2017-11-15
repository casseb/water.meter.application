package br.com.simnetwork.model.service;

import java.util.List;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Usuario;

public interface CaixaService {
	
	public void salvar(Usuario usuario);
	
	public boolean editarMedidas(float capacidade, int medida_max, Caixa caixa);
	
	public List<Caixa> findByUsuario(Usuario usuario);
	
	public Caixa findById(String id);

}
