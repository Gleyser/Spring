package br.com.inicio.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Classe intermediaria entre a camada web e a camada de persistencia
 * 
*/
public class CarroService {
	private CarroDAO db = new CarroDAO();
	
	public List<Carro> getCarros() throws Exception{
		try {
			List<Carro> carros = this.db.getCarros();
			return carros;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
	}
	
	public List<Carro> findByName(String nome) throws Exception{
		try {
			List<Carro> carros = this.db.findByName(nome);
			return carros;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
	}
	
	public List<Carro> findByTipo(String tipo) throws Exception{
		try {
			List<Carro> carros = this.db.findByTipo(tipo);
			return carros;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
	}
	
	public boolean delete(Long id) throws Exception{
		try {
			return this.db.deleteCarro(id);			
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
	}
	
	public boolean save(Carro carro) throws Exception{
		try {
			this.db.save(carro);
			return true;
		} catch (Exception e) {
			return false; 
		}
	}
	
	public Carro getCarro(Long id) throws Exception{
		try {
			return this.db.getCarroById(id);			
		} catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
	}

}
