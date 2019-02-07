package br.com.digitro.documentocrud.model.test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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
	public void testQuandoUmTituloValidoForPesquisadoNoDocumento() {
		Documento documentoMock = new Documento();
		when(documentoMock.getId()).thenReturn(7);
		verify(documentoMock).setTitulo("Titulo Mock");
		Assert.assertEquals(documentoMock.getTitulo(), "Titulo Mock");
	}
	
	@Test
	public void testQuandoDeletaDocumento() {
		
		when(dao.deleteDocumento(1)).thenReturn(1d);
		boolean deleteDocumentoServico = service.deleteDocumentoServico(1);
		Assert.assertTrue(deleteDocumentoServico);
		
		
	}
	
	@Test
	public void testQuandoCriaDocumento() {
		Documento documento = new Documento("Titulo documento 1", "Corpo do teste");
		when(dao.insertDocumento(documento)).thenReturn(1d);
		boolean criaDocumentoServico = service.insertDocumentoServico(documento);
		assertTrue(criaDocumentoServico);
	}
	
	
//	public void prepararMock() {
//		documentoDao = Mockito.mock(DocumentoDao.class);
//		Mockito.when(documentoDao.getDocumento()).thenReturner) invocation.getArguments()[(criarDocumentoMock());
//		
//		
//	}
//	private Documento criarDocumentoMock() {
//		Documento documento = new Documento();
//		documento.setTitulo("teste get");
//		return documento;
//	}
//
//	public void testaGetDocumento() {
//		Documento documento = documentoDao.getDocumento();
//		
//		assertEquals("teste get", documento.getTitulo());
//		
//	}
}
