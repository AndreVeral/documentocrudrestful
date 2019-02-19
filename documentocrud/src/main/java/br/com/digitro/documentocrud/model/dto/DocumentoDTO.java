package br.com.digitro.documentocrud.model.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class DocumentoDTO {
private int id;
private String titulo;
private String texto;
private String dataInicio;
private Date dataFim;

public Date getDataFim() {
	return dataFim;
}
public void setDataFim(Date dataFim) {
	this.dataFim = dataFim;
}
public void setDataInicio(String dataInicio) {
	this.dataInicio = dataInicio;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getTexto() {
	return texto;
}
public void setTexto(String texto) {
	this.texto = texto;
}
public int getId() {
	return id;
}
public String getDataInicio() {
	return dataInicio;
}
public void setId(int id) {
	this.id = id;
}
public DocumentoDTO() {
	// TODO Auto-generated constructor stub
}
public DocumentoDTO(String titulo, String texto, String dataInicio, Date dataFim) {
	this.titulo = titulo;
	this.texto = texto;
	this.dataInicio = dataInicio;
	this.dataFim = dataFim;

}

public DocumentoDTO(String titulo, String texto) {
	this.titulo = titulo;
	this.texto = texto;	
}
}
