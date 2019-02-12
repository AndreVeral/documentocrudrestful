package br.com.digitro.documentocrud.model.test;

import java.nio.charset.Charset;
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
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class DocumentoDaoTest {
	private DocumentoDao documentoDao;
	private List<Documento> documentos;

	@Before
	public void setUp() {
		//documentoDao = mock(DocumentoDaoImpl.class);
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

	private Documento deveAtualizarDocumentoExistente() {
		Documento documento = new Documento();
		DocumentoDaoTest daoTestImpl = new DocumentoDaoTest();
		documento.setId(2);
		documento.setTitulo("título alterado " + deveGerarTextoAleatorio());
		documento.setTexto("texto alterado " + deveGerarTextoAleatorio());
		return documento;
		
	}
	private Documento criarDocumento() {
		Documento documento = new Documento();
		documento.setTitulo(deveGerarTextoAleatorio());
		documento.setTexto(deveGerarTextoAleatorio());
		return documento;
	}

	@Test
	public void deveGravarUmDocumentoNoBanco() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = daoTestImpl.insertDocumento(criarDocumento());
		Assert.assertEquals("Saída", 1, d, 0.0001);


	}
	@Test
	public void deveDeletarUmDocumentoDoBanco() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = daoTestImpl.deleteDocumento(5);
		Assert.assertEquals(0, d, 0.0001);
	}

	@Test
	public void deveAtualizarUmDocumentoDoBanco() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = daoTestImpl.updateDocumento(deveAtualizarDocumentoExistente());
		Assert.assertEquals(1, d, 0.0001);	
	}

	@Test
	public void deveRetornarUmDocumentoPorId() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		Documento d = daoTestImpl.getDocumentoPorId(9);
		Assert.assertNotNull(d.getClass());
	}
	@Test
	public void deveRetornarTodosOsDocumentos() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		List<Documento> documentos = daoTestImpl.getTodosDocumentos();
		assertNotNull(documentos);

	}
//	@Test
//	public void deveRetornarTodosOsDocumentosMock() {
//		when(documentoDao.getTodosDocumentos()).thenReturn(documentos);
//		assertNotNull(documentos);
//	}
}
