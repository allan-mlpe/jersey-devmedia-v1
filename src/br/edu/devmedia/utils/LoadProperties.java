package br.edu.devmedia.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Esta classe tem a responsabilidade de carregar o arquivo de propriedades da aplicação
 * @author Allan
 *
 */
public class LoadProperties {
	
	/**
	 * Instância única da classe
	 */
	private static LoadProperties load;
	
	/**
	 * Propriedades da aplicação
	 */
	private Properties prop;	
	
	/**
	 * Contrutor privado da classe.
	 */
	private LoadProperties() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("WebContent/WEB-INF/db.properties");
			prop.load(fis);
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que segue o padrão singleton para impedir a criação de várias instâncias da classe
	 * @return instância única da classe LoadProperties
	 */
	public static LoadProperties getInstance() {
		if(load == null) {
			load = new LoadProperties();
		}
		return load;
	}
	
	/**
	 * Obtém o objeto prop que contém as configurações da aplicação.
	 * @return valor do atributo prop
	 */
	public Properties getProperties() {
		return prop;
	}
}
