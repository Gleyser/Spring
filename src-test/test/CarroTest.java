package test;

import org.junit.Test;

import br.com.inicio.domain.Carro;
import br.com.inicio.domain.CarroService;
import junit.framework.Assert;
import junit.framework.TestCase;

public class CarroTest extends TestCase {
	private CarroService carroService = new CarroService();
	
	@Test
	public void testListaCarros() {
		try {
			assertNotNull(this.carroService.getCarros());
			assertTrue(this.carroService.getCarros().size() > 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testGetCarro() {
		try {
			Carro carro1 = this.carroService.getCarro(1L);
			assertEquals("Tucker 1948", carro1.getNome());
			assertEquals("classicos", carro1.getTipo());
			assertEquals(1L, carro1.getId(), 0.1);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	@Test
	public void testSalvaCarro() {
		try {
			System.out.println(this.carroService.getCarros().size());
			assertTrue(this.carroService.getCarros().size() == 35);
			assertTrue(this.carroService.delete(35L));
			assertTrue(this.carroService.getCarros().size() == 34);
			// testando inserir
			Carro carro1 = new Carro();
			carro1.setNome("fusca");
			carro1.setDescricao("antigo");
			carro1.setUrlFoto("lala/1.png");
			carro1.setUrlVideo("lala/1.mp4");
			carro1.setLatitude("45.1234");
			carro1.setLongitude("46.6541");
			carro1.setTipo("classicos");
			this.carroService.save(carro1);
			// o numero aumentou
			assertTrue(this.carroService.getCarros().size() == 35);
			try {
				Carro carro = this.carroService.getCarro(36L);
				assertEquals("fusca", carro.getNome());
				assertEquals("classicos", carro.getTipo());
				assertEquals(36L, carro.getId(), 0.1);
				
				// testando a atualização
				carro.setNome("caminhao");
				carro.setTipo("grande");
				this.carroService.save(carro);
				
				// nao deve ter aumentando a quantidade de carros
				assertTrue(this.carroService.getCarros().size() == 35);
				assertEquals("caminhao", carro.getNome());
				assertEquals("grande", carro.getTipo());
				assertEquals(36L, carro.getId(), 0.1);
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}
