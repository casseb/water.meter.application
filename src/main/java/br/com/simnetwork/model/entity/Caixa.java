package br.com.simnetwork.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "Caixa_Dagua")
public class Caixa {
	
	@Id
	@Column(name = "seq_caixa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "seq_usuario")
	private Usuario usuario;
	
	@Column(name = "capacidade")
	private float capacidade;
	
	@Column(name = "medida_max")
	private int medida_max;

	public Caixa() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public float getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(float capacidade) {
		this.capacidade = capacidade;
	}

	public int getMedida_max() {
		return medida_max;
	}

	public void setMedida_max(int medida_max) {
		this.medida_max = medida_max;
	}
	
	public float litrosPorMedida() {
		return capacidade/medida_max;
	}

}
