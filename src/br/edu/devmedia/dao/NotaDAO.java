package br.edu.devmedia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import br.edu.devmedia.model.Nota;

/**
 * Objeto de controle e acesso aos dados da entidade Nota
 * 
 * @author Allan
 *
 */
public class NotaDAO {

	/**
	 * Objeto de conexão com o banco de dados
	 */
	private Connection con;

	/**
	 * Construtor padrão da classe
	 */
	public NotaDAO() {
		try {
			con = DatabaseConnectionFactory.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Define um método para busca e listagem de todas as notas do banco.
	 * 
	 * @return lista de notas cadastradas no banco de dados
	 */

	public List<Nota> listarNotas() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, SQLException {
		List<Nota> notas = new Vector<Nota>();
		con = DatabaseConnectionFactory.getConnection();

		String sqlQuery = "SELECT * FROM nota";

		PreparedStatement st = con.prepareStatement(sqlQuery);

		/*
		 * Os resultados retornados no ResultSet vem em um formato do banco
		 * dados. Precisamos usar esses dados par criar um objeto nota
		 */
		ResultSet result = st.executeQuery();

		while (result.next()) {
			Nota nota = new Nota();

			/*
			 * devemos obter os resultados retornados de acordo com o seu tipo
			 * (int, string, etc.) e o nome da coluna da tabela
			 */
			nota.setId(result.getInt("ID_NOTA"));
			nota.setTitulo(result.getString("TITULO"));
			nota.setDescricao(result.getString("DESCRICAO"));

			notas.add(nota);
		}

		return notas;
	}

	/**
	 * Busca uma nota a partir do seu ID
	 * 
	 * @param id
	 *            ID da nota que será buscada
	 * @return nota cujo ID foi passado por parâmetro ou null caso a nota não
	 *         seja encontrada
	 */
	public Nota buscarNotaPorId(Integer id) throws SQLException {
		Nota nota = null;

		String sqlQuery = "SELECT * FROM nota WHERE id=?";

		PreparedStatement st = con.prepareStatement(sqlQuery);

		// os parâmetros começam com 1 e não com 0!
		st.setInt(1, id);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			nota = new Nota();
			nota.setId(rs.getInt("ID_NOTA"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));
		}

		return nota;
	}

	/**
	 * Adiciona uma nota nota no banco de dados
	 * 
	 * @param nota
	 *            objeto nota que representa a nota que será adicionada ao banco
	 *            de dados
	 */
	public void adicionarNota(Nota nota) throws SQLException {

		String sql = "INSERT INTO nota(TITULO, DESCRICAO) VALUES(?, ?)";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, nota.getTitulo());
		st.setString(2, nota.getDescricao());

		st.execute();

	}

	/**
	 * Exclui uma nota do banco de dados
	 * 
	 * @param id
	 *            ID da nota que será excluída
	 */
	public void excluirNota(Integer id) throws SQLException {

		String sql = "DELETE FROM nota WHERE ID_NOTA = ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);

		st.execute();

	}

	/**
	 * Atualiza uma nota do banco de dados
	 * 
	 * @param nota
	 *            objeto nota que representa a nota que será atualizada
	 */
	public void atualizarNota(Nota nota) throws SQLException {
		String sql = "UPDATE nota SET TITULO = ?, DESCRICAO = ? WHERE ID_NOTA = ? ";

		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, nota.getTitulo());
		st.setString(2, nota.getDescricao());
		st.setInt(3, nota.getId());

		st.execute();

	}
}
