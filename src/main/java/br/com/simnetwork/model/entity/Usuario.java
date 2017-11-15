package br.com.simnetwork.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.simnetwork.view.View;


@Entity(name = "Usuario")
public class Usuario {

	@Id
	@Column(name = "seq_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.noPrivateInformations.class)
	private int id;
	
	@Column(name = "login", length = 45)
	@JsonView(View.noPrivateInformations.class)
	private String login;
	
	@Column(name = "senha", length = 45)
	@JsonView(View.All.class)
	private String senha;
	

	public Usuario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
}
