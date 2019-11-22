package com.rcm.diario.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Poste implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long codigo;
	
	@ManyToOne
	@JoinTable(name = "usuarios_postes", joinColumns = {@JoinColumn(name="poste_codigo")},
    inverseJoinColumns = {@JoinColumn(name="usuario_codigo")} )
	private Usuario usuario;
	
	@DateTimeFormat(pattern="dd-mm-yyyy 00:00")
	private String dataCriacao;
	
	@DateTimeFormat(pattern="dd-mm-yyyy 00:00")
	private String dataEdicao;
	
	private String tag;
	
	private String conteudo;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(String dataEdicao) {
		this.dataEdicao = dataEdicao;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
}
