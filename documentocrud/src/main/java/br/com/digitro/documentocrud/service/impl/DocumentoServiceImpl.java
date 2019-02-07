package br.com.digitro.documentocrud.service.impl;

import java.util.Set;

import javax.management.RuntimeErrorException;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.dao.impl.DocumentoDaoImpl;
import br.com.digitro.documentocrud.model.Documento;
import br.com.digitro.documentocrud.service.DocumentoService;

public class DocumentoServiceImpl implements DocumentoService{
	
	private DocumentoDao dao = new DocumentoDaoImpl();
	



	public void deveVerificarRegrasDeTitulo(Documento documento) {
		deveVerificarCaracteresTitulo(documento);
		deveVerificarTamanhoMinimoTitulo(documento);
		deveVerificarTituloVazio(documento);

	}

	public boolean deveVerificarTituloVazio(Documento documento) throws RuntimeException {

		if(documento.getTitulo().isEmpty()) {
			throw new RuntimeException("Título obrigatório");
		}
		return false;
	}

	public void deveVerificarCaracteresTitulo(Documento documento) throws RuntimeException{
		char[] chars = documento.getTitulo().toCharArray();

		for (char c : chars) {
			if(!Character.isLetter(c) && !Character.isWhitespace(c)) {
				throw new RuntimeException("Utilize apenas letras sem acentos ou números.");
			}
		}


	}

	public boolean deveVerificarTamanhoMinimoTitulo(Documento documento){

		if(documento.getTitulo().length() < 5) {
			throw new RuntimeException("Título mínimo com 5 letras");
		}
		return false;
	}

	public boolean verificarIdNegativo(Integer id){
		if(id < 0) {
			throw new RuntimeException("Id não pode ser negativo");	
		}
		return false;
	}

	public Documento getDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Documento> getTodosDocumentos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Documento getDocumentoPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean insertDocumentoServico(Documento documento) {
		if(dao.insertDocumento(documento) == 1) {
			deveVerificarRegrasDeTitulo(documento);
			return true;
		}else {
			return false;
		}
		
		
	}

	public boolean updateDocumentoServico(Documento documento) {
		deveVerificarRegrasDeTitulo(documento);
		if(dao.updateDocumento(documento) == 1) {
			return true;
		}
		return false;
	}

	public boolean deleteDocumentoServico(int id) {
		verificarIdNegativo(id);
		if(dao.deleteDocumento(id) == 1) {
			return true;
		}
		return false;
	}
	
	public void setDao(DocumentoDao dao) {
		this.dao = dao;
	}
	
}
