package zouzou.servlet.produit;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Produit;
import zouzou.model.Model_produit;

public class Index_produit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Index_produit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_produit mP = new Model_produit();
		List<Produit> listProduit = mP.getProduits();
		
		request.setAttribute("model_produit", listProduit);
		request.getRequestDispatcher("views/Produit/index_produit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
