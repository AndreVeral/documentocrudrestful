package br.com.digitro.documentocrud.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import br.com.digitro.documentocrud.model.Documento;
import br.com.digitro.documentocrud.service.DocumentoService;
import br.com.digitro.documentocrud.service.impl.DocumentoServiceImpl;

@Path("/documentos")
public class DocumentoResource {
	private DocumentoService documentoService;
	@GET
	@Produces("application/json")
	public List<Documento> deveListarTodosDocumentos() throws Exception {
		List<Documento> documentos = new ArrayList<>();
		documentoService = new DocumentoServiceImpl();
		documentos = documentoService.getTodosDocumentos();
		return documentos;
	}

}
