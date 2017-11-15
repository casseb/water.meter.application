package br.com.simnetwork.model.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Medicao;
import br.com.simnetwork.model.repository.MedicaoRepository;

@Service("medicaoService")
public class MedicaoServiceImpl implements MedicaoService{

	@Autowired
	private MedicaoRepository medicaoRepo;
	
	/**
	 * Retorna quantos litros esta atualmente na caixa dágua
	 * @param caixa
	 * @param medida
	 * @return
	 */
	public float litrosAtual(Caixa caixa, int medida) {
		int medidaAtual = caixa.getMedida_max()-medida;
		return medidaAtual*caixa.litrosPorMedida();
	}
	
	public float litrosAtual(Medicao medicao) {
		Caixa caixa = medicao.getCaixa();
		int medida = medicao.getMedida();
		return litrosAtual(caixa,medida);
	}
	
	@Transactional
	public void medicaoControlada(Caixa caixa, int medida) {
		Medicao medicao = new Medicao();
		medicao.setDataHora(Timestamp.valueOf(LocalDateTime.now()));
		medicao.setCaixa(caixa);
		medicao.setMedida(medida);
		medicaoRepo.save(medicao);
	}
	
	public List<Medicao> buscaPorDia(int dia, int mes, int ano, Caixa caixa){
		LocalDate data = LocalDate.parse(ano+"-"+mes+"-"+dia);
		return medicaoRepo.findByCaixaAndDataHoraBetween(caixa, Timestamp.valueOf(data.atStartOfDay()), Timestamp.valueOf(data.atStartOfDay().plusDays(1)));
	}
	
}
