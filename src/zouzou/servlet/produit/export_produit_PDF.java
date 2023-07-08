package zouzou.servlet.produit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.model.Model_produit;

public class export_produit_PDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public export_produit_PDF() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_produit model = new Model_produit();
		model.exporterPDF();
		response.sendRedirect("produit");
	}

}
