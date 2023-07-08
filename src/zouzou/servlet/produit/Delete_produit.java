package zouzou.servlet.produit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.model.Model_produit;

public class Delete_produit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete_produit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numProduit = request.getParameter("numProduit");
		Model_produit model = new Model_produit();
		
		model.removeProduit(numProduit);
		response.sendRedirect("produit");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
