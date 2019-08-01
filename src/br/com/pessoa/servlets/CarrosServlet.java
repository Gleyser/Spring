package br.com.pessoa.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pessoa.domain.CarroService;
import br.com.pessoa.domain.ListaCarros;
import br.com.pessoa.domain.Carro;
import br.com.pessoa.util.JAXBUtil;
import br.com.pessoa.util.ServletUtil;

/**
 * Servlet implementation class CarrosServlet
 */
@WebServlet("/carros")
public class CarrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CarroService carroService = new CarroService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Carro> carros = carroService.getCarros();
			ListaCarros lista = new ListaCarros();
			lista.setCarros(carros);
			String carrosString = carros.toString();
			String xml = JAXBUtil.toXML(lista);
			ServletUtil.writeXML(response, xml);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
