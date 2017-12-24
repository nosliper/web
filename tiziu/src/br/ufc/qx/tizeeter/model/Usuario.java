package br.ufc.qx.tizeeter.model;

import java.util.Date;

public class Usuario {

	private Long id;
	private String nomeDeUsuario;
	private String email;
	private String senha;

	private String nome;
	private String endereco;
	private Date dataDeNascimento;
	private char sexo;
	private boolean novidades;

	public String getNomeDeUsuario() {
		return nomeDeUsuario;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public boolean getNovidades() {
		return novidades;
	}

	public void setNomeDeUsuario(String nomeDeUsuario) {
		this.nomeDeUsuario = nomeDeUsuario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public void setNovidades(boolean novidades) {
		this.novidades = novidades;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}

}
