package br.com.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import java.util.List;

import javax.ws.rs.Consumes;

import br.com.inicio.domain.CarroService;
import br.com.inicio.domain.Response;
import br.com.inicio.domain.Carro;

@Path("/carros")
@Consumes(MediaType.APPLICATION_JSON +";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON +";charset=utf-8")
public class CarrosResource {
	private CarroService carroService = new CarroService();
	
	@GET
	public List<Carro> get() {		
		List<Carro> carros = carroService.getCarros();
		return carros;		
	}
	
	@GET
	@Path("{id}")
	public Carro get(@PathParam("id") long id) throws Exception {		
		Carro carro = carroService.getCarro(id);
		return carro;		
	}
	
	@GET
	@Path("/tipo/{tipo}")
	public List<Carro> getCarrosPorTipo(@PathParam("tipo") String tipo) throws Exception {		
		List<Carro> carros = carroService.findByTipo(tipo);
		return carros;		
	}
	
	@GET
	@Path("/nome/{nome}")
	public List<Carro> getCarrosPorNome(@PathParam("nome") String nome) throws Exception {		
		List<Carro> carros = carroService.findByName(nome);
		return carros;		
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) throws Exception {		
		carroService.delete(id);
		return Response.Ok("Deletado com sucesso");		
	}
	
	@POST
	public Response post(Carro carro){		
		carroService.save(carro);
		return Response.Ok("Salvo com sucesso");				
	}
	
	@PUT
	public Response put(Carro carro){		
		carroService.save(carro);
		return Response.Ok("Atualizado com sucesso");				
	}

}
