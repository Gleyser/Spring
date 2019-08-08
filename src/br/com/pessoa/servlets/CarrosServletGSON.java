package br.com.pessoa.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.pessoa.domain.CarroService;
import br.com.pessoa.domain.ListaCarros;
import br.com.pessoa.domain.Carro;
import br.com.pessoa.util.JAXBUtil;
import br.com.pessoa.util.RegexUtil;
import br.com.pessoa.util.ServletUtil;

/**
 * Servlet implementation class CarrosServlet
 */
@WebServlet("/carrosEmGSON/*")
public class CarrosServletGSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarroService carroService = new CarroService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarrosServletGSON() {
		super();

	}

	/**
	 * O GET de /carrosEmGSON retorna uma lista de carros em JSON criada usando o
	 * Gson caso o id informado não seja encontrado no banco de dados Se o id for
	 * encontrado, os dados desse carro é retornado na URL carros/id
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String requestUri = request.getRequestURI();
			Long id = RegexUtil.matchId(requestUri);
			if (id != null) {
				Carro carro = carroService.getCarro(id);
				if (carro != null) {
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					String json = gson.toJson(carro);
					ServletUtil.writeJSON(response, json);
				} else {
					response.sendError(404, "carro não encontrado");
				}
			}

			else {
				List<Carro> carros = carroService.getCarros();
				ListaCarros lista = new ListaCarros();
				lista.setCarros(carros);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(lista);
				ServletUtil.writeJSON(response, json);			

			}
		} catch (Exception e1) {
			response.sendError(404, e1.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Carro carro = getCarroFromRequest(request);
			carroService.save(carro);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(carro);
			ServletUtil.writeJSON(response, json);
			
		} catch (Exception e) {
			response.sendError(404, "não foi possivel realizar a ação");
		}
		
	}

	private Carro getCarroFromRequest(HttpServletRequest request) throws NumberFormatException, Exception {
		Carro carro = new Carro();
		String id = request.getParameter("id");
		if (id != null) {
				carro = carroService.getCarro(Long.parseLong(id));
		}
		carro.setNome(request.getParameter("nome"));
		carro.setDescricao(request.getParameter("descricao"));
		carro.setUrlFoto(request.getParameter("urlfoto"));
		carro.setUrlVideo(request.getParameter("urlVideo"));
		carro.setLatitude(request.getParameter("latitude"));
		carro.setLongitude(request.getParameter("longitude"));
		carro.setTipo(request.getParameter("tipo"));
		return carro;
		
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String requestUri = request.getRequestURI();
			Long id = RegexUtil.matchId(requestUri);
			if (id != null) {
				carroService.delete(id);
				response.sendError(200, "excluído com sucesso");
			}
			
			else {
				response.sendError(404, "URl inválida");
			}
		} catch (Exception e) {
			response.sendError(404, e.getMessage());
		}
		
		
	}

}
