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
@Table(name = "atendimento")
public class Atendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idatendimento")
	private Integer id;

	@Column(name = "dataatendimento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataatendimento;

	@Column(name = "assunto")
	private String assunto;

	@Column(name = "registro")
	private String registro;

	@Column(name = "atanexo")
	@Lob
	private byte[] atanexo;

	@Column(name = "tipoatendimento")
	private String tipoatendimento;

	@ManyToOne
	@JoinColumn(name = "idcliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "idsoftware")
	private Software software;

	@ManyToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Date getDataatendimento() {
		return dataatendimento;
	}

	public void setDataatendimento(Date dataatendimento) {
		this.dataatendimento = dataatendimento;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public byte[] getAtanexo() {
		return atanexo;
	}

	public void setAtanexo(byte[] atanexo) {
		this.atanexo = atanexo;
	}

	

	public String getTipoatendimento() {
		return tipoatendimento;
	}

	public void setTipoatendimento(String tipoatendimento) {
		this.tipoatendimento = tipoatendimento;
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
