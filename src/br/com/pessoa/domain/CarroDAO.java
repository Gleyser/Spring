package br.com.pessoa.domain;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Teremos um CRUD para acessar os carros cadastrados no Banco.
 * Create, Read, Update and Delete 
*/
public class CarroDAO extends BaseDAO {

	public Carro createCarro(ResultSet rs) throws SQLException {
		
		/**
		| ID        | bigint(20)   | NO   | PRI | NULL    | auto_increment |
		| NOME      | varchar(255) | YES  |     | NULL    |                |
		| DESCRICAO | varchar(255) | YES  |     | NULL    |                |
		| URL_FOTO  | varchar(255) | YES  |     | NULL    |                |
		| URL_VIDEO | varchar(255) | YES  |     | NULL    |                |
		| LATITUDE  | varchar(255) | YES  |     | NULL    |                |
		| LONGITUDE | varchar(255) | YES  |     | NULL    |                |
		| TIPO      | varchar(255) | YES  |     | NULL    |
		*/
		// Os parametros dos metodos de ResultSet devem ser exatamente iguais ao usado no banco
		Carro carro = new Carro();
		carro.setId(rs.getLong("id"));
		carro.setDescricao(rs.getString("descricao"));
		carro.setLatitude(rs.getString("latitude"));
		carro.setLongitude(rs.getString("longitude"));
		carro.setNome(rs.getString("nome"));
		carro.setTipo(rs.getString("tipo"));
		carro.setUrlFoto(rs.getString("url_foto"));
		carro.setUrlVideo(rs.getString("url_video"));		
		return carro;
	}
	
	public List<Carro> getCarros() throws Exception{
		List<Carro> carros = new ArrayList<Carro>();		
		try {
			Connection conn = super.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARRO");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Carro carro = createCarro(rs);
				carros.add(carro);
			}
			rs.close();
			stmt.close();
			conn.close();
			return carros;
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		throw new Exception("Nenhum carro nao encontrado"); 
		
	}
	
	public Carro getCarroById(Long id) throws Exception {		
		try {
			Connection conn = super.getConnection();
			// Aqui eh possivel fazer de duas formas - MODO 1
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARRO WHERE ID="+id);
			
			// Aqui eh possivel fazer de duas formas - MODO 2
			// PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARRO WHERE ID=?");
			// stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			// Se a query retornar algo, temos o carro. Se não retornar vai cair na excecao.
			if (rs.next()) {
				Carro carro = createCarro(rs);				
				rs.close();
				stmt.close();
				conn.close();
				return carro;
			} 		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		throw new Exception("Carro nao encontrado"); 
		
		
	}
	
	public List<Carro> findByName(String name) throws Exception {		
		try {
			Connection conn = super.getConnection();
			// Aqui eh possivel fazer de duas formas - MODO 1
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARRO WHERE LOWER(NOME)="+ name.toLowerCase());
			
			// Aqui eh possivel fazer de duas formas - MODO 2
			// PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARRO WHERE LOWER(NOME) like ?");
			// stmt.setLong(1, "%" + name.toLowerCase() + "%");
			ResultSet rs = stmt.executeQuery();
			
			// Se a query retornar algo, temos o carro. Se não retornar vai cair na excecao.
			if (rs.next()) {
				Carro carro = createCarro(rs);
				List<Carro> carros = new ArrayList<Carro>();
				carros.add(carro);
				while (rs.next()) {
					carros.add(createCarro(rs));
				}
				rs.close();
				stmt.close();
				conn.close();
				return carros;
			} 		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		throw new Exception("Nenhum carro nao encontrado"); 
		
		
	}
	
	public List<Carro> findByTipo(String tipo) throws Exception {		
		try {
			Connection conn = super.getConnection();
			// Aqui eh possivel fazer de duas formas - MODO 1
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARRO WHERE TIPO="+ tipo);
			
			// Aqui eh possivel fazer de duas formas - MODO 2
			// PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CARRO WHERE TIPO=?");
			// stmt.setLong(1, tipo);
			ResultSet rs = stmt.executeQuery();
			
			// Se a query retornar algo, temos o carro. Se não retornar vai cair na excecao.
			// REPETIÇÃO DE CÓDIGO - MODULARIZAR
			if (rs.next()) {
				Carro carro = createCarro(rs);
				List<Carro> carros = new ArrayList<Carro>();
				carros.add(carro);
				while (rs.next()) {
					carros.add(createCarro(rs));
				}
				rs.close();
				stmt.close();
				conn.close();
				return carros;
			} 		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		throw new Exception("Nenhum carro nao encontrado"); 
		
		
	}
}
