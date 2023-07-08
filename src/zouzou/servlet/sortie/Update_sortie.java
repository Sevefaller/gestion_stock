package zouzou.servlet.sortie;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Produit;
import zouzou.bean.Sortie;
import zouzou.model.Model_produit;
import zouzou.model.Model_sortie;

public class Update_sortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Model_sortie model = new Model_sortie();
	Model_produit model1 = new Model_produit();
	
    public Update_sortie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numBonSortie= request.getParameter("numBonSortie");
		Produit produit = new Produit();
		Sortie sortie_to_update = new Sortie();

		List<Produit> listProduit = model1.getProduits();
		sortie_to_update = model.getSortie(numBonSortie);
		produit = model1.getProduit(model.getSortie(numBonSortie).getNumProduit());
		
		request.setAttribute("sortie_to_update", sortie_to_update);
		request.setAttribute("model1", listProduit);
		request.setAttribute("produit", produit);
		
		request.getRequestDispatcher("views/Sortie/edit_sortie.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sortie s = new Sortie();
		s.setNumBonSortie(request.getParameter("numBonSortie_m"));
		s.setNumProduit(request.getParameter("numProduit_m"));
		s.setQteSortie(Integer.parseInt(request.getParameter("qteSortie_m")));
		s.setDateSortie(Date.valueOf(request.getParameter("dateSortie_m")));
		
		model.updateSortie(s);
		
		response.sendRedirect("sortie");
	}

}
