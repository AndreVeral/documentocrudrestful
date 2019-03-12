package test.main;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.dao.impl.DocumentoDaoImpl;
import br.com.digitro.documentocrud.model.Documento;
import br.com.digitro.documentocrud.service.DocumentoService;
import br.com.digitro.documentocrud.service.impl.DocumentoServiceImpl;

public class Main {
	//	private String deveGerarTextoAleatorio(){
	//
	//		byte[] array = new byte[20];
	//		new Random().nextBytes(array);
	//		String lorem = new String(array, Charset.forName("UTF-8"));
	//		return lorem;
	//	}

	private String deveGerarTextoAleatorio() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // numero de caracteres.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public Documento criarDocumentoCaracteresAleatorios() {
		Documento documento = new Documento();
		documento.setTitulo(deveGerarTextoAleatorio());
		documento.setTexto(deveGerarTextoAleatorio());
		return documento;
	}
	
	public void deveGravarDocumentoPadrao() {
		Documento documento = new Documento("titulo", "texto");
		DocumentoService service = new DocumentoServiceImpl();
		boolean resultado = service.insertDocumentoServico(documento);
		if (resultado) {
			System.out.println("Gravado");
		}else {
			System.out.println("Erro");
		}
	}
	
	private Documento deveAtualizarDocumentoExistente() {
		Documento documento = new Documento();
		documento.setId(2);
		documento.setTitulo("título alterado " + deveGerarTextoAleatorio());
		documento.setTexto("texto alterado " + deveGerarTextoAleatorio());
		return documento;
	}
	public void deveGravarUmDocumentoNoBanco() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = daoTestImpl.insertDocumento(criarDocumentoCaracteresAleatorios());
		if(d == 1) {
			System.out.println("Sucesso");
		}else {
			System.out.println("Erro");
		}
	}
	public void deveDeletarUmDocumentoNoBanco() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = daoTestImpl.deleteDocumento(0);
		if(d == 1) {
			System.out.println("Documento deletado");
		}else {
			System.out.println("Erro");
		}
	}

	public void deveAtualizarUmDocumentoNoBanco() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		double d = daoTestImpl.updateDocumento(deveAtualizarDocumentoExistente());
		if(d == 1) {
			System.out.println("Documento atualizado");
		}else {
			System.out.println("Erro. Atualização não realizada.");
		}
	}
	
	public void deveRetornarDocumentoPorId() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		Documento d = new Documento();
		d = daoTestImpl.getDocumentoPorId(3);
		System.out.println(d.getTexto());
	}
	
	public void deveRetornarTodosOsDocumentos() {
		DocumentoDaoImpl daoTestImpl = new DocumentoDaoImpl();
		List<Documento> documentos = daoTestImpl.getTodosDocumentos();
		for (Documento documento : documentos) {
			System.out.println(documento.getId());
		}
		
	}
	public static void main(String[] args) {
		Main m = new Main();
		m.deveGravarDocumentoPadrao();
	}
}
