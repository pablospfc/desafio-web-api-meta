package br.com.restcontato.rest;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.restcontato.entidade.Contato;
import br.com.restcontato.exceptions.ContatoException;
import br.com.restcontato.service.ContatoService;


@Path("/contato")
public class ContatoRest {
	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private ContatoService contatoService;

	@PostConstruct
	private void init() {
		contatoService = new ContatoService();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Contato> listarContatos(@QueryParam("size") Integer size,
			@QueryParam("page") Integer page) {
		List<Contato> lista = null;
		try {
			lista = contatoService.getAll(page, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@GET
	@Path("{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Response buscarPorId(@PathParam("id") int id) throws ContatoException {
		Contato contato = null;
		try {
			contato = contatoService.getById(id);
			if (contato == null )
				return Response.status(Response.Status.NOT_FOUND).entity("Contato não encontrado").build();
					
			return Response.status(Response.Status.OK).entity(contato).build();
			
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
		
	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionarContato(Contato contato) throws ContatoException {
		try {
			return Response.status(Response.Status.CREATED).entity(contatoService.create(contato)).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON)
	public Response editarContato(Contato contato, @PathParam("id") Integer id) throws ContatoException {

		Contato c = contatoService.getById(id);
		
		if (c == null) 
			return Response.status(Response.Status.NOT_FOUND).entity("Contato não encontrado").build();
		
		try {
			return Response.status(Response.Status.OK).entity(contatoService.update(contato, id)).build();
	
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removerContato(@PathParam("id") Integer id) throws ContatoException {
		Contato c = contatoService.getById(id);

		if(c == null)
			return Response.status(Response.Status.NOT_FOUND).entity("Contato não encontrado").build();
		
		try {
			contatoService.remove(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception ex) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
	}

}
