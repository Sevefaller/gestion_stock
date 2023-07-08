package zouzou.servlet.entree;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Entree;
import zouzou.bean.Produit;
import zouzou.model.Model_entree;
import zouzou.model.Model_produit;


public class Index_entree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Index_entree() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_entree model = new Model_entree();
		List<Entree> listEntree = model.getEntrees();
		
		Model_produit mP = new Model_produit();
		List<Produit> listProduit = mP.getProduits();
		
		request.setAttribute("model_entree", listEntree);
		request.setAttribute("model_produit", listProduit);
		request.getRequestDispatcher("views/Entree/entree_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
