package br.com.digitro.documentocrud.model;



import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Documento {
	private int id;
	private String titulo;
	private String texto;
	private String dataInicio;



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
	public void setId(int id) {
		this.id = id;
	}
	public String getDataInicio() {
		return dataInicio;
	}
		public Documento() {
		// TODO Auto-generated constructor stub
	}
	public Documento(String titulo, String texto, String dataInicio) {
		this.titulo = titulo;
		this.texto = texto;
		this.dataInicio = dataInicio;
	}
	public Documento(String titulo, String texto) {
		this.titulo = titulo;
		this.texto = texto;	
	}

	public Documento(int id, String titulo, String texto) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
	}
}
