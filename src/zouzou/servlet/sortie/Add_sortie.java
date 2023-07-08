package zouzou.servlet.sortie;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Sortie;
import zouzou.model.Model_produit;
import zouzou.model.Model_sortie;

public class Add_sortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Add_sortie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_sortie model = new Model_sortie();
		Sortie s = new Sortie();
		Model_produit mP = new Model_produit();
		
		s.setNumBonSortie(request.getParameter("numBonSortie"));
		s.setNumProduit(request.getParameter("numProduit"));
		s.setQteSortie(Integer.parseInt(request.getParameter("qteSortie")));
		s.setDateSortie(Date.valueOf(request.getParameter("dateSortie")));
		
		int stock_actuel = mP.getProduit(request.getParameter("numProduit")).getStock();
		int qte_sortie = s.getQteSortie();
		
		if(model.verifieNumExiste(s.getNumBonSortie())) {
			request.setAttribute("source", "sortie");
			request.getRequestDispatcher("views/Erreurs/check-num.jsp").forward(request, response);
		}else if( stock_actuel < qte_sortie) {		
			request.getRequestDispatcher("views/Erreurs/check-stock.jsp").forward(request, response);
		}else {
			model.addSortie(s);
			response.sendRedirect("sortie");
		}
	}

}
