package zouzou.servlet.sortie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.model.Model_sortie;

public class Delete_sortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete_sortie() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numBonSortie = request.getParameter("numBonSortie");
		Model_sortie model = new Model_sortie();
		
		model.removeSortie(numBonSortie);
		response.sendRedirect("sortie");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
