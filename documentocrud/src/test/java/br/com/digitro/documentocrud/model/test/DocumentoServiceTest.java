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

@RunWith(MockitoJUnitRunner.class)
public class DocumentoServiceTest {
	private DocumentoDao dao;
	private DocumentoService service;
	

	@Before
	public void setUp() {
		service = new DocumentoServiceImpl();
		dao = mock(DocumentoDaoImpl.class);
		service.setDao(dao);
		Documento documento = new Documento();
	}
	
	public Documento deveCriarDocumentoNulo() {
		String titulo = null;
		String texto = null;
		Documento documentoNulo = new Documento(titulo, texto);
		return documentoNulo;
	}
	
	@Test
	public void testQuandoDeletaDocumentoComIdNegativo() {
		
		//when(dao.deleteDocumento(-1)).thenReturn(1d);
		boolean deleteDocumentoServico = service.deleteDocumentoServico(-1);
		Assert.assertTrue(deleteDocumentoServico);
	}

	@Test
	public void testQuandoDeletaDocumento() {
		
		when(dao.deleteDocumento(1)).thenReturn(1d);
		boolean deleteDocumentoServico = service.deleteDocumentoServico(1);
		Assert.assertTrue(deleteDocumentoServico);
	}
	
	@Test
	public void testQuandoCriaDocumento() {
		Documento documento = new Documento("Titulo documento", "Corpo do teste");
		when(dao.insertDocumento(documento)).thenReturn(1d);
		boolean criaDocumentoServico = service.insertDocumentoServico(documento);
		assertTrue(criaDocumentoServico);
	}
	
	@Test
	public void testQuandoCriaDocumentoTituloMenorQue5Caracteres() {
		Documento documento = new Documento("Titu", "Corpo do teste");
		when(dao.insertDocumento(documento)).thenReturn(1d);
		//boolean criaDocumentoServico = service.insertDocumentoServico(documento);
		//assertTrue(criaDocumentoServico);
	}
	
	@Test
	public void testQuandoCriaDocumentoTituloVazio() {
		Documento documento = new Documento(null, "Corpo do teste");
		when(dao.insertDocumento(documento)).thenReturn(1d);
		//boolean criaDocumentoServico = service.insertDocumentoServico(documento);
		//assertTrue(criaDocumentoServico);
	}
	@Test
	public void testDeveListarTodosDocumentosRest() {
		List<Documento> documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		documentos = service.getTodosDocumentos();
		assertNotNull("Objeto nulo", documentos);;
		
	}
	@Test
	public void testDeveFiltarDocumentoPorTitulo() {
		Documento documento = new Documento();
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeveGerarUmaExcecaoAoFiltrarPorTitulo() {
		
	}
	
	@Test
	public void testDeveFiltrarDocumentoPorTextoTest() {
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeveGerarUmaExcecaoAoFiltrarPorTexto() {
		
	}
	
	@Test
	public void testDeveFiltrarDocumentoPorIntervaloDataTest() {
		//List<Documento> documentos = service.getDocumentosPorIntervaloData();
		//assertNotNull(documentos);
	
}
	@Test(expected = IllegalArgumentException.class)
	public void testDeveGerarExcecaoAoFiltrarPorIntervaloData() {
		 
	}

}