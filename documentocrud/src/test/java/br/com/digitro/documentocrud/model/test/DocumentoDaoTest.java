package br.com.digitro.documentocrud.model.test;

import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.print.Doc;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.dao.impl.DocumentoDaoImpl;
import br.com.digitro.documentocrud.model.Documento;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class DocumentoDaoTest {
	private DocumentoDao documentoDao;
	private DocumentoDao documentoDaoMock;
	private List<Documento> documentos;

	@Before
	public void setUp() {
		documentoDaoMock = mock(DocumentoDaoImpl.class, Mockito.CALLS_REAL_METHODS);
		documentoDao = new DocumentoDaoImpl();
		documentos = new ArrayList<Documento>();
	}

	private String deveGerarTextoAleatorio() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { 
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	public Documento deveAlterarDocumentoExistente() {
		Documento documento = new Documento();
		//DocumentoDaoTest daoTestImpl = new DocumentoDaoTest();
		documento.setId(2);
		documento.setTitulo("título alterado " + deveGerarTextoAleatorio());
		documento.setTexto("texto alterado " + deveGerarTextoAleatorio());
		return documento;

	}
	
	public Documento deveFalharAlterarDocumentoExistente() {
		Documento documento = new Documento();
		//DocumentoDaoTest daoTestImpl = new DocumentoDaoTest();
		documento.setId(100);
		documento.setTitulo("título alterado " + deveGerarTextoAleatorio());
		documento.setTexto("texto alterado " + deveGerarTextoAleatorio());
		return documento;

	}
	
	public Documento criarDocumento() {
		Documento documento = new Documento();
		documento.setTitulo(deveGerarTextoAleatorio());
		documento.setTexto(deveGerarTextoAleatorio());
		return documento;
	}
	
	public Documento FalharDocumento() {
		Documento documento = new Documento();
		documento.setTitulo(null);
		documento.setTexto(deveGerarTextoAleatorio());
		return documento;
	}

	@Test
	public void deveGravarUmDocumentoNoBancoTest() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.insertDocumento(criarDocumento());
		Assert.assertEquals("Saída", 1, d, 0.0001);
	}
	
	@Test
	public void deveFalharGravarUmDocumentoNoBancoTest() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.insertDocumento(FalharDocumento());
		assertEquals(0, d, 0.0001);
	}
	
	@Test
	public void deveDeletarUmDocumentoDoBancoTest() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.deleteDocumento(29);
		Assert.assertEquals(1, d, 0.0001);
	}
	
	@Test
	public void deveFalharDeletarUmDocumentoDoBancoTest() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.deleteDocumento(50);
		Assert.assertEquals(0, d, 0.0001);
	}

	@Test
	public void deveAtualizarUmDocumentoDoBancoTest() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.updateDocumento(deveAlterarDocumentoExistente());
		Assert.assertEquals(1, d, 0.0001);	
	}
	
	@Test
	public void deveFalharAtualizarUmDocumentoDoBancoTest() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.updateDocumento(deveFalharAlterarDocumentoExistente());
		Assert.assertEquals(0, d, 0.0001);	
	}

	@Test
	public void deveRetornarUmDocumentoPorIdTest() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		Documento d = daoTestImpl.getDocumentoPorId(9);
		Assert.assertNotNull(d.getClass());
	}
	
	@Test
	public void deveFalharRetornarUmDocumentoPorIdTest() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		Documento d = daoTestImpl.getDocumentoPorId(100);
		//Assert.assertNotNull(d.getClass());
	}
	
	@Test
	public void deveRetornarTodosOsDocumentosTest() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		List<Documento> documentos = daoTestImpl.getTodosDocumentos();
		assertNotNull(documentos);

	}
	
	@Test
	public void deveFiltarDocumentoPorTituloTest() {
		
	}
	
	@Test
	public void deveFiltrarDocumentoPorTextoTest() {
		
	}
	
	@Test
	public void deveFiltrarDocumentoPorIntervaloDataTest() {
		
	}
	
	
	
//	@Test(expected=RuntimeException.class)
//	public void deveFalharRetornarTodosOsDocumentos() throws SQLException {
//		when(documentoDaoMock.extrairDocumentoDeResultSet(any(ResultSet.class))).thenThrow(SQLException.class);
//		List<Documento> documentos = documentoDaoMock.getTodosDocumentos();
//		System.out.println(documentos);
//	}
	
//	@Test
//	public void deveRetornarTodosOsDocumentosMock() {
//		when(documentoDao.getTodosDocumentos()).thenReturn(documentos);
//		assertNotNull(documentos);
//	}
}
