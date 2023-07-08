package zouzou.servlet.entree;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.model.Model_entree;

public class export_entree_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public export_entree_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_entree model = new Model_entree();
		model.exporterPDF();
		response.sendRedirect("entree");
	}

}
