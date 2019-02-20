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
	private Documento documento;

	@Before
	public void setUp() {
		documentoDaoMock = mock(DocumentoDaoImpl.class, Mockito.CALLS_REAL_METHODS);
		documentoDao = new DocumentoDaoImpl();
		documentos = new ArrayList<Documento>();
		documento = new Documento();
		
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

	public Documento deveCriarDocumentoComCamposAleatorios() {
		documento.setTitulo(deveGerarTextoAleatorio());
		documento.setTexto(deveGerarTextoAleatorio());
		return documento;
	}
	
	public Documento deveCriarDocumentoComCamposNaoAleatorios() {
		String titulo = "titulo";
		String texto = "texto";
		Documento documentoNaoAleatorio = new Documento(titulo,texto);
		return documentoNaoAleatorio;
	}
	
	public Documento deveCriarDocumentoNulo() {
		String titulo = null;
		String texto = null;
		Documento documentoNulo = new Documento(titulo, texto);
		return documentoNulo;
	}

//	public Documento deveFalharDocumento() {
//		documento.setTitulo(null);
//		documento.setTexto(deveGerarTextoAleatorio());
//		return documento;
//	}

	@Test
	public void testDeveGravarUmDocumentoAleatorioNoBanco() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.insertDocumento(deveCriarDocumentoComCamposAleatorios());
		Assert.assertEquals("Saída", 1, d, 0.0001);
	}

	@Test
	public void testDeveGravarUmDocumentoNaoAleatorioNoBanco() {
		
		Documento documentoNaoAleatorio = deveCriarDocumentoComCamposNaoAleatorios();
		documentoDao.insertDocumento(documentoNaoAleatorio);
		for (Documento documento : documentos) {
			if (documento.getTitulo().equalsIgnoreCase("titulo") && documento.getTexto().equalsIgnoreCase("texto")) {
				assertEquals(documentoNaoAleatorio, documento);
			}
		}
		
	}
//	@Test(expected = IllegalArgumentException.class)
//	public void testGerarExcecaoAoGravarUmDocumentoNoBanco() {
//		documentoDao.insertDocumento(deveCriarDocumentoNulo());
//	}

	@Test
	public void testDeveDeletarUmDocumentoDoBanco() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.deleteDocumento(29);
		Assert.assertEquals(1, d, 0.0001);
	}

	@Test
	public void testDeveFalharDeletarUmDocumentoDoBanco() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.deleteDocumento(50);
		Assert.assertEquals(0, d, 0.0001);
	}

	@Test
	public void testdeveAtualizarUmDocumentoDoBanco() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.updateDocumento(deveAlterarDocumentoExistente());
		Assert.assertEquals(1, d, 0.0001);	
	}

	@Test
	public void testDeveFalharAtualizarUmDocumentoDoBanco() {
		//DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = documentoDao.updateDocumento(deveFalharAlterarDocumentoExistente());
		Assert.assertEquals(0, d, 0.0001);	
	}

	@Test
	public void testDeveRetornarUmDocumentoPorId() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		Documento d = daoTestImpl.getDocumentoPorId(9);
		Assert.assertNotNull(d.getClass());
	}

	@Test
	public void testDeveFalharRetornarUmDocumentoPorId() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		Documento d = daoTestImpl.getDocumentoPorId(100);
		//Assert.assertNotNull(d.getClass());
	}

	@Test
	public void testDeveRetornarTodosOsDocumentos() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		List<Documento> documentos = daoTestImpl.getTodosDocumentos();
		assertNotNull(documentos);

	}
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

