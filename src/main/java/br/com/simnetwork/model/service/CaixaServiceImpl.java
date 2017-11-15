package br.com.simnetwork.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Usuario;
import br.com.simnetwork.model.repository.CaixaRepository;

@Service("caixaService")
public class CaixaServiceImpl implements CaixaService {

	@Autowired
	private CaixaRepository caixaRepo;

	@Transactional
	public void salvar(Usuario usuario) {
		Caixa caixa = new Caixa();
		caixa.setUsuario(usuario);
		caixa.setMedida_max(0);
		caixa.setCapacidade(0);
		caixaRepo.save(caixa);
	}

	/**
	 * 
	 * @param capacidade
	 * @param medida_max
	 * @param caixa
	 * @return true para a primeira medição, false para as demais
	 */
	@Transactional
	public boolean editarMedidas(float capacidade, int medida_max, Caixa caixa) {

		boolean primeira = true;

		if (caixa.getMedida_max() != 0) {
			primeira = false;
		}

		caixa.setMedida_max(medida_max);
		caixa.setCapacidade(capacidade);
		caixaRepo.save(caixa);

		return primeira;

	}
	
	public List<Caixa> findByUsuario(Usuario usuario){
		return caixaRepo.findByUsuario(usuario);
	}

	@Override
	public Caixa findById(String id) {
		return caixaRepo.findOne(Integer.parseInt(id));
	}
	
}
