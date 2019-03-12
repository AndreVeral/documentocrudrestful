package br.com.digitro.documentocrud.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "codigo", "mensagem" })
public class ErrorInfo {

    private String codigo;

    private String mensagem;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

    public ErrorInfo(String codigo, String mensagem) {
    	this.codigo = codigo;
    	this.mensagem = mensagem;
    }

}
