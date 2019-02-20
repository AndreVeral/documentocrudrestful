package br.com.digitro.documentocrud.service.impl;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.management.RuntimeErrorException;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.dao.impl.DocumentoDaoImpl;
import br.com.digitro.documentocrud.model.Documento;
import br.com.digitro.documentocrud.service.DocumentoService;

public class DocumentoServiceImpl implements DocumentoService{

	private DocumentoDao dao = new DocumentoDaoImpl();
	private Documento documento =  new Documento();


	public void setDao(DocumentoDao dao) {
		this.dao = dao;
	}
	public void deveVerificarRegrasDeTitulo(Documento documento) {
		deveVerificarCaracteresTitulo(documento);
		deveVerificarTamanhoMinimoTitulo(documento);
		deveVerificarTituloVazio(documento);
		deveVerificarTituloNulo(documento);
	}
	public boolean deveVerificarTituloVazio(Documento documento) throws RuntimeException {
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
	public boolean deveVerificarIdNegativo(Integer id){
		if(id < 0) {
			throw new RuntimeException("Id não pode ser negativo");	
		}
		return false;
	}
	public boolean deveVerificarTituloNulo(Documento documento) {
		if (documento.getTitulo() == null) {
			throw new RuntimeException("Erro: Titulo não pode ser nulo.");
		}
			return false;
	}
	public boolean deveVerificarTextoNulo(Documento documento) {
		if (documento.getTexto() == null) {
			throw new RuntimeException("Erro: Titulo não pode ser nulo.");
		}
		return false;
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

		return null;
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
