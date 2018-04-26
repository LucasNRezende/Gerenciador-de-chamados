package com.gerenciadorchamados.GerenciadorChamados.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "chamado")
public class Chamado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idchamado")
	private Integer id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "datachamado")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datachamado;

	@Column(name = "statuschamado")
	private String statuschamado;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "interacao")
	private String interacao;

	@Column(name = "chanexo")
	@Lob
	private byte[] chanexo;

	@Column(name = "tipochamado")
	private String tipochamado;

	@ManyToOne
	@JoinColumn(name = "idcliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "idsoftware")
	private Software software;
	
	@ManyToOne
	@JoinColumn (name = "idusuario")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDatachamado() {
		return datachamado;
	}

	public void setDatachamado(Date datachamado) {
		this.datachamado = datachamado;
	}

	public String getStatuschamado() {
		return statuschamado;
	}

	public void setStatuschamado(String statuschamado) {
		this.statuschamado = statuschamado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getInteracao() {
		return interacao;
	}

	public void setInteracao(String interacao) {
		this.interacao = interacao;
	}

	public byte[] getChanexo() {
		return chanexo;
	}

	public void setChanexo(byte[] chanexo) {
		this.chanexo = chanexo;
	}

	public String getTipochamado() {
		return tipochamado;
	}

	public void setTipochamado(String tipochamado) {
		this.tipochamado = tipochamado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

	
}
