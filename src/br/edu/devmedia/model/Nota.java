package br.edu.devmedia.model;

/**
 * Representa uma nota simples.
 * @author Allan
 *
 */
public class Nota {
	/**
	 * Id da nota
	 */
	private int id;
	
	/**
	 * Título da nota
	 */
	private String titulo;
	
	/**
	 * Descrição da nota
	 */
	private String descricao;
	
	/**
	 * Obtém o valor do ID da nota
	 * @return valor do atributo id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Define um valor para o ID da nota
	 * @param id valor para o atributo id da nota.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obtém o valor do título da nota
	 * @return valor do atributo título
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Define um valor para o título da nota
	 * @param titulo valor para o atributo titulo da nota
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	/**
	 * Obtém o valor da descrição da nota
	 * @return valor do atributo descrição
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Define um valor para a descrição da nota
	 * @param descricao valor para o atributo descricao da nota
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
