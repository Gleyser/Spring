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
import br.com.pessoa.util.ServletUtil;

/**
 * Servlet implementation class CarrosServlet
 */
@WebServlet("/carrosEmGSON")
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
	 * O GET de /carrosEmGSON retorna uma lista de carros em JSON criada usando o Gson 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Carro> carros = carroService.getCarros();
			ListaCarros lista = new ListaCarros();
			lista.setCarros(carros);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(lista);
			ServletUtil.writeJSON(response, json);
			
			// O uso do Gson pode também trazer do Json para o objeto 
			// Gson gson = new Gson();
			// List<Carro> lista = (List<Carro> gson.fromJson(json, ListaCarros.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
