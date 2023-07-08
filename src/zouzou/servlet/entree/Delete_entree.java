package zouzou.servlet.entree;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.model.Model_entree;

public class Delete_entree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete_entree() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numBonEntree = request.getParameter("numBonEntree");
		Model_entree model = new Model_entree();
		
		model.removeEntree(numBonEntree);
		response.sendRedirect("entree");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
