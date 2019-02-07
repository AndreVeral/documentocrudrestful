package br.com.digitro.documentocrud.model.test;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.Set;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.dao.impl.DocumentoDaoImpl;
import br.com.digitro.documentocrud.model.Documento;
import org.junit.*;
import static org.junit.Assert.*;

public class DocumentoDaoTest {
	private DocumentoDao documentoDao;
	private DocumentoDaoImpl documentoDaoImpl;

	private String deveGerarTextoAleatorio() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // numero de caracteres gerados.
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
		double d = daoTestImpl.deleteDocumento(4);
		Assert.assertEquals(1, d, 0.0001);
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
		Documento d = daoTestImpl.getDocumentoPorId(3);
		Assert.assertNotNull(d.getClass());
	}
	@Test
	public void deveRetornarTodosOsDocumentos() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		Set<Documento> documentos = daoTestImpl.getTodosDocumentos();
		assertNotNull(documentos);
		
	}

}
