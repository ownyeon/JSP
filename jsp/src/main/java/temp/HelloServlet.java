package temp;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class HelloServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head><title>나의 첫 웹</title></head>");
		out.print("<body>");
		out.print("<h1>이것이 서블릿이다!!!!!!!</h1>");
		out.print("</body>");
		out.print("</html>");
		
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
