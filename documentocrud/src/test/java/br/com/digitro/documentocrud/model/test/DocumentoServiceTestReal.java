package br.com.digitro.documentocrud.model.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.junit.Before;
import org.junit.Test;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.dao.impl.DocumentoDaoImpl;
import br.com.digitro.documentocrud.model.Documento;
import br.com.digitro.documentocrud.service.DocumentoService;
import br.com.digitro.documentocrud.service.impl.DocumentoServiceImpl;

public class DocumentoServiceTestReal {
	private DocumentoDao dao;
	private DocumentoService service;
	private List<Documento> documentos;
	private Documento documento;

	@Before
	public void setUp() {
		service = new DocumentoServiceImpl();
		dao = new DocumentoDaoImpl();
		documentos = new ArrayList<>();
		documento = new Documento();
	}
	
	public Documento deveCriarDocumentoNulo() {
		String titulo = null;
		String texto = null;
		Documento documentoNulo = new Documento(titulo, texto);
		return documentoNulo;
	}
		
	
	
	@Test(expected = WebServiceException.class)
	public void testDeveGerarExcecaoAoPesquisarDataInicialComFormatoInvalido() {
		
	}
	@Test(expected = WebServiceException.class)
	public void testDeveGerarExcecaoAoPesquisarDataFimComFormatoInvalido() {
		
	}
	@Test(expected = WebServiceException.class)
	public void testDeveGerarExcecaoAoPesquisarPeriodoComFormatoInvalido() {
		
	}
	@Test(expected = WebServiceException.class)
	public void testDeveGerarExcecaoAoPesquisarTituloMaiorQue250Caracteres() {
		
	}
	@Test(expected = WebServiceException.class)
	public void testDeveGerarExcecaoAoPesquisarTextoMaiorQue500Caracteres() {
		
	}
}
