package br.com.digitro.documentocrud.model.test;


import org.junit.*;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.junit.MockitoJUnitRunner;
import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.dao.impl.DocumentoDaoImpl;
import br.com.digitro.documentocrud.model.Documento;
import br.com.digitro.documentocrud.service.DocumentoService;
import br.com.digitro.documentocrud.service.impl.DocumentoServiceImpl;
import static junit.framework.Assert.*;


public class DocumentoServiceTest {
	private DocumentoDao dao;
	private DocumentoService service;
	

	@Before
	public void setUp() {
		service = new DocumentoServiceImpl();
		service.setDao(dao);
		Documento documento = new Documento();
	}
		
	@Test
	public void deveListarTodosDocumentosTest() {
		List<Documento> documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		documentos = service.getTodosDocumentos();
		assertEquals(8, documentos.size(), 0.01);
		
	}
	
	@Test
	public void deveListarDocumentoPorIdTest() {
		Documento documento = new Documento(1);
		Documento documentoRetorno = new Documento();
		service = new DocumentoServiceImpl();
		documentoRetorno = service.getDocumentoPorId(1);
		assertEquals(documento.getId(), documentoRetorno.getId());
	}
	
	@Test
	public void deveListarDocumentosPorFiltroDataInicialTest() {
		Documento documento = new Documento(0, null, null, "2019-02-26 10:17:48", null);
		List<Documento> documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		documentos = service.getDocumentosPorIntervaloData(documento);
		assertEquals(8d, documentos.size(), 0.01);
	}
	@Test
	public void deveListarDocumentosPorFiltroDataFimTest() {
		Documento documento = new Documento(0, null, null, null, "2019-02-26 13:54:52");
		List<Documento> documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		documentos = service.getDocumentosPorIntervaloData(documento);
		assertEquals(6d, documentos.size(), 0.01);
	}
	
	@Test
	public void deveListarDocumentosPorIntervaloDataTest() {
		Documento documento = new Documento(0, null, null, "2019-02-26 10:17:48", "2019-02-26 13:54:52");
		List<Documento> documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		documentos = service.getDocumentosPorIntervaloData(documento);
		assertEquals(8d, documentos.size(), 0.01);
		
	}
	@Test
	public void deveFiltarDocumentoPorTituloTest() {
		List<Documento> documentos = new ArrayList<>();
		Documento documento = new Documento(0, null, "texto", null, null);
		service = new DocumentoServiceImpl();
		documentos = service.getDocumentosPorTituloOuTexto(documento);
		assertEquals(8d, documentos.size(), 0.01);
				
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveGerarUmaExcecaoAoFiltrarPorTituloTest() {
		
	}
	
	@Test
	public void deveFiltrarDocumentoPorTextoTest() {
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveGerarUmaExcecaoAoFiltrarPorTextoTest() {
		
	}
	
	@Test
	public void deveFiltrarDocumentoPorIntervaloDataTest() {
		//List<Documento> documentos = service.getDocumentosPorIntervaloData();
		//assertNotNull(documentos);
	
}
	@Test(expected = IllegalArgumentException.class)
	public void deveGerarExcecaoAoFiltrarPorIntervaloDataTest() {
		 
	}

}