package br.com.pessoa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PessoaA
 * 
 * Nesse servlet os parametros são recebidos/passados pela URL usando o método getParameter(string) da classe HttpServletRequest
 * Exemplo de URL: http://localhost:8080/ProjetoWeb/pessoaA?nome=gleyser&sobrenome=guimaraes
 */
@WebServlet("/pessoaA")
public class PessoaA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PessoaA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		resp.getWriter().print("O nome recebido como parâmetro eh: " + nome + " e o sobrenome: " + sobrenome);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
