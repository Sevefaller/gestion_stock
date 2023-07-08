package zouzou.servlet.sortie;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Produit;
import zouzou.bean.Sortie;
import zouzou.model.Model_produit;
import zouzou.model.Model_sortie;

public class Index_sortie extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Index_sortie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_sortie model = new Model_sortie();
		List<Sortie> listSortie = model.getSorties();

		Model_produit model1 = new Model_produit();
		List<Produit> listProduit = model1.getProduits();

		request.setAttribute("model_sortie", listSortie);
		request.setAttribute("model_produit", listProduit);
		request.getRequestDispatcher("views/Sortie/sortie_view.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
