package br.com.digitro.documentocrud.service;

import java.util.List;
import java.util.Set;

import br.com.digitro.documentocrud.dao.DocumentoDao;
import br.com.digitro.documentocrud.model.Documento;

public interface DocumentoService {
	
	List<Documento> getTodosDocumentos();
	Documento getDocumentoPorId(int id);
	List<Documento> getDocumentosPorIntervaloData(Documento documento);
	List<Documento> getDocumentosPorData(Documento documento);
	List<Documento> getDocumentosPorTituloOuTexto(Documento documento);
	boolean insertDocumentoServico(Documento documento);
	boolean updateDocumentoServico(Documento documento);
	boolean deleteDocumentoServico(int id);
	void setDao(DocumentoDao dao);
}
