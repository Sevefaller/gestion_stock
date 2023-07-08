package zouzou.servlet.entree;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Entree;
import zouzou.bean.Produit;
import zouzou.model.Model_entree;
import zouzou.model.Model_produit;

public class Update_entree extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Model_entree model = new Model_entree();
	Model_produit model1 = new Model_produit();
	
    public Update_entree() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numBonEntree = request.getParameter("numBonEntree");
		Produit produit = new Produit();
		Entree entree_to_update = new Entree();

		List<Produit> listProduit = model1.getProduits();
		entree_to_update = model.getEntree(numBonEntree);
		produit = model1.getProduit(model.getEntree(numBonEntree).getNumProduit());
		System.out.println(produit);
		
		request.setAttribute("entree_to_update", entree_to_update);
		request.setAttribute("model1", listProduit);
		request.setAttribute("produit", produit);
		
		request.getRequestDispatcher("views/Entree/edit_entree.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Entree e = new Entree();
		e.setNumBonEntree(request.getParameter("numBonEntree_m"));
		e.setNumProduit(request.getParameter("numProduit_m"));
		e.setQteEntree(Integer.parseInt(request.getParameter("qteEntree_m")));
		e.setDateEntree(Date.valueOf(request.getParameter("dateEntree_m")));
		
		model.updateEntree(e);
		
		response.sendRedirect("entree");
	}

}
