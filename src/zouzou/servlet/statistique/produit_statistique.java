package zouzou.servlet.statistique;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.model.Statistique_produit_model;

public class produit_statistique extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public produit_statistique() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataPoints = null;
		Statistique_produit_model model = new Statistique_produit_model();
		dataPoints = model.create_chart();
		
		request.setAttribute("dataPoints", dataPoints);		
		request.getRequestDispatcher("views/Statistique/produit_statistique.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
