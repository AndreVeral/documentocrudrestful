package br.com.digitro.documentocrud.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import br.com.digitro.documentocrud.model.Documento;

public interface DocumentoDao {
List<Documento> getTodosDocumentos();
Documento getDocumentoPorId(int id);
List<Documento> getDocumentosPorFiltroTituloTexto(Documento documento);
List<Documento> getDocumentosPorData(Documento documento);
List<Documento> getDocumentosPorIntervaloData(Documento documento);
double insertDocumento(Documento documento);
double updateDocumento(Documento documento);
double deleteDocumento(int id);
boolean limparBanco();
boolean criaBanco();
boolean zerarId();
}
