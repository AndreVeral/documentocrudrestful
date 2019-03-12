package br.com.digitro.documentocrud.service.impl;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.activity.InvalidActivityException;
import javax.management.RuntimeErrorException;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.dao.impl.DocumentoDaoImpl;
import br.com.digitro.documentocrud.exception.ErrorInfo;
import br.com.digitro.documentocrud.exception.InvalidInputException;
import br.com.digitro.documentocrud.model.Documento;
import br.com.digitro.documentocrud.service.DocumentoService;

public class DocumentoServiceImpl implements DocumentoService{

	private DocumentoDao dao = new DocumentoDaoImpl();
	private Documento documento =  new Documento();
	private List<ErrorInfo> erros = new ArrayList<>();
	private ErrorInfo erro;


	public void setDao(DocumentoDao dao) {
		this.dao = dao;
	}
	public void deveVerificarRegrasDeTitulo(Documento documento){
		deveVerificarTamanhoMaximoTitulo(documento);
		deveVerificarTituloNulo(documento);
	}
	public void deveVerificarRegrasDeTexto(Documento documento) {
		deveVerificarTamanhoMaximoTexto(documento);
	}
	public boolean deveVerificarTituloVazio(Documento documento) {
		if(documento.getTitulo().isEmpty()) {
			throw new RuntimeException("Título obrigatório");
		}
		return false;
	}
	public void deveVerificarCaracteresTitulo(Documento documento) throws RuntimeException{
		char[] chars = documento.getTitulo().toCharArray();

		for (char c : chars) {
			if(!Character.isLetter(c) && !Character.isWhitespace(c)) {
				throw new RuntimeException("Utilize apenas letras sem acentos ou números.");
			}
		}
	}
	public boolean deveVerificarTamanhoMinimoTitulo(Documento documento){
		if(documento.getTitulo().length() < 5) {
			throw new RuntimeException("Título mínimo com 5 letras");
		}
		return false;
	}
	public boolean deveVerificarTamanhoMaximoTitulo(Documento documento) {
		if (documento.getTitulo().length() >= 250) {
			throw new RuntimeException("Título do documento deve ter no máximo 250 caracteres");
		}
		return false;
	}
	public boolean deveVerificarTamanhoMaximoTexto(Documento documento) {
		if (documento.getTexto().length() >= 500) {
			throw new RuntimeException("Texto do documento deve ter no máximo 500 caracteres");
		}
		return false;
	}
	public boolean deveVerificarFormatoData(Documento documento) {
		String formatoPadraoData = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formataData = DateTimeFormatter.ofPattern(formatoPadraoData);
		try {
			LocalDateTime.parse(documento.getDataCriacao(), formataData);
			throw(new Exception("Data Inicial possui um formato inválido."));
		}catch(Exception e) {
			e.getMessage();
		}
		return true;
	}
	public boolean deveVerificarIdNegativo(Integer id){
		if(id < 0) {
			throw new RuntimeException("Id não pode ser negativo");	
		}
		return false;
	}
	public boolean deveVerificarTituloNulo(Documento documento) {
		if (documento.getTitulo() == null) {
			erro = new ErrorInfo("documento.titulo.formato.invalido", "Título está nulo.");
			erros = new ArrayList<>();
			erros.add(erro);
			throw new InvalidInputException(erros);
		}
			return false;
	}
	public boolean deveVerificarTextoNulo(Documento documento) {
		if (documento.getTexto() == null) {
			throw new RuntimeException("Erro: Titulo não pode ser nulo.");
		}
		return false;
	}
	
	public void deveVerificarIdValido(Documento documento) {
		if (documento == null) {
			erro = new ErrorInfo("documento.id.inexistente", "Documento não encontrado.");
			erros = new ArrayList<>();
			erros.add(erro);
			throw new InvalidInputException(erros);
		}
	}

	//	public void deveFormatarData (Documento documento) {
	//		String dataAntiga = documento.getDataInicio();
	//		LocalDateTime dataFormatada = LocalDateTime.parse(dataAntiga, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	//		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//		String dataFormatadaString = formatter.format(dataFormatada);
	//		documento.setDataInicio(dataFormatadaString);
	//	}

	public List<Documento> getTodosDocumentos() {
		List<Documento> documentos = new ArrayList<Documento>();
		if(documentos.isEmpty()) {
			documentos = dao.getTodosDocumentos();
			return documentos;
		}else {
			throw new RuntimeException("Documentos não encontrados.");
		}
	}
	public Documento getDocumentoPorId(int id) {
		Documento documento =  new Documento();
		documento = dao.getDocumentoPorId(id);
		deveVerificarIdValido(documento);
		return documento;
	}
	
	public List<Documento> getDocumentosPorData(Documento documento){
		deveVerificarFormatoData(documento);
		List<Documento> documentos = new ArrayList<>();
		documentos = dao.getDocumentosPorData(documento);
		return documentos;
	}
	public List<Documento> getDocumentosPorIntervaloData(Documento documento){
		deveVerificarFormatoData(documento);
		List<Documento> documentos = new ArrayList<>();
		documentos = dao.getDocumentosPorIntervaloData(documento);
		return documentos;
		
	}
	public List<Documento> getDocumentosPorTituloOuTexto(Documento documento){
		if (documento.getTitulo() != null) {
			deveVerificarRegrasDeTitulo(documento);
		}if(documento.getTexto() != null) {
			deveVerificarRegrasDeTexto(documento);
		}
//		else {
//			deveVerificarRegrasDeTitulo(documento);
//			deveVerificarRegrasDeTexto(documento);
//		}
		List<Documento> documentos = new ArrayList<>();
		documentos = dao.getDocumentosPorFiltroTituloTexto(documento);
		return documentos;
	}
	public boolean insertDocumentoServico(Documento documento) {
		if(dao.insertDocumento(documento) == 1) {
			deveVerificarRegrasDeTitulo(documento);
			return true;
		}else {
			return false;
		}
	}
	public boolean updateDocumentoServico(Documento documento) {
		deveVerificarRegrasDeTitulo(documento);
		if(dao.updateDocumento(documento) == 1) {
			return true;
		}
		return false;
	}
	public boolean deleteDocumentoServico(int id) {
		deveVerificarIdNegativo(id);
		if(dao.deleteDocumento(id) == 1) {
			return true;
		}
		return false;
	}
	public Documento getDocumentosPorIntervaloData() {

		return null;
	}
	

}
