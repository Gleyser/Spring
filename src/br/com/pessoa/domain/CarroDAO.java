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
	
	public List<Carro> getCarros(){
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carros;
		
	}
	
	
}
