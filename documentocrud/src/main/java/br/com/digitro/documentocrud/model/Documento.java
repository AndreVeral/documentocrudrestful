package br.com.digitro.documentocrud.model;

import java.util.Date;

public class Documento {
private int id;
private String titulo;
private String corpo;
private Date dataCriacao;

public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getTexto() {
	return corpo;
}
public void setTexto(String texto) {
	this.corpo = texto;
}
public int getId() {
	return id;
}
public Date getDataCriacao() {
	return dataCriacao;
}

public void setId(int id) {
	this.id = id;
}
public void setDataCriacao(Date dataCriacao) {
	this.dataCriacao = dataCriacao;
}
public Documento() {
	// TODO Auto-generated constructor stub
}

public Documento(int id, String titulo, String texto, Date data) {
	this.id = id;
	this.titulo = titulo;
	this.corpo = texto;
	this.dataCriacao = data;
}

public Documento(String titulo, String corpo) {
	this.titulo = titulo;
	this.corpo = corpo;
}
}
