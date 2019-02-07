package br.com.digitro.documentocrud.service;

import java.util.Set;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.model.Documento;

public interface DocumentoService {
	Documento getDocumento();
	Set<Documento> getTodosDocumentos();
	Documento getDocumentoPorId(int id);
	boolean insertDocumentoServico(Documento documento);
	boolean updateDocumentoServico(Documento documento);
	boolean deleteDocumentoServico(int id);
	void setDao(DocumentoDao dao);
}
