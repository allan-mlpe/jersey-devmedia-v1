package br.edu.devmedia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


import br.edu.devmedia.utils.LoadProperties;

/**
 * Provê um objeto de conexão com o banco de dados da aplicação
 * @author Allan
 *
 */
public class DatabaseConnectionFactory {
	
	/**
	 * Host onde está localizado o servidor do banco de dados
	 */
	private static String host;
	
	/**
	 * Usuário do banco de de dados
	 */
	private static String user;
	
	/**
	 * Senha para o usuário do banco de dados
	 */
	private static String password;
	
	/**
	 * Porta do host em que o servidor do banco de dados está rodando
	 */
	private static String port;
	
	/**
	 * Nome do banco de dados da aplicação
	 */
	private static String databaseName;
	
	/**
	 * Objeto de conexão com o banco de dados da aplicação
	 */
	public static Connection connection;
	
	/**
	 * Bloco estático para carregar parâmetros do arquivo de propriedades do banco de dados.
	 */
	static {
		LoadProperties loadProperties = LoadProperties.getInstance();
		Properties prop = loadProperties.getProperties();
		
		host = prop.getProperty("db_host");
		user = prop.getProperty("db_user");
		password = prop.getProperty("db_password");
		port = prop.getProperty("db_port");
		databaseName = prop.getProperty("db_name");
	}
	
	/**
	 * Construtor padrão da classe
	 */
	private DatabaseConnectionFactory() {
		
	}
	
	/**
	 * Retorna uma instância de conexão com o banco de dados
	 * @return conexão com o banco de dados da aplicação
	 * 
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(connection == null) {
			
			//declaração explícita sobre qual classe deve ser usada pelo driver para gerenciar a conexão com o banco
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection(getMySQLConnectionUrl(), user, password);
		}
		
		return connection;
	}
	
	/**
	 * Gera uma string no formato da URL do banco de dados MySQL da aplicação
	 * @return URL de conexão ao banco de dados
	 */
	public static String getMySQLConnectionUrl() {
		return "jdbc:mysql//" + host + ":" + port + "/" + databaseName;
	}
}
