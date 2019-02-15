package br.com.digitro.documentocrud.resource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import br.com.digitro.documentocrud.model.Documento;

@Path("documentos")
public class DocumentoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Documento> hello() throws Exception {
//		Pessoa pessoa = new Pessoa();
//		pessoa.setId(44325L);
//		pessoa.setNome("Joãozinho");
//		return Arrays.asList(pessoa);
		return null;
	}
	
}
