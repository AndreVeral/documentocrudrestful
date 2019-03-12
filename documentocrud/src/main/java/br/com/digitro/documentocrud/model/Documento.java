package br.com.digitro.documentocrud.model;



import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Documento {
	private int id;
	private String titulo;
	private String texto;
	private String dataCriacao;
	private String dataFim;



	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
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
	public String getDataCriacao() {
		return dataCriacao;
	}
		public Documento() {
		// TODO Auto-generated constructor stub
	}
	public Documento(String titulo, String texto, String dataInicio) {
		this.titulo = titulo;
		this.texto = texto;
		this.dataCriacao = dataInicio;
	}
	public Documento(String titulo, String texto) {
		this.titulo = titulo;
		this.texto = texto;	
	}

	public Documento(int id) {
		this.id = id;
	}
	public Documento(int id, String titulo, String texto) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
	}
	public Documento(int id, String titulo, String texto, String dataInicio) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.dataCriacao = dataInicio;
	}
	public Documento(int id, String titulo, String texto, String dataInicio, String dataFim) {
		this.id = id;
		this.titulo = titulo;
		this.texto = texto;
		this.dataCriacao = dataInicio;
		this.dataFim = dataFim;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + id;
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (id != other.id)
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Documento [id=" + id + ", titulo=" + titulo + ", texto=" + texto + ", dataInicio=" + dataCriacao + "]";
	}
	public String getDataFim() {		
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
}
