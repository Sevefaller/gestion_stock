package zouzou.servlet.sortie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zouzou.model.Model_sortie;

public class export_sortie_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public export_sortie_controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model_sortie model = new Model_sortie();
		model.exporterPDF();
		response.sendRedirect("sortie");
	}

}
