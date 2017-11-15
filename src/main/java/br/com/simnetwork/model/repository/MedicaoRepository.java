package br.com.simnetwork.model.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Medicao;

public interface MedicaoRepository extends CrudRepository<Medicao, Integer>{

	public List<Medicao> findByCaixaAndDataHoraBetween(Caixa caixa, Timestamp dataInicial, Timestamp dataFinal);
	
}
