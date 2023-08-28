package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servletInicioAdmin")
public class servletInicioAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public servletInicioAdmin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!=null) {
			if(request.getParameter("Param").equals("CS")) {
			
				request.getSession().removeAttribute("Admin");
				
				RequestDispatcher rd = request.getRequestDispatcher("/LogIn.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
