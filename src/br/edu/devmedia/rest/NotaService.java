package br.edu.devmedia.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	 * Método executado quando da criação do objeto. A annotation "@PostConstruct" é utilizada
	 * para evitar a utilização de um construtor, uma vez que é o Jersey que irá gerenciar
	 * a criação dos objetos de serviços da aplicação.
	 */
	@PostConstruct
	private void init() {
		dao = new NotaDAO();
	}
	
	@GET //método da requisição HTTP aceito pelo serviço
	@Path("/") //path do serviço
	@Produces(MediaType.APPLICATION_JSON) //tipo de dado retornado pelo serviço
	public List<Nota> listarNotas() {
		List<Nota> notas = dao.listarNotas();
		return notas;
	}
}
