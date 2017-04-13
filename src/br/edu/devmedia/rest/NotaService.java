package br.edu.devmedia.rest;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.devmedia.dao.NotaDAO;
import br.edu.devmedia.model.Nota;

/**
 * Contém os endpoints básicos para operações de inserção, exclusão, atualização e recuperação de notas
 * @author Allan
 *
 */

@Path("/notas") //padrão de url dos serviços dessa classe
public class NotaService {
	
	/**
	 * Objeto DAO para gerenciar o CRUD de notas
	 */
	private NotaDAO dao; 
	
	/**
	 * Constante que será usada para definir o padrão de codificação dos serviços para UTF-8
	 */
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	/**
	 * Método executado quando da criação do objeto. A annotation "@PostConstruct" é utilizada
	 * para evitar a utilização de um construtor, uma vez que é o Jersey que irá gerenciar
	 * a criação dos objetos de serviços da aplicação.
	 */
	@PostConstruct
	private void init() {
		dao = new NotaDAO();
	}
	
	@GET //método da requisição HTTP aceito pelo serviço
	//@Path("/") //path do serviço
	@Produces(MediaType.APPLICATION_JSON) //tipo de dado retornado pelo serviço
	public Response listarNotas() {
		List<Nota> notas = null;
		try {
			notas = dao.listarNotas();
		} catch (Exception e) {
			e.printStackTrace();
			return 
					Response.status(Response.Status.NOT_FOUND)
					.type(MediaType.TEXT_PLAIN)
					.entity("Resource not found")
					.build();
		}
		
		/*
		 * É necessário usar a classe GenericEntity porque o Jersey não consegue
		 * converter listas como ArrayList ou Vector nativamente.
		 */
		GenericEntity<List<Nota>> genericListObject = new GenericEntity<List<Nota>>(notas) {};
		
		return Response.ok().entity(genericListObject).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarNotaPorId(@PathParam("id") int id) {
		Nota nota = null;
		
		try {
			nota = dao.buscarNotaPorId(id);
			
			if(nota == null) 
				return 
					Response.status(Response.Status.NO_CONTENT)
					.build();
					/*Response.status(Response.Status.NOT_FOUND)
					.type(MediaType.TEXT_PLAIN)
					.entity("Resource not found")
					.build();*/
										
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return 
					Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.type(MediaType.TEXT_PLAIN)
					.entity("Internal server error")
					.build();
		}
		
		
		return Response.ok().entity(nota).build();
	}
	
	@POST //usamos o POST para inserir um recurso no servidor
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionarNota(@Valid Nota nota) {
		try {
			dao.adicionarNota(nota);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return
					Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.type(MediaType.TEXT_PLAIN)
					.entity("Internal server error")
					.build();
		}
		
		return Response.status(Response.Status.CREATED).build();
	}
	
	@PUT //usamos o PUT para fazer atualizações de recursos no servidor
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	//além do parâmetro na URL, enviamos também a nota
	public Response atualizarNota(Nota nota, @PathParam("id") int id) {
		try {
			dao.atualizarNota(nota, id);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return
					Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.type(MediaType.TEXT_PLAIN)
					.entity("Internal server error")
					.build();
		}
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deletarNota(@PathParam("id") int id) {
		try {
			dao.excluirNota(id);
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return
					Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.type(MediaType.TEXT_PLAIN)
					.entity("Internal server error")
					.build();
		}
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
