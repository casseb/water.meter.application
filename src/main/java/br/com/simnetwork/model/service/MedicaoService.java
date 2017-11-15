package br.com.simnetwork.model.service;

import java.util.List;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Medicao;

public interface MedicaoService {

	public float litrosAtual(Caixa caixa, int medida);
	public float litrosAtual(Medicao medicao);
	public void medicaoControlada(Caixa caixa, int medida);
	public List<Medicao> buscaPorDia(int dia, int mes, int ano, Caixa caixa);
	
}
