package zouzou.servlet.entree;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.bean.Entree;
import zouzou.model.Model_entree;

public class Add_entree extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Add_entree() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_entree model = new Model_entree();
		Entree e = new Entree();

		e.setNumBonEntree(request.getParameter("numBonEntree"));
		e.setNumProduit(request.getParameter("numProduit"));
		e.setQteEntree(Integer.parseInt(request.getParameter("qteEntree")));
		e.setDateEntree(Date.valueOf(request.getParameter("dateEntree")));
		
		if(model.verifieNumExiste(e.getNumBonEntree())) {
			request.setAttribute("source", "entree");
			request.getRequestDispatcher("views/Erreurs/check-num.jsp").forward(request, response);
		}else {
			model.addEntree(e);
			response.sendRedirect("entree");
		}
	}

}
