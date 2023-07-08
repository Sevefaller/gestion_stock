package zouzou.servlet.produit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Produit;
import zouzou.model.Model_produit;

public class Update_produit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Model_produit model = new Model_produit();
	
    public Update_produit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numProduit = request.getParameter("numProduit");
		Produit produit_to_update = new Produit();
		produit_to_update = model.getProduit(numProduit);
		request.setAttribute("produit_to_update", produit_to_update);
		
		request.getRequestDispatcher("views/Produit/edit_produit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produit p = new Produit();
		p.setNumProduit(request.getParameter("numProduit_m"));
		p.setDesign(request.getParameter("design_m"));
		p.setStock(Integer.parseInt(request.getParameter("stock_m")));
		
		if(model.verifieDesignExiste(p.getDesign())) {
			//request.getRequestDispatcher("views/Erreurs/check-design.jsp").forward(request, response);
		}
		else {
			model.updateProduit(p);
			
			response.sendRedirect("produit");
		}
	}

}
