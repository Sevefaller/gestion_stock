package zouzou.servlet.produit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Produit;
import zouzou.model.Model_produit;

public class Add_produit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Add_produit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_produit model = new Model_produit();
		Produit p = new Produit();
		
		p.setNumProduit(request.getParameter("numProduit"));
		p.setDesign(request.getParameter("design"));
		p.setStock(Integer.parseInt(request.getParameter("stock")));
		
		if(model.verifieNumExiste(p.getNumProduit())) {
			request.setAttribute("source", "produit");
			request.getRequestDispatcher("views/Erreurs/check-num.jsp").forward(request, response);
		}else if(model.verifieDesignExiste(p.getDesign())) {
			request.getRequestDispatcher("views/Erreurs/check-design.jsp").forward(request, response);
		}else {
			model.addProduit(p);
			response.sendRedirect("produit");
		}
	}

}
