package br.com.digitro.documentocrud.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.message.internal.Statuses;
import org.glassfish.jersey.server.internal.scanning.ResourceFinderException;

import br.com.digitro.documentocrud.exception.InvalidInputException;
import br.com.digitro.documentocrud.model.Documento;
import br.com.digitro.documentocrud.service.DocumentoService;
import br.com.digitro.documentocrud.service.impl.DocumentoServiceImpl;

@Path("/documentos")
public class DocumentoResource {
	private DocumentoService service;
	private Documento documento;
	private List<Documento> documentos;
//	@GET
//	@Produces("application/json")
//	public List<Documento> deveListarTodosDocumentos() throws Exception {
//		List<Documento> documentos = new ArrayList<>();
//		service = new DocumentoServiceImpl();
//		documentos = service.getTodosDocumentos();
//		return documentos;
//	}
	
@GET
@Produces("application/json")
public Response deveListarTodosDocumentos() throws RuntimeException{
	documentos = new ArrayList<>();
	service = new DocumentoServiceImpl();
	try {
		documentos = service.getTodosDocumentos();
		return Response.status(Response.Status.OK).entity(documentos).type("application/json").build();
	}catch(InvalidInputException e) {
		return Response.status(Status.BAD_REQUEST).entity(e.getErrors()).build();
	}
}
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("{id}")
	public Response deveListarDocumentosPorIdResponse(final @PathParam("id") int id) throws RuntimeException {
		Documento documento = new Documento(id);
		service = new DocumentoServiceImpl();
		try {
			documento = service.getDocumentoPorId(id);
			return Response.status(Response.Status.OK).entity(documento).type("application/json").build();

		}catch(InvalidInputException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getErrors()).build();
		}
		
		
//		return Response.ok(documento.toString(), MediaType.APPLICATION_JSON).build();
		
	}
	
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/titulo")
	public Response deveListarDocumentosPorTitulo(final @QueryParam("titulo") String titulo) throws RuntimeException{
		documento = new Documento();
		documento.setTitulo(titulo);
		documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		try {
			documentos = service.getDocumentosPorTituloOuTexto(documento);
			return Response.status(Response.Status.OK).entity(documentos).type("application/json").build();
		}catch(InvalidInputException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrors()).build();
		}
	}
	
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/texto")
	public Response deveListarDocumentosPorTexto(final @QueryParam("texto") String texto) throws RuntimeException{
		documento = new Documento();
		documento.setTexto(texto);
		documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		try {
			documentos = service.getDocumentosPorTituloOuTexto(documento);
			return Response.status(Response.Status.OK).entity(documentos).type("application/json").build();
		}catch(InvalidInputException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrors()).build();
		}
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/dataInicio")
	public Response deveListarDocumentosPorDataInicio(final @QueryParam("dataInicio") String dataCriacao) throws RuntimeException{
		documento = new Documento();
		documento.setDataCriacao(dataCriacao);;
		documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		try {
			documentos = service.getDocumentosPorIntervaloData(documento);
			return Response.status(Response.Status.OK).entity(documentos).type("application/json").build();
		}catch(InvalidInputException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrors()).build();
		}
	}
	
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/dataFim")
	public Response deveListarDocumentosPorDataFim(final @QueryParam("dataFim") String dataFim) throws RuntimeException{
		documento = new Documento();
		documento.setDataFim(dataFim);
		documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		try {
			documentos = service.getDocumentosPorData(documento);
			return Response.status(Response.Status.OK).entity(documentos).type("application/json").build();
		}catch(InvalidInputException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrors()).build();
		}
	}
	
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/intervaloData")
	public Response deveListarDocumentosPorIntervaloData(final @QueryParam("dataInicio") String dataCriacao, final @QueryParam("dataFim") String dataFim) throws RuntimeException{
		documento = new Documento();
		documento.setDataFim(dataFim);
		documento.setDataCriacao(dataCriacao);
		documentos = new ArrayList<>();
		service = new DocumentoServiceImpl();
		try {
			documentos = service.getDocumentosPorIntervaloData(documento);
			return Response.status(Response.Status.OK).entity(documentos).type("application/json").build();
		}catch(InvalidInputException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getErrors()).build();
		}
	}
}
