package br.com.simnetwork.model.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "Medicao")
public class Medicao {

	@Id
	@Column(name = "seq_medicao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "data_hora")
	private Timestamp dataHora;
	
	@Column(name = "medida")
	private int medida;
	
	@ManyToOne
	@JoinColumn(name = "Caixa_Dagua_seq_caixa")
	private Caixa caixa;

	public Medicao() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	


	public Timestamp getDataHora() {
		return dataHora;
	}

	public void setDataHora(Timestamp dataHora) {
		this.dataHora = dataHora;
	}

	public int getMedida() {
		return medida;
	}

	public void setMedida(int medida) {
		this.medida = medida;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}
	
	
}
