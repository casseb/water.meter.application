package br.com.simnetwork.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.simnetwork.model.entity.Caixa;
import br.com.simnetwork.model.entity.Medicao;
import br.com.simnetwork.model.service.CaixaService;
import br.com.simnetwork.model.service.MedicaoService;

@RestController
@RequestMapping(value = "/medicao")
public class MedicaoController {

	@Autowired
	private MedicaoService medicaoService;
	@Autowired
	private CaixaService caixaService;

	private Map<String, Integer> medicoes = new HashMap<String, Integer>();

	@RequestMapping(value = "/insert/{idcaixa}/{medida}")
	public void insert(@PathVariable("idcaixa") String idcaixa, @PathVariable("medida") String medida) {
		medicoes.put(idcaixa, Integer.parseInt(medida));
		if (LocalDateTime.now().getMinute() == 0) {
			medicaoService.medicaoControlada(caixaService.findById(idcaixa), Integer.parseInt(medida));
		}
	}

	@RequestMapping(value = "/litros/{idcaixa}")
	public ResponseEntity<Float> litros(@PathVariable("idcaixa") String idcaixa) {
		Integer medida = medicoes.get(idcaixa);
		if (medida != null) {
			Float litros = medicaoService.litrosAtual(caixaService.findById(idcaixa), medida);
			return new ResponseEntity<Float>(litros, HttpStatus.OK);
		} else {
			return new ResponseEntity<Float>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/consulta/{idcaixa}/{data}")
	public ResponseEntity<Collection<Medicao>> consulta(@PathVariable("idcaixa") String idcaixa,
			@PathVariable("data") String data) {
		String[] dataLista = data.split("-");
		List<Medicao> medicoes = medicaoService.buscaPorDia(Integer.parseInt(dataLista[0]),
				Integer.parseInt(dataLista[1]), Integer.parseInt(dataLista[2]), caixaService.findById(idcaixa));
		if (medicoes.isEmpty()) {
			return new ResponseEntity<Collection<Medicao>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Collection<Medicao>>(medicoes, HttpStatus.OK);
		}
	}
}
