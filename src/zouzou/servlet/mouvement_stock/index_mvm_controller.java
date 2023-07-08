package zouzou.servlet.mouvement_stock;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Produit;
import zouzou.model.Model_produit;
import zouzou.model.Mvm_stock_model;

public class index_mvm_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Mvm_stock_model model = new Mvm_stock_model();
	private Model_produit model11 = new Model_produit();
	private static String VUE = "views/Mouvement_stock/mouvement_stock.jsp";
	private int totalE = 0;
	private int totalS = 0;
	private int stock = 0;
	
    public index_mvm_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		model.setMvm(model.list_simple("P001"));
		List<Produit> model1 = model11.getProduits();
		totalE = model.totalEntree("P001");
		totalS = model.totalSortie("P001");
		stock = model.get_stock("P001");
		
		request.setAttribute("model_produit", model1);
		request.setAttribute("model", model);
		request.setAttribute("totalE", totalE);
		request.setAttribute("totalS", totalS);
		request.setAttribute("stock", stock);
		request.getRequestDispatcher(VUE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choix = request.getParameter("choix");		
		String numProduit = request.getParameter("numProduit");
		
		//remplir la liste des produits a selectionner
		List<Produit> model1 = model11.getProduits();
		request.setAttribute("model_produit", model1);
		
		//initialiser les valeurs 
		int an = 0;
		int mois = 0;
		Date av = null;
		Date ap = null;
		
		if(choix.equals("tous")) {
			model.setMvm(model.list_simple(numProduit));
			totalE = model.totalEntree(numProduit);
			totalS = model.totalSortie(numProduit);
			
			request.setAttribute("model", model);
			request.setAttribute("totalE", totalE);
			request.setAttribute("totalS", totalS);
			request.getRequestDispatcher(VUE).forward(request, response);
		}
		else if (choix.equals("mois")) {
			mois = Integer.parseInt(request.getParameter("mois"));
			model.setMvm(model.liste_mois(numProduit, mois));
			totalE = model.totalEntree_mois(numProduit, mois);
			totalS = model.totalSortie_mois(numProduit, mois);
			
			request.setAttribute("totalE", totalE);
			request.setAttribute("totalS", totalS);
			request.setAttribute("model", model);
			request.getRequestDispatcher(VUE).forward(request, response);
		}
		else if (choix.equals("an")) {
			an = Integer.parseInt(request.getParameter("an"));
			model.setMvm(model.list_an(numProduit, an));
			totalE = model.totalEntree_an(numProduit, an);
			totalS = model.totalSortie_an(numProduit, an);
			
			request.setAttribute("totalE", totalE);
			request.setAttribute("totalS", totalS);			
			request.setAttribute("model", model);
			request.getRequestDispatcher(VUE).forward(request, response);
		}
		else if (choix.equals("deux_dates")) {
			av = Date.valueOf(request.getParameter("date1"));
			ap = Date.valueOf(request.getParameter("date2"));
			model.setMvm(model.list_entre_date(numProduit, av, ap));
			totalE = model.totalEntree_deux_dates(numProduit, av, ap);
			totalS = model.totalSortie_deux_dates(numProduit, av, ap);
			
			request.setAttribute("totalE", totalE);
			request.setAttribute("totalS", totalS);				
			request.setAttribute("model", model);			
			request.getRequestDispatcher(VUE).forward(request, response);
		}
	}
}
