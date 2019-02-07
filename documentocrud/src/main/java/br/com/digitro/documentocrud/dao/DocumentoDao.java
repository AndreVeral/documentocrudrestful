package br.com.digitro.documentocrud.dao;

import java.util.Set;

import br.com.digitro.documentocrud.model.Documento;

public interface DocumentoDao {
Set<Documento> getTodosDocumentos();
Documento getDocumentoPorId(int id);
double insertDocumento(Documento documento);
double updateDocumento(Documento documento);
double deleteDocumento(int id);
}