package br.com.digitro.documentocrud.model.test;


import org.junit.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
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
}
